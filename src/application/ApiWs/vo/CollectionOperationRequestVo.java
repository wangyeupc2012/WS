package application.ApiWs.vo;

/**
 * 用户收藏商品相关操作通用请求参数实体类
 */
public class CollectionOperationRequestVo {
    private String userId;
    private String itemId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "CollectionOperationRequestVo{" +
                "userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
