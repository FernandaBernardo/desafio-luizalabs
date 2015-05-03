<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VRaptor Blank Project</title>
</head>
<body>
	<c:forEach var="node" items="${nodes}">
		<p>Id: ${node.personId}</p>
		<p>Name: ${node.name}</p>
		<p>Email: ${node.email}</p>
		<br>
	</c:forEach>
</body>
</html>