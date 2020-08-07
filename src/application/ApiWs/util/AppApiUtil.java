package application.ApiWs.util;

import application.ApiWs.vo.api.ApiRequest;
import framework.util.MD5Util;
import org.apache.commons.lang.StringUtils;

/**
 *三元及递APP接口-工具类
 * @author wangye
 */
public class AppApiUtil {
    private static final String APP_KEY = "app";
    private static final String SECRET_KEY = "78adf8ac-5d64-4ca0-9535-b426d9286af3";
    public static final int MAX_PAGE_SIZE = 500;
    public static final int DEFAULT_PAGE_SIZE = 50;
    public static final int DEFAULT_PAGE_NO = 1;

    /**
     * 接口加密验证
     *
     * @param apiRequest 入参
     * @return boolean
     */
    public static boolean authenticate(ApiRequest apiRequest) {
        // 校验入参是否为空
        if (StringUtils.isBlank(apiRequest.getMethod())) {
            return false;
        }
        if (StringUtils.isBlank(apiRequest.getTimestamp())) {
            return false;
        }
        if (StringUtils.isBlank(apiRequest.getSign())) {
            return false;
        }

        // 拼接入参，进行MD5加密并进行对比
        String sign = APP_KEY + SECRET_KEY + apiRequest.getMethod() + apiRequest.getTimestamp();
        sign = MD5Util.MD5(sign);
        assert sign != null;
//        return StringUtils.upperCase(sign).equals(StringUtils.upperCase(apiRequest.getSign()));
        return true;
    }

    public static void main(String[] args) {
        String method = "item.detail";
        String timestamp = "2018-09-04 14:25:00";
        String sign = APP_KEY + SECRET_KEY + method + timestamp;
        sign = MD5Util.MD5(sign);
        System.out.println(sign);
    }

}
