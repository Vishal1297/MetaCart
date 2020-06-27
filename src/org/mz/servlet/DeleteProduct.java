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
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
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
		int productId = Integer.parseInt(request.getParameter("id"));
		try {
			boolean isProductDeleted = AdminDbservice.deleteProduct(productId);
			if (isProductDeleted) {
				ArrayList<Product> productList = AdminDbservice.getProducts();
				HttpSession session = request.getSession(false);
				session.setAttribute("productList", productList);
				response.sendRedirect("viewProduct.jsp");
				System.out.println("Record Deleted");
			} else {
				errorMessage(request, response);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			errorMessage(request, response);
		}
	}
	public static void errorMessage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "Product can't be deleted !!");
		request.setAttribute("link", "view");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}
}
