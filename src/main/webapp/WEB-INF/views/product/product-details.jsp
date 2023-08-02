<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/products/product-details.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">
    <title>Product</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@ include file="../cart/update-cart-overlay.jsp" %>
<main class="container-fluid flex-fill product-page">
    <hr />
    <article class="container">
        <div class="row align-items-center">
            <div class="col-md-12">
                <div class="d-flex flex-wrap flex-md-nowrap justify-content-center align-items-start">
                    <!-- Product Image -->
                    <div class="d-flex justify-content-center align-items-center mb-2">
                        <a href="#">
                            <img class="img-thumbnail img-fluid" style="max-width: 650px !important"
                                 src="${productDetails.thumbnail}" alt="product-details-image" />
                        </a>
                    </div>
                    <!-- Product Details -->
                    <div class="flex-fill ps-4">
                        <!-- Product Title -->
                        <h3 class="fs-1 fw-bold">${productDetails.title}</h3>
                        <!-- Product Price -->
                        <div class="d-flex align-items-center flex-wrap justify-content-start">
                            <div class="d-flex my-4">
                                <span class="fs-4 fw-normal"><b>Price</b> : &nbsp; ${productDetails.price} RON</span>
                            </div>
                        </div>
                        <!-- Product Stock Info -->
                        <div class="in-stock">
                            <span class="fs-4 fw-normal
                                <c:choose>
                                    <c:when test="${productDetails.stockStatus == 'In stock'}">text-success</c:when>
                                    <c:when test="${productDetails.stockStatus == 'Limited stock'}">text-warning</c:when>
                                    <c:when test="${productDetails.stockStatus == 'Out of stock'}">text-danger</c:when>
                                    <c:otherwise></c:otherwise>
                                 </c:choose>">
                                ${productDetails.stockStatus}
                            </span>
                        </div>
                        <!-- Product Insert Quantity -->
                        <div class="d-flex flex-grow-1 my-4">
                            <!-- Add to cart button and form -->
                            <form class="d-flex" action="/cart/add" method="POST" id="orderQuantity">
                                <input type="hidden" name="productId" value="${productDetails.id}" id="productId" />
                                <div class="input-group mb-1" style="width: 9rem;">
                                    <button class="btn btn-outline-secondary" type="button" id="decrement-btn">-</button>
                                    <input type="number" class="form-control text-center" value="1" id="quantity" readonly />
                                    <button class="btn btn-outline-secondary" type="button" id="increment-btn">+</button>
                                </div>
                            </form>
                            <button form="orderQuantity" type="submit" class="btn btn-outline-primary ms-4 h-25" style="width: 150px;"
                            ${productDetails.stockStatus == 'Out of stock' ? 'disabled="disabled"' : ''}>Add To Cart</button>
                        </div>
                        <!-- Product Description -->
                        <div class="my-5">
                            <h3 class="fs-3 fw-bold">Description</h3>
                            <p>${productDetails.description}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>
    <hr />
</main>

<script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/product/product-details.js"></script>

</body>
</html>
