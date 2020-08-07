package application.ApiWs.vo;

/**
 * 用户登陆请求参数
 * @author wangye
 */
public class UserLoginRequestVo {
    private String loginUsername;
    private String encodedPassword;

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    @Override
    public String toString() {
        return "UserLoginRequestVo{" +
                "loginUsername='" + loginUsername + '\'' +
                ", encodedPassword='" + encodedPassword + '\'' +
                '}';
    }
}
