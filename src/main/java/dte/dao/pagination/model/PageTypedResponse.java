package dte.dao.pagination.model;

import java.util.List;

/**
 * Created by Eliza on 29.03.2018.
 */
public class PageTypedResponse<T> {
	private long recordsTotal;
	private long recordsFiltered;
	private int draw;
	private List<T> data;

	public PageTypedResponse(long recordsTotal, long recordsFiltered, int draw, List<T> data) {
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.draw = draw;
		this.data = data;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public int getDraw() {
		return draw;
	}

	public List<T> getData() {
		return data;
	}
}
