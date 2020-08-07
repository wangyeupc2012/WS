package application.ApiWs.vo;

/**
 * 手机验证码请求参数
 * @author wangye
 */
public class VerifiedCodeRequestVo {
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "VerifiedCodeRequestVo{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
