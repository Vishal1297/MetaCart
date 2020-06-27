package org.mz.servlet;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mz.bean.Product;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
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
		String productName = request.getParameter("productName").trim();
		int price = Integer.parseInt(request.getParameter("price").trim());
		int quantity = Integer.parseInt(request.getParameter("quantity").trim());
		Product product = new Product(productName,price,quantity);
		HttpSession session = request.getSession();
		ArrayList<Product> productList = (ArrayList<Product>)(session.getAttribute("productList"));
		if (productList==null) {
			productList = new ArrayList<>();
			session.setAttribute("productList", productList);
		}
		productList.add(product);
		response.sendRedirect("cart.jsp");
	}

}
