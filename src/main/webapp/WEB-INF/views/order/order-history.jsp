<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orders</title>

    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">
</head>
<body>

<%@ include file="../header.jsp"%>

<div class="container">
    <div class="row">

        <%@ include file="../sidebar.jsp" %>

        <main class="col d-flex flex-column">
            <header>
                <h1 class=" fw-normal fs-3">Orders</h1>
            </header>

            <hr/>

            <section>
                <ul class="list-unstyled" id="order-history">
                    <!-- Order list item template -->
                </ul>
            </section>

            <hr/>

            <!-- Pagination hook -->
            <nav><ul class="pagination justify-content-center" id="pagination"></ul></nav>
        </main>
    </div>
</div>
<%@include file="order-history.mustache"%>
<script src="${pageContext.request.contextPath}/static/js/mustache.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.6.0.min.js"></script>
<script type="module" src="${pageContext.request.contextPath}/static/js/order/order-history.js"></script>
</body>
</html>