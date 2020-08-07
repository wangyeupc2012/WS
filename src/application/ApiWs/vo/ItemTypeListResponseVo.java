package application.ApiWs.vo;

import java.util.List;

/**
 * 获取商品分类响应参数
 */
public class ItemTypeListResponseVo {
    private String itemTypeCode;
    private String itemTypeName;
    private List<ItemTypeVo> itemTypeVoList;

    public String getItemTypeCode() {
        return itemTypeCode;
    }

    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public List<ItemTypeVo> getItemTypeVoList() {
        return itemTypeVoList;
    }

    public void setItemTypeVoList(List<ItemTypeVo> itemTypeVoList) {
        this.itemTypeVoList = itemTypeVoList;
    }

    @Override
    public String toString() {
        return "ItemTypeListResponseVo{" +
                "itemTypeCode='" + itemTypeCode + '\'' +
                ", itemTypeName='" + itemTypeName + '\'' +
                ", itemTypeVoList=" + itemTypeVoList +
                '}';
    }
}
