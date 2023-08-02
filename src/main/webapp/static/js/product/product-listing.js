import Pagination from "../pagination.js";

const paginationHook = document.getElementById("pagination-hook");
const productCardTemplate = document.getElementById("product-card-template");
const productCardsHook = document.getElementById("product-cards-hook");
const searchMessageHook = document.getElementById("search-message-hook");

const paginationComponent = new Pagination(paginationHook, fetchProducts);

function renderProducts(products) {
    products.map(product => {
        if (product.stockStatus === 'In stock') {
            product.stockBootstrapClass = 'text-success'
        } else if (product.stockStatus === 'Limited stock') {
            product.stockBootstrapClass = 'text-warning'
        } else if (product.stockStatus === 'Out of stock') {
            product.stockBootstrapClass = 'text-danger'
            product.disabledAddToCartButton = 'disabled';
        }
        return product;
    });

    productCardsHook.innerHTML = ``;

    const productCards = Mustache.render(productCardTemplate.innerText, { products });

    productCardsHook.insertAdjacentHTML("beforeend", productCards);
}

function renderSearchMessage(searchTerms) {
    searchMessageHook.classList.remove("d-none");
    searchMessageHook.innerText = `Search results for "${searchTerms.split(',').join(' ')}"`;
}

function sanitizeSearchTerms(searchTerms) {
    return searchTerms
        .split(',')
        .map(st => st.replace(/[.,\/\\#!$%^&*;:{}=\-_`~()]*$/,""))
        .map(st => st.replace(/^[.,\/\\#!$%^&*;:{}=\-_`~()]*/,""))
        .filter(st => st !== "").join(',');
}

async function fetchProducts() {
    const search = new URLSearchParams(window.location.search);
    const page = +search.get("page");
    const searchTerms = search.get("searchTerms")

    const searchParams = new URLSearchParams();

    if(page) {
        searchParams.set("page", `${page - 1}`);
    }

    if(searchTerms) {
        searchParams.set("searchTerms", sanitizeSearchTerms(searchTerms));
        renderSearchMessage(searchTerms);
    }

    const response = await fetch(`/products?${searchParams.toString()}`);

    if (response.status !== 200) {
        alert("Failed to fetch the products!");
        return;
    }

    const { totalPages, responseDTOs } = await response.json();

    renderProducts(responseDTOs);
    paginationComponent.renderPagination(page || 1, totalPages);
}

fetchProducts();