package framework.dev.mybatis.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import framework.dev.mybatis.service.MyBatisService;
import framework.dev.mybatis.vo.MyBatisVO;
import framework.web.controller.ApplicationController;

@Controller
@SuppressWarnings("all")
public class MyBatisController extends ApplicationController {

	@Resource
	private MyBatisService myBatisService;
	
	/**
	 * 生成MyBatis文件
	 */
	@RequestMapping(value = "dev/mybatis/generator")
	public void generator(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String responseText = "";
		try {
			String owner = request.getParameter("owner") == null ? "" : request.getParameter("owner");
			String tableName = request.getParameter("tableName") == null ? "" : request.getParameter("tableName");

			if (!"".equals(owner) && !"".equals(tableName)) {
				myBatisService.generator(owner.toUpperCase(), tableName.toUpperCase());
				responseText = "生成MyBatis文件成功！";
			} else {
				responseText = "用户或表名不能为空！";
			}
		} catch (Exception e) {
			responseText = "生成MyBatis文件失败！";
			e.printStackTrace();
		} finally {
			this.writeJson(response, responseText);
		}
	}
	
}
