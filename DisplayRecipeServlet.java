package klo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class DisplayRecipeServlet
 */
@WebServlet("/DisplayRecipeServlet")
public class DisplayRecipeServlet extends HttpServlet {
	
	String selectedRecipe ="";
	Vector<Ingredient> vector_ingredients = new Vector<Ingredient>();
	Vector<String> temp = new Vector<String>();
	String type = "";
	String instr = "";
	String entry = "";
	NodeList recipedetails;
	NodeList ingredients;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayRecipeServlet() {
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
		
		selectedRecipe = request.getParameter("recipelisting");
		request.getSession().setAttribute("recipeSelected", selectedRecipe);
		
		Vector<Ingredient> vector_ingredients = new Vector<Ingredient>();
		ArrayList<Ingredient> array = new ArrayList<Ingredient>();
		String type = "";
		String instr = "";
		String entry;
		
		RecipeBookCompiler rbc = (RecipeBookCompiler) request.getSession().getAttribute("rbc");		
		Node selectedrecipe = rbc.getRecipeAttribute(selectedRecipe, "Ingredients");
		
		type = rbc.getRecipeAttribute(selectedRecipe, "type").getTextContent();
		instr = rbc.getRecipeAttribute(selectedRecipe, "Instructions").getTextContent();	
		
		NodeList ingr = selectedrecipe.getChildNodes();
			
		entry = "";
		for ( int i = 0; i<ingr.getLength(); i++) {
			NodeList ingr_entry = ingr.item(i).getChildNodes();
			for (int z = 0; z<ingr_entry.getLength(); z++) {
				String s = ingr_entry.item(z).getTextContent();
				entry += s + " ";
			}
			int firstSpace = entry.indexOf(" ");
			int lastSpace = entry.lastIndexOf(" ");
			String name = entry.substring(0, firstSpace);
			String amount = entry.substring(firstSpace, lastSpace);
			String unit = entry.substring(lastSpace, entry.length());
			vector_ingredients.add(new Ingredient(name, amount, unit));	
			array.add(new Ingredient(name, amount, unit));
			entry = "";
		}
			
		Recipe recipe = new Recipe(vector_ingredients, selectedRecipe, instr, type);
		
		request.getSession().setAttribute("SelectedRecipe", selectedRecipe); //Tätä käytetään kun halutaan päivittää resepti eri servletissä
		request.setAttribute("Recipe", recipe);
		request.setAttribute("IngredientsArray", array);
		request.setAttribute("IngredientsVect", vector_ingredients);
		
		request.getRequestDispatcher("displayRecipe.jsp").forward(request, response);
		
	}
	
	public void parseIngredient(String ingr) {
			
		
			
		
	}
	
}
