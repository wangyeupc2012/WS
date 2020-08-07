package application.ApiWs.vo;

/**
 * 用户信息响应参数
 */
public class UserInfoResponseVo {
    private String userId;
    private String userName;
    private String userType;
    private String userPhone;
    private String userWalletAmount;
    private String userAddress;
    private String shippingMilkStationId;
    private String shippingMilkStationName;
    private String servicePhone;

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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserWalletAmount() {
        return userWalletAmount;
    }

    public void setUserWalletAmount(String userWalletAmount) {
        this.userWalletAmount = userWalletAmount;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getShippingMilkStationId() {
        return shippingMilkStationId;
    }

    public void setShippingMilkStationId(String shippingMilkStationId) {
        this.shippingMilkStationId = shippingMilkStationId;
    }

    public String getShippingMilkStationName() {
        return shippingMilkStationName;
    }

    public void setShippingMilkStationName(String shippingMilkStationName) {
        this.shippingMilkStationName = shippingMilkStationName;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    @Override
    public String toString() {
        return "UserInfoResponseVo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userWalletAmount='" + userWalletAmount + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", shippingMilkStationId='" + shippingMilkStationId + '\'' +
                ", shippingMilkStationName='" + shippingMilkStationName + '\'' +
                ", servicePhone='" + servicePhone + '\'' +
                '}';
    }
}
