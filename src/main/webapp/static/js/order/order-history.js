import Pagination from '../pagination.js';

const paginationHook = document.getElementById('pagination');
const pagination = new Pagination(paginationHook, refreshOrders);
const ordersHistory = document.getElementById("order-history");
const templateString = $("#orders-template").html();

function showOrders(orders) {
    ordersHistory.innerHTML = "";
    let html = Mustache.render(templateString, { orders });

    ordersHistory.insertAdjacentHTML("beforeend", html);
}

async function refreshOrders() {
    const search = new URLSearchParams(location.search);
    const page = +search.get("page") || 1;

    const response = await fetch(`/order/api/history?page=${page - 1}`);

    if (response.status !== 200) {
        const msg = `Something went wrong ${response.status}`;
        alert(msg);
        return;
    }

    const json = await response.json();
    const {currentPage, totalPages, responseDTOs} = json;

    await showOrders(responseDTOs.map(order => ({
        ...order, orderDate: new Date(order.orderDate).toLocaleDateString()
    })));
    pagination.renderPagination(currentPage + 1, totalPages);
}

refreshOrders();

