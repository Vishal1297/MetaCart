package org.mz.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mz.bean.Product;
import org.mz.dbservice.AdminDbservice;
/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productPrice,categoryId,productId;
		if (request.getParameter("productPrice").trim().equals("")) {
			productPrice = 0;
		} else {
			productPrice = Integer.parseInt(request.getParameter("productPrice").trim());
		}
		String productName = request.getParameter("productName").trim();
		String categoryName = request.getParameter("categoryName").trim();
		String productDescription = request.getParameter("productDescription").trim();
		if (productPrice>0 && categoryName.matches("^[ A-Za-z]+$")  && !productName.isEmpty() && !categoryName.isEmpty() && !productDescription.isEmpty()) {
			try {
					productId = AdminDbservice.getProductId(productName);
					categoryId = AdminDbservice.getCategoryId(categoryName);
					Product product = new Product(productId, productName, categoryName , productPrice, productDescription,categoryId);
					boolean isProductUpdated = AdminDbservice.updateProduct(product);
					System.out.println(categoryId+" ctdyId");
					if (isProductUpdated) {
						System.out.println("Product Updated");
						ArrayList<Product> productList = AdminDbservice.getProducts();
						HttpSession session = request.getSession(false);
						session.setAttribute("productList", productList);
						response.sendRedirect("viewProduct.jsp");
					}else {
						response.sendRedirect("editProduct.jsp");
					}
			} catch(IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException ex){
				System.out.println(ex);
				response.sendRedirect("editProduct.jsp");
			}
		} else {
			response.sendRedirect("editProduct.jsp");
		}
	}

}
