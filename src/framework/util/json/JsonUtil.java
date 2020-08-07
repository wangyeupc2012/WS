package framework.util.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@SuppressWarnings("all")
public class JsonUtil {

	/**
	 * JSON转换成Map
	 */
	public static Map getMapFromJSON(String json) {
		Map map = new HashMap();
		JSONObject jsonObject = JSONObject.fromObject(json);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = jsonObject.get(key);
			//net.sf.json包中，如果json字符串的值为null，那么jsonObject会返回JSONNull
			value = value instanceof JSONNull||value==null||"null".equals(value)?"":value;
			map.put(key, value);
		}
		return map;
	}

	/**
	 * Map转换成JSON
	 */
	public static String getJSONFromMap(Map map) {
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
	
	/**
	 * JSON转换成Java对象
	 */
	public static Object getObjectFromJSON(String originJson, Class targetClass) {
		JSONObject jsonObject = JSONObject.fromObject(originJson);
		return JSONObject.toBean(jsonObject, targetClass);
	}

	/**
	 * Java对象转换成JSON
	 */
	public static String getJSONFromObject(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}

	/**
	 * Array转换成JSON
	 */
	public static String getJSONFromObjectArray(Object[] objectArray) {
		JSONArray jsonArray = JSONArray.fromObject(objectArray);
		return jsonArray.toString();
	}
	
	/**
	 * Array转换成JSON
	 */
	public static String getJSONFromObjectArray(Object objectArray) {
		JSONArray jsonArray = JSONArray.fromObject(objectArray);
		return jsonArray.toString();
	}
	
	/**
	 * JSON转换成List
	 */
	public static List getObjectListFromJSON(String json, Class targetClass) {
		List targetList = new ArrayList();
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection jsonCollection = JSONArray.toCollection(jsonArray);
		if (jsonCollection != null && !jsonCollection.isEmpty()) {
			Iterator it = jsonCollection.iterator();
			while (it.hasNext()) {
				JSONObject targetJSONObj = JSONObject.fromObject(it.next());
				Object obj = JSONObject.toBean(targetJSONObj, targetClass);
				targetList.add(obj);
			}
		}
		return targetList;
	}

	/**
	 * List转换成JSON
	 */
	public static String getJSONFromObjectList(List objectList) {
		JSONArray jsonArray = JSONArray.fromObject(objectList);
		return jsonArray.toString();
	}
	
	/**
	 * json 转换成 js json
	 * @param xml
	 * @return 
	 */
	public static String string2Json(String s,boolean isJSjson){
		 StringBuffer sb = new StringBuffer ();      
		 for(int i=0; i<s.length(); i++){
			 char c =s.charAt(i);
			 switch(c){
			 case'\'': if(isJSjson) {sb.append("\\\'");}else{sb.append("\'");} break;
			 case'\"': if(!isJSjson) {sb.append("\\\"");}else{sb.append("\"");} break;
			 case'\\':sb.append("\\\\"); break; //如果不处理单引号，可以释放此段代码，若结合StringDanYinToJSON()处理单引号就必须注释掉该段代码
			 case'/': sb.append("\\/");break;
			 case'\b':sb.append("\\b");break;//退格
			 case'\f':sb.append("\\f");break;//走纸换页
			 case'\n':sb.append("\\n");break;//换行
			 case'\r':sb.append("\\r");break;//回车
			 case'\t':sb.append("\\t");break;//横向跳格
			 default: sb.append(c);
			 }}
			 return sb.toString();  
	}
	public void main(String[] args){
		String json = JsonUtil.getJSONFromObjectArray("");
	}

	/** 
	    * 将容易引起xss漏洞的半角字符直接替换成全角字符 
	    * 目前xssProject对注入代码要求是必须开始标签和结束标签(如<script></script>)正确匹配才能解析，否则报错；因此只能替换调xssProject换为自定义实现 
	    * @param s 
	    * @return 
	    */  
	   private static String xssEncode(String s) {  
	        if (s == null || s.isEmpty()) {  
	            return s;  
	        }  
	  
	        String result = stripXSS(s);  
	        if (null != result) {  
	            result = escape(result);  
	        }  
	  
	        return result;  
	  
	    }  
	  
	    public static String escape(String s) {  
	        StringBuilder sb = new StringBuilder(s.length() + 16);  
	        for (int i = 0; i < s.length(); i++) {  
	            char c = s.charAt(i);  
	            switch (c) {  
	            case '>':  
	                sb.append('＞');// 全角大于号  
	                break;  
	            case '<':  
	                sb.append('＜');// 全角小于号  
	                break;  
	            case '\'':  
	                sb.append('‘');// 全角单引号  
	                break;  
	            case '\\':  
	                sb.append('＼');// 全角斜线 
	                break; 
	            case '\"':  
	                sb.append('“');// 全角双引号  
	                break;  
	            case '%':  
	                sb.append('％'); // 全角冒号  
	                break;  
	            default:  
	                sb.append(c);  
	                break;  
	            }  
	  
	        }  
	        return sb.toString();  
	    }  
	  
	    private static String stripXSS(String value) {  
	        if (value != null) {  
	            // NOTE: It's highly recommended to use the ESAPI library and  
	            // uncomment the following line to  
	            // avoid encoded attacks.  
	            // value = ESAPI.encoder().canonicalize(value);  
	            // Avoid null characters  
	            value = value.replaceAll("", "");  
	            // Avoid anything between script tags  
	            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",  
	                    Pattern.CASE_INSENSITIVE);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Avoid anything in a src='...' type of expression  
	            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",  
	                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
	                            | Pattern.DOTALL);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",  
	                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
	                            | Pattern.DOTALL);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Remove any lonesome </script> tag  
	            scriptPattern = Pattern.compile("</script>",  
	                    Pattern.CASE_INSENSITIVE);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Remove any lonesome <script ...> tag  
	            scriptPattern = Pattern.compile("<script(.*?)>",  
	                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
	                            | Pattern.DOTALL);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Avoid eval(...) expressions  
	            scriptPattern = Pattern.compile("eval\\((.*?)\\)",  
	                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
	                            | Pattern.DOTALL);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Avoid expression(...) expressions  
	            scriptPattern = Pattern.compile("expression\\((.*?)\\)",  
	                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
	                            | Pattern.DOTALL);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Avoid javascript:... expressions  
	            scriptPattern = Pattern.compile("javascript:",  
	                    Pattern.CASE_INSENSITIVE);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Avoid vbscript:... expressions  
	            scriptPattern = Pattern.compile("vbscript:",  
	                    Pattern.CASE_INSENSITIVE);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Avoid onload= expressions  
	            scriptPattern = Pattern.compile("onload(.*?)=",  
	                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
	                            | Pattern.DOTALL);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	  
	            scriptPattern = Pattern.compile("<iframe>(.*?)</iframe>",  
	                    Pattern.CASE_INSENSITIVE);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	  
	            scriptPattern = Pattern.compile("</iframe>",  
	                    Pattern.CASE_INSENSITIVE);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	            // Remove any lonesome <script ...> tag  
	            scriptPattern = Pattern.compile("<iframe(.*?)>",  
	                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
	                            | Pattern.DOTALL);  
	            value = scriptPattern.matcher(value).replaceAll("");  
	        }  
	        return value;  
	    }  
	  
	
}
