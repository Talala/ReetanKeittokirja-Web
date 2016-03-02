package klo;

public class Ingredient {
	
	
	String mVolume;
	String mName;
	String mUnittype;
	
	public Ingredient (String name, String volume, String unittype) {
		mVolume = volume;
		mName = name;
		mUnittype = unittype;
		
		
	}
	
	public String getVolume()
	{
		return mVolume;
	}
	
	public String getName() {
		
		return mName;
	}
	
	public String getUnitType() {
		
		return mUnittype;
	}
	
	public String showRecipeEntry() {
		
		return mName + " " + mVolume + " " + mUnittype;
	}

}
