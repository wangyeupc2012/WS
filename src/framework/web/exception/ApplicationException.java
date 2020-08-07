package framework.web.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("all")
public class ApplicationException implements HandlerExceptionResolver {

	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// 将异常信息写入日志
		logger.error(ex);
		
		String reg = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at ";
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		
		String error = sw.getBuffer().toString();
		String message = error.replaceAll("at ", reg);
		
		Map map = new HashMap();
		map.put("errorMessage", message);
		
		// 跳转到错误页面
		return new ModelAndView("common/exception", map);
	}

}
