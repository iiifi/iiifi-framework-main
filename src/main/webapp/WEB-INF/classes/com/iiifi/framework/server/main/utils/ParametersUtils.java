package com.iiifi.framework.server.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.iiifi.framework.server.common.common.result.CommonParameters;


public class ParametersUtils {
	@SuppressWarnings("static-access")
	public static CommonParameters initCommonPatameters(HttpServletRequest request){
		CommonParameters commonParameters=new CommonParameters();
		commonParameters.setRequestId(request.getParameter(commonParameters.REQUEST_ID));
		commonParameters.setToken(request.getParameter(commonParameters.TOKEN));
		commonParameters.setUserId(request.getParameter(commonParameters.USER_ID));
		commonParameters.setDeviceType(request.getParameter(commonParameters.DEVICE_TYPE));
		commonParameters.setDeviceInfo(request.getParameter(commonParameters.DEVICE_INFO));
		commonParameters.setDeviceNum(request.getParameter(commonParameters.DEVICE_NUM));
		commonParameters.setPlatform(request.getParameter(commonParameters.PLATFORM));
		return commonParameters;
	}
}
