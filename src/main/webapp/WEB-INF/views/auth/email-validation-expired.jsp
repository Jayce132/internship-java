<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>

    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/c5b8468187.js" crossorigin="anonymous"></script>
</head>

<body>
<main class="d-flex flex-column justify-content-center align-items-center vw-100 vh-100 text-center">
    <p class="display-6 mb-5"> The activation link has expired. Please register again <a href="${pageContext.request.contextPath}/register" class="text-decoration-none">here</a>.</p>
    <i class="fa-solid fa-hourglass-end" style="font-size: 25vh;"></i>
</main>
</body>
</html>