package application.AliPay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import java.io.InputStreamReader;
import java.util.MissingResourceException;
import java.util.Properties;

public class AlipayAPIClientFactory {
    public static final String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";
    private static final String RESOURCE_WXPAY_PROPERTIES = "/zfbinfo.properties";
    private static String public_key = "";
    private static String sign_type = "";
    private static String charset = "";
    /**
     * API调用客户端
     */
    private static AlipayClient alipayClient;


    // CONFIG
    protected static Properties conf = new Properties();

    private static void init() {
        try {
            conf.load(
                    new InputStreamReader(
                            AlipayAPIClientFactory.class.getResourceAsStream(RESOURCE_WXPAY_PROPERTIES),
                            "utf-8"
                    ));
            public_key = conf.getProperty("ALIPAY_PUBLIC_KEY");
            sign_type = conf.getProperty("SIGN_TYPE");
            charset = conf.getProperty("CHARSET");
        } catch (Exception ex) {
            MissingResourceException mrex = new MissingResourceException(
                    "Failed to load conf resource.",
                    AlipayAPIClientFactory.class.getName(),
                    RESOURCE_WXPAY_PROPERTIES
            );
            mrex.initCause(ex);
            throw (mrex);
        }
    }

    public Properties getConf() {
        return (this.conf);
    }

    /**
     * 获得API调用客户端
     *
     * @return
     */
    public static AlipayClient getAlipayClient() {
        if (null == alipayClient) {
            init();
            String app_id = conf.getProperty("APPID");
            String private_key = conf.getProperty("PRIVATE_KEY");
            String format = conf.getProperty("FORMAT");
            String charset = conf.getProperty("CHARSET");
            String alipay_public_key = conf.getProperty("ALIPAY_PUBLIC_KEY");
            String sign_type = conf.getProperty("SIGN_TYPE");
            alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY, app_id, private_key, format, charset, alipay_public_key,sign_type);
            //alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY, app_id, conf.getProperty("PRIVATE_KEY"), conf.getProperty("FORMAT"), conf.getProperty("CHARSET"), conf.getProperty("ALIPAY_PUBLIC_KEY"),conf.getProperty("SIGN_TYPE"));
        }
        return alipayClient;
    }

    public static String getPublicKey() {
        return public_key;
    }

    public static String getSignType() {
        return sign_type;
    }
    public static String getCharset() {
        return charset;
    }
}
