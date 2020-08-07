package application.ApiWs.vo;

/**
 * 用户收藏更新请求参数实体类
 */
public class CollectionUpdateRequestVo {
    private String userId;
    private String collectionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public String toString() {
        return "CollectionUpdateRequestVo{" +
                "userId='" + userId + '\'' +
                ", collectionId='" + collectionId + '\'' +
                '}';
    }
}
