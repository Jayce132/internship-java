const searchForm = document.getElementById("search-form");
const searchInput = document.getElementById("search-input");

searchForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const searchTerms = searchInput.value.split(" ").join(",");

    window.location.href = `/products/product-listing?searchTerms=${searchTerms}`;
});