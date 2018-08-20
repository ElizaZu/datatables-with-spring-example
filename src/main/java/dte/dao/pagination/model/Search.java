package dte.dao.pagination.model;

/**
 * Created by Eliza on 30.03.2018.
 */
public class Search {
	private String value;
	private Boolean regex;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getRegex() {
		return regex;
	}

	public void setRegex(Boolean regex) {
		this.regex = regex;
	}
}
