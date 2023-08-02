<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/orders/order-details.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">
</head>
<body>
<%@ include file="../header.jsp"%>
<main class="container-lg my-3">
    <header class="mb-3 p-3 d-flex flex-column flex-md-row justify-content-md-between align-items-center text-bg-primary rounded">
        <a class="btn btn-primary" type="button" href="/order"><i class="fa-solid fa-arrow-left text-white fa-lg me-2"></i>Back</a>
        <h1 class="fs-2 fw-normal text-center">Order No. ${orderDetails.orderNumber}</h1>
        <span class="badge badge-order-date fs-6 fw-normal">
            <tags:localDate date="${orderDetails.orderDate}"/>
        </span>
    </header>

    <!-- Order Items Card -->
    <article class="card mb-3">
        <header class="card-header">
            <h2 class="fs-4 fw-normal">Order Items</h2>
        </header>

        <div class="px-3 py-2 d-none d-md-flex justify-content-end">Subtotal</div>

        <!--Order Items List-->
            <ul class="list-group list-group-flush">
                <!-- Order Item Template -->
                <c:forEach var="orderItem" items="${orderDetails.orderItemDTOS}">
                    <li class="list-group-item py-3">
                        <article class="container-fluid">
                            <div class="row">
                                <div class="col p-0 flex-grow-0 flex-shrink-0 order-item-thumbnail">
                                    <img src="${orderItem.productResponseDTO.thumbnail}" class="img-thumbnail w-100" alt="Sneakers">
                                </div>
                                <div class="col fs-5">${orderItem.productResponseDTO.title}</div>
                                <div class="col-12 col-md mt-3 my-md-0 p-0 text-end">
                                    <span>RON ${orderItem.price} x</span>
                                    <span class="fs-6 fw-normal py-2 badge text-bg-secondary">
                                        <c:choose>
                                            <c:when test="${orderItem.quantity == '1'}">
                                            ${orderItem.quantity} item
                                            </c:when>
                                            <c:otherwise>
                                                ${orderItem.quantity} items
                                            </c:otherwise>
                                        </c:choose>
                                    </span>
                                </div>
                                <div class="col-12 col-md p-0 pt-1 text-end">
                                    <span class="d-md-none">Subtotal:&nbsp</span>
                                    <span><c:out value="${orderItem.price * orderItem.quantity}"/> RON</span>
                                </div>
                            </div>
                        </article>
                    </li>
                </c:forEach>
            </ul>

        <div class="card-footer d-flex justify-content-end fs-5">Total: ${orderDetails.totalSum} RON</div>
    </article>

    <!-- Shipping and Billing Details -->
    <div class="container-lg px-0">
        <div class="row">

            <div class="col-12 col-lg-6  mb-3 mb-lg-0">
                <!--Shipping Details Card -->
                <article class="card">
                    <header class="card-header fs-4">Shipping Details</header>
                    <div class="card-body">
                        <table class="table table-borderless">
                            <tbody>
                                <tr>
                                    <th scope="row">First Name</th>
                                    <td>${orderDetails.orderUserDetailsDTO.firstName}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Last Name</th>
                                    <td>${orderDetails.orderUserDetailsDTO.lastName}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Email</th>
                                    <td>${orderDetails.userProfileResponseDTO.email}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Phone Number</th>
                                    <td>${orderDetails.orderUserDetailsDTO.phoneNumber}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Address</th>
                                    <td>
                                        ${orderDetails.shippingAddress}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </article>
            </div>

            <div class="col-12 col-lg-6">
                <!-- Billing Details Card -->
                <article class="card">
                    <header class="card-header fs-4">Billing Details</header>
                    <div class="card-body">
                        <table class="table table-borderless">
                            <tbody>
                                <tr>
                                    <th scope="row">First Name</th>
                                    <td>${orderDetails.orderUserDetailsDTO.firstName}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Last Name</th>
                                    <td>${orderDetails.orderUserDetailsDTO.lastName}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Email</th>
                                    <td>${orderDetails.userProfileResponseDTO.email}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Phone Number</th>
                                    <td>${orderDetails.orderUserDetailsDTO.phoneNumber}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Address</th>
                                    <td>
                                        ${orderDetails.billingAddress}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </article>
            </div>

        </div>
    </div>
</main>
</body>

<script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
</html>