package com.hmm.web.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.domain.Device;
import com.hmm.web.domain.GroupWithBLOBs;
import com.hmm.web.domain.MessageContent;
import com.hmm.web.domain.MessageJm;
import com.hmm.web.domain.SendList;
import com.hmm.web.domain.SendListContent;
import com.hmm.web.domain.SendPlan;
import com.hmm.web.domain.Wechat;
import com.hmm.web.domain.WechatGroupRelate;
import com.hmm.web.service.IDeviceService;
import com.hmm.web.service.IGroupService;
import com.hmm.web.service.IMessageContentService;
import com.hmm.web.service.IMessageJmService;
import com.hmm.web.service.ISendListContentService;
import com.hmm.web.service.ISendPlanService;
import com.hmm.web.service.IWechatGroupRelateService;
import com.hmm.web.service.IWechatService;
import com.hmm.web.utils.message.Message;
import com.hmm.web.vo.Constant;

@Component
public class HandleSendPlanService {
	
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private ISendPlanService sendPlanService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IWechatService wechatService;
	@Autowired
	private IWechatGroupRelateService wechatGroupRelateService;
	@Autowired
	private IMessageJmService messageJmService;
	@Autowired
	private ISendListContentService sendListContentService;
	@Autowired
	private IMessageContentService messageContentService;
	@Autowired
	private JpushClientUtil jpushClientUtil;
	
	public boolean handleSendPlan(SendPlan sendPlan,SendList sendList){
		SendPlan sendPlanBak = new SendPlan();
		sendPlanBak.setId(sendPlan.getId());
		//发送中
		sendPlanBak.setStatus(2);
		sendPlanService.updateByPrimaryKeySelective(sendPlanBak);
		//发送列表最新一条
		SendListContent sendListContent = sendListContentService.selectTopOne(sendPlan.getPlatformListId());
		if(sendListContent!=null){
			//保存消息设置
			MessageJm messageJm = new MessageJm();
			messageJm.setPlatformPlanId(sendPlan.getId());
			messageJm.setPlatformListId(sendPlan.getPlatformListId());
			messageJm.setPlatformContentId(sendListContent.getId());
			messageJm.setSendTime(sendPlan.getStartTime());
			messageJmService.insertSelective(messageJm);
			//设定极光定时推送
			String jmScheduleId = jpushClientUtil.sendToTagSchedule(messageJm.getId().toString(),messageJm.getId().toString(),sendList.getId()+sendList.getName(),sendPlan.getStartTime());
			//极光定时完成更新数据
			messageJm.setJmScheduleId(jmScheduleId);
			messageJm.setStatus(Constant.MESSAGE_SEND_TRUE);
			messageJmService.updateByPrimaryKeySelective(messageJm);
			//发送完成-更新消息状态
			sendListContent.setStatus(Constant.STATUS_ABNORMAL);
			sendListContentService.updateByPrimaryKeySelective(sendListContent);
			//获取相关群
			List<Long> groupIds = groupService.selectGroupIdByPlatListId(messageJm.getPlatformListId());
			Integer count = 0;
			for (Long groupId : groupIds) {
				GroupWithBLOBs group = groupService.selectByPrimaryKey(groupId);
				Wechat baseWechat = null;
				Device baseDevice = null;
				String jmId;
				Message message = null;
				if (group.getStatus().equals(Constant.IS_MANAGED)) {
					List<Long> ids = wechatGroupRelateService.selectIdByGroupIdNormal(groupId);
					for (Long id : ids) {
						WechatGroupRelate temp = wechatGroupRelateService.selectByPrimaryKey(id);
						if (temp.getStatus().equals(Constant.IS_MANAGED)) {
							baseWechat = wechatService.selectByPrimaryKey(temp.getWechatId());
							if (baseWechat != null) {
								baseDevice = deviceService.selectByPrimaryKey(baseWechat.getDeviceId());
								if (baseDevice != null) {
									jmId = baseDevice.getJmId();
									message = new Message();
									message.setGroupUin(group.getGroupUin());
									message.setSendListContent(sendListContent);
									//保存消息
									MessageContent messageContent = new MessageContent();
									messageContent.setMessageJmId(messageJm.getId());
									messageContent.setJmId(jmId);
									String jsonString = JSONUtils.toJSONString(message);
									messageContent.setMsgContent(jsonString);
									messageContentService.insertSelective(messageContent);
									count++;
								}
							}
						}
					}
				}
			}
			messageJm.setMessageCount(count.longValue());
			messageJmService.updateByPrimaryKeySelective(messageJm);
		}
		//发送完成
		sendPlanBak.setStatus(0);
		sendPlanService.updateByPrimaryKeySelective(sendPlanBak);
		return true;
	}
	
}
