<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Order Details</title>

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
    <div class="row">
        <div class="col-12 mb-4 col-lg-7 mb-lg-0">
            <header>
                <h2 class="fs-4">Delivery Information</h2>
                <hr />
            </header>
            <table class="table table-borderless">
                <tbody>
                <tr>
                    <th scope="row">First name</th>
                    <td>${order.userProfileResponseDTO.firstName}</td>
                </tr>
                <tr>
                    <th scope="row">Last name</th>
                    <td>${order.userProfileResponseDTO.lastName}</td>
                </tr>
                <tr>
                    <th scope="row">Email</th>
                    <td>${order.userProfileResponseDTO.email}</td>
                </tr>
                <tr>
                    <th scope="row">Phone number</th>
                    <td>${order.userProfileResponseDTO.phoneNumber}</td>
                </tr>
                <tr>
                    <th scope="row">Billing address</th>
                    <td>${order.billingAddress != null ? order.billingAddress : order.userProfileResponseDTO.address}</td>
                </tr>
                <tr>
                    <th scope="row">Shipping address</th>
                    <td>${order.shippingAddress != null ? order.shippingAddress : order.userProfileResponseDTO.address}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col">
            <header>
                <h2 class="fs-4">Items</h2>
                <hr />
            </header>
            <ul class="list-group list-group-flush">
                <c:forEach var="product" items="${order.orderItemDTOS}">
                    <li class="list-group-item py-3">
                        <article class="container-fluid">
                            <div class="row">
                                <div class="col p-0 flex-grow-0 flex-shrink-0" style="flex-basis: 150px">
                                    <img src="${product.productResponseDTO.thumbnail}" class="w-100" alt="${product.productResponseDTO.title}" />
                                </div>
                                <div class="col fs-5">${product.productResponseDTO.title}</div>
                                <div class="col-12 mt-3 p-0 text-end">
                                    <span>${product.price} RON x</span>
                                    <span class="fs-6 fw-normal py-2 badge text-bg-secondary">
                                        <c:choose>
                                           <c:when test="${product.quantity == '1'}">
                                             ${product.quantity} item
                                           </c:when>
                                           <c:otherwise>
                                             ${product.quantity} items
                                           </c:otherwise>
                                        </c:choose>
                                    </span>
                                </div>
                                <div class="col-12 p-0 pt-1 text-end">Subtotal: <c:out value="${product.price * product.quantity}"/> RON</div>
                            </div>
                        </article>
                    </li>
                </c:forEach>
                <li class="list-group-item fs-5 text-end">Total: ${order.totalSum} RON</li>
            </ul>
        </div>
    </div>

    <div class="d-flex justify-content-end mt-5">
        <a href="fill-order-details" class="btn btn-secondary mb-3 me-1" id="back-button">Back</a>
        <a href="/checkout/order-placed" class="btn btn-primary mb-3" id="next-button">Place order</a>
    </div>
</div>
</body>
</html>

