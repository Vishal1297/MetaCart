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
 * Servlet implementation class UpdateCategory
 */
@WebServlet("/UpdateCategory")
public class UpdateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategory() {
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
		if (request.getParameter("categoryId").trim().equals("")) {
			categoryId = 0;
		} else {
			categoryId = Integer.parseInt(request.getParameter("categoryId").trim());
		}
		String categoryName = request.getParameter("categoryName").trim();
		if (categoryId>=0 && categoryName.matches("^[ A-Za-z]+$") && !categoryName.isEmpty()) {
			try {
					Category category = new Category(categoryId, categoryName);
					boolean isCategoryUpdated = AdminDbservice.updateCategory(category);
					if (isCategoryUpdated) {
						ArrayList<Category> categoryList = AdminDbservice.getCategories();
						HttpSession session = request.getSession(false);
						session.setAttribute("categoryList", categoryList);
						response.sendRedirect("viewCategory.jsp");
						System.out.println("Record Updated");
					}
			} catch(IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException ex){
				System.out.println(ex);
				response.sendRedirect("editCategory.jsp");
			}
		} else {
			response.sendRedirect("editCategory.jsp");
		}
	}

}
