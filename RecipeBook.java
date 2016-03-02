package klo;

import java.util.Vector;

public class RecipeBook {
	
	String mName;
	String mFileName;
	Vector<Recipe> mRecipes;
	
	public RecipeBook(String name, String filename, Vector<Recipe> recipes) {
		mName = name;
		mFileName = filename;
		mRecipes = recipes;
		
	}
	
	public String getFilename() {return mFileName;}
	public String getBookname() {return mName;}
	public Vector<Recipe> getRecipes(){return mRecipes;}
	
	public void setFilename(String name) {mFileName = name; }
	public void setBookname(String name) {mName = name;}
	public void setRecipes(Vector<Recipe> recipes) {mRecipes = recipes;}
	
}
