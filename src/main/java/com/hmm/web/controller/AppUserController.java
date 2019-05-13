package com.hmm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hmm.web.domain.Device;
import com.hmm.web.domain.User;
import com.hmm.web.reponse.ResponseJson;
import com.hmm.web.service.IDeviceService;
import com.hmm.web.service.IUserService;

@RestController
@SuppressWarnings({"rawtypes"})
@RequestMapping("/appUser")
public class AppUserController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IDeviceService deviceService;
	
	//登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseJson login(@RequestBody User user) throws Exception {
		User userBak = userService.selectByTelephonePassword(user.getTelephone(),user.getPassword());
		if(userBak!=null){
			return ResponseJson.getSuccessResponse();
		}else{
			return ResponseJson.getFailedResponse();
		}
	}
	
	//校验设备激活状态  0:未激活1:已激活 2:黑名单
	@RequestMapping(value = "/checkDevice", method = RequestMethod.GET)
	public ResponseJson checkDevice(@RequestParam(value = "deviceNo") String deviceNo) throws Exception{
		Device device = deviceService.selectByDeviceNo(deviceNo);
		if(device!=null){
			return ResponseJson.getFailedResponse();
		}else{
			return ResponseJson.getSuccessResponse();
		}
	}
	
	//激活设备(注册极光ID) 操作状态 0:异常 1:正常
	@RequestMapping(value = "/activeDevice", method = RequestMethod.POST)
	public ResponseJson activeDevice(@RequestBody Device device) throws Exception{
		String deviceNo = device.getDeviceNo();
		if(deviceNo==null||deviceNo.equals("")){
			return ResponseJson.getFailedResponse();
		}
		// 处理设备
		Device deviceBak = deviceService.selectByDeviceNo(deviceNo);
		if(deviceBak==null) {
			Device bd = new Device();
			bd.setDeviceNo(deviceNo);
			deviceService.insertSelective(bd);
		}
		return ResponseJson.getSuccessResponse();
	}
	
}
