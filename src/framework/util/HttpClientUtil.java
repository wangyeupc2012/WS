package framework.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class HttpClientUtil {

	public static String executeHttpPost(String url, Map<String, String> paramMap, String sessionId) throws Exception {  
        BufferedReader reader = null;
        CloseableHttpClient client = null;
        HttpPost post = null;
        
        try {
        	// 构建HttpClient对象
            client = HttpClients.createDefault();
            
            // 实例化HTTP POST方法
            post = new HttpPost(url);
            
            // 设置参数列表 
            List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
            Set<String> keySet = paramMap.keySet();
            for(String key : keySet) {
            	nvpList.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            post.setEntity(new UrlEncodedFormEntity(nvpList, "UTF-8"));
            //超时设置
            int timeout=5000;
            Builder builder = RequestConfig.custom();
            builder.setConnectionRequestTimeout(timeout);
            builder.setConnectTimeout(timeout);
            builder.setSocketTimeout(timeout);
            RequestConfig config = builder.build();
            post.setConfig(config);
            // 保持会话有效
            post.addHeader(new BasicHeader("Cookie", "JSESSIONID=" + sessionId));
            
            // 执行请求  
            HttpResponse response = client.execute(post);  
  
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8")); 
            
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            //String NL = System.getProperty("line.separator");  
            while ((line = reader.readLine()) != null) {  
                //sb.append(line + NL);
                sb.append(line.trim());
            }  
            reader.close();
            String result = sb.toString();
            return result;  
        } finally {  
            if (reader != null) {  
                try {  
                	reader.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }
            
            if (client != null) {
            	client.close();
            }
        }  
    }  

	public static String executeHttpPost(String url, String body) throws Exception {  
        BufferedReader reader = null;
        CloseableHttpClient client = null;
        HttpPost post = null;
        try {
        	// 构建HttpClient对象
        	
            client = HttpClients.createDefault();
            // 实例化HTTP POST方法
            post = new HttpPost(url);
            // 设置参数列表 
            HttpEntity entity = new StringEntity(body, "utf-8");
            post.setEntity(entity);
            //超时设置
            /*
             * 
             
            int timeout = 5000;
            Builder builder = RequestConfig.custom();
            builder.setConnectionRequestTimeout(timeout);
            builder.setConnectTimeout(timeout);
            builder.setSocketTimeout(timeout);
            RequestConfig config = builder.build();
            post.setConfig(config);
            * */
            // 执行请求  
            HttpResponse response = client.execute(post);  
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8")); 
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            while ((line = reader.readLine()) != null) {  
                sb.append(line.trim());
            }  
            reader.close();
            String result = sb.toString();
            return result;  
        } finally {  
            if (reader != null) {  
                try {  
                	reader.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }
            
            if (client != null) {
            	client.close();
            }
        }  
    }  

	public static String executeHttpPost(String url) throws Exception {  
        BufferedReader reader = null;
        CloseableHttpClient client = null;
        HttpPost post = null;
        try {
        	// 构建HttpClient对象
            client = HttpClients.createDefault();
            // 实例化HTTP POST方法
            post = new HttpPost(url);
            // 执行请求  
            HttpResponse response = client.execute(post);  
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8")); 
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            while ((line = reader.readLine()) != null) {  
                sb.append(line.trim());
            }  
            reader.close();
            String result = sb.toString();
            return result;  
        } finally {  
            if (reader != null) {  
                try {  
                	reader.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }
            
            if (client != null) {
            	client.close();
            }
        }  
    }  
	
//	public static void main(String[] args) {
//		String url = "http://localhost:8082/SYJDWS/resource/app/operationMsCourierInfo/dkadfs/dskfsajasd";
//		try {
//			String payload = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
//            payload = payload + "<request>";
//            payload = payload + "<ms_courier_id>"+"27"+"</ms_courier_id>";
//            payload = payload + "<ms_courier_name>"+"测试配送员5ee"+"</ms_courier_name>";
//            payload = payload + "<tel_number>"+"13255427894"+"</tel_number>";
//            payload = payload + "<id_card_number>"+"231121190009092321"+"</id_card_number>";
//            payload = payload + "<sex>"+"1"+"</sex>";
//            payload = payload + "<header_image>"+"http://image2"+"</header_image>";
//            payload = payload + "<status>"+"VALID"+"</status>";
//            payload = payload + "<operation>"+"insert"+"</operation>";
//            payload = payload + "<last_updated_by>"+"204"+"</last_updated_by>";
//            payload = payload + "<milk_station_id>"+"2529"+"</milk_station_id>";
//            payload = payload + "</request>";
//			String result = HttpClientUtil.executeHttpPost(url, payload);
//			System.out.println("result:"+result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

    /**发送get请求
     *
     *@param
     *@return
     *throws Exception
     *@author dongyh
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
}
