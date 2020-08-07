package application.ApiWs.vo;

import java.util.List;

/**
 * 消息推送实体类
 */
public class PushMessageVo {
    private String messageId;
    private String messageContent;
    private String readFlag;
    private String creationDate;
    private String messageTitle;
    private String createdBy;
    private List<ItemAdVo> itemAdVoList;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public List<ItemAdVo> getItemAdVoList() {
        return itemAdVoList;
    }

    public void setItemAdVoList(List<ItemAdVo> itemAdVoList) {
        this.itemAdVoList = itemAdVoList;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "PushMessageVo{" +
                "messageId='" + messageId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", readFlag='" + readFlag + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", itemAdVoList=" + itemAdVoList +
                '}';
    }
}
