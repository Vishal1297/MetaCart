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
 * Servlet implementation class UpdateImage
 */
public class UpdateImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateImage() {
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
		String productName=null,imageName = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> fields = upload.parseRequest(request);
				for (FileItem item : fields) {
					if (item.isFormField()) {
						if (item.getFieldName().equals("productName")) {
							productName = item.getString();
						}
					}
					if (!item.isFormField()) {
						imageName =  new File(item.getName()).getName();
						File file = new File("/home/vishal/Projects/MetaCart/WebContent/images/"+imageName);
						if (!file.exists()) {
			                 item.write(new File("/home/vishal/Projects/MetaCart/WebContent/images/"+imageName));
						}
						if(imageName.isEmpty()) {
							imageName=request.getParameter("image");
						}
						if(!imageName.isEmpty() && !productName.isEmpty()) {
							int productId = AdminDbservice.getProductId(productName);
							int imageId = AdminDbservice.getImageId(productId);
							Image image = new Image(imageId, imageName,productId);
							boolean isImageUpdated = AdminDbservice.updateImage(image);
							if (isImageUpdated) {
								ArrayList<Image> imageList = AdminDbservice.getImages();
								HttpSession session = request.getSession(false);
								session.setAttribute("imageList", imageList);
								response.sendRedirect("viewImage.jsp");
								System.out.println("Image Updated");
							}
						}
					}					
				}
			} catch(IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException ex){
					ex.printStackTrace();
					response.sendRedirect("editImage.jsp");
			} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("editImage.jsp");
			}
		}
	}
}