package klo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SaveandSendFile
 */
@WebServlet("/SaveandSendFile")
public class SaveandSendFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveandSendFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int BUFFERSIZE = 4096;
		String UPLOAD_DIRECTORY = request.getServletContext().getRealPath("/");
    	
    	File fi = new File(UPLOAD_DIRECTORY + "recipebook.xml");
    	 int length   = 0;
         ServletOutputStream outStream = response.getOutputStream();
         ServletContext context  = getServletConfig().getServletContext();
         String mimetype = context.getMimeType(fi.getAbsolutePath());
         
         
         if (mimetype == null) {
             mimetype = "application/octet-stream";
         }
         
         response.setContentType(mimetype);
         response.setContentLength((int)fi.length());
         
         String fileName = fi.getName();
         
         // sets HTTP header
         response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
         
         byte[] byteBuffer = new byte[BUFFERSIZE];
        
         
       //  DataInputStream intwo = new DataInputStream();
         DataInputStream in = new DataInputStream(new FileInputStream(fi));
         
         // reads the file's bytes and writes them to the response stream
         while ((in != null) && ((length = in.read(byteBuffer)) != -1))
         {
             outStream.write(byteBuffer,0,length);
         }
         
         in.close();
         outStream.close();
         
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
