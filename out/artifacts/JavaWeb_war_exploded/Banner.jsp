  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        int rows = 10;
        for (int i = 1; i <= rows; i++) {
            //输出新兴的空格
            int blank =10 -i;
            for(int k=0;k<blank;k++){
                out.print("&nbsp");
            }
            for (int j = 1; j <= i; j++) {
                out.print("* ");
            }
            out.print("<br>");
        }
    %>
</body>
</html>
