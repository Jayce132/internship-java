<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Order Details</title>

    <!-- Boostrap -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>

    <!-- Fontawesome -->
    <link href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css" rel="stylesheet" />

    <!-- Custom Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css" />
</head>

<body>
<%@include file="../../header.jsp" %>

<div class="container-xl">
    <div class="d-sm-none mb-4">
        <h1 class="fs-3">Order Details</h1>
        <hr />
    </div>

    <%@ include file="stepper.jsp" %>

    <!-- Order details form -->
    <form id="order-details-form" novalidate>
        <div class="row mb-0 mb-md-3">
            <!-- First name input -->
            <div class="cold-12 mb-3 col-md-6 mb-md-0">
                <label for="first-name-input" class="form-label label-required">First Name</label>
                <input type="text" class="form-control" id="first-name-input" name="firstName" required minlength="3" placeholder="Jane" value="${user.firstName}" />
                <!--Error hook -->
                <div class="invalid-feedback" id="first-name-validation-error-hook"></div>
            </div>

            <!-- Last name input -->
            <div class="cold-12 mb-3 col-md-6 mb-md-0">
                <label for="last-name-input" class="form-label label-required">Last Name</label>
                <input type="text" class="form-control" id="last-name-input" name="lastName" required minlength="3" placeholder="Doe" value="${user.lastName}" />
                <!--Error hook -->
                <div class="invalid-feedback" id="last-name-validation-error-hook"></div>
            </div>
        </div>

        <div class="row mb-0 mb-md-3">
            <!-- Email input -->
            <div class="cold-12 mb-3 col-md-6 mb-md-0">
                <label for="email-input" class="form-label label-required">Email</label>
                <input type="email" class="form-control" id="email-input" name="email" required disabled placeholder="janedoe@example.com" value="${user.email}" />
                <!--Error hook -->
                <div class="invalid-feedback" id="email-validation-error-hook"></div>
            </div>

            <!-- Phone input -->
            <div class="cold-12 mb-3 col-md-6 mb-md-0">
                <label for="phone-number-input" class="form-label label-required">Phone</label>
                <input type="tel" class="form-control" id="phone-number-input" name="phoneNumber" required placeholder="+40712345678" value="${user.phoneNumber}" />
                <!--Error hook -->
                <div class="invalid-feedback" id="phone-number-validation-error-hook"></div>
            </div>
        </div>

        <!-- Billing Address input -->
        <div class="mb-3">
            <label for="billing-address-input" class="form-label label-required">Billing Address</label>
            <input
                    type="text"
                    class="form-control"
                    id="billing-address-input"
                    name="billingAddress"
                    required
                    placeholder="County Gorj, City Motru, Str. Nowhere, No. 42, Building A7, Apt. 1408"
                    value="${order.billingAddress != null ? order.billingAddress : user.address }"
            />
            <!--Error hook -->
            <div class="invalid-feedback" id="billing-address-validation-error-hook"></div>
        </div>

        <!-- Shipping Address input -->
        <div class="mb-3">
            <div class="d-flex justify-content-between">
                <label for="shipping-address-input" class="form-label label-required">Shipping Address</label>
                <div>
                    <input type="checkbox" class="form-check-input" id="same-as-billing-address-check" ${order.billingAddress == order.shippingAddress ? 'checked' : ''} />
                    <label for="same-as-billing-address-check" class="form-check-label">Same as the billing address</label>
                </div>
            </div>
            <input
                    type="text"
                    class="form-control"
                    id="shipping-address-input"
                    name="shippingAddress"
                    required
                    ${order.billingAddress == order.shippingAddress ? 'disabled' : ''}
                    placeholder="County Gorj, City Motru, Str. Nowhere, No. 42, Building A7, Apt. 1408"
                    value="${order.shippingAddress != null ? order.shippingAddress : user.address }"
            />
            <!--Error hook -->
            <div class="invalid-feedback" id="shipping-address-validation-error-hook"></div>
        </div>

        <!-- Accept terms and conditions check -->
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="accepted-terms-and-conditions-check" name="acceptedTermsAndConditions" required autocomplete="off" />
            <label class="form-check-label" for="accepted-terms-and-conditions-check">Accept&nbsp;<a class="text-decoration-none" href="#">Terms and Conditions</a></label>
            <div class="invalid-feedback" id="accepted-terms-and-conditions-validation-error-hook"></div>
        </div>

        <!-- Form buttons -->
        <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary mb-3" id="next-button" disabled form="order-details-form">Next</button>
        </div>
    </form>
</div>
</body>

<script>
    const orderBillingAddress = "${order.billingAddress}";
    const orderShippingAddress = "${order.shippingAddress}";
</script>

<script src="${pageContext.request.contextPath}/static/js/order/checkout/fill-order-details.js"></script>
</html>
