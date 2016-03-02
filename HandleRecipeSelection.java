package klo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.xml.sax.SAXException;

/**
 * Servlet implementation class HandleRecipeSelection
 */
@WebServlet("/HandleRecipeSelection")
public class HandleRecipeSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleRecipeSelection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UPLOAD_DIRECTORY = request.getServletContext().getRealPath("/");
		File file = new File(UPLOAD_DIRECTORY + "recipebook.xml");
		String selectedRecipe="";
		try {
		
		selectedRecipe = request.getParameter("recipelisting");
		selectedRecipe.trim();
		} catch (NullPointerException npe) {
			//PrintWriter
		}
		
		RecipeBookCompiler rbc;
		
		try {
			rbc = new RecipeBookCompiler();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rbc =(RecipeBookCompiler) request.getSession().getAttribute("rbc");
		request.getSession().removeAttribute("rbc");
		/*Tehd‰‰n pysyv‰ muutos tiedostoon jolloin Tuo tietokoneelle napilla saadaan up-to-date reseptikirja*/
			rbc.deleteRecipe(selectedRecipe);    
	        XmlFileWriter xmlwriter = new XmlFileWriter(file, file.getAbsolutePath(), rbc.getDoc() );
	        xmlwriter.writeFile();
			
			
			request.getSession().setAttribute("rbc", rbc);
			request.getRequestDispatcher("displayRecipeList.jsp").forward(request, response);
		
		
	}

}
