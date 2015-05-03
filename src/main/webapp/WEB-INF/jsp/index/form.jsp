<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VRaptor Blank Project</title>
</head>
<body>
	<form method="post" action="<c:url value='/add'/>">
    Id:
    <input type="text" name="costumer.personId"/><br/>
    Email:
    <input type="text" name="costumer.email"/><br/>
    Name:
    <input type="text" name="costumer.name"/><br/>

    <input type="submit" value="Save" />
</form>
</body>
</html>