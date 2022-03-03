<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring Boot Application with JSP</title>
        <script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#flag").html("Hello World !! (display due to jQuery)");
            });
        </script>
    </head>
    <body>
        <div>Hello, Spring Boot App</div>
        <div id="flag"></div>
    </body>
</html>

