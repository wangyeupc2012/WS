package application.ApiWs.vo;

/**
 * 用户信息请求参数
 */
public class UserInfoRequestVo {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfoRequestVo{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
