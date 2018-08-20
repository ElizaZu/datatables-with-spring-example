package dte.dao.pagination.model;

import java.util.List;

/**
 * Created by Eliza on 29.03.2018.
 */
public class PageRequest {
	private int draw;
	private int start;
	private int length;
	private List<Column> columns;
	private Search search;
	private List<Order> order;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public String findColumnName(int index){
		return this.columns.get(index).getName();
	}

	/*public String getOrderColumnName(){
		Order order = getSingleOrder();
		if(order == null) return null;
		if(order.getColumn() > this.columns.size()) return null;
		return this.columns.get(order.getColumn()).getName();
	}

	public boolean getOrderAsc(){
		Order order = getSingleOrder();
		return order != null && "asc".equals(order.getDir());

	}

	private Order getSingleOrder(){
		if(this.getOrder().size() > 0){
			return this.getOrder().get(0);
		}
		return  null;
	}*/
}
