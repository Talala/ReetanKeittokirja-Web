package klo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.SAXException;

/**
 * Servlet implementation class MakeNewRecipe
 */
@WebServlet("/MakeNewRecipe")
public class MakeNewRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeNewRecipe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeBookCompiler rbc;
		try {
			rbc = new RecipeBookCompiler();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name =(String) request.getAttribute("newrecname");
		String type =(String) request.getAttribute("newrectype");
		rbc = (RecipeBookCompiler) request.getSession().getAttribute("rbc");
		/*
		rbc.deleteRecipe(selectedRecipe);    
        XmlFileWriter xmlwriter = new XmlFileWriter(file, file.getAbsolutePath(), rbc.getDoc() );
        xmlwriter.writeFile();
		
		
		request.getSession().setAttribute("rbc", rbc);
		*/
	}

}
