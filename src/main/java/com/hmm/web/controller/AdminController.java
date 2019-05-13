package com.hmm.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hmm.web.domain.Device;
import com.hmm.web.domain.GroupWithBLOBs;
import com.hmm.web.domain.SendList;
import com.hmm.web.domain.Wechat;
import com.hmm.web.domain.WechatGroupRelate;
import com.hmm.web.reponse.ResponseJson;
import com.hmm.web.service.IDeviceService;
import com.hmm.web.service.IGroupService;
import com.hmm.web.service.ISendListService;
import com.hmm.web.service.IWechatGroupRelateService;
import com.hmm.web.service.IWechatService;
import com.hmm.web.utils.JpushClientUtil;
import com.hmm.web.vo.AdminDataVo;
import com.hmm.web.vo.AdminWechatGroupVo;
import com.hmm.web.vo.Constant;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ISendListService sendListService;
	@Autowired
	private IWechatGroupRelateService wechatGroupRelateService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IWechatService wechatService;
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private JpushClientUtil jpushClientUtil;
	
	//管理微信群
	@RequestMapping(value = "/adminWechatGroup", method = RequestMethod.POST)
	public synchronized ResponseJson adminWechatGroup(@RequestBody AdminDataVo adminDataVo) throws Exception {
		ResponseJson responseMsg = new ResponseJson();
		//过滤数据
		List<AdminWechatGroupVo> adminWechatGroups = adminDataVo.getAdminWechatGroups();
		if(adminWechatGroups!=null&&adminWechatGroups.size()>0){
			//错误结果集
			List<AdminWechatGroupVo> errorDatas = new ArrayList<AdminWechatGroupVo>();
			//更新成功的微信
			Set<Long> tagWechatIds = new HashSet<Long>();
			//处理数据
			for (AdminWechatGroupVo adminWechatGroupVo : adminWechatGroups) {
				Long wechatId = adminWechatGroupVo.getWechatId();
				if(wechatId==null){
					adminWechatGroupVo.setMessage("参数异常:wechatId");
					errorDatas.add(adminWechatGroupVo);
					continue;
				}
				Long groupId = adminWechatGroupVo.getGroupId();
				if(groupId==null){
					adminWechatGroupVo.setMessage("参数异常:groupId");
					errorDatas.add(adminWechatGroupVo);
					continue;
				}
				Integer manageStatus = adminWechatGroupVo.getManageStatus();
				if(manageStatus==null||!manageStatus.equals(Constant.STATUS_NORMAL)||!manageStatus.equals(Constant.IS_MANAGED)){
					adminWechatGroupVo.setMessage("参数异常:manageStatus");
					errorDatas.add(adminWechatGroupVo);
					continue;
				}
				AdminWechatGroupVo errorData = this.handleWechatGroup(adminWechatGroupVo);
				if(errorData!=null){
					errorDatas.add(errorData);
				}else{
					tagWechatIds.add(adminWechatGroupVo.getWechatId());
				}
			}
			//极光打标签
			this.handleTag(tagWechatIds);
			if(errorDatas!=null&&errorDatas.size()>0){
				responseMsg.setData(errorDatas);
				return responseMsg;
			}else{
				return responseMsg;
			}
		}else{
			return responseMsg;
		}
	}
	
	//处理数据
	public AdminWechatGroupVo handleWechatGroup(AdminWechatGroupVo adminWechatGroupVo) throws Exception {
		//过滤发送列表
		SendList sendList = null;
		if(adminWechatGroupVo.getPlatformListId()!=null){
			sendList = sendListService.selectByPrimaryKey(adminWechatGroupVo.getPlatformListId());
			if(sendList==null||sendList.getStatus().equals(Constant.STATUS_ABNORMAL)){
				adminWechatGroupVo.setMessage("发送列表不存在或已删除");
				return adminWechatGroupVo;
			}
		}
		//群
		GroupWithBLOBs baseGroup = new GroupWithBLOBs();
		//过滤微信群管理状态
		WechatGroupRelate bwgr = wechatGroupRelateService.selectByWechatIdGroupIdNormal(adminWechatGroupVo.getWechatId(),adminWechatGroupVo.getGroupId());
		if(bwgr!=null){
			baseGroup = groupService.selectByPrimaryKey(adminWechatGroupVo.getGroupId());
			//设置管理状态
			if(!bwgr.getStatus().equals(Constant.IS_MANAGED)){
				if(baseGroup.getStatus().equals(Constant.IS_MANAGED)){
					adminWechatGroupVo.setMessage("该群已被其他微信管理");
					return adminWechatGroupVo;
				}
			}
		}else{
			adminWechatGroupVo.setMessage("微信群不存在或已删除");
			return adminWechatGroupVo;
		}
		//绑定关系-设置管理状态
		baseGroup.setStatus(adminWechatGroupVo.getManageStatus());
		bwgr.setStatus(adminWechatGroupVo.getManageStatus());
		wechatGroupRelateService.updateByPrimaryKeySelective(bwgr);
		if(sendList!=null){
			baseGroup.setPlatformListId(sendList.getId());
			baseGroup.setPlatformListName(sendList.getName());
		}
		groupService.updateByPrimaryKeySelective(baseGroup);
		return null;
	}
	
	//极光打标签
	public void handleTag(Set<Long> tagWechatIds){
		Map<String,Set<String>> addMap=new HashMap<String,Set<String>>();
		Map<String,Set<String>> removeMap=new HashMap<String,Set<String>>();
		//处理数据
		for (Long wechatId : tagWechatIds) {
			Wechat baseWechat =wechatService.selectByPrimaryKey(wechatId);
			if(baseWechat!=null){
				//获取jmid
				Device baseDevice = deviceService.selectByPrimaryKey(baseWechat.getDeviceId());
				String jmid = baseDevice.getJmId();
				//统计极光-现有标签-删除
				List<String> tagList = jpushClientUtil.getTags(jmid);
				for (String tag : tagList) {
					if(removeMap.containsKey(tag)){
						removeMap.get(tag).add(jmid);
					}else{
						Set<String> jmids=new HashSet<String>();
						jmids.add(jmid);
						removeMap.put(tag, jmids);
					}
				}
				//获取最新的-添加
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("wechatId", wechatId);
				map.put("status", Constant.IS_MANAGED);
				List<String> groupUins = wechatGroupRelateService.selectGroupUinByMap(map);
				for (String groupUin : groupUins) {
					GroupWithBLOBs bg = groupService.selectByGroupUin(groupUin);
					String tag = bg.getPlatformListId()+bg.getPlatformListName();
					if(addMap.containsKey(tag)){
						addMap.get(tag).add(jmid);
					}else{
						Set<String> jmids=new HashSet<String>();
						jmids.add(jmid);
						addMap.put(tag, jmids);
					}
				}
			}
		}
		//删除所有-旧标签
		Set<String> removeKeys=removeMap.keySet();
		for (String key : removeKeys) {
			jpushClientUtil.generateGroupByImei(key, null, removeMap.get(key));
		}
		//添加-全部标签
		Set<String> addKeys = addMap.keySet();
		for (String key : addKeys) {
			jpushClientUtil.generateGroupByImei(key, addMap.get(key), null);
		}
	}
	
}
