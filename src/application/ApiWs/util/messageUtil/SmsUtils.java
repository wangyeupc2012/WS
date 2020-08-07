package application.ApiWs.util.messageUtil;

import application.ApiWs.vo.SmsInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class SmsUtils {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String result = "#242";
        System.out.println(result.substring(1));
    }

    public static String sendSms(SmsInfo smsInfo) {
        String result = "";
        String sendUrl = smsInfo.getSend_url();
        String corp_id = smsInfo.getCorp_id();
        String corp_service = smsInfo.getCorp_service();
        String user_id = smsInfo.getSms_user();
        String user_pwd = smsInfo.getSms_psw();
        String mobile = smsInfo.getMobile_number();
        String msg_content = smsInfo.getSend_msg() + ";�ظ�TD�˶�;";
        String msg_id = smsInfo.getSms_msg_id();

        System.out.println("sendUrl:" + sendUrl);
        System.out.println("corp_id:" + corp_id);
        System.out.println("corp_service:" + corp_service);
        System.out.println("user_id:" + user_id);
        System.out.println("user_pwd:" + user_pwd);
        System.out.println("mobile:" + mobile);
        System.out.println("msg_content:" + msg_content);
        System.out.println("msg_id:" + msg_id);

        int timeout = 5000;
        HttpConnectionManager httpConnectionManager = new SimpleHttpConnectionManager(true);
        httpConnectionManager.getParams().setSoTimeout(timeout);
        httpConnectionManager.getParams().setConnectionTimeout(timeout);

        HttpClientParams hcParams = new HttpClientParams();
        hcParams.setSoTimeout(timeout);
        hcParams.setConnectionManagerTimeout(timeout);
        HttpClient hc = new HttpClient(hcParams, httpConnectionManager);

        PostMethod postMethod = new PostMethod(sendUrl);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        NameValuePair[] param = new NameValuePair[]{new NameValuePair("destination", sendUrl),
                new NameValuePair("corp_id", corp_id),
                new NameValuePair("corp_service", corp_service),
                new NameValuePair("user_id", user_id),
                new NameValuePair("user_pwd", user_pwd),
                new NameValuePair("mobile", mobile),
                new NameValuePair("msg_content", msg_content),
                new NameValuePair("msg_id", msg_id)
        };
        postMethod.setRequestBody(param);
        try {
            hc.executeMethod(postMethod);
            result = postMethod.getResponseBodyAsString();
            postMethod.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                hc.executeMethod(postMethod);
                String response = postMethod.getResponseBodyAsString();
            } catch (Exception e1) {
            }
            if (postMethod != null)
                postMethod.releaseConnection();
        } finally {
            if (postMethod != null)
                postMethod.releaseConnection();
            if (hc != null && hc.getHttpConnectionManager() != null) {
                hc.getHttpConnectionManager().closeIdleConnections(0);
                ((SimpleHttpConnectionManager) hc.getHttpConnectionManager()).shutdown();
                httpConnectionManager = null;
                hc = null;
            }
        }
        return result;
    }
}
