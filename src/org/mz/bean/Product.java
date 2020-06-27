package org.mz.bean;

public class Product extends Category {
	
	private int productId;
	private String productName;
	private int price;
	private String description;
	private int quantity;
	
	public Product() {
		super();
	}
	
	public Product(int productId, String productName, String categoryName, int price, String description, int categoryId) {
		super(categoryId, categoryName);
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
	}

	public Product(int productId, String productName, int price, String description, int categoryId) {
		super(categoryId);
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
	}
	
	public Product(int productId, String productName, int price, String description, int categoryId , String categoryName) {
		super(categoryId, categoryName);
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
	}
	
	public Product(String productName, String categoryName, int price, String description, int categoryId) {
		super(categoryId, categoryName);
		this.productName = productName;
		this.price = price;
		this.description = description;
	}

	public Product(int productId, String productName, int price, String description, String categoryName) {
		super(categoryName);
		this.productId=productId;
		this.productName=productName;
		this.price=price;
		this.description=description;
	}
	
	public Product(String productName, int price, int quantity) {
		this.productName=productName;
		this.price=price;
		this.quantity=quantity;
	}
	
	public Product(int categoryId, String productName, int price) {
		super(categoryId);
		this.productName=productName;
		this.price=price;
	}
	
	public Product(String productName, int price,String categoryName) {
		super(categoryName);
		this.productName = productName;
		this.price = price;
	}
	
	public Product(int productId) {
		this.productId=productId;
	}
	
	public Product(String productName) {
		this.productName=productName;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
