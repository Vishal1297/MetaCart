package org.mz.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.mz.bean.Image;
import org.mz.dbservice.AdminDbservice;

/**
 * Servlet implementation class AddImage
 */
public class AddImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddImage() {
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
		String productName = null, imageName = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				errorMessage(request, response);
				return;
			} else {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> fields = upload.parseRequest(request);
				for (FileItem item : fields) {
					if (item.isFormField()) {
						if (item.getFieldName().equals("productName")) {
							productName = item.getString();
						}
					}
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						if (item.getSize() > 0) {
							item.write(new File("/home/vishal/Projects/MetaCart/WebContent/images/" + imageName));
						}
					}
				}
				if (imageName != null && !imageName.equals("") && !productName.isEmpty()) {
					int productId = AdminDbservice.getProductId(productName);
					Image image = new Image(imageName, productId);
					System.out.println(imageName + " " + productName);
					boolean isImageAdded = AdminDbservice.addImage(image);
					if (!isImageAdded) {
						System.out.println("Image Not Added");
						errorMessage(request, response);
						return;
					} else {
						ArrayList<Image> imageList = AdminDbservice.getImages();
						HttpSession session = request.getSession(false);
						session.setAttribute("imageList", imageList);
						response.sendRedirect("viewImage.jsp");
					}
				}
			}
		} catch (IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException ex) {
			System.out.println(ex);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void errorMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("message", "Please Enter Valid Input");
		request.setAttribute("link", "addImage.jsp");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
		return;
	}

}
