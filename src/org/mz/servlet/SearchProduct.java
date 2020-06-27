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
 * Servlet implementation class SearchProduct
 */
@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProduct() {
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
		int categoryId,price;
		if (request.getParameter("categoryId").trim().equals("")) {
			categoryId = 0;
		} else {
			categoryId = Integer.parseInt(request.getParameter("categoryId").trim());
		}
		if (request.getParameter("prdtPrice").trim().equals("")) {
			price = 0;
		} else {
			price = Integer.parseInt(request.getParameter("prdtPrice").trim());
		}
		String productName = request.getParameter("prdtName").trim();
		try {
			Product product = new Product(categoryId,productName, price);
			ArrayList<Product> productList = AdminDbservice.searchProduct(product);
			HttpSession session = request.getSession(false);
			session.setAttribute("productList", productList);
			response.sendRedirect("viewProduct.jsp");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
