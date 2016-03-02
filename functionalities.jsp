<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script>

$(document).ready(function() {

    });
    

    	

</script>
<head>
<style type="text/css"> 

html, body {
                width: 100%;
                height: 100%;
                margin: 0;
                padding: 0;
                background-color:#e5eecc;
            }
.funcs
{
height:30%;
width:20%
background-color:#e5eecc;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="funcs">
<form action="MakeNewBookServlet">
<button>Uusi Reseptikirja</button><br>
</form>
<form action="SaveandSendFile" method="get">
<input type="submit" value="Tuo Tietokoneelle"><br>
</form>
<form action="FileLoader" target=listingFrame id="loadrecipe"
 enctype="multipart/form-data" method="post">
<p>Lataa Reseptikirja</p>
<input type="file" id="filelocation" name="uploaded-filepath" accept="text/xml" size="6">
<input type="submit" id="loadbutton" value="Lataa">
</form>
</div>
</body>
</html>