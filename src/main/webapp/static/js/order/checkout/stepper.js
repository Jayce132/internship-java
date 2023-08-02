const page = location.pathname.split('/').at(-1);

const fillOrderDetailsStep = document.getElementById("fill-order-details-step");
const orderSummaryStep = document.getElementById("order-summary-step");
const orderPlacedStep = document.getElementById("order-placed-step");

const stepLinkFirst = document.getElementById("step-link-first");
const stepLinkSecond = document.getElementById("step-link-second");

if(page !== "fill-order-details") {
    fillOrderDetailsStep.querySelector(".step-progress i").classList.remove("fa-circle-dot");
    fillOrderDetailsStep.querySelector(".step-progress i").classList.add("fa-circle-check");

    stepLinkFirst.classList.remove("text-secondary");
    stepLinkFirst.classList.add("text-primary");

    orderSummaryStep.classList.remove("text-secondary");
    orderSummaryStep.classList.add("text-primary");

    if(page !== "order-summary") {
        orderSummaryStep.querySelector(".step-progress i").classList.remove("fa-circle-dot");
        orderSummaryStep.querySelector(".step-progress i").classList.add("fa-circle-check");

        stepLinkSecond.classList.remove("text-secondary");
        stepLinkSecond.classList.add("text-primary");

        orderPlacedStep.classList.remove("text-secondary");
        orderPlacedStep.classList.add("text-primary");
        orderPlacedStep.querySelector(".step-progress i").classList.remove("fa-circle-dot");
        orderPlacedStep.querySelector(".step-progress i").classList.add("fa-circle-check");
    }
}

