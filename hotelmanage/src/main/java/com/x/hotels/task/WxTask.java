//package com.x.hotels.task;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.x.tools.util.RedissonUtil;
//import com.x.tools.util.WxUtil;
//
//
//@Component
//public class WxTask {
//
//	@Autowired
//	RedissonUtil redisson;
//	
//	//每隔一个小时刷新一次wx小程序的token
//	@Scheduled(fixedRate=3600000)
//	public void refreshAccessToken() {
//		try {
//			String token = WxUtil.getToken();
//			redisson.set("wechat_access_token", token, 7200000L);	
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
