package application.ApiWs.vo;

public class ItemDetailRequestVo {
    private String itemId;
    private String itemName;
    private String userId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ItemDetailRequestVo{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
