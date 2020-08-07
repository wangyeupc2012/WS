package application.ApiWs.vo;

/**
 * 手机验证码响应参数
 * @author wangye
 */
public class VerifiedCodeResponseVo {
    private String mobile;
    private String code;
    private String status;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VerifiedCodeResponseVo{" +
                "mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
