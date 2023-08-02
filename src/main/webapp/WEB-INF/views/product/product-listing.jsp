<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Products</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/products/product-listing.css">
</head>
<body>

<%@ include file="../header.jsp" %>
<%@ include file="../cart/update-cart-overlay.jsp" %>

<main class="container-xl d-flex flex-column">
    <header>
        <!-- Search message hook -->
        <h2 class="fs-4 mb-3 d-none" id="search-message-hook"></h2>
    </header>

<section class="d-flex justify-content-center">
    <!-- Product cards hook -->
    <ul class="list-unstyled product-grid d-flex flex-wrap justify-content-start" id="product-cards-hook"></ul>
</section>

<hr/>

<!-- Pagination hook -->
<nav>
    <ul class="pagination justify-content-center" id="pagination-hook"></ul>
</nav>
</main>

<script>
    async function addToCart(productId) {
        document.getElementById("update-cart-overlay").classList.remove("d-none");

        const response = await fetch("/cart/add", {
            method: "POST",
            body: JSON.stringify({ productId, quantity: 1 }),
            headers: { "content-type": "application/json" }
        });

        if(!response.ok) {
            alert("Couldn't update product quantity!");
        }

        location.reload();
    }
</script>

<%@ include file="product-card.mustache" %>
<script src="${pageContext.request.contextPath}/static/js/mustache.min.js"></script>

<script type="module" src="${pageContext.request.contextPath}/static/js/product/product-listing.js"></script>
</body>
</html>
