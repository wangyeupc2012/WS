package application.ApiWs.vo;

/**
 * 门店bean类
 * @author wangye
 * 2018-08-16 09：21：24
 */
public class StoreVo {
    private String storeId;
    private String storeName;
    private String storeManagerId;
    private String storeSalesmanId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreManagerId() {
        return storeManagerId;
    }

    public void setStoreManagerId(String storeManagerId) {
        this.storeManagerId = storeManagerId;
    }

    public String getStoreSalesmanId() {
        return storeSalesmanId;
    }

    public void setStoreSalesmanId(String storeSalesmanId) {
        this.storeSalesmanId = storeSalesmanId;
    }

    @Override
    public String toString() {
        return "StoreVo{" +
                "storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeManagerId='" + storeManagerId + '\'' +
                ", storeSalesmanId='" + storeSalesmanId + '\'' +
                '}';
    }
}
