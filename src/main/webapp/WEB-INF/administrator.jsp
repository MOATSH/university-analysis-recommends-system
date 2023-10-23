<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="/UARS/">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="D_bg.css">
    <title>UARS-Manager</title>
</head>
<body>
    <div class="centerContainer">
        <a href="userServlet?action=managerUser">
            <div class="left">
                <div class="logo">
                    <img src="img/用户管理.png" alt="">
                </div>
                <h2>
                    用户管理
                </h2>
            </div>
        </a> 

        <a href="userServlet?action=managerUniv">
            <div class="right">
                <div class="logo">
                    <img src="img/学校管理.png" alt="">
                </div>
                <h2>
                    院校管理
                </h2>
            </div>
        </a>

    </div>
</body>
</html>