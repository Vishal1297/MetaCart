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
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
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
		int productPrice,categoryId;
		try {
			if (request.getParameter("productPrice").trim().equals("")) {
				productPrice = 0;
			} else {
				productPrice = Integer.parseInt(request.getParameter("productPrice").trim());
			}
			String productName = request.getParameter("productName").trim();
			String categoryName = request.getParameter("categoryName").trim();
			String productDescription = request.getParameter("productDescription").trim();
			if (productPrice>0 && categoryName.matches("^[ A-Za-z]+$")  && !productName.isEmpty() && !categoryName.isEmpty() && !productDescription.isEmpty()) {
				categoryId = AdminDbservice.getCategoryId(categoryName);
				Product product = new Product(productName, categoryName , productPrice, productDescription,categoryId);
				boolean isProductAdded = AdminDbservice.addProduct(product);
				if (isProductAdded) {
					ArrayList<Product> productList = AdminDbservice.getProducts();
					HttpSession session = request.getSession(false);
					session.setAttribute("productList", productList);
					response.sendRedirect("viewProduct.jsp");
					System.out.println("Record Added");
				} else {
					errorMessage(request, response);
				}
			} else {
				errorMessage(request, response);
			}
		} catch(IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException ex){
			ex.printStackTrace();
			System.out.println(ex);
			errorMessage(request, response);
		}
	}

	public static void errorMessage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "Please Enter Valid Input");
		request.setAttribute("link", "addProduct.jsp");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}
}
