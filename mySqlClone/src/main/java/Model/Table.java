package Model;

public class Table {
	private String columnName;
	private String options;
	private String length;
	private String defaults;
	private String primaryKey;
	private String notNull;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getNotNull() {
		return notNull;
	}
	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}
	public Table(String columnName, String options, String length, String defaults, String primaryKey, String notNull) {
		super();
		this.columnName = columnName;
		this.options = options;
		this.length = length;
		this.defaults = defaults;
		this.primaryKey = primaryKey;
		this.notNull = notNull;
	}
	public Table() {
		// TODO Auto-generated constructor stub
	}
	

}
