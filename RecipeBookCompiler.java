package klo;


/*
 * RecipeBookCompiler
 * This class holds File object of the current recipebook opened by user and Document object that represents the XML
 * structure of the recipebook. 
 * This class is also used to manipulate the recipebook and its recipies by altering its XML structure and to search particular XML elements
 * such as particular recipies.
 * 
 * 
 * */

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class RecipeBookCompiler {
	
	
	String filename;
	String mXmlString;
	Document mDoc;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	
	Element rootElement;
	Element child;
	
	String root = "Recipebook";
	String bookName = "";
	String recipeElement = "Recipe";
	String recipeType = "Type";
	String name = "name";
	String ingredient = "Ingredient"; String ingredients = "Ingredients";
	
	/*Default Recipe*/
	Recipe default_Recipe;
	
	public Recipe makeDefaultRecipe() {
		
		Vector<Ingredient> list = new Vector<Ingredient>();

		list.add(new Ingredient("Jauheliha ", "400.0 ", "gr" ));
		list.add(new Ingredient("Soija ",  "0.25 ", "dl" ));
		list.add(new Ingredient("Lihaliemikuutio ",  "1 ", "kpl"));
		list.add(new Ingredient("Vehnajauho ", "1,5 ", "dl"));
		list.add(new Ingredient("Salsa ", "1 ", "dl"));
		list.add(new Ingredient("Ruokakerma ", "2 ", "dl"));
		list.add(new Ingredient("Vesi ", "2 ", "dl"));
		
		String instructions = "1. Ruskista jauhelihaa hieman. 2. Lis‰‰ vehn‰jauhot ja ruskista 5-8min jonka j‰lkeen" +
				" lis‰‰ lihaliemikuutio, mausteet ja salsa. Anna t‰m‰n ‰ssehty‰ ihan v‰h‰n aikaa ja sitten lis‰‰" +
				" kerma. Anna hautua korkeintaan 45min, lis‰‰ vett‰ tarvittaessa jos kuivuu. Sitte t‰ss‰" +
				"kerron muutaki asiaa ja kokeilen ett‰ mahtuuko t‰m‰ teksti ikkunaan, eli fragmenttiin jossa esit‰n" +
				"dataa.. Jos ei meinaa mahtua, t‰h‰n pit‰isi viereen tulla Scrollbar jolla esitet‰‰n tavarat.";
		default_Recipe = new Recipe(list,"Jauhelihakastike", instructions, "P‰‰ruoka" );
		return default_Recipe;
		
	}
	
	public Recipe getDefaultRecipe() {
		
		return default_Recipe;
	}
	
	public Document getDoc() {
		
		return mDoc;
	}
	
	
	
	 public RecipeBookCompiler() throws SAXException, IOException {
		 
		 dbf = DocumentBuilderFactory.newInstance();
		 
		try {
			db = dbf.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} 


	 }
	 
	 public void attachRecipebook(File f) throws SAXException, IOException {
		 
		 try {
			 
		 mDoc = db.parse(f);
		 
		 
		 } catch(IOException e) {}
		 
	 }

	 
	 public void detachRecipeBook() {
		 
		 mDoc = null;
	 }
	 
	 /*returns an empty Recipebook as DOM Document. For demonstration, a minced meat sauce recipe is added to book by default.*/
	 public Document compileEmptyRB(String bname)
	 {
		 Element rootElement, child, recipeElement;
		 mDoc = db.newDocument();
		 // we name the book by the filename, without .xml
		 bookName = bname.substring(0, bname.length()-4);	 
		 rootElement = mDoc.createElement(root);
		 mDoc.appendChild(rootElement);
		 child = mDoc.createElement("name");
		 child.setTextContent(bookName);
		 rootElement.appendChild(child); 
		 
		 Recipe r = makeDefaultRecipe();
		 child = mDoc.createElement("Recipes");
		 rootElement.appendChild(child);
		 recipeElement = compileRecipeFromObject(r);
		 child.appendChild(recipeElement);
		 
		 
		 return mDoc;
	 }
	 
	 
	 
	 
	 public Element compileRecipeFromObject(Recipe recipe) {
		 
		 Element root, child, z, x;
		 
		 
		 root = mDoc.createElement(recipeElement);
		 child = mDoc.createElement("name"); 
		 child.setTextContent(recipe.getName().trim());
		 root.appendChild(child);
		 child = mDoc.createElement("type");
		 child.setTextContent(recipe.getType().trim());
		 root.appendChild(child);
		 Vector<Ingredient> ingredients = recipe.getIngredients();

		 Element ingrs = mDoc.createElement("Ingredients");
		 for(int i = 0; i<ingredients.size(); i++ ) {
			 
			 Ingredient in = ingredients.get(i);
			 
			 // Here we append each ingredient to ingrs.
			 z = mDoc.createElement("Ingredient");
			 
				 x = mDoc.createElement("name");
				 x.setTextContent(in.getName().trim());
				 z.appendChild(x);
			 
				 x = mDoc.createElement("volume");
				 x.setTextContent( in.getVolume().trim() );
				 z.appendChild(x);
			 
				 x = mDoc.createElement("unit");
				 x.setTextContent(in.getUnitType().trim());
				 z.appendChild(x);
				 
			 ingrs.appendChild(z);
			 
		 }
		 root.appendChild(ingrs);
		 child = mDoc.createElement("Instructions");
		 child.setTextContent(recipe.getInstructions());
		 root.appendChild(child);
		 
		 return root;
	 } 
	 
	 
	 public Element compileRecipeFromObjectData(String name, List<Ingredient> ingredients, String instructions, String type) {
		 
		 Element root, child, z, x;
		 
		 root = mDoc.createElement(recipeElement);
		 child = mDoc.createElement(name); 
		 	child.setTextContent(name);
		 	
		 child = mDoc.createElement(type);
		 child.setTextContent("Paaruoka");
		 root.appendChild(child);

		 Element ingrs = mDoc.createElement("Ingredients");
		 for(int i = 0; i<=ingredients.size(); i++ ) {
			 
			 Ingredient in = ingredients.get(i);
			 
			 // Here we append each ingredient to ingrs.
			 z = mDoc.createElement("Ingredient");
			 
				 x = mDoc.createElement("name");
				 x.setTextContent(in.getName());
				 z.appendChild(x);
			 
				 x = mDoc.createElement("volume");
				 x.setTextContent( in.getVolume() );
				 z.appendChild(x);
			 
				 x = mDoc.createElement("unit");
				 x.setTextContent(in.getUnitType());
				 z.appendChild(x);
				 
			 ingrs.appendChild(z);
			 
		 }
		 
		 child = mDoc.createElement(instructions);
		 root.appendChild(child);
		 
		 return root;
	 }
	 
	 
	 public Document getDocument() {
		 
		 return mDoc;
	 }
	 
	 public void addRecipe(Element recipe) {
		
		Node parent = mDoc.getElementsByTagName("Recipes").item(0);
		Element added = recipe;
		parent.appendChild(added);
		mDoc.normalize();
 
	 }
	 
	 /*This is used to replace a recipe, used when we change content of a recipe, for example*/
	 public void replaceRecipe(Element recipe) {
		 Node newNode = (Node) recipe;
		 Node parent = this.mDoc.getElementsByTagName("Recipes").item(0);
		 Node oldNode = null;
		 String nameofNewNode = recipe.getFirstChild().getTextContent();
		 NodeList recipes = this.mDoc.getElementsByTagName("Recipe");	//Nodelist of current recipies
		 
		 for (int i = 0; i<recipes.getLength(); i++) {
			 if ( recipes.item(i).getFirstChild().getTextContent().equals(nameofNewNode) )
				 oldNode = recipes.item(i);
		 }	 	
 
		 parent.removeChild(oldNode);
		 parent.appendChild(newNode);
		 
		 
		 this.mDoc.normalize();
		 
	 }
	 
	 
	 public void editAttribute(String root, String Attribute) {}
	 
	 public void deleteRecipe(String name) {
		 
		 Node recipes = mDoc.getElementsByTagName("Recipes").item(0);
		 NodeList list =  recipes.getChildNodes();
		 Element deleted = null;
		 
			for (int i = 0; i<list.getLength(); i++ ) {
				String s = list.item(i).getFirstChild().getTextContent();
				if ( s.equals(name)) {
					deleted = (Element)list.item(i);
				
				}
			}
		deleted.getParentNode().removeChild(deleted);

		mDoc.normalize();
		
		 //mDoc.removeChild(deleted);		 
		
	 }
	 
	 public NodeList getRecipiesNodeList() {
		 
		 return mDoc.getElementsByTagName("Recipe");
	 }
	 
	 public NodeList getRecipeIngredients(String recipeName) {
		 
		 Node recipe = mDoc.getElementsByTagName(recipeName).item(0);
		 Node temp = recipe.getChildNodes().item(2);
		 NodeList ingredients = temp.getChildNodes();
		 return ingredients;
		 
		 
	 }
	 
	 /*Used to get the searched recipe*/
	 public Node getRecipeNodebyName(String name) {
		 
		 Node n = null;
		 NodeList recipeList;
		 recipeList = mDoc.getElementsByTagName(name);
		 n = recipeList.item(0);			 
		 return n;
		 }
	 
	 
	 /*Returns sub-element, that is, Node presenting an attribute of a recipe by the name given and the attribute type*/
	 public Node getRecipeAttribute(String recipeName, String attributeName) {
		 
		 NodeList attributes = null;
		 Node attribute = null;
		 
		 
			NodeList recipes = mDoc.getElementsByTagName("Recipe");
			for (int i = 0; i<recipes.getLength(); i++ ) {
				if ( recipes.item(i).getTextContent().contains(recipeName)) {
					attributes = recipes.item(i).getChildNodes();
				}
			}
				for ( int z = 0; z<attributes.getLength(); z++ ) {
					
					String s = attributes.item(z).getNodeName();
					if ( s.contains(attributeName)) {
						 attribute =  attributes.item(z);
						}
						
					}
				
			
			
		 return attribute;
	 }
	 
	 /*Used to get a recipe with all of its children node, such as recipe type, ingredients etc.*/
	 public NodeList getRecipeNodeListbyName(String name) {
		 
		 NodeList n = null;

		 n = mDoc.getElementsByTagName(name);
		 
		return n;
	 }
	 
	 
	 
	 /**
	  * 
	  * @param e Element child is appended to
	  * @param n Name of the child element
	  */
	 
	 public void makeChild(Element e, String n) {
		 e.appendChild(mDoc.createElement(n));
		 
	 }
	 
	 
	 
	 
	 
	 }


