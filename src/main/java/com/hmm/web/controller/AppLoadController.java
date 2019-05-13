package com.hmm.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hmm.web.domain.Device;
import com.hmm.web.domain.GroupWithBLOBs;
import com.hmm.web.domain.MessageContent;
import com.hmm.web.domain.Wechat;
import com.hmm.web.domain.WechatGroupRelate;
import com.hmm.web.reponse.ResponseJson;
import com.hmm.web.service.IDeviceService;
import com.hmm.web.service.IGroupService;
import com.hmm.web.service.IMessageContentService;
import com.hmm.web.service.IWechatGroupRelateService;
import com.hmm.web.service.IWechatService;
import com.hmm.web.utils.message.Message;
import com.hmm.web.utils.message.SendMessage;
import com.hmm.web.vo.Constant;
import com.hmm.web.vo.LoadGroupVo;
import com.hmm.web.vo.LoadJmWechatVo;
import com.hmm.web.vo.LoadWechatGroupVo;

@RestController
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/appLoad")
public class AppLoadController {
	
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IWechatService wechatService;
	@Autowired
	private IWechatGroupRelateService wechatGroupRelateService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IMessageContentService messageContentService;
	
	//上报设备JmId和微信信息  操作状态 0:异常 1:正常
	@PostMapping(value = "/loadJmWechat")
	public ResponseJson loadJmWechat(@RequestBody LoadJmWechatVo loadJmWechatVo) throws Exception {
		//设备查询
		String deviceNo = loadJmWechatVo.getDeviceNo();
		String jmId = loadJmWechatVo.getJmId();
		String wechatUin = loadJmWechatVo.getWechatUin();
		if(deviceNo==null||deviceNo.equals("")||jmId==null||jmId.equals("")||wechatUin==null||wechatUin.equals("")){
			return ResponseJson.getFailedResponse();
		}
		Device device = deviceService.selectByDeviceNo(deviceNo);
		if(device==null){
			return ResponseJson.getFailedResponse();
		}
		//处理微信/群
		this.handleJmWechat(device.getDeviceId(),loadJmWechatVo);
		return ResponseJson.getSuccessResponse();
	}
	@Transactional
	public void handleJmWechat(Long deviceId,LoadJmWechatVo loadJmWechatVo) throws Exception {
		//更新设备jmId
		Device device = new Device();
		device.setDeviceId(deviceId);
		device.setJmId(loadJmWechatVo.getJmId());
		deviceService.updateByPrimaryKeySelective(device);
		//处理微信信息
		Wechat wechat = wechatService.selectByWechatUin(loadJmWechatVo.getWechatUin());
		if(wechat!=null){
			//更新微信
			wechat.setWechatName(loadJmWechatVo.getWechatName().getBytes());
			wechat.setDeviceId(deviceId);
			wechatService.updateByPrimaryKeySelective(wechat);
		}else{
			//新增微信
			Wechat wechatBak = new Wechat();
			wechatBak.setWechatUin(loadJmWechatVo.getWechatUin());
			wechatBak.setWechatName(loadJmWechatVo.getWechatName().getBytes());
			wechatBak.setDeviceId(deviceId);
			wechatService.insertSelective(wechatBak);
		}
	}
	
