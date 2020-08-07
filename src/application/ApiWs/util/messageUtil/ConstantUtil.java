/**
 * 
 */
package application.ApiWs.util.messageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iRP_Xcm
 *
 */
public class ConstantUtil {
	public static final String WEIXIN_APP_ID_bosun = "wxd9b6f259d5075ca8";
	public static final String WEIXIN_APP_SECRET_bosun ="1ed25348bb270b58e86c596476eec717";
	public static final String WEIXIN_IMG_PATH_bosun ="";

	public static final String WEIXIN_APP_ID_gd = "wx56289cbbc09be841";
	public static final String WEIXIN_APP_SECRET_gd ="d7065f6cbb06593e3033f8ef603136fd";
	public static final String WEIXIN_IMG_PATH ="";

	public static final int INTEGER_ZERO = 0;
	public static final String STRING_ZERO = "0";
	public static final String STRING_Y = "Y";
	public static final String STRING_N = "N";
	public static final String STRING_TEN = "10";
	public static final String STRING_ONE = "1";


	//短信平台静态变量 ---暂不使用
	public static final String MESSAGE_GET_BALANCE = "http://sms.hbsmservice.com:8080/get_balance.do";
	public static final String MESSAGE_GET_DELIVERMSG = "http://sms.hbsmservice.com:8080/post_deliverMsg.do";
	public static final String MESSAGE_GET_STATUS = "http://sms.hbsmservice.com:8080/post_report.do";
	public static final String MESSAGE_SEND_MESSAGE = "http://client.cloud.hbsmservice.com:8080/sms_send2.do";

	public static final String MESSAGE_TYPE_SEND = "fs";  //短信类型为发送  即下行
	public static final String MESSAGE_TYPE_RECEIVE = "js";  //短信类型为接收  即上行
	public static final String MESSAGE_CORP_ID = "sa6522";  //账户名
	public static final String MESSAGE_CORP_PWD = "6586rf";  //账户密码
	public static final String MESSAGE_CORP_SERVICE = "106902707075";  //业务号码

	//短信平台静态变量  ---使用中
	public static final String MESSAGE_SEND_MESSAGES = "http://dx.ipyy.net/sms.aspx";  //发送短信地址
	public static final String MESSAGE_QUERY_STATUS = "http://dx.ipyy.net/statusApi.aspx";//查询状态地址
	public static final String MESSAGE_QUERY_RETURN = "http://dx.ipyy.net/callApi.aspx";//查询上行信息
	public static final String MESSAGE_QUERY_BALANCE = "http://dx.ipyy.net/sms.aspx";//查询短信余额
	public static final String MESSAGE_SEND_MESSAGES_ACTION = "send";  //发送短信地址ACTION
	public static final String MESSAGE_QUERY_STATUS_ACTION = "query";//查询状态地址ACTION
	public static final String MESSAGE_QUERY_RETURN_ACTION = "query";//查询上行信息ACTION
	public static final String MESSAGE_QUERY_BALANCE_ACTION = "overage";//查询短信余额ACTION
	public static final String MESSAGE_ACCOUNT = "cc00007";  //短信账号名
	public static final String MESSAGE_PASSWORD = "000000";  //短信密码

	//EXCEL 导出样式
	public static final String HSSFCELLSTYLE_ALIGN_LEFT = "ALIGN_LEFT";
	public static final String HSSFCELLSTYLE_ALIGN_CENTER = "ALIGN_CENTER";
	public static final String HSSFCELLSTYLE_ALIGN_RIGHT = "ALIGN_RIGHT";


	//调用存储过程使用的参数类型
	public static final String PARA_STRING="String";//对应java.lang.String
	public static final String PARA_INTEGER="Integer";//对应java.lang.Integer
	public static final String PARA_DOUBLE="Double";//对应java.lang.Double
	public static final String PARA_LONG="Long";//对应java.lang.Long
	public static final String PARA_DATE="Date";//对应java.lang.Date

	//存储过程参数模式
	public static final String PARA_MODE_IN="in";//输入参数
	public static final String PARA_MODE_OUT="out";//输出参数

	//通知接口验证变量
	public static Map<String,String> INTERCEPTOR_VALIDATIOIN_MAP = new HashMap<String,String>();

}
