
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session="true"

    import="java.util.Vector"
    import="java.io.*"
    import="javax.servlet.http.HttpServletResponse"
	import="org.w3c.dom.NodeList"
	import="org.w3c.dom.Node"
	import="klo.*"
	
	%>
	

 
<html>
<head>
<style type="text/css"> 
html, body {
                width: 100%;
                height: 100%;
                margin: 0;
                padding: 0;
                
                background-color:#e5eecc;
	
            }

.listingPanel
{
height:500px;
width:30%

}



</style>


    <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script>
    /*Tätä Jquery-skriptiä käytetään poistamaan käytöstä poista- ja esitä painikkeet jos reseptilista on tyhjä*/
    $(document).ready(function() {
    	$("#delete").prop("disabled", true);
    	$("#display").prop("disabled", true);
    	checkRecipeList();
        });
    
    $( "#selectrecipe" )
	  .change(checkRecipeList());
    
    function checkRecipeList() {
    	if( $("#selectrecipe option").length > 0 ) {
    		$("#delete").prop("disabled", false);
        	$("#display").prop("disabled", false); 
        if( $("#selectrecipe").length == 0 ) {
    		$("#delete").prop("disabled", true);
        	$("#display").prop("disabled", true);      	
        }    		
    }
 	
   }
    
    /*Tätä käytetään määrittämään että mitä painiketta painettiin reseptilistan alla ja sen mukaan lomakkeen
    kohde ja toiminta määrätään*/
    function submitTo(act, target)
    {      
        $("#1").prop("action",act);
        $("#1").prop("target",target);
        $("#1").submit();
    }
    
    function newRecipe() {
    	var name = window.prompt("Mikä on uuden reseptin nimi ? ","");
    	var type = window.prompt("Mikä on reseptin " + name + " tyyppi ?");
    	document.getElementById("newrecname").value = name;
    	document.getElementById("newrectype").value = type;
    	("#1").prop("action","MakeNewRecipe");
    	("#1").submit();
    }

    </script>
    
<body>

 <div id="result">
 			<h2>Reseptit</h2>
            <h3>${message}</h3>
            
            
            <form name="listing" id="1" action="" target="" method="POST" >
             <select size="5" name="recipelisting" id="selectrecipe" >
            <%      
            String n = "";
            NodeList list;
            Recipe recipe = null;
            String mail = "";
            String name = "";
           
            RecipeBookCompiler rbc = new RecipeBookCompiler();
            
           if ( request.getSession().getAttribute("rbc") != null) {
        	   rbc = (RecipeBookCompiler) request.getSession().getAttribute("rbc");
        	   
        	  
           	list = rbc.getDoc().getElementsByTagName("Recipe");
           	
                    
             for (int i = 0; i<list.getLength(); i++) { 
            	
            	 name = list.item(i).getFirstChild().getTextContent();
                 %>                   
                    <option value="<%=name%>"> <% out.println(name); %> </option>                               
                <%               
                name = "";
                
                
            }  
           }    
           
              %>  
              </select><br>  
              
			
              <input type=button id="delete" name="option" value="Poista" onclick="submitTo('HandleRecipeSelection', 'listingFrame');"/>
              <input type=button id="display" name="option" value="Esitä" onclick="submitTo('DisplayRecipeServlet', 'displayFrame');"/> 
              <br><br>
              
			  <input id="addnew" type="button" value="Uusi Resepti" onclick="newRecipe()">
			  <input id="newrecname" type="hidden" value="">
			  <input id="newrectype" type="hidden" value="">
</form> 

             
              
              

                        
        </div>
</body>
</head>

</html>