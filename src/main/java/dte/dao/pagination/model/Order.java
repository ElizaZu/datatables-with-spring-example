package dte.dao.pagination.model;

/**
 * Created by Eliza on 30.03.2018.
 */
public class Order {
	private int column;
	private String dir;

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean isAsc(){
		return "asc".equals(getDir());
	}
}
