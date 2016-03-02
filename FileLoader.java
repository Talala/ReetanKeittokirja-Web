package klo;

import java.io.File;
import java.io.IOException;
import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/FileLoader")
public class FileLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileLoader() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * T��ll� asetetaan reseptikirjan alkio serverille, kirjoitetaan ladatun tiedoston sis�lt� alkioon ja sitten asetetaan
	 * HttpServletRequestin attribuutiksi ja v�litet��n edelleen jsp-tiedostoon joka esitt�� reseptikirjan sis�ll�n
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String UPLOAD_DIRECTORY = request.getServletContext().getRealPath("/");
    	String message="";
    	
    	File fi = new File(UPLOAD_DIRECTORY + "recipebook.xml");
    	/*if( fi.exists() ) {
    		fi.delete();
    		fi.createNewFile();
    		}
    	else
    		fi.mkdir();*/
    	
    //	if(!request.getSession().isNew())
    	//	request.getSession().removeAttribute("rbc");
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        
                        //kirjoitetaan ladatun reseptikirjan sis�lt� "alkioon"
                        item.write( fi);                
                    }
                }
                
               //luodaan reseptikirjan koostaja ja kytket��n siihen reseptikirja.
                RecipeBookCompiler rbc = new RecipeBookCompiler();
                rbc.attachRecipebook(fi);
                
               //lis�t��n olio attribuuttina.
               
               request.getSession().setAttribute("rbc", rbc);
               message = "Reseptikirja ladattu";
               request.setAttribute("message", message);
              
            
            } catch (Exception ex) {
            	message = "Tiedoston Latauksessa on tapahtunut virhe. Valitsithan tiedoston ensin ?";
               request.setAttribute("message", message);
            }          
         
        }else{
        	message =  "Vain XML-tiedostoja otetaan vastaan.";
            request.setAttribute("message",
                                message);
        }
        // v�litet��n request ja response jsp-sivulle joka esitt�� ladatun tiedoston sis�ll�n.
        request.getRequestDispatcher("displayRecipeList.jsp").forward(request, response);
     
	}

}
