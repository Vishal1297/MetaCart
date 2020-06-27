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
 * Servlet implementation class DeleteImage
 */
@WebServlet("/DeleteImage")
public class DeleteImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImage() {
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
		int imageId = Integer.parseInt(request.getParameter("id"));
		try {
			boolean isImageDeleted = AdminDbservice.deleteImage(imageId);
			if (isImageDeleted) {
				ArrayList<Image> imageList = AdminDbservice.getImages();
				HttpSession session = request.getSession(false);
				session.setAttribute("imageList", imageList);
				response.sendRedirect("viewImage.jsp");
			} else {
				errorMessage(request, response);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			errorMessage(request, response);
		}
	}
	public static void errorMessage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "Image can't be deleted !!");
		request.setAttribute("link", "view");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}
}
