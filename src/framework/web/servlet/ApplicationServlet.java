package framework.web.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import framework.util.ConstantUtil;
import framework.util.SpringInit;


@SuppressWarnings("all")
public class ApplicationServlet extends HttpServlet {

	private Logger logger = Logger.getLogger(getClass());

	private ApplicationContext ctx;
	
	public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		logger.info("ApplicationServlet initialization started ...");
		logger.info("SYJDWS.webapp.root: " + System.getProperty("SYJDWS.webapp.root"));
		logger.info("ApplicationServlet initialization completed ...");
		try {
//			response.setHeader("Access-Control-Allow-Origin", "*");
			this.initConstantParameter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//初始化一些常量值
	public void initConstantParameter() throws Exception{
		try {
			if(ctx ==null){
               ctx = SpringInit.getApplicationContext();
            }
			logger.info("ApplicationServlet initConstantParameter completed ...");
        } catch (Exception e) {
        	e.printStackTrace();
        }		
	}
	
}
