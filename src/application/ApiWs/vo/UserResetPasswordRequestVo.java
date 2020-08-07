package application.ApiWs.vo;

/**
 * 用户密码更新&重置请求参数
 * @author wangye
 */
public class UserResetPasswordRequestVo {
    private String newEncodedPassword;
    private String oldEncodedPassword;
    private String userName;
    private String updateType;

    public String getNewEncodedPassword() {
        return newEncodedPassword;
    }

    public void setNewEncodedPassword(String newEncodedPassword) {
        this.newEncodedPassword = newEncodedPassword;
    }

    public String getOldEncodedPassword() {
        return oldEncodedPassword;
    }

    public void setOldEncodedPassword(String oldEncodedPassword) {
        this.oldEncodedPassword = oldEncodedPassword;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserResetPasswordRequestVo{" +
                "newEncodedPassword='" + newEncodedPassword + '\'' +
                ", oldEncodedPassword='" + oldEncodedPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", updateType='" + updateType + '\'' +
                '}';
    }
}
