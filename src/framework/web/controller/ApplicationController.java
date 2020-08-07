package framework.web.controller;

import javax.servlet.http.HttpServletResponse;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class ApplicationController {

	public void writeJson(HttpServletResponse response, String json) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 报表标题格式
	 * @return WritableCellFormat
	 * @throws WriteException
	 */
	public WritableCellFormat getHeaderTitleFormatter() throws WriteException
	{
		WritableCellFormat headerTitleFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD));
		headerTitleFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		headerTitleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		headerTitleFormat.setAlignment(Alignment.CENTRE);
		headerTitleFormat.setBackground(Colour.GREY_25_PERCENT);
		
		return headerTitleFormat;

	}
	
	/**
	 * 报表标题格式
	 * @return WritableCellFormat
	 * @throws WriteException
	 */
	public WritableCellFormat getCollectHeaderFormatter() throws WriteException
	{
		WritableCellFormat headerTitleFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD));
		headerTitleFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		headerTitleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		headerTitleFormat.setAlignment(Alignment.CENTRE);
		
		return headerTitleFormat;

	}
	/**
	 * 报表标题格式
	 * @return WritableCellFormat
	 * @throws WriteException
	 */
	public WritableCellFormat getCollectSmallHeaderFormatter() throws WriteException
	{
		WritableCellFormat headerTitleFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD));
		headerTitleFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		headerTitleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		headerTitleFormat.setAlignment(Alignment.CENTRE);
		
		return headerTitleFormat;

	}
	
	/**
	 * 报表右对齐格式
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat getRightContextFormat() throws WriteException
	{

		//标题格式
		WritableCellFormat rightContextFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11,
						WritableFont.NO_BOLD));
		rightContextFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		rightContextFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		rightContextFormat.setAlignment(Alignment.RIGHT);
		
		return rightContextFormat;
	}
	
	/**
	 * 中间对齐格式
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat getCenterContextFormat() throws WriteException
	{
		
		
		//标题格式
		WritableCellFormat centerContextFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11,
						WritableFont.NO_BOLD));
		centerContextFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		centerContextFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		centerContextFormat.setAlignment(Alignment.CENTRE);
		return centerContextFormat;
	}
	
	/**
	 * 左对齐格式
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat getLeftContextFormat() throws WriteException
	{
		//标题向左对齐
		WritableCellFormat leftContextFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11,
						WritableFont.NO_BOLD));
		leftContextFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		leftContextFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		leftContextFormat.setAlignment(Alignment.LEFT);
		return leftContextFormat;
	}
	/**
	 * 浅绿色
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat getRedBackgroundFormat() throws WriteException
	{
		//标题向左对齐
		WritableCellFormat redBackgroundFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11,
						WritableFont.NO_BOLD));
		redBackgroundFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		redBackgroundFormat.setBackground(Colour.LIGHT_GREEN);
		redBackgroundFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		redBackgroundFormat.setAlignment(Alignment.LEFT);
		return redBackgroundFormat;
	}
	
	/**
	 * very light yellow
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat getLightYellowBackgroundFormat() throws WriteException
	{
		//标题向左对齐
		WritableCellFormat redBackgroundFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11,
						WritableFont.BOLD));
		redBackgroundFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
				Colour.BLACK);
		redBackgroundFormat.setBackground(Colour.VERY_LIGHT_YELLOW);
		redBackgroundFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		redBackgroundFormat.setAlignment(Alignment.LEFT);
		return redBackgroundFormat;
	}

}
