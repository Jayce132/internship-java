/*
Target HTML structure
===============================================================================================
<!-- Don't render anything if there is only one page -->

<!-- Link to the previous page, disabled if the user is on the first page -->
<li class="page-item"><a class="page-link" href="#">&laquo;</a></li>

<!-- Link to the first page -->
<li class="page-item"><a class="page-link" href="#">1</a></li>

<!-- Display if difference between the active page and the first page is greater than 3 -->
<li class="page-item disabled"><a class="page-link" href="#">...</a></li>

<!-- Links to the previous two pages -->
<li class="page-item"><a class="page-link" href="#">3</a></li>
<li class="page-item"><a class="page-link" href="#">4</a></li>

<!-- Active page -->
<li class="page-item pe-none"><a class="page-link active" href="#">5</a></li>

<!-- Links to the next two pages-->
<li class="page-item"><a class="page-link" href="#">6</a></li>
<li class="page-item"><a class="page-link" href="#">7</a></li>

<!-- Display if difference between the last page and the active page is greater than 3 -->
<li class="page-item disabled"><a class="page-link" href="#">...</a></li>

<!-- Link to the last page -->
<li class="page-item"><a class="page-link" href="#">10</a></li>

<!-- Link to the next page, disabled if the user is on the last page -->
<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
*/

// Make sure there are no elements on your page having the "page-link" class
class Pagination {
    // An HTML list element possessing the "pagination" bootstrap class. This is where the pagination buttons will be redered
    #hook;
    // A function that will be called whenever the page changes
    #callback;

    constructor(hook, callback) {
        this.#hook = hook;
        this.#callback = callback;

        document.addEventListener('click', (event) => {
            if(event.target.classList.contains("page-link")) return this.#paginatorBtnClickEventHandler(event);
        });
    }

    renderPagination(currentPage, numberOfPages) {
        this.#hook.innerHTML = '';

        if(numberOfPages <= 1) return;

        this.#hook.insertAdjacentHTML("beforeend", `<li class="page-item ${currentPage === 1 ? "disabled" : ""}"><a role="button" class="page-link">&laquo;</a></li>`);
        this.#hook.insertAdjacentHTML("beforeend", `<li class="page-item ${currentPage === 1 ? "active" : ""}"><a role="button" class="page-link">1</a></li>`)

        if(currentPage >= 5)
            this.#hook.insertAdjacentHTML("beforeend", `<li class="page-item disabled"><a role="button" class="page-link">...</a></li>`);

        for(let page = currentPage - 2; page <= currentPage + 2; ++page) {
            if(page <= 1 || page >= numberOfPages) continue;


            this.#hook.insertAdjacentHTML("beforeend", `<li class="page-item ${page === currentPage ? "active pe-none" : ""}"><a role="button" class="page-link">${page}</a></li>`)
        }

        if(numberOfPages >= currentPage + 4)
            this.#hook.insertAdjacentHTML("beforeend", `<li class="page-item disabled"><a role="button" class="page-link">...</a></li>`);

        this.#hook.insertAdjacentHTML("beforeend", `<li class="page-item ${currentPage === numberOfPages ? "active" : ""}"><a role="button" class="page-link">${numberOfPages}</a></li>`);
        this.#hook.insertAdjacentHTML("beforeend", `<li class="page-item ${currentPage === numberOfPages ? "disabled" : ""}"><a role="button" class="page-link">&raquo;</a></li>`);
    }

    async #paginatorBtnClickEventHandler(event) {
        const btnText = event.target.innerText;
        const searchParams = new URLSearchParams(window.location.search);
        const currentPage = +searchParams.get("page") || 1;

        if(btnText === "«")
            searchParams.set("page", `${currentPage - 1}`);
        else if(btnText === "»")
            searchParams.set("page", `${currentPage + 1}`);
        else
            searchParams.set("page", `${+btnText}`);

        const newRelativePathQuery = window.location.pathname + '?' + searchParams.toString();
        history.pushState(null, '', newRelativePathQuery);

        await this.#callback();
    }
}

export default Pagination;