<%@ page import = "guessnumber.GuessNumberServlet.Result" %>
<html>
<body>
	<h1>Guess a Number</h1>
<%
Result result = (Result)request.getAttribute("result");

if(result.equals(Result.WON)) {%>
	Gagné !
<% } else if(result.equals(Result.TOO_HIGH)){ %>
	Trop haut !
<% } else if(result.equals(Result.TOO_LOW)){ %>
	Trop bas !
<%} %>
	<form method="get">
		<input name="guess" /><input type="submit" />
	</form>


</body>


</html>