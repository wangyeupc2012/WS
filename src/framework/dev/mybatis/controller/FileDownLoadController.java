package framework.dev.mybatis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 下载所有的文件
 * @author AI
 * @since 2012-08-02
 */

@Controller
@SuppressWarnings("all")
public class FileDownLoadController {

	private Log logger = LogFactory.getLog(getClass());

	/**
	 * request中的fileName为要下载的文件名称，路径excel_model
	 */
	@RequestMapping(value="sys/fileDownLoad.do")
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getSession().getServletContext().getRealPath("/") + "/excel_model/";
		String fileName = request.getParameter("fileName");
		
		FileInputStream fis = null;
		PrintWriter pw = null;
		
		try {
//			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "iso-8859-1"));
			
			fis = new FileInputStream(new File(filePath + fileName));
			pw = response.getWriter();
			
			int b = 0;
			while((b=fis.read())!=-1) {
				pw.write(b);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.close();
		}
	}

}
