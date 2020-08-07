package application.ApiWs.util.messageUtil;
import application.ApiWs.vo.SmsInfo;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


public class MessageUtil {

    /**
     * 发送信息
     * @param smsInfo 短信类信息
     * @return
     */
    @SuppressWarnings("finally")
    public static String send_catNew(SmsInfo smsInfo) {
        String result = "";
        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod(ConstantUtil.MESSAGE_SEND_MESSAGE);
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
            NameValuePair[] data = { new NameValuePair("corp_id", ConstantUtil.MESSAGE_CORP_ID),
                    new NameValuePair("corp_pwd", ConstantUtil.MESSAGE_CORP_PWD),
                    new NameValuePair("corp_service", ConstantUtil.MESSAGE_CORP_SERVICE),
                    new NameValuePair("mobile", smsInfo.getMobile_number()),
                    new NameValuePair("msg_content", smsInfo.getSend_msg()),
                    new NameValuePair("corp_msg_id ", smsInfo.getSms_info_id()), new NameValuePair("ext ", "999") };
            post.setRequestBody(data);
            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:" + statusCode);
            for (Header h : headers) {
                System.out.println(h.toString());
            }
            result = new String(post.getResponseBodyAsString());
            System.out.println(result);
            post.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(result!="" && result!=null){
                result=result.substring(0,1);
            }else{
                result="error";
            }
            return result;
        }
    }

}
