package framework.dev.mybatis.service;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import framework.dev.mybatis.dao.MyBatisDao;
import framework.dev.mybatis.vo.MyBatisVO;

@Service
@SuppressWarnings("all")
public class MyBatisService {
	
	@Resource
	private MyBatisDao myBatisDao;
	
	public final static String default_user = "IRP";

	public final static String prefixPath = "D:\\workspace8.6\\bhecds\\src\\";
	
	public final static String model_package = "application.common.model";
	public final static String mapper_package = "application.common.mapper";
	public final static String dao_package = "application.common.dao";
	
	public final static String model_path = "application/common/model/";
	public final static String mapper_path = "application/common/mapper/";
	public final static String dao_path = "application/common/dao/";
	
	private String owner;
	private String tableName;
	private List<MyBatisVO> columnList;
	private List<String> pkColumnList;
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<MyBatisVO> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<MyBatisVO> columnList) {
		this.columnList = columnList;
	}

	public List<String> getPkColumnList() {
		return pkColumnList;
	}

	public void setPkColumnList(List<String> pkColumn) {
		this.pkColumnList = pkColumn;
	}

	/**
	 * 获取数据库表信息
	 * @param owner     数据库用户
	 * @param tableName 数据库表
	 */
	public List<MyBatisVO> getTableList(String owner, String tableName) throws Exception {
		return myBatisDao.getTableList(owner, tableName);
	}
	
	/**
	 * 生成Model、Mapper、Dao文件
	 * @param owner     数据库用户
	 * @param tableName 数据库表
	 */
	public void generator(String owner, String tableName) throws Exception {
		// 设置数据库用户
		this.setOwner(owner);
		// 设置表名
		this.setTableName(tableName);
		// 设置字段
		this.setColumnList(myBatisDao.getColumnList(owner, tableName));
		// 设置主键
		this.setPkColumnList(myBatisDao.getPKColumnList(owner, tableName));
		
		if ((this.getColumnList() != null && this.getColumnList().size() > 0)
			&& (this.getPkColumnList() != null && this.getPkColumnList().size() > 0)) {
			// 生成Model文件
			generatorModel();
			// 生成Mapper文件
			generatorMapper();
			// 生成Dao文件
			generatorDao();
		} else {
			System.out.println("您确定输入的用户名或表名正确？");
		}
	}

	/**
	 * 生成Model文件
	 */
	private void generatorModel() throws Exception {
//		String prefixPath = MyBatisService.class.getResource("/").toString();
//		prefixPath = prefixPath.substring(6, prefixPath.indexOf("WebRoot")) + "src/";
		
		String filePath = prefixPath + model_path;
		String fileName = getModelName(tableName) + ".java";
		String fileContent = getModelFileContent();
		
		writeFile(filePath, fileName, fileContent);
		
		System.out.println("Model文件生成成功：" + filePath + fileName);
	}
	
	/**
	 * 生成Mapper文件
	 */
	private void generatorMapper() throws Exception {
//		String prefixPath = MyBatisService.class.getResource("/").toString();
//		prefixPath = prefixPath.substring(6, prefixPath.indexOf("WebRoot")) + "src/";
		
		String filePath = prefixPath + mapper_path;
		String fileName = getModelName(tableName) + ".xml";
		String fileContent = getMapperFileContent();
		
		writeFile(filePath, fileName, fileContent);
		
		System.out.println("Mapper文件生成成功：" + filePath + fileName);
	}
	
	/**
	 * 生成Dao文件
	 */
	private void generatorDao() throws Exception {
//		String prefixPath = MyBatisService.class.getResource("/").toString();
//		prefixPath = prefixPath.substring(6, prefixPath.indexOf("WebRoot")) + "src/";
		
		String filePath = prefixPath + dao_path;
		String fileName = getModelName(tableName) + "Dao.java";
		String fileContent = getDaoFileContent();
		
		writeFile(filePath, fileName, fileContent);
		
		System.out.println("DAO文件生成成功：" + filePath + fileName);
	}
	
	/**
	 * 获取Model文件内容
	 */
	private String getModelFileContent() throws Exception {
		String fileContent = "";
		
		fileContent += "package " + model_package + ";\n\n";
		fileContent += "public class " + getModelName(tableName) + " {\n\n";
		
		for (MyBatisVO column : columnList) {
			fileContent += "    private String " + column.getColumnName() + ";\n";
		}
		
		fileContent += "\n";
		
		for (MyBatisVO column : columnList) {
			String columnName = column.getColumnName();
			String firstUpper = String.valueOf(columnName.charAt(0)).toUpperCase() + columnName.substring(1, columnName.length());
			
			fileContent += "    public String get" + firstUpper + "() {\n";
			fileContent += "        return " + columnName + ";\n";
			fileContent += "    }\n\n";
			
			fileContent += "    public void set" + firstUpper + "(String " + columnName + ") {\n";
			fileContent += "        this." + columnName + " = " + columnName + ";\n";
			fileContent += "    }\n\n";
		}
		
		fileContent += "}";
		
		return fileContent;
	}
	
	/**
	 * 获取Mapper文件内容
	 */
	private String getMapperFileContent() throws Exception {
		String modelName = getModelName(tableName);
		
		String fileContent = "";
		
		fileContent += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		fileContent += "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n";
		fileContent += "<mapper namespace=\"" + mapper_package + "." + modelName + "\">\n\n";
		
		fileContent += "    <insert id=\"insert" + modelName + "\" parameterType=\"" + modelName + "\">\n";
		fileContent += "        insert into " + tableName.toLowerCase() + "\n";
		int i = 0;
		for (MyBatisVO column : columnList) {
			if (i == 0) {
				fileContent += "       (" + column.getColumnName() + "\n";
			} else if (i == columnList.size() - 1){
				fileContent += "       ," + column.getColumnName() + ")\n";
			} else {
				fileContent += "       ," + column.getColumnName() + "\n";
			}
			i++;
		}
		fileContent += "        values\n";
		int j = 0;
		for (MyBatisVO column : columnList) {
			if (j == 0) {
				if ("date".equals(column.getDataType())) {
					fileContent += "       (to_date(#{" + column.getColumnName() + "},'yyyy-mm-dd hh24:mi:ss')\n";
				} else {
					fileContent += "       (#{" + column.getColumnName() + "}\n";
				}
			} else if (j == columnList.size() - 1){
				if ("date".equals(column.getDataType())) {
					fileContent += "       ,to_date(#{" + column.getColumnName() + "},'yyyy-mm-dd hh24:mi:ss'))\n";
				} else {
					fileContent += "       ,#{" + column.getColumnName() + "})\n";
				}
			} else {
				if ("date".equals(column.getDataType())) {
					fileContent += "       ,to_date(#{" + column.getColumnName() + "},'yyyy-mm-dd hh24:mi:ss')\n";
				} else {
					fileContent += "       ,#{" + column.getColumnName() + "}\n";
				}
			}
			j++;
		}
		fileContent += "    </insert>\n\n";
		
		fileContent += "    <update id=\"update" + modelName + "\" parameterType=\"" + modelName + "\">\n";
		fileContent += "        update " + tableName.toLowerCase() + "\n";
		int k = 0;
		for (MyBatisVO column : columnList) {
			if (k == 0) {
				if ("date".equals(column.getDataType())) {
					fileContent += "           set " + column.getColumnName() + " = to_date(#{" + column.getColumnName() + "},'yyyy-mm-dd hh24:mi:ss')\n";
				} else {
					fileContent += "           set " + column.getColumnName() + " = #{" + column.getColumnName() + "}\n";
				}
			} else {
				if ("date".equals(column.getDataType())) {
					fileContent += "              ," + column.getColumnName() + " = to_date(#{" + column.getColumnName() + "},'yyyy-mm-dd hh24:mi:ss')\n";
				} else {
					fileContent += "              ," + column.getColumnName() + " = #{" + column.getColumnName() + "}\n";
				}
				
			}
			k++;
		}
		fileContent += "         where 1 = 1\n";
		
		for (String pkColumn : pkColumnList) {
			fileContent += "           and " + pkColumn + " = #{" + pkColumn + "}\n";
		}
		
		fileContent += "    </update>\n\n";
		
		fileContent += "    <delete id=\"delete" + modelName + "\" parameterType=\"" + modelName + "\">\n";
		fileContent += "        delete\n";
		fileContent += "          from " + tableName.toLowerCase() + "\n";
		
		fileContent += "         where 1 = 1\n";
		
		for (String pkColumn : pkColumnList) {
			fileContent += "           and " + pkColumn + " = #{" + pkColumn + "}\n";
		}
		
		fileContent += "    </delete>\n\n";
		
		fileContent += "    <select id=\"select" + modelName + "ByPk\" parameterType=\"" + modelName + "\" resultType=\"" + modelName + "\">\n";
		int l = 0;
		for (MyBatisVO column : columnList) {
			if (l == 0) {
				if ("date".equals(column.getDataType())) {
					fileContent += "        select to_char(" + column.getColumnName() + ",'yyyy-mm-dd hh24:mi:ss') " + column.getColumnName() + "\n";
				} else {
					fileContent += "        select " + column.getColumnName() + "\n";
				}
			} else {
				if ("date".equals(column.getDataType())) {
					fileContent += "              ,to_char(" + column.getColumnName() + ",'yyyy-mm-dd hh24:mi:ss') " + column.getColumnName() + "\n";
				} else {
					fileContent += "              ," + column.getColumnName() + "\n";
				}
			} 
			l++;
		}
		fileContent += "          from " + tableName.toLowerCase() + "\n";
		
		fileContent += "         where 1 = 1\n";
		
		for (String pkColumn : pkColumnList) {
			fileContent += "           and " + pkColumn + " = #{" + pkColumn + "}\n";
		}
		fileContent += "    </select>\n\n";
		
		fileContent += "</mapper>";
		
		return fileContent;
	}
	
	/**
	 * 获取Dao文件内容
	 */
	private String getDaoFileContent() throws Exception {
		String fileContent = "";
		
		fileContent += "package " + dao_package + ";\n\n";
		fileContent += "import org.springframework.stereotype.Repository;\n\n";
		fileContent += "import application.common.mybatis.model." + getModelName(tableName) + ";\n";
		fileContent += "import framework.mybatis.BaseDao;\n\n";
		fileContent += "@Repository\n";
		fileContent += "public class " + getModelName(tableName) + "Dao extends BaseDao {\n\n";
		fileContent += "    public static final String NAMESPACE = \"application.common.mybatis.mapper." + getModelName(tableName) + ".\";\n\n";
		
		fileContent += "    public int insert" + getModelName(tableName) + "(" + getModelName(tableName) + " obj) throws Exception {\n";
		fileContent += "        return insert(NAMESPACE + \"insert" + getModelName(tableName) + "\", obj);\n";
		fileContent += "    }\n\n";	
		
		fileContent += "    public int update" + getModelName(tableName) + "(" + getModelName(tableName) + " obj) throws Exception {\n";
		fileContent += "        return update(NAMESPACE + \"update" + getModelName(tableName) + "\", obj);\n";
		fileContent += "    }\n\n";	
		
		fileContent += "    public int delete" + getModelName(tableName) + "(" + getModelName(tableName) + " obj) throws Exception {\n";
		fileContent += "        return delete(NAMESPACE + \"delete" + getModelName(tableName) + "\", obj);\n";
		fileContent += "    }\n\n";	
		
		fileContent += "    public " + getModelName(tableName) + " select" + getModelName(tableName) + "ByPk" + "(" + getModelName(tableName) + " obj) throws Exception {\n";
		fileContent += "        return selectOne(NAMESPACE + \"select" + getModelName(tableName) + "ByPk\", obj);\n";
		fileContent += "    }\n\n";	
		
		fileContent += "}";
		
		return fileContent;
	}

	/**
	 * 根据表名获取Model类的名称
	 * @param tableName 表名
	 */
	private String getModelName(String tableName) {
		StringBuffer modelName = new StringBuffer("");
		
		tableName = tableName.toLowerCase();
		
		List<Integer> upperList = new ArrayList<Integer>();
		upperList.add(0);
		
		for (int i=0; i<tableName.length(); i++) {
			String str = String.valueOf(tableName.charAt(i));
			if ("_".equals(str)) {
				upperList.add(i+1);
			}
		}
		
		for (int i=0; i<tableName.length(); i++) {
			String str = String.valueOf(tableName.charAt(i));
			if (upperList.contains(i)) {
				modelName.append(str.toUpperCase());
			} else {
				modelName.append(str);
			}
		}
		
		return modelName.toString().replaceAll("_", "");
	}
	
	/**
	 * 写文件
	 * @param path    文件路径
	 * @param name    文件名称
	 * @param content 文件内容
	 */
	public void writeFile(String path, String name, String content) throws Exception {
		FileWriter fw = new FileWriter(path + name);
		fw.write(content);
		fw.close();
	}
	
}