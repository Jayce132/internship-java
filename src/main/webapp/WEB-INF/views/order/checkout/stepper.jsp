<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/orders/checkout/stepper.css" />

<ul class="stepper d-none d-sm-flex w-100 p-0 mb-5 pe-none list-unstyled">
    <li class="step d-flex flex-column align-items-center px-3 justify-content-between text-primary" id="fill-order-details-step">
        <div class="step-progress fs-4">
            <i class="fa-regular fa-circle-dot"></i>
        </div>
        <div class="step-icon py-2 fs-2">
            <i class="fa-solid fa-pen-to-square"></i>
        </div>
        <div class="step-text">
            <p>Fill Order Details</p>
        </div>
    </li>

    <li class="step-link text-secondary" id="step-link-first"></li>

    <li class="step d-flex flex-column align-items-center px-3 text-secondary" id="order-summary-step">
        <div class="step-progress fs-4">
            <i class="fa-regular fa-circle-dot"></i>
        </div>
        <div class="step-icon py-2 fs-2">
            <i class="fa-brands fa-readme"></i>
        </div>
        <div class="step-text">
            <p>Order Summary</p>
        </div>
    </li>

    <li class="step-link text-secondary" id="step-link-second"></li>

    <li class="step d-flex flex-column align-items-center px-3 text-secondary" id="order-placed-step">
        <div class="step-progress fs-4">
            <i class="fa-regular fa-circle-dot"></i>
        </div>
        <div class="step-icon py-2 fs-2">
            <i class="fa-solid fa-truck"></i>
        </div>
        <div class="step-text">
            <p>Order Placed</p>
        </div>
    </li>
</ul>

<script src="${pageContext.request.contextPath}/static/js/order/checkout/stepper.js"></script>
