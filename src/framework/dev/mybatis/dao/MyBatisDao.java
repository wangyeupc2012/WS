package framework.dev.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import framework.dev.mybatis.vo.MyBatisVO;
import framework.mybatis.BaseDao;

@Repository
@SuppressWarnings("all")
public class MyBatisDao extends BaseDao {
	
	public static final String NAMESPACE = "framework.dev.mybatis.mapper.MyBatisMapper.";
	
	/**
	 * 获取表信息
	 * @param owner     数据库用户
	 * @param tableName 数据库表
	 */
	public List<MyBatisVO> getTableList(String owner, String tableName) throws Exception {
		MyBatisVO vo = new MyBatisVO();
		vo.setOwner(owner);
		vo.setTableName(tableName);
		return selectList(NAMESPACE + "getTableList", vo);
	}
	
	/**
	 * 获取表字段信息
	 * @param owner     数据库用户
	 * @param tableName 数据库表
	 */
	public List<MyBatisVO> getColumnList(String owner, String tableName) throws Exception {
		MyBatisVO vo = new MyBatisVO();
		vo.setOwner(owner);
		vo.setTableName(tableName);
		return selectList(NAMESPACE + "getColumnList", vo);
	}
	
	/**
	 * 获取表唯一索引信息
	 * @param owner     数据库用户
	 * @param tableName 数据库表
	 */
	public List<String> getPKColumnList(String owner, String tableName) throws Exception {
		MyBatisVO vo = new MyBatisVO();
		vo.setOwner(owner);
		vo.setTableName(tableName);
		return selectList(NAMESPACE + "getPKColumnList", vo);
	}
	
}
