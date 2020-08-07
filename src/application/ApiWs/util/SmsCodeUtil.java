package application.ApiWs.util;

import application.ApiWs.util.messageUtil.CHttpPost;
import application.ApiWs.util.messageUtil.ConfigManager;
import application.ApiWs.util.messageUtil.Message;
import framework.util.XmlUtils;
import framework.util.json.JsonUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SmsCodeUtil {
    /**
     * 深圳市梦网科技发展有限公司获取手机验证码
     */
    public static Map<String, String> getMobileCheckNum(String postBody) throws Exception {
//        String mobilenum = XmlUtils.getTextForElement(postBody,"mobilenum");
        String mobilenum = postBody;
        String return_status = "Y";
        String return_message = "获取验证码成功！";
        Map resultMap = new HashMap();
        Map map = new HashMap<String, String>();
        try {
            ConfigManager.removeAllIpInfo();
            // 用户账号
            String userid = "M10218";
            // 用户密码
            String pwd = "265110";
            //主IP信息，请前往您的控制台获取请求域名(IP)或联系梦网客服进行获取
            String masterIpAddress = "";
            if (CheckPhone.isChinaMobilePhoneNum(mobilenum)) {
                masterIpAddress = "120.196.116.126:8027";
            } else if (CheckPhone.isChinaTelecomPhoneNum(mobilenum)) {
                masterIpAddress = "61.145.229.28:8027";
            } else if (CheckPhone.isChinaUnicomPhoneNum(mobilenum)) {
                masterIpAddress = "112.91.147.38:8027";
            }
            //备IP1  选填
            String ipAddress1 = null;
            //备IP2  选填
            String ipAddress2 = null;
            //备IP3  选填
            String ipAddress3 = null;
            //设置IP
            ConfigManager.setIpInfo(masterIpAddress, ipAddress1, ipAddress2, ipAddress3);
            //密码是否加密   true：密码加密;false：密码不加密
            ConfigManager.IS_ENCRYPT_PWD = true;
            boolean isEncryptPwd = ConfigManager.IS_ENCRYPT_PWD;
            Random random = new Random();
            String code = random.nextInt(1000000) + "";
            String return_msg = "尊敬的客户，您本次操作验证码为" + code + "，请在10分钟内使用。谢谢!";
            // 单条发送
            Map<String, String> return_map = singleSend(userid, pwd, isEncryptPwd, return_msg, code, mobilenum);
            if ("Y".equals(return_map.get("status"))) {
                resultMap.put("code", return_map.get("code"));
                resultMap.put("mobile", mobilenum);
                //resultMap.put("userId",userVo.getUser_id());
                resultMap.put("status", "success");
            } else {
                return_status = "N";
                return_message = return_map.get("return_message");
                resultMap.put("sendDate", "");
                resultMap.put("code", "");
                resultMap.put("mobile", mobilenum);
                resultMap.put("status", "failed");
            }
        } catch (Exception e) {
            return_status = "N";
            return_message = e.getMessage();
            e.printStackTrace();
            resultMap.put("sendDate", "");
            resultMap.put("code", "");
            resultMap.put("mobile", mobilenum);
            resultMap.put("status", "failed");
        } finally {
            map.put("return_status", return_status);
            map.put("return_message", return_message);
            map.put("resultJson", JsonUtil.getJSONFromMap(resultMap));
        }
        return map;
    }

    /**
     * @param userid       用户账号
     * @param pwd          用户密码
     * @param isEncryptPwd 密码是否加密   true：密码加密;false：密码不加密
     * @description 单条发送
     */
    public static Map<String, String> singleSend(String userid, String pwd, boolean isEncryptPwd, String return_msg, String code, String phone) {
        Map<String, String> map = new HashMap<String, String>();
        // 参数类
        Message message = new Message();
        try {
            // 实例化短信处理对象
            CHttpPost cHttpPost = new CHttpPost();

            // 设置账号   将 userid转成大写,以防大小写不一致
            message.setUserid(userid.toUpperCase());

            //判断密码是否加密。
            //密码加密，则对密码进行加密
            if (isEncryptPwd) {
                // 设置时间戳
                SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
                String timestamp = sdf.format(Calendar.getInstance().getTime());
                message.setTimestamp(timestamp);

                // 对密码进行加密
                String encryptPwd = cHttpPost.encryptPwd(message.getUserid(), pwd, message.getTimestamp());
                // 设置加密后的密码
                message.setPwd(encryptPwd);

            } else {
                // 设置密码
                message.setPwd(pwd);
            }
            // 设置手机号码 此处只能设置一个手机号码
            message.setMobile(phone);
            // 设置内容
            message.setContent(return_msg);
            // 设置扩展号
            message.setExno("");
            // 用户自定义流水编号
            message.setCustid("");
            // 自定义扩展数据
            message.setExdata("");
            //业务类型
            message.setSvrtype("");

            // 返回的平台流水编号等信息
            StringBuffer msgId = new StringBuffer();
            // 返回值
            int result = -310099;
            // 发送短信
            result = cHttpPost.singleSend(message, msgId);
            // result为0:成功;非0:失败
            if (result == 0) {
                map.put("code", code);
                map.put("phone", message.getMobile());
                map.put("status", "Y");
                map.put("return_message", "单条发送提交成功！");
                System.out.println("单条发送提交成功！");
                System.out.println(msgId.toString());

            } else {
                map.put("code", "");
                map.put("phone", message.getMobile());
                map.put("status", "N");
                map.put("return_message", "单条发送提交失败,错误码：" + result);
                System.out.println("单条发送提交失败,错误码：" + result);
            }
        } catch (Exception e) {
            map.put("code", "");
            map.put("phone", message.getMobile());
            map.put("status", "N");
            map.put("return_message", e.getMessage());
            //异常处理
            e.printStackTrace();
        }
        return map;
    }
}
