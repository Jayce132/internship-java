"use strict";

const firstNameInput = document.getElementById("first-name-input");
const lastNameInput = document.getElementById("last-name-input");
const phoneNumberInput = document.getElementById("phone-number-input");
const addressInput = document.getElementById("address-input");

const firstNameValidationErrorHook = document.getElementById("first-name-validation-error-hook");
const lastNameValidationErrorHook = document.getElementById("last-name-validation-error-hook");

const editProfileForm = document.getElementById("edit-profile-form");
const editProfileSaveButton = document.getElementById("edit-profile-save-button");

// Enable save button only if the forms inputs have changed
const defaultValues = {
    firstName: firstNameInput.value,
    lastName: lastNameInput.value,
    phoneNumber: phoneNumberInput.value,
    address: addressInput.value
}

function isEditProfileFormDirty() {
    let temp = false;

    temp ||= (firstNameInput.value !== defaultValues.firstName);
    temp ||= (lastNameInput.value !== defaultValues.lastName);
    temp ||= (phoneNumberInput.value !== defaultValues.phoneNumber);
    temp ||= (addressInput.value !== defaultValues.address);

    return temp;
}

function checkSaveButtonState() {
    editProfileSaveButton.disabled = !isEditProfileFormDirty()
}

firstNameInput.addEventListener("input", checkSaveButtonState);
lastNameInput.addEventListener("input", checkSaveButtonState);
phoneNumberInput.addEventListener("input", checkSaveButtonState);
addressInput.addEventListener("input", checkSaveButtonState);


/**
 * Attach custom validity to First Name input.
 */
function updateFirstNameInputValidity() {
    if (firstNameInput.validity.valueMissing) {
        firstNameInput.setCustomValidity("Can't be empty.");
    } else if (firstNameInput.validity.tooShort) {
        firstNameInput.setCustomValidity("At least 3 characters required.");
    } else {
        firstNameInput.setCustomValidity("");
    }
}

/**
 * Attach custom validity to Last Name input.
 */
function updateLastNameInputValidity() {
    if (lastNameInput.validity.valueMissing) {
        lastNameInput.setCustomValidity("Can't be empty.");
    } else if (lastNameInput.validity.tooShort) {
        lastNameInput.setCustomValidity("At least 3 characters required.");
    } else {
        lastNameInput.setCustomValidity("");
    }
}

/**
 * Add/Remove "is-invalid" property based on the return value of checkValidity().
 * @param inputElement - the input where the "is-valid" property is added/removed
 */
function updateBootstrapValidationClasses(inputElement) {
    if (inputElement.checkValidity()) {
        inputElement.classList.remove("is-invalid");
    } else {
        inputElement.classList.add("is-invalid");
    }
}

/**
 *  Attach custom error message to First Name input.
 *  Add/Remove "is-invalid" property.
 *  Display the custom error message.
 */
function performFirstNameInputValidation() {
    updateFirstNameInputValidity();
    updateBootstrapValidationClasses(firstNameInput);
    firstNameValidationErrorHook.innerText = firstNameInput.validationMessage;
}

/**
 *  Attach custom error message to Last Name input.
 *  Add/Remove "is-invalid" property.
 *  Display the custom error message.
 */
function performLastNameInputValidation() {
    updateLastNameInputValidity();
    updateBootstrapValidationClasses(lastNameInput);
    lastNameValidationErrorHook.innerText = lastNameInput.validationMessage;
}

/**
 * Add onClick event listener to Save button,
 * if button is clicked and inputs are valid, displays confirmation modal.
 */
editProfileSaveButton.addEventListener("click", () => {
    performFirstNameInputValidation();
    performLastNameInputValidation();

    if (editProfileForm.checkValidity()) {
        const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("edit-profile-modal"));
        modal.show();
    }
});

// When the edit profile form is submitted, make an API call to update the user profile
editProfileForm.addEventListener("submit", async (event) =>{
    event.preventDefault();
    event.stopPropagation();

    const response = await fetch("/user", {
        method: "put",
        body: JSON.stringify({
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
            phoneNumber: phoneNumberInput.value,
            address: addressInput.value
        }),
        headers: { "content-type": "application/json" }
    });

    if(!response.ok) {
        alert("Failed to update the profile :(")
    } else {
        location.reload();
    }
});