package com.ysu.util;

public class PageBean {
private int page;
private int rows;
private int start;
public int getPage() {
	return page;
}

public PageBean(int page, int rows) {
	super();
	this.page = page;
	this.rows = rows;
}
public void setPage(int page) {
	this.page = page;
}
public int getRows() {
	return rows;
}
public void setRows(int rows) {
	this.rows = rows;
}
public int getStart() {
	return (page-1)*rows;
}

public void setStart(int start) {
	this.start = start;
}


}
