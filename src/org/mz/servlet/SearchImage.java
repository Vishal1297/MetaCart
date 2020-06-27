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

import org.mz.bean.Image;
import org.mz.dbservice.AdminDbservice;

/**
 * Servlet implementation class SearchImage
 */
@WebServlet("/SearchImage")
public class SearchImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchImage() {
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
		int imageId,productId;
		if (request.getParameter("imageId").trim().equals("")) {
			imageId = 0;
		} else {
			imageId = Integer.parseInt(request.getParameter("imageId").trim());
		}
		if (request.getParameter("pdtId").trim().equals("")) {
			productId = 0;
		} else {
			productId = Integer.parseInt(request.getParameter("pdtId").trim());
		}
		String imageFile = request.getParameter("image").trim();
		try {
			Image image = new Image(imageId, imageFile, productId);
			ArrayList<Image> imageList = AdminDbservice.searchImage(image);
			HttpSession session = request.getSession(false);
			session.setAttribute("imageList", imageList);
			response.sendRedirect("viewImage.jsp");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
