package application.AliPay.config;

public class AlipayConfig {
    // 1.商户appid
//    public static String APPID = "2018102261767221";
    // SandBox
    public static String APPID = "2016092100562856";

    //2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="E8kJNeKTmJhr9DC1FTKThg==";

    // 3.支付宝公钥
//    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkIhDVbbj3HJcBju8Gi+3JnO73be1MMhJlLxZNgC4OCVgMeQaOvERGTIcrpFEkbfWLiAwyR5xDdHMKmI0KZf1vcGDcKOqYcmK63UDxqNV62bbET0l2pZj0CewKbaoIEEqpufEQPSj9en961fMuAIll0FoEpgXWs1iqJCZ/id5sNfBCybFYo4+iSJvoo+HraHxId5q7w+BD1M6OUQ9cZzK+HOpGTTs/qAqwmmeHWcObhQWU3TDCVMkdNLNLnd6nBmCN0VFpDSeiiDe8lcelvE+TvRxhKpKoMqX0bKCULgSew572Odv4uo1RIpTjQwINrOlQu9DiC6z3F4OQojy19gC7QIDAQAB";
    //sandbox
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9XLc8wHadJh4DPeTARoK6X+ixY/CdE+8eiWcrtP/PQINMLDP1o6Tx5CxIT+ljKdu7zYc7CPVigwLK9BtA4ljiPZbtmVdvGii+VOU51Kw2Qr5lb3hY8gd6M1SKj7gI/EDlej3PHUZS05rUTsaFC13V01upSvYq8nxpbHePvSeF9dLOeoyJj7jK7gj/dhqLb5oNFmRaLylGfJQY8Fhfy6f83WRTQUOrf3BiXGytBdEDOd9raYPJkHU92pgf9dsNJTtdMeapMeexR08HKaDbZXepjlIXvWt41JsMbLpO1eOojY607UUrUa8M6wT0LHGYAOMIGwSi4i8z8nAUXWjiByGdwIDAQAB";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://122.115.53.96:8080/WS/apiws/service/notify_url";


    //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.xxx.com/alipay/return_url.do";

    // 6.请求支付宝的网关地址
//    public static String URL = "https://openapi.alipay.com/gateway.do";
    // sandbox
    public static String URL = "https://openapi.alipaydev.com/gateway.do";

    // 7.编码
    public static String CHARSET = "UTF-8";

    // 8.返回格式
    public static String FORMAT = "json";

    // 9.加密类型
    public static String SIGNTYPE = "RSA2";
}
