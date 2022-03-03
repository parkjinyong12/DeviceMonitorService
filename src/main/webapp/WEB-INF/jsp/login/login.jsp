<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <meta name="description" content="">
        <meta name="author" content="">

        <title>Starter Template for Bootstrap</title>

        <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
        <link href="/css/custom/signin.css" rel="stylesheet">
    </head>
    <body>
        <form class="form-signin" action="/login_process" method="post">
            <h1 class="h3 mb-3 font-weight-normal">디바이스 모니터링</h1>
            <label for="username" class="sr-only">User name</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="User name" required autofocus>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
            <!-- <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div> -->
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                printMessage();
            });
            function printMessage() {
                var errorCode = "${errorCode}";
                if(errorCode != "") {
                    if(errorCode == "1004") {
                        alert("아이디 또는 비밀번호가 잘못되었습니다.")
                    } else {
                        alert("알 수 없는 에러가 발생하였습니다.")
                    }
                }
            }
        </script>
    </body>
</html>