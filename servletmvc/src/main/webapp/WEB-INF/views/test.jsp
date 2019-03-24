<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: 01
  Date: 2019/3/10
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    testinside ===> ${userName}
<p>----------------------------------------------------------------------------</p>


    <%
        Enumeration<String> names=request.getParameterNames();

        while(names.hasMoreElements()){
              String name=names.nextElement();
              String value=request.getParameter(name);
                System.out.println(name+"vvv"+value);
            %>
            <h2>keys:${requestScope.keySet()}</h2>
            <h2>values:${requestScope.values()}</h2>
    <%
        }

    %>

</body>
</html>
