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

public class AdminDbservice {
	
	private static Connection conn;
	public static void databaseConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metacart","root","root");
	}
	
	public static boolean addCategory(Category category) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into category values ("+category.getCategoryId()+",'" + category.getCategoryName()+ "');");
		int value = stmt.executeUpdate(); 
        conn.close();
        return value > 0;
	}
	
	public static boolean addProduct(Product product) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into product(product_name,price,description,category_id) values ('"+product.getProductName()+"','" + product.getPrice() 
		+ "','" + product.getDescription() +"',"+ product.getCategoryId() + ");");
		int value = stmt.executeUpdate(); 
        conn.close();
        return value > 0;
	}
	
	public static boolean addImage(Image image) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into image(image,product_id) values ('"+image.getImage()+"',"+image.getProductId()+");");
		int value = stmt.executeUpdate(); 
        conn.close();
        return value > 0;
	}
	
	public static boolean deleteCategory(int categoryId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("delete from category where category_id="+categoryId+";");
		int value = stmt.executeUpdate(); 
        conn.close();
        return value > 0;
	}
	
	public static boolean deleteProduct(int productId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("delete from product where product_id="+productId+";");
		int value = stmt.executeUpdate();
        conn.close();
        return value > 0;
	}
	
	public static boolean deleteImage(int imageId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("delete from image where image_id="+imageId+";");
		int value = stmt.executeUpdate(); 
        conn.close();
        return value > 0;
	}
	
	public static ArrayList<Category> searchCategory(Category category) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		ArrayList<Category> categoryList = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select * from category where");
        if (category.getCategoryId()>0) {
            sql.append(" category_id =").append(category.getCategoryId()).append(" and");
        }
        if (!category.getCategoryName().isEmpty()) {
            sql.append(" category_name like'").append(category.getCategoryName()).append("%' and");
        }
        String search = sql.toString();
        String replace = null;
        
        if (search.endsWith("where")) {
            replace = search.replace("where", ";");
        } else if (search.endsWith("and")) {
            replace = search.substring(0, search.length() - 4);
        }
        PreparedStatement stmt = conn.prepareStatement(replace);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	int categoryId = rs.getInt("category_id");
            String categoryName = rs.getString("category_name");
            Category categoryTwo = new Category(categoryId, categoryName);
            categoryList.add(categoryTwo);
        }
        conn.close();
		return categoryList;
	}
	
	public static ArrayList<Product> searchProduct(Product product) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		ArrayList<Product> productList = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select product.product_id, product.product_name, product.price, product.description ,"
				+ " category.category_name as 'category_name' from product left join category on product.category_id=category.category_id where");
        if (product.getCategoryId()>0) {
            sql.append(" category.category_id =").append(product.getCategoryId()).append(" and");
        }
        if (!product.getProductName().isEmpty()) {
            sql.append(" product.product_name like'").append(product.getProductName()).append("%' and");
        }
        if (product.getPrice()>0) {
            sql.append(" product.price =").append(product.getPrice()).append(" and");
        }
        String search = sql.toString();
        String replace = null;
        
        if (search.endsWith("where")) {
            replace = search.replace("where", ";");
        } else if (search.endsWith("and")) {
            replace = search.substring(0, search.length() - 4);
        }
        PreparedStatement stmt = conn.prepareStatement(replace);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	int productId = rs.getInt("product_id");
        	String productName = rs.getString("product_name");
        	int price = rs.getInt("price");
        	String description = rs.getString("description");
            String categoryName = rs.getString("category_name");
            Product productTwo = new Product(productId, productName, price, description, categoryName);
            productList.add(productTwo);
        }
        conn.close();
		return productList;
	}
	
	public static ArrayList<Image> searchImage(Image image) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		ArrayList<Image> imageList = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select image.image_id, image.image, product.product_name as 'product_name' from image left join "
				+ "product on image.product_id=product.product_id where");
        if (image.getImageId()>0) {
            sql.append(" image.image_id =").append(image.getImageId()).append(" and");
        }
        if (!image.getImage().isEmpty()) {
            sql.append(" image.image ='").append(image.getImage()).append("' and");
        }
        System.out.println(image.getProductId());
        if (image.getProductId()>0) {
            sql.append(" product.product_id =").append(image.getProductId()).append(" and");
        }
        String search = sql.toString();
        String replace = null;
        if (search.endsWith("where")) {
            replace = search.replace("where", ";");
        } else if (search.endsWith("and")) {
            replace = search.substring(0, search.length() - 4);
        }
        PreparedStatement stmt = conn.prepareStatement(replace);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	int imageId = rs.getInt("image_id");
            String imageFile = rs.getString("image");
            String productName = rs.getString("product_name");
            Image imageTwo = new Image(imageId, imageFile, productName);
            imageList.add(imageTwo);
        }
        conn.close();
		return imageList;
	}
	
	public static boolean updateCategory(Category category) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("update category set category_name='" + category.getCategoryName() + "' where category_id=" + category.getCategoryId() + ";");
		int value = stmt.executeUpdate();
		conn.close();
        return value > 0;
	}
	
	public static boolean updateProduct(Product product) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("update product set product_name='" + product.getProductName() + "', price=" + product.getPrice() 
		+ ", description='" + product.getDescription() + "', category_id=" + product.getCategoryId() + " where product_id=" + product.getProductId() + ";");
		int value = stmt.executeUpdate();
		conn.close();
        return value > 0;
	}
	
	public static boolean updateImage(Image image) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		PreparedStatement stmt = conn.prepareStatement("update image set image='" + image.getImage() + "', product_id=" + image.getProductId() +" where image_id=" 
		+ image.getImageId() + ";");
		int value = stmt.executeUpdate();
		conn.close();
        return value > 0;
	}
	
	public static int getCategoryId(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		int category_id=0;
		PreparedStatement stmt = conn.prepareStatement("select category_id from category where category_name='"+name+"';");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
        	category_id = rs.getInt("category_id");
		}
		return category_id;
	}
	
	public static int getProductId(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		int productId=0;
		PreparedStatement stmt = conn.prepareStatement("select product_id from product where product_name='"+name+"';");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
        	productId = rs.getInt("product_id");
		}
		return productId;
	}
	
	public static Category getCategory(int categoryId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		int ctgyId=0;
		String categoryName=null;
		PreparedStatement stmt = conn.prepareStatement("select * from category where category_id="+categoryId+";");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            ctgyId = rs.getInt("category_id");
            categoryName = rs.getString("category_name");
        }
        Category category = new Category(ctgyId, categoryName);
        conn.close();
        return category;
	}
	
	public static Product getProduct(int productId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
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
	
	public static Image getImage(int imageId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		int imgId=0;
		String imageName=null,productName=null;
		PreparedStatement stmt = conn.prepareStatement("select image.image_id , image.image, product.product_name from image left join product "
				+ "on product.product_id=image.product_id where image_id="+imageId+";");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            imgId = rs.getInt("image_id");
            imageName = rs.getString("image");
            productName = rs.getString("product_name");
        }
        Image image = new Image(imgId, imageName, productName);
        conn.close();
        return image;
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
            String categoryName = rs.getString("category_name");
            Product product = new Product(productId, productName, price, description,categoryName);
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

	public static int getImageId(int productId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		databaseConnection();
		int imageId = 0;
		PreparedStatement stmt = conn.prepareStatement("select image_id from image where product_id="+productId+";");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            imageId = rs.getInt("image_id");
        }
        conn.close();
        return imageId;
	}
}
