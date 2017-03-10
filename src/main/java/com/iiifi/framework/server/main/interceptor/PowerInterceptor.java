package com.iiifi.framework.server.main.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iiifi.framework.server.common.common.config.IiifiConstant;
import com.iiifi.framework.server.common.common.result.ResultConstant;
import com.iiifi.framework.server.common.common.service.BaseService;

/**
 * 权限拦截器
 * @version 2014-8-19
 */
public class PowerInterceptor extends BaseService implements HandlerInterceptor {
	
	
	@Autowired
	protected static Logger logger = Logger.getLogger("ioLog");
	private static Map<String, String> map=new HashMap<String, String>();
	static{
/*		map.put(ResultConstant.APP_NOT_LOGIN_CODE, ResultConstant.APP_NOT_LOGIN_MESSAGE);
		map.put(ResultConstant.APP_NOT_BINDING_PHONE_CODE, ResultConstant.APP_NOT_BINDING_PHONE_MESSAGE);
		map.put(ResultConstant.APP_NOT_PERFECT_CODE, ResultConstant.APP_NOT_PERFECT_MESSAGE);
		map.put(ResultConstant.APP_NOT_REALNAME_CODE, ResultConstant.APP_NOT_REALNAME_MESSAGE);*/
	}
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		printInLog(request);
		//String requestUrl =request.getRequestURI();
		String code="";//sysCenterService.checkPower(ParametersUtils.initCommonPatameters(request),requestUrl);
		String requestId=request.getParameter("requestId");
		if(requestId==null||"".equals(requestId)){
			response.setContentType("text/plain;charset=UTF-8");
    		response.getWriter().write("{\"code\":\""+ResultConstant.APP_RETURN_ARGS_ERROR_CODE+"\",\"message\":\""+ResultConstant.APP_RETURN_ARGS_ERROR_MESSAGE+"\"}");
    		return false;
		}
        if(ResultConstant.APP_RETURN_SUCESS.equals(code)){
    		return true;
    	}else{
    		response.setContentType("text/plain;charset=UTF-8");
    		response.getWriter().write("{\"code\":\""+code+"\",\"message\":\""+map.get(code)+"\"}");
    		return false;
    	}
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    	//怎么获取返回结果?
    }
    public void printInLog(HttpServletRequest request){
    	StringBuffer urlAddress=new StringBuffer(IiifiConstant.getConfig("interface.url"));
    	//请求的路径
        String contextPath=request.getContextPath();
        String  url=request.getServletPath().toString();
        urlAddress.append(contextPath).append(url).append("?");
        @SuppressWarnings({ "rawtypes" })
		Enumeration paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
          String paramName = (String) paramNames.nextElement();  
          String[] paramValues = request.getParameterValues(paramName);  
          if (paramValues.length == 1) {  
            String paramValue = paramValues[0];  
            if (paramValue.length() != 0) {  
              urlAddress.append(paramName).append("=").append(paramValue).append("&");
            }  
          }
        }
        String httpurl=urlAddress.substring(0, urlAddress.length()-1).toString();
        logger.info(httpurl);
    }
}
