package application.WxPay.config;

import java.io.InputStreamReader;
import java.util.MissingResourceException;
import java.util.Properties;

public class WxPayConfig {
    // CONSTANT
    private static final String RESOURCE_WXPAY_PROPERTIES = "/wxpay.properties";

    private static String appid = "";
    private static String partner = "";
    private static String appsecret = "";
    private static String paternerKey = "";
    // private static String notify_url = "http://www.xxx.com/pay/pay_notify";
    // private static String openidForwardUrl = "";
    //private static String token = "";


    // CONFIG
    protected static Properties conf = new Properties();

    static{
        init();
    }
    private synchronized static  void init() {
        try {
            conf.load( new InputStreamReader( WxPayConfig.class.getResourceAsStream(RESOURCE_WXPAY_PROPERTIES), "utf-8"  ));
            appid=conf.getProperty("app_id");
            partner=conf.getProperty("mch_id");
            appsecret=conf.getProperty("app_secret");
            paternerKey=conf.getProperty("paternerKey");
            //token=conf.getProperty("token");
            //openidForwardUrl=conf.getProperty("openidForwardUrl");
        } catch (Exception ex) {
            MissingResourceException mrex = new MissingResourceException(
                    "Failed to load conf resource.",
                    WxPayConfig.class.getName(),
                    RESOURCE_WXPAY_PROPERTIES
            );
            mrex.initCause(ex);
            throw (mrex);
        }
    }

    public static String getAppid() {
        return appid;
    }

    public static String getPartner() {
        return partner;
    }

    public static String getAppSecret() {
        return appsecret;
    }

    //public static String getOpenidForwardUrl(){return openidForwardUrl;}


   /* public static String getToken() {
        return token;
    }*/

    public static String getPaternerKey() {
        return paternerKey;
    }
}
