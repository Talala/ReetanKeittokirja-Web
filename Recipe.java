package klo;


import java.util.Vector;
public class Recipe {

	Vector<Ingredient> mIngredients;
	String mName;
	String mInstructions;
	String mType;
	
	public Recipe( Vector<Ingredient> ingredients, String name, String instructions, String t) {
		
		mIngredients = ingredients;
		mName = name;
		mInstructions = instructions;
		mType = t;
		
		
	}
	
	public Recipe() {
		
	}
	
	public String getName() {
		
		return mName;
	}
	
	public String getType() {
		
		return mType;
	}
	
	public void setType( String type) {
		
		mType = type;
	}
	
	
	public Vector<Ingredient> getIngredients() {
		
		return mIngredients;
	}
	
	public void setIngredients(Vector<Ingredient> v) {
		
		mIngredients = v;
	}
	
	public Ingredient getIngredient (int index) {
		
		return mIngredients.elementAt(index);
		
	}
	
	public void removeIngredient (int index) {
		
		mIngredients.remove(index);
	}
	
	public void setIngredient(Ingredient ingr) {
		mIngredients.add(ingr);
		
	}
	
	public void setRecipename(String n) {
		
		mName = n;
	}
	
	public String getInstructions() {
		
		return mInstructions;
	}
	
	public void setInstructions(String ins) {
		
		mInstructions = ins;
	}
	
	
}
