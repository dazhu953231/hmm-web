package com.hmm.web.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmm.web.utils.message.SendContent;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.schedule.ScheduleResult;

@Component
public class JpushClientUtil {
	
	private final static String appKey = "9b2a61189eea7b766453d552";
	private final static String masterSecret = "f46c10cc832de48a9f15f46d";
	private static JPushClient jPushClient = new JPushClient(masterSecret, appKey);
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 生成分组tag
	 */
	public boolean generateGroupByImei(String tag, Set<String> addImeis, Set<String> remImeis) {
		boolean ret=false;
		try {
			DefaultResult result=jPushClient.addRemoveDevicesFromTag(tag, addImeis, remImeis);
			ret=result.isResultOK();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 获取tag
	 */
	public List<String> getTags(String registrationId) {
		List<String> list=new ArrayList<String>();
		try {
			TagAliasResult result=jPushClient.getDeviceTagAlias(registrationId);
			list=result.tags;
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 发送给所有安卓用户
	 * @param sendMessage
	 * @return 0推送失败，1推送成功
	 */
	public int sendToAllAndroid(SendContent sendContent) {
		int result = 0;
		try {
			PushPayload pushPayload = buildPayLoadBySendAll(sendContent);
			PushResult pushResult = jPushClient.sendPush(pushPayload);
			if (pushResult.getResponseCode() == 200) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送给指定设备
	 * @param sendMessage
	 * @param jmId 极光id
	 * @return 0推送失败，1推送成功
	 */
	public int sendToDevice(SendContent sendContent,String jmId) {
		int result = 0;
		try {
			PushPayload pushPayload = buildPushObjectBySendDevice(sendContent,jmId);
			PushResult pushResult = jPushClient.sendPush(pushPayload);
			if (pushResult.getResponseCode() == 200) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送给指定tag
	 * @param sendMessage
	 * @param tag
	 * @return 0推送失败，1推送成功
	 */
	public Long sendToTag(String content,String tag) {
		Long msgId = -1L;
		try {
			PushPayload pushPayload = buildPushObjectBySendTag(content, tag);
			PushResult pushResult = jPushClient.sendPush(pushPayload);
			if (pushResult.getResponseCode() == 200) {
				msgId = pushResult.msg_id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgId;
	}
	
	/**
	 * 定时发送给指定tag
	 * @param sendMessage
	 * @param tag
	 * @return 0推送失败，1推送成功
	 */
	public String sendToTagSchedule(String name,String content,String tag,Long sendTime) {
		//格式  2016-07-30 12:30:25
		String time = DateUtils.TimeMillisToDateTime(sendTime);
		String jmScheduleId ="";
		try {
			PushPayload pushPayload = buildPushObjectBySendTag(content, tag);
			ScheduleResult result = jPushClient.createSingleSchedule(name, time, pushPayload);
			if (result.getResponseCode() == 200) {
				jmScheduleId = result.getSchedule_id();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jmScheduleId;
	}
	
	private static PushPayload buildPayLoadBySendAll(SendContent sendContent) throws JsonGenerationException, JsonMappingException, IOException {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setMessage(Message.newBuilder().setMsgContent(mapper.writeValueAsString(sendContent)).build())
				.build();
	}
	
	private static PushPayload buildPushObjectBySendDevice(SendContent sendContent,String jmId) throws JsonGenerationException, JsonMappingException, IOException {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.registrationId(jmId))
				.setMessage(Message.newBuilder().setMsgContent(mapper.writeValueAsString(sendContent)).build())
				.build();
	}
	
	private static PushPayload buildPushObjectBySendTag(String content,String groupTag) throws JsonGenerationException, JsonMappingException, IOException {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.tag(groupTag))
				.setMessage(Message.newBuilder().setMsgContent(mapper.writeValueAsString(content)).build())
				.build();
	}
	
}
