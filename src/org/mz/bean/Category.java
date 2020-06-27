package org.mz.bean;

public class Category {
	private int categoryId;
	private String categoryName;
	
	public Category () {
	}
	
	public Category(String categoryName) {
		this.categoryName=categoryName;
	}
	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	
	public Category(int categoryId) {
		this.categoryId=categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
