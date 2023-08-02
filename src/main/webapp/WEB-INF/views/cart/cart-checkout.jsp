<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Cart</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />


    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">

</head>
<body>

<%@ include file="../header.jsp" %>

<div class="container">
    <main>
        <div class="row g-5">
            <div class="col-md-5 col-lg-4 order-md-last">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-primary">Your cart</span>
                    <span class="badge bg-primary rounded-pill">3</span>
                </h4>
                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                            <h6 class="my-0">Product name</h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">$12</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                            <h6 class="my-0">Second product</h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">$8</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                            <h6 class="my-0">Third item</h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">$5</span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between">
                        <span>Total (USD)</span>
                        <strong>$20</strong>
                    </li>
                </ul>

            </div>
            <div class="col-md-7 col-lg-8">
                <h4 class="mb-3">Shipping address</h4>
                <form class="needs-validation" novalidate action="/cart/checkout/success">
                    <div class="row g-3">


                        <div class="col-12">
                            <label for="address2" class="form-label">Address</label>
                            <input type="text" class="form-control" id="address2" placeholder="Street, Apartment">
                        </div>

                    </div>

                    <!--                    <hr class="my-4">-->

                    <!--                    <div class="form-check">-->
                    <!--                        <input type="checkbox" class="form-check-input" id="same-address">-->
                    <!--                        <label class="form-check-label" for="same-address">Shipping address is the same as my billing address</label>-->
                    <!--                    </div>-->

                    <!--                    <div class="form-check">-->
                    <!--                        <input type="checkbox" class="form-check-input" id="save-info">-->
                    <!--                        <label class="form-check-label" for="save-info">Save this information for next time</label>-->
                    <!--                    </div>-->

                    <hr class="my-4">

                    <h4 class="mb-3">Payment</h4>

                    <div class="my-3">
                        <div class="form-check">
                            <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required checked>
                            <label class="form-check-label" for="debit">Card</label>
                        </div>

                        <div class="form-check">
                            <input id="cash" name="paymentMethod" type="radio" class="form-check-input" required>
                            <label class="form-check-label" for="cash">Cash on Arrival</label>
                        </div>
                    </div>

                    <div class="row gy-3">
                        <div class="col-md-6">
                            <label for="cc-name" class="form-label">Name on card</label>
                            <input type="text" class="form-control" id="cc-name" placeholder="" required>
                            <small class="text-muted">Full name as displayed on card</small>
                            <div class="invalid-feedback">
                                Name on card is required
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label for="cc-number" class="form-label">Credit card number</label>
                            <input type="text" class="form-control" id="cc-number" placeholder="" required>
                            <div class="invalid-feedback">
                                Credit card number is required
                            </div>
                        </div>

                        <div class="col-md-3">
                            <label for="cc-expiration" class="form-label">Expiration</label>
                            <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                            <div class="invalid-feedback">
                                Expiration date required
                            </div>
                        </div>

                        <div class="col-md-3">
                            <label for="cc-cvv" class="form-label">CVV</label>
                            <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                            <div class="invalid-feedback">
                                Security code required
                            </div>
                        </div>
                    </div>

                    <hr class="my-4">

                    <button class="w-100 btn btn-primary btn-lg mb-5" type="submit">Finish Order</button>
                </form>
            </div>
        </div>
    </main>
</div>

<script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
</body>
</html>

