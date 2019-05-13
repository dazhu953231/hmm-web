package com.hmm.web.vo;

/**
 * @Description 红蜘蛛常量类
 */
public class Constant {
	
	//操作异常
	public final static int HANDLE_ABNORMAL = 0;
	//操作正常
	public final static int HANDLE_NORMAL = 1;
	//操作未处理
	public final static int HANDLE_NORESULT = 2;
	
	//不可用
	public final static int STATUS_ABNORMAL = 0;
	//可用
	public final static int STATUS_NORMAL = 1;
	//管理标志
	public final static int IS_MANAGED = 2;
	
	
	//上传状态-空闲中
	public final static String UPLOAD_FREE = "1";
	//上传状态-处理中
	public final static String UPLOAD_BUSY = "0";
	
	//发送计划-已完成
	public final static int EXECUTED = 0;
	//发送计划-待执行
	public final static int EXECUTE = 1;
	//发送计划-执行中
	public final static int EXECUTION = 2;
	
	//极信是否推送失败-是
	public final static String ISFAILED_TRUE = "是";
	//极信是否推送失败-否
	public final static String ISFAILED_FALSE = "否";
	
	//未读消息
	public final static int STATUSREAD_DONT = 0;
	//已读消息
	public final static int STATUSREAD_DO = 1;
	
	//消息 - 已发送
	public final static int MESSAGE_SEND_TRUE = 0;
	//消息 - 未发送
	public final static int MESSAGE_SEND_FALSE = 1;
 	
}
