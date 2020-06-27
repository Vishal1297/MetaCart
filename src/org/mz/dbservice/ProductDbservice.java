package org.mz.dbservice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mz.bean.Category;
import org.mz.bean.Image;
import org.mz.bean.Product;

public class ProductDbservice {
	
	private static Connection conn;
	public static void databaseConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metacart","root","root");
	}
	
	public static ArrayList<Category> getCategories() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		ArrayList<Category> categoryList = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("select * from category;");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int categoryId = rs.getInt("category_id"); 
            String categoryName = rs.getString("category_name");
            Category category = new Category(categoryId, categoryName);
            categoryList.add(category);
        }
        conn.close();
        return categoryList;
	}

	public static Product showProduct(int productId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		int prdtId=0,price=0;
		String productName=null,description=null,categoryName=null;
		PreparedStatement stmt = conn.prepareStatement("select product.product_id , product.product_name, product.price, product.description, "
				+ "category.category_name from product left join category on product.category_id=category.category_id where product.product_id="+productId+";");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            prdtId = rs.getInt("product_id");
            productName = rs.getString("product_name");
            price = rs.getInt("price");
            description = rs.getString("description");
            categoryName = rs.getString("category_name");
        }
        Product product = new Product(prdtId,productName,price,description,categoryName);
        conn.close();
        return product;
	}
	
	public static ArrayList<Product> getProduct(int ctgyId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		int prdtId=0,price=0;
		String productName=null,description=null,categoryName=null;
		ArrayList<Product> productList = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("select product.product_id , product.product_name, product.price, product.description,"
				+ "category.category_name from product left join category on product.category_id=category.category_id where category.category_id="+ctgyId+";");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            prdtId = rs.getInt("product_id");
            productName = rs.getString("product_name");
            price = rs.getInt("price");
            description = rs.getString("description");
            categoryName = rs.getString("category_name");
            Product product = new Product(prdtId,productName,price,description,categoryName);
            productList.add(product);
        }
        conn.close();
        return productList;
	}
	
	public static ArrayList<Product> getProducts() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		ArrayList<Product> productList = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("select product.product_id , product.product_name, product.price, product.description,"
				+ "category.category_name from product left join category on product.category_id=category.category_id;");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt("product_id");
            String productName = rs.getString("product_name");
            int price = rs.getInt("price");
            String description = rs.getString("description");
            int categoryId = rs.getInt("category_id");
            String categoryName = rs.getString("category_name");
            Product product = new Product(productId, productName, price, description, categoryId, categoryName);
            productList.add(product);
        }
        conn.close();
        return productList;
	}
	
	public static ArrayList<Image> getImages() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		ArrayList<Image> imageList = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("select image.image_id , image.image, product.product_name from image left join product "
				+ "on product.product_id=image.product_id;");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int imageId = rs.getInt("image_id");
            String imageName = rs.getString("image");
            String productName = rs.getString("product_name");
            Image image = new Image(imageId, imageName, productName);
            imageList.add(image);
        }
        conn.close();
        return imageList;
	}
	
	public static String getImage(int productId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		String imageName=null;
		PreparedStatement stmt = conn.prepareStatement("select image from image where product_id="+productId+";");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            imageName = rs.getString("image");
        }
        conn.close();
        return imageName;
	}
}
