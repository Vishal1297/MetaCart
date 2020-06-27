package org.mz.bean;
public class Image extends Product{
	private int imageId;
	private String image;
	
	public Image(int imageId, String image, int productId) {
		super(productId);
		this.imageId = imageId;
		this.image = image;
	}
	
	public Image(String imagePath,int productId) {   
		super(productId);
		this.image=imagePath;
	}

	public Image(int imageId, String imagePath, String productName) {
		super(productName);
		this.imageId=imageId;
		this.image=imagePath;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
