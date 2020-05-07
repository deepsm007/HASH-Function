<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project1Task1</title>
</head>
<body>
	<h1>Hash Function</h1>

	<table>
		<div id="result">
			<form name="hash" action="result" method="POST" target="resultFrame">

				<tr>Enter a string to generate hashes:
				</tr>
				<tr>
					<input type="text" name="input" required />
					<br />
				</tr>

				<tr>
					<button type="submit" class="button" value=index>Submit</button>
				</tr>

			</form>
			<tr>
				<pre>${requestScope.output}</pre>
			</tr>
		</div>
	</table>

</body>
</html>