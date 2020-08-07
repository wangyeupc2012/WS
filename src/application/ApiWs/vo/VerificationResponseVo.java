package application.ApiWs.vo;

/**
 * 图形验证码响应参数
 */
public class VerificationResponseVo {
    private String code;
    private String base64Code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBase64Code() {
        return base64Code;
    }

    public void setBase64Code(String base64Code) {
        this.base64Code = base64Code;
    }

    @Override
    public String toString() {
        return "VerificationResponseVo{" +
                "code='" + code + '\'' +
                ", base64Code='" + base64Code + '\'' +
                '}';
    }
}
