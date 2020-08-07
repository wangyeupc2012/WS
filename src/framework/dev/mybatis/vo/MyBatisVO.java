package framework.dev.mybatis.vo;

public class MyBatisVO {

	private String owner;
	private String tableName;
	private String tableComments;
	private String columnName;
	private String dataType;

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

	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "MyBatisVO{" +
				"owner='" + owner + '\'' +
				", tableName='" + tableName + '\'' +
				", tableComments='" + tableComments + '\'' +
				", columnName='" + columnName + '\'' +
				", dataType='" + dataType + '\'' +
				'}';
	}
}
