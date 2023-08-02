<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Order Placed</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>

    <!-- Fontawesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css" />
</head>
<body>

<%@ include file="../../header.jsp" %>

<div class="container-xl">
    <%@ include file="stepper.jsp" %>
    <div>
        <h1 class="mb-3"> Your order has been registered! </h1>
        <h2> Order number: ${order.orderNumber} </h2>
    </div>
</div>
