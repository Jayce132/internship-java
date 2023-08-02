$(document).ready(function () {
    $('form').submit(async function (e) {
        e.preventDefault();

        document.getElementById("update-cart-overlay").classList.remove("d-none");

        const response = await fetch("/cart/add", {
            method: "POST",
            body: JSON.stringify({
                productId:  $("#productId").val(),
                quantity: $('#quantity').val()
            }),
            headers: { "content-type": "application/json" }
        });

        if(!response.ok) {
            alert("An error occurred while trying to add the product in the cart. :( ");
        }

        location.reload();
    });

   $('#increment-btn').click(function (e) {
       e.preventDefault();
       let inc_value = $("#quantity").val();
       let value = parseInt(inc_value, 10);
       value = isNaN(value) ? 0 : value;
       value++;
       $("#quantity").val(value);
   });

   $('#decrement-btn').click(function (e) {
        e.preventDefault();
        let dec_value = $("#quantity").val();
        let value = parseInt(dec_value, 10);
        value = isNaN(value) ? 0 : value;
        if (value > 1) {
            value--;
            $("#quantity").val(value);
        }
   });
});



