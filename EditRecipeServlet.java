package klo;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class EditRecipeServlet
 */
@WebServlet("/EditRecipeServlet")
public class EditRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String recipeName;
	String instructions;
	String type;
    String[] ingr;
    Vector<Ingredient> list;
    Recipe recipe;
    public EditRecipeServlet() {
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
		
		rbc =(RecipeBookCompiler) request.getSession().getAttribute("rbc");
		recipeName = (String)request.getSession().getAttribute("SelectedRecipe");
		type = rbc.getRecipeAttribute(recipeName, "type").getTextContent();
		ingr = request.getParameterValues("ingredientlisting");
		list = new Vector<Ingredient>();
	
		fromArrayToVect();
		
		
		instructions = request.getParameter("instr");
		//instructions = "t‰m‰ on defaultviesti.";
		
		String UPLOAD_DIRECTORY = request.getServletContext().getRealPath("/");
		File file = new File(UPLOAD_DIRECTORY + "recipebook.xml");
		
		recipe = new Recipe(list, recipeName, instructions, type); //( Vector<Ingredient> ingredients, String name, String instructions, String t)
		Element appendable = rbc.compileRecipeFromObject(recipe);
		rbc.replaceRecipe(appendable);
		
		/*Tehd‰‰n pysyv‰ muutos tiedostoon jolloin Tuo tietokoneelle napilla saadaan up-to-date reseptikirja*/
			   
	        XmlFileWriter xmlwriter = new XmlFileWriter(file, file.getAbsolutePath(), rbc.getDoc() );
	        xmlwriter.writeFile();
	        request.getSession().setAttribute("rbc", rbc);
	        request.getRequestDispatcher("displayRecipe.jsp").forward(request, response);
	        
	}
	
	public void fromArrayToVect() {
		
		for( int i= 0; i<ingr.length; i++ ) {
			String entry = ingr[i];
			entry.trim();
			int firstSpace = entry.indexOf(" ");
			int lastSpace = entry.lastIndexOf(" ");
			String name = entry.substring(0, firstSpace);
			String amount = entry.substring(firstSpace, lastSpace);
			String unit = entry.substring(lastSpace, entry.length());
			list.add(new Ingredient(name, amount, unit));	
			
			
		}
	}

}
