<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session="true"
    import="org.w3c.dom.NodeList"
	import="org.w3c.dom.Node"
    import="java.util.Vector"
    import="java.util.ArrayList"
    import="klo.*"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css"> 
html,body {
background-color:#e5eecc;
}

fieldsetBasic{
  border: 1px solid rgb(255,232,57);
  float:left;
  width: 20%;
  margin: 0 auto;
  text-align: left; 
}




</style>


    <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script>
    /*Tätä Jquery-skriptiä käytetään poistamaan käytöstä poista- ja esitä painikkeet jos aineslista on tyhjä*/
    
   /*  $(document).ready(function() {
    	$("#addingrid").prop("disabled", true);
    	$("#removeingrid").prop("disabled", true);
    	checkIngrList();
        }); */
    
    $( "#selectingredient" )
	  .change(checkIngrList());
    
    function checkIngrList() {
    	if( $("#selectingredient option").length > 0 ) {
    		$("#addingrid").prop("disabled", false);
        	$("#removeingrid").prop("disabled", false); 
        if( $("#selectingredient").length == 0 ) {
    		$("#addingrid").prop("disabled", true);
        	$("#removeingrid").prop("disabled", true);      	
        }    		
    }
 	
   }
    function setIngredientData() {
    	var select = document.getElementById("selectingredient");
       	var option = document.createElement("option");
    	var ingrentry = window.prompt("Anna ainesosan tiedot muodossa; nimi määrä yksikkö (esim; marjoja 2 dl)","");
    	if (ingrentry.length > 0 && ingrentry.indexOf(' ') > 0) {
    	option.text = ingrentry;
    	select.add(option);
    	}
    	
    }
    
    function removeIngredient() {
    	var select = document.getElementById("selectingredient");
       	var remove=confirm("Haluatko poistaa aineksen ?");
       	if (remove==true)		
       		select.remove(select.selectedIndex);
	
    }
    
    function setIngr() {
    	
    	  var select = document.getElementById("selectingredient");
    	  for (var i=0; i<select.options.length; i++) {
    	    select.options[i].selected = true;
    	  }
    	  
    	  $("#recipeeditID").submit();
    	  
    }
    
    	

    </script>






<title>Insert title here</title>

<body>

<!-- tämän kaavakkeen EditRecipeServlet muokkaa reseptiä jota katsotaan parhaillaan. -->
<form id="recipeeditID" method="post" action="EditRecipeServlet"> 
<fieldset>
<div id="recipeBasics" class="fieldsetBasic">
<p>Reseptin tiedot</p>
<p>Nimi; ${Recipe.getName()}</p>
<p>Tyyppi; ${Recipe.getType()}</p>

</div>
</fieldset>
<fieldset>
<div id="recipeIngredients" class="fieldingr">
<select size="10" name="ingredientlisting" id="selectingredient" multiple >
  
	<%
	if(request.getAttribute("Recipe") != null) {
	
	Recipe recipe = (Recipe) request.getAttribute("Recipe");
	Vector<Ingredient> ingr_vect = new Vector<Ingredient>();
	ingr_vect = recipe.getIngredients();
	String n = recipe.getName();
	RecipeBookCompiler rbc = (RecipeBookCompiler) session.getAttribute("rbc");

	  for (int i=0; i<ingr_vect.size(); i++) {   	
   	 String entry = ingr_vect.elementAt(i).showRecipeEntry(); //list.item(i).getTextContent();
        %>                   
           <option value="<%=entry%>"> <% out.println(entry); %> </option>                               
       <%               
       
          
   }  
  }    

  %>
</select>

<input type="button" value="Uusi ainesosa" id="addingrid" onclick="setIngredientData();">
<input type="button" value="Poista ainesosa" id="removeingrid" onclick="removeIngredient();">
</div>
</fieldset>
<fieldset id="recipeinstr">
<div id="recipeInstructions" class="fieldsetInstr">
<textarea name="instr" id="instructionsid" rows="20" cols="50">
${Recipe.getInstructions()}
</textarea>

<input type="button" value="Tallenna resepti" onclick="setIngr();">
</div>
</fieldset>
</form>


</body>
</head>
</html>