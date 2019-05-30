package com.zp.security.properties;

/**
 * Description: im
 * Created by Zhu Peng on 2019/5/21
 */
public interface SecurityConstants {
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";
}
