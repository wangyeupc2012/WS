package application.ApiWs.vo.api;

/**
 * API接口请求参数
 *
 * @author wangye
 * 2018-08-08
 */
public class ApiRequest {

    public enum MethodEnum {
        USER_LOGIN("user.login",1),
        VERIFIED_CODE_SEND("verificationCode.send",2),
        USER_RESET_PASSWORD("user.resetPassword",3),
        ITEM_LIST("item.list",4),
        ITEM_TYPE("item.type",5),
        SHOPPINGCART_LIST("shoppingCart.list",6),
        SHOPPINGCART_MODIFIED("shoppingCart.modified",7),
        SHOPPINGCART_ADD("shoppingCart.add",8),
        SHOPPINGCART_CONFIRM("shoppingCart.confirm",9),
        ITEM_DETAIL("item.detail",10),
        ORDER_LIST("order.list",11),
        ORDER_COPY("order.copy",12),
        WALLET_LOGLIST("wallet.logList",13),
        PUSHMESSAGE_LIST("pushMessage.list",14),
        PUSHMESSAGE_DETAIL("pushMessage.detail",15),
        HOTSEARCH_LIST("hotSearch.list",16),
        ITEM_HOT("item.hot",17),
        ITEM_SPECIAL("item.special",18),
        ITEM_PROMOTION("item.promotion",19),
        ORDER_UPDATE("order.update",20),
        GET_VERIFIEDCODE("get.verifiedcode",21),
        HOMEPAGE_LIST("homePage.list",22),
        COLLECTION_LIST("collection.list",23),
        COLLECTION_ADD("collection.add",24),
        COLLECTION_UPDATE("collection.update",25),
        ALIPAY("alipay",26),
        USER_INFO("user.info",50),
        ERROR_METHOD("error", 99);

        private String name;
        private int index;

        /**
         * 构造方法
         *
         * @param name
         * @param index
         */
        MethodEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        /**
         * 返回枚举名
         *
         * @param name
         * @return
         */
        public static MethodEnum getEnumFromName(String name) {
            if (name != null) {
                for (MethodEnum methodEnum : MethodEnum.values()) {
                    if (name.equals(methodEnum.getName())) {
                        return methodEnum;
                    }
                }
            }
            return ERROR_METHOD;
        }

    }

    /**
     * 业务接口名称
     */
    private String method;
    /**
     * 佳邦提供的appKey
     */
    private String appKey;
    /**
     * MD5加密密钥
     */
    private String sign;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 业务请求数据
     */
    private String content;

    public MethodEnum getMethodIndex() {
        return MethodEnum.getEnumFromName(this.method);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "method='" + method + '\'' +
                ", appKey='" + appKey + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
