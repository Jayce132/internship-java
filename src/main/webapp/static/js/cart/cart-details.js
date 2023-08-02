async function updateQuantity(quantityInput){
    const response = await fetch("/cart/update", {
        method:"POST",
        body:JSON.stringify({
            productId:quantityInput.dataset.productId,
            quantity:quantityInput.value
        }),
        headers:{
            "content-type": "application/json"
        }
    });

    if(!response.ok){
        alert("Couldn't update product quantity!");
    }

    location.reload();
}

async function deleteCartItem(productId){
    document.getElementById("update-cart-overlay").classList.remove("d-none");

    const response = await fetch(`/cart/delete/${productId}`, { method:"DELETE"});

    if(!response.ok){
        alert("Couldn't delete cart item!");
    }

    location.reload();
}

function stepUp(){
    document.getElementById("update-cart-overlay").classList.remove("d-none");
    const quantityInput = this.parentNode.querySelector('input[type=number]');
    quantityInput.stepUp();
    updateQuantity(quantityInput).then();
 }

function stepDown(){
    document.getElementById("update-cart-overlay").classList.remove("d-none");
    const quantityInput = this.parentNode.querySelector('input[type=number]');
    quantityInput.stepDown();
    updateQuantity(quantityInput).then();
 }

const cartItemsOutOfStockModal = new bootstrap.Modal(document.getElementById("modal-cart-items-out-of-stock"), {});

 document.getElementById("check-out-btn")?.addEventListener("click", async () => {
    const response = await fetch("/checkout");

    if(!response.ok) {
        const { errorMessage } = await response.json();

        if(errorMessage === "The cart contains items that are out of stock.") {
            cartItemsOutOfStockModal.toggle(null);
        } else {
            alert(errorMessage);
        }
    } else {
        location.href = "/checkout/fill-order-details";
    }
})

