package application.ApiWs.vo;

import java.util.List;

/**
 * 获取热卖商品相应参数
 */
public class HotItemResponseVo {
    private List<HotItemVo> hotItemVoList;

    public List<HotItemVo> getHotItemVoList() {
        return hotItemVoList;
    }

    public void setHotItemVoList(List<HotItemVo> hotItemVoList) {
        this.hotItemVoList = hotItemVoList;
    }

    @Override
    public String toString() {
        return "HotItemResponseVo{" +
                "hotItemVoList=" + hotItemVoList +
                '}';
    }
}
