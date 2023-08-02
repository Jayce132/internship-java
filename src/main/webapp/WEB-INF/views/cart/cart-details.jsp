<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Cart</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/products/product-details.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">

</head>
<body>

<%@ include file="../header.jsp" %>
<%@ include file="../cart/update-cart-overlay.jsp" %>

<div class="modal fade" id="modal-cart-items-out-of-stock" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="modal-cart-items-out-of-stock" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                Your cart contains products that are out of stock, they will be removed in order to continue.
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-primary" href="/cart/details">Done</a>
            </div>
        </div>
    </div>
</div>

<section class="h-100">
    <div class="container h-100 ">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-10">

                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3 class="fw-normal mb-0 text-black">Shopping Cart</h3>
                </div>
                <c:if test="${cartDTO.cartItemDTOs.size()==0}">
                    <h3>The cart is empty :(</h3>
                </c:if>
                <c:if test="${cartDTO.cartItemDTOs.size()>0}">

                    <c:forEach var="cartItem" items="${cartDTO.cartItemDTOs}">

                        <div class="card rounded-3 mb-4">
                            <div class="card-body p-4">
                                <div class="row d-flex justify-content-between align-items-center">
                                    <div class="col-md-2 col-lg-2 col-xl-2">
                                        <img
                                                src="${cartItem.productResponseDTO.thumbnail}"
                                                class="img-fluid rounded-3" alt="Product image">
                                    </div>
                                    <div class="col-md-3 col-lg-3 col-xl-3">
                                        <p class="lead fw-normal mb-2">${cartItem.productResponseDTO.title}</p>
                                        <p>
                                            <span class="
                                                <c:choose>
                                                    <c:when test="${cartItem.productResponseDTO.stockStatus == 'In stock'}">text-success</c:when>
                                                    <c:when test="${cartItem.productResponseDTO.stockStatus == 'Limited stock'}">text-warning</c:when>
                                                    <c:when test="${cartItem.productResponseDTO.stockStatus == 'Out of stock'}">text-danger</c:when>
                                                <c:otherwise></c:otherwise>
                                                </c:choose>">
                                                ${cartItem.productResponseDTO.stockStatus}
                                            </span>
                                        </p>
                                        <p>RON ${cartItem.productResponseDTO.price}</p>
                                    </div>
                                    <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                        <button class="btn btn-link px-2 btn-decrement"
                                                onclick="stepDown.call(this)"
                                                <c:if test="${cartItem.quantity == 1}">disabled</c:if>
                                        >
                                            <i class="fas fa-minus"></i>
                                        </button>

                                        <input readonly id="form1" min="1" name="quantity" value=${cartItem.quantity} data-product-id="${cartItem.productResponseDTO.id}" type="number"
                                               class="form-control form-control-sm quantity-input pe-none text-center"/>

                                        <button class="btn btn-link px-2 btn-increment" onclick="stepUp.call(this)">
                                            <i class="fas fa-plus"></i>
                                        </button>
                                    </div>
                                    <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                        <h5 class="mb-0 pt-3 pt-md-0">RON ${cartItem.productResponseDTO.price * cartItem.quantity}</h5>
                                    </div>
                                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                        <a type="button" class="text-danger" onclick="deleteCartItem(${cartItem.productResponseDTO.id})"><i class="fas fa-trash fa-lg"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between mb-3">
                                <h5 class="text-uppercase">Total price</h5>
                                <h5>RON ${cartDTO.totalPrice}</h5>
                            </div>

                            <div class="row-cols-1 row-cols-md-3">
                            <button type="button" class="btn btn-primary btn-block btn-lg m-1" id="check-out-btn">Check Out</button>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/cart/cart-details.js"></script>

</body>
</html>

