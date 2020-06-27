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

import org.mz.bean.Category;
import org.mz.dbservice.AdminDbservice;

/**
 * Servlet implementation class SearchCategory
 */
@WebServlet("/SearchCategory")
public class SearchCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCategory() {
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
		int categoryId;
		if (request.getParameter("catgyId").trim().equals("")) {
			categoryId = 0;
		} else {
			categoryId = Integer.parseInt(request.getParameter("catgyId").trim());
		}
		String categoryName = request.getParameter("catgyName").trim();
		try {
			Category category = new Category(categoryId, categoryName);
			ArrayList<Category> categoryList = AdminDbservice.searchCategory(category);
			HttpSession session = request.getSession(false);
			session.setAttribute("categoryList", categoryList);
			response.sendRedirect("viewCategory.jsp");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
