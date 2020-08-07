package application.ApiWs.vo;

/**
 * 推送消息详情页请求参数
 */
public class PushMessageDetailRequestVo {
    private String userId;
    private String messageId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "PushMessageDetailRequestVo{" +
                "userId='" + userId + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
