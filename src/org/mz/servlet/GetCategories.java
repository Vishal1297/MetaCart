package org.mz.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mz.bean.Category;
import org.mz.bean.Product;
import org.mz.dbservice.ProductDbservice;

/**
 * Servlet implementation class GetCategory
 */
@WebServlet("/GetCategory")
public class GetCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCategories() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<Category> categoryList = ProductDbservice.getCategories();
			if (!categoryList.isEmpty()) {
				request.setAttribute("categoryList", categoryList);
				int categoryId = categoryList.get(0).getCategoryId();
				ArrayList<Product> productList = ProductDbservice.getProduct(categoryId);
				request.setAttribute("productList", productList);
				request.getRequestDispatcher("category.jsp").forward(request, response);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
