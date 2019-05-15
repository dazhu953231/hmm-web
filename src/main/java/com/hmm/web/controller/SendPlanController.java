package com.hmm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmm.web.domain.SendList;
import com.hmm.web.domain.SendPlan;
import com.hmm.web.reponse.ResponseJson;
import com.hmm.web.service.ISendListService;
import com.hmm.web.service.ISendPlanService;
import com.hmm.web.utils.DateUtils;
import com.hmm.web.utils.HandleSendPlanService;
import com.hmm.web.vo.SendPlanVo;

@RestController
@SuppressWarnings({ "rawtypes" })
@RequestMapping("/sendPlan")
public class SendPlanController {
	
	@Autowired
	private ISendPlanService sendPlanService;
	@Autowired
	private ISendListService sendListService;
	@Autowired
	private HandleSendPlanService handleSendPlanService;
	
	//添加发送计划
	@PostMapping("/saveSendPlan") //操作状态 0:异常 1:正常
	public ResponseJson insertPlatformSendPlan(@RequestBody SendPlanVo sendPlanVo) throws Exception{
		Long startTime = sendPlanVo.getStartTime();
		Long endTime = sendPlanVo.getEndTime();
		//发送计划提前2分钟
		long ranCurrentTime = (DateUtils.currentTimeSecs()+120l);
		if(startTime==null||endTime==null||(endTime.longValue()<startTime.longValue())||(startTime.longValue()<ranCurrentTime)){
			return ResponseJson.getFailedResponse();
		}
		//TODO 发送间隔暂时不处理
		Long platformListId = sendPlanVo.getPlatformListId();
		if(platformListId==null||platformListId.equals(0l)){
			return ResponseJson.getFailedResponse();
		}
		SendList sendList = sendListService.selectByPrimaryKey(platformListId);
		if(sendList==null||sendList.getId()==null||sendList.getId().equals(0l)){
			return ResponseJson.getFailedResponse();
		}
		//保存发送计划
		SendPlan sendPlan = new SendPlan();
		sendPlan.setStartTime(startTime);
		sendPlan.setEndTime(endTime);
		sendPlan.setMessageSendInterval(sendPlanVo.getMessageSendInterval());
		sendPlan.setPlatformListId(platformListId);
		sendPlan.setPlanComment(sendPlanVo.getPlanComment());
		sendPlanService.insertSelective(sendPlan);
		//执行任务-暂时实行一次
		boolean flag = handleSendPlanService.handleSendPlan(sendPlan,sendList);
		if(!flag){
			return ResponseJson.getFailedResponse();
		}
		return ResponseJson.getSuccessResponse();
	}
	
}