	//上报微信群  操作状态 0:异常 1:正常
	@PostMapping(value = "/loadWechatGroup")
	public ResponseJson loadWechatGroup(@RequestBody LoadWechatGroupVo loadWechatGroupVo) throws Exception {
		//参数过滤
		String wechatUin = loadWechatGroupVo.getWechatUin();
		if(wechatUin==null||wechatUin.equals("")){
			return ResponseJson.getFailedResponse();
		}
		Wechat wechat = wechatService.selectByWechatUin(wechatUin);
		if(wechat!=null){
			//处理群
			List<LoadGroupVo> baseGroups = loadWechatGroupVo.getBaseGroups();
			if(baseGroups!=null&&baseGroups.size()>0){
				this.handleGroup(wechat.getWechatId(), baseGroups);
			}
			return ResponseJson.getSuccessResponse();
		}else{
			return ResponseJson.getFailedResponse();
		}
	}
	@Transactional
	public void handleGroup(Long wechatId,List<LoadGroupVo> baseGroups) throws Exception {
		Wechat wechat = wechatService.selectByPrimaryKey(wechatId);
		//数据库已有 - 群
		Set<String> result = new HashSet<String>();
		Set<String> oldGroupUins = new HashSet<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("wechatId", wechatId);
		List<String> groupUins = wechatGroupRelateService.selectGroupUinByMap(map);
		for (String groupUin : groupUins) {
			oldGroupUins.add(groupUin);
		}
		//新增 - 群
		Set<String> newGroupUins = new HashSet<String>();
		for (LoadGroupVo baseGroup : baseGroups) {
			newGroupUins.add(baseGroup.getGroupUin());
		}
		result.clear();
		result.addAll(oldGroupUins);
		//重合
		result.retainAll(newGroupUins);
		if(result.size()>0){
			List<GroupWithBLOBs> list1 = new ArrayList<GroupWithBLOBs>();
			for (String str1 : result) {
				for (LoadGroupVo baseGroup : baseGroups) {
					if(str1.equals(baseGroup.getGroupUin())){
						GroupWithBLOBs bg = groupService.selectByGroupUin(str1);
						String displayName = baseGroup.getDisplayName();
						if(displayName==null){ displayName = "";}
						String nickName = baseGroup.getNickName();
						if(nickName==null){ nickName = "";}
						bg.setDisplayName(displayName.getBytes());
						bg.setNickName(nickName.getBytes());
						bg.setGroupMembers(baseGroup.getGroupMembers());
						list1.add(bg);
					}
				}
			}
			for (GroupWithBLOBs group : list1) {
				groupService.updateByPrimaryKeySelective(group);
			}
		}
		//添加群信息
		newGroupUins.removeAll(result);
		if(newGroupUins.size()>0){
			List<GroupWithBLOBs> groups1 = new ArrayList<GroupWithBLOBs>();
			for (String str2 : newGroupUins) {
				for (LoadGroupVo baseGroup : baseGroups) {
					if(str2.equals(baseGroup.getGroupUin())){
						GroupWithBLOBs bg = groupService.selectByGroupUin(str2);
						if(bg!=null){
							if(bg.getStatus().equals(Constant.STATUS_ABNORMAL)){
								bg.setStatus(Constant.STATUS_NORMAL);
								groupService.updateByPrimaryKeySelective(bg);
							}
							groups1.add(bg);
						}else{
							GroupWithBLOBs bg1 = new GroupWithBLOBs();
							bg1.setGroupUin(str2);
							String displayName = baseGroup.getDisplayName();
							String nickName = baseGroup.getNickName();
							if(displayName==null){ displayName = "";}
							if(nickName==null){ nickName = "";}
							bg1.setDisplayName(displayName.getBytes());
							bg1.setNickName(nickName.getBytes());
							bg1.setGroupMembers(baseGroup.getGroupMembers());
							groupService.insertSelective(bg1);
							groups1.add(bg1);
						}
					}
				}
			}
			//新增关系
			for (GroupWithBLOBs group : groups1) {
				WechatGroupRelate bwgr = new WechatGroupRelate();
				bwgr.setWechatId(wechat.getWechatId());
				bwgr.setWechatUin(wechat.getWechatUin());
				bwgr.setGroupId(group.getGroupId());
				bwgr.setGroupUin(group.getGroupUin());
				wechatGroupRelateService.insertSelective(bwgr);
			}
		}
		//逻辑删除群信息
		oldGroupUins.removeAll(result);
		List<Long> oldGroupIdRes = new ArrayList<Long>();
		List<Long> oldGroupIds = new ArrayList<Long>();
		if(oldGroupUins.size()>0){
			for (String str3 : oldGroupUins) {
				GroupWithBLOBs bg = groupService.selectByGroupUin(str3);
				if(bg!=null){
					if(bg.getStatus().equals(Constant.IS_MANAGED)){
						List<Long> bwgrs = wechatGroupRelateService.selectIdByGroupIdNormal(bg.getGroupId());
						if(bwgrs.size()>1){
							WechatGroupRelate bwgr = wechatGroupRelateService.selectByWechatIdGroupIdNormal(wechatId, bg.getGroupId());
							if(bwgr.getStatus().equals(Constant.IS_MANAGED)){
								bg.setStatus(Constant.STATUS_NORMAL);
								groupService.updateByPrimaryKeySelective(bg);
							}
							oldGroupIdRes.add(bg.getGroupId());
						}else{
							oldGroupIdRes.add(bg.getGroupId());
							oldGroupIds.add(bg.getGroupId());
						}
					}else{
						List<Long> bwgrs = wechatGroupRelateService.selectIdByGroupIdNormal(bg.getGroupId());
						if(bwgrs.size()>1){
							oldGroupIdRes.add(bg.getGroupId());
						}else{
							oldGroupIdRes.add(bg.getGroupId());
							oldGroupIds.add(bg.getGroupId());
						}
					}
				}
			}
			//处理关系
			for (Long groupId : oldGroupIdRes) {
				WechatGroupRelate bwgr = wechatGroupRelateService.selectByWechatIdGroupIdNormal(wechatId,groupId);
				if(bwgr!=null){
					bwgr.setStatus(Constant.STATUS_ABNORMAL);
					wechatGroupRelateService.updateByPrimaryKeySelective(bwgr);
				}
			}
			for (Long groupId : oldGroupIds) {
				GroupWithBLOBs baseGroup = groupService.selectByPrimaryKey(groupId);
				baseGroup.setStatus(Constant.STATUS_ABNORMAL);
				groupService.updateByPrimaryKeySelective(baseGroup);
			}
		}
	}
	
	//加载发送消息    操作状态 0:异常 1:正常
	@PostMapping(value = "/getSendMessage")
	public ResponseJson getMessageToApp(@RequestBody MessageContent messageContentVo) throws Exception{
		ResponseJson responseJson=new ResponseJson();
		SendMessage sendMessage = new SendMessage();
		//参数校验
		Long messageJmId = messageContentVo.getMessageJmId();
		String jmId = messageContentVo.getJmId();
		if(messageJmId==null||jmId==null||jmId.equals("")){
			return ResponseJson.getFailedResponse();
		}
		//加载数据
		sendMessage.setJmMessageId(messageJmId);
		//获取消息
		Set<Message> messageSet = new HashSet<Message>();
		Map<String,Object> mapData = new HashMap<String,Object>();
		mapData.put("messageJmId", messageJmId);
		mapData.put("jmId", jmId);
		List<MessageContent> messageContents = messageContentService.selectByMap(mapData);
		List<Long> mcIds = new ArrayList<Long>();
		for (MessageContent messageContent : messageContents) {
			Message message = JSON.parseObject(messageContent.getMsgContent(),Message.class);
			messageSet.add(message);
			mcIds.add(messageContent.getId());
		}
		sendMessage.setMessages(messageSet);
		//更新发送状态
		messageContentService.updateStatusByIds(mcIds);
		responseJson.setData(sendMessage);
		return responseJson;
	}
	
}
