const orderDetailsForm = document.getElementById("order-details-form");
const nextButton = document.getElementById("next-button");

const firstNameInput = document.getElementById("first-name-input");
const lastNameInput = document.getElementById("last-name-input");
const phoneNumberInput = document.getElementById("phone-number-input");
const billingAddressInput = document.getElementById("billing-address-input");
const shippingAddressInput = document.getElementById("shipping-address-input");
const acceptedTermsAndConditionsCheck = document.getElementById("accepted-terms-and-conditions-check");
const sameAsBillingAddressCheck = document.getElementById("same-as-billing-address-check");

// Validation error hooks - the elements inside which error messages will be displayed
const firstNameValidationErrorHook = document.getElementById("first-name-validation-error-hook");
const lastNameValidationErrorHook = document.getElementById("last-name-validation-error-hook");
const phoneNumberValidationErrorHook = document.getElementById("phone-number-validation-error-hook");
const shippingAddressValidationErrorHook = document.getElementById("shipping-address-validation-error-hook");
const billingAddressValidationErrorHook = document.getElementById("billing-address-validation-error-hook");

// Disable the register button unless the user accepts the terms and conditions
acceptedTermsAndConditionsCheck.addEventListener("input", () => {
    nextButton.disabled = !acceptedTermsAndConditionsCheck.checked;
});

// Enable the shipping address input if the shipping address it's NOT the same as the billing address, and clear the shipping address
// Disable the shipping address input if the shipping address it's the same as the billing address, and copy the value of the billing address input into the shipping address input
sameAsBillingAddressCheck.addEventListener("change", () => {
    if (sameAsBillingAddressCheck.checked) shippingAddressInput.value = billingAddressInput.value;
    else shippingAddressInput.value = "";

    shippingAddressInput.disabled = sameAsBillingAddressCheck.checked;
});

// Copy the value of the shipping address input into the billing address input if the billing address it's the same as the shipping address
billingAddressInput.addEventListener("input", () => {
    if (sameAsBillingAddressCheck.checked) shippingAddressInput.value = billingAddressInput.value;
});

// Functions used for invalidating/attaching validation errors to input fields
function updateFirstNameInputValidity() {
    if (firstNameInput.validity.valueMissing) firstNameInput.setCustomValidity("The first name is required.");
    else if (firstNameInput.validity.tooShort) firstNameInput.setCustomValidity("The first name must contain at least 3 characters.");
    else if (firstNameInput.value.length > 256) firstNameInput.setCustomValidity("Character limit exceeded!");
    else firstNameInput.setCustomValidity("");
}

function updateLastNameInputValidity() {
    if (lastNameInput.validity.valueMissing) lastNameInput.setCustomValidity("The last name is required.");
    else if (lastNameInput.validity.tooShort) lastNameInput.setCustomValidity("The last name must contain at least 3 characters.");
    else if (lastNameInput.value.length > 256) lastNameInput.setCustomValidity("Character limit exceeded!");
    else lastNameInput.setCustomValidity("");
}

function updatePhoneValidity() {
    if (phoneNumberInput.validity.valueMissing) phoneNumberInput.setCustomValidity("The phone number is required.");
    else phoneNumberInput.setCustomValidity("");
}

function updateShippingAddressValidity() {
    if (shippingAddressInput.validity.valueMissing) shippingAddressInput.setCustomValidity("The shipping address is required.");
    else shippingAddressInput.setCustomValidity("");
}

function updateBillingAddressValidity() {
    if (billingAddressInput.validity.valueMissing) billingAddressInput.setCustomValidity("The billing address is required.");
    else billingAddressInput.setCustomValidity("");
}

// Function used for attaching/detaching bootstrap-validation-related classes depending on the validity of the input element
function updateBootstrapValidationClasses(inputElement) {
    if (inputElement.checkValidity()) inputElement.classList.remove("is-invalid");
    else inputElement.classList.add("is-invalid");
}

/*
    Functions used for coordinating the entire input validation process:
    - attaching an error message to the input, in case the input is invalid
    - adding/detaching bootstrap classes to the input, depending on the validity of the input
    - displaying the error message, if any
*/
function performFirstNameInputValidation() {
    updateFirstNameInputValidity();
    updateBootstrapValidationClasses(firstNameInput);
    firstNameValidationErrorHook.innerText = firstNameInput.validationMessage;
}

function performLastNameInputValidation() {
    updateLastNameInputValidity();
    updateBootstrapValidationClasses(lastNameInput);
    lastNameValidationErrorHook.innerText = lastNameInput.validationMessage;
}

function performPhoneInputValidation() {
    updatePhoneValidity();
    updateBootstrapValidationClasses(phoneNumberInput);
    phoneNumberValidationErrorHook.innerText = phoneNumberInput.validationMessage;
}

function performShippingAddressValidation() {
    updateShippingAddressValidity();
    updateBootstrapValidationClasses(shippingAddressInput);
    shippingAddressValidationErrorHook.innerText = shippingAddressInput.validationMessage;
}

function performBillingAddressValidation() {
    updateBillingAddressValidity();
    updateBootstrapValidationClasses(billingAddressInput);
    billingAddressValidationErrorHook.innerText = billingAddressInput.validationMessage;
}

// Functions used to make API calls
async function updateUserDetails() {
    const response = await fetch("/user", {
        method: "PUT",
        body: JSON.stringify({
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
            phoneNumber: phoneNumberInput.value,
            address: billingAddressInput.value,
        }),
        headers: {"content-type": "application/json"}
    });

    if(!response.ok) throw new Error("Failed to update user details :(");
}

async function updateOrderDetails() {
    const response = await fetch("/checkout/fill-order-details", {
        method: "POST",
        body: JSON.stringify({
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
            phoneNumber: phoneNumberInput.value,
            billingAddress: billingAddressInput.value,
            shippingAddress: shippingAddressInput.value
        }),
        headers: {"content-type": "application/json"}
    });

    if(!response.ok) throw new Error("Failed to update order details :(");
}

// Form events handlers
orderDetailsForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    event.stopPropagation();

    performFirstNameInputValidation();
    performLastNameInputValidation();
    performPhoneInputValidation();
    performShippingAddressValidation();
    performBillingAddressValidation();

    if (!orderDetailsForm.checkValidity()) return;

    try {
        await updateUserDetails();
        await updateOrderDetails();
        location.href = "./order-summary";
    } catch (err) {
        alert(err);
    }
});