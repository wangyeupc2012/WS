package application.ApiWs.vo;

import java.util.List;

/**
 * 用户登陆响应参数
 * @author wangye
 *
 */
public class UserLoginResponseVo {
    private String userId;
    private String userName;
    private String userType;
    private String userBelongs;
    private List<StoreVo> storeVoList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserBelongs() {
        return userBelongs;
    }

    public void setUserBelongs(String userBelongs) {
        this.userBelongs = userBelongs;
    }

    public List<StoreVo> getStoreVoList() {
        return storeVoList;
    }

    public void setStoreVoList(List<StoreVo> storeVoList) {
        this.storeVoList = storeVoList;
    }

    @Override
    public String toString() {
        return "UserLoginResponseVo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", userBelongs='" + userBelongs + '\'' +
                ", storeVoList=" + storeVoList +
                '}';
    }
}
