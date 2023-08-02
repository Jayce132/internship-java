"use strict";

const errorModal = document.getElementById('error-modal');

if(errorModal) {
    new bootstrap.Modal(errorModal, {}).toggle();
}

const logInForm = document.getElementById("log-in-form");

const emailInput = document.getElementById("email-input");
const passwordInput = document.getElementById("password-input");

// Validation error hooks - the elements inside which error messages will be displayed
const emailValidationErrorHook = document.getElementById("email-validation-error-hook");
const passwordValidationErrorHook = document.getElementById("password-validation-error-hook");

// Functions used for invalidating/attaching validation errors to input fields
function updateEmailInputValidity() {
    if (emailInput.validity.valueMissing)
        emailInput.setCustomValidity("The email is required.");
    else if (emailInput.validity.typeMismatch)
        emailInput.setCustomValidity("The email is invalid.");
    else
        emailInput.setCustomValidity("");
}

function updatePasswordInputValidity() {
    if (passwordInput.validity.valueMissing)
        passwordInput.setCustomValidity("The password is required.");
    else
        passwordInput.setCustomValidity("");
}

// Function used for attaching/detaching bootstrap-validation-related classes depending on the validity of the input element
function updateBootstrapValidationClasses(inputElement) {
    if (inputElement.checkValidity())
        inputElement.classList.remove("is-invalid");
    else
        inputElement.classList.add("is-invalid");
}

/*
    Functions used for coordinating the entire input validation process:
    - attaching an error message to the input, in case the input is invalid
    - adding/detaching bootstrap classes to the input, depending on the validity of the input
    - displaying the error message, if any
*/
function performEmailInputValidation() {
    updateEmailInputValidity();
    updateBootstrapValidationClasses(emailInput);
    emailValidationErrorHook.innerText = emailInput.validationMessage;
}

function performPasswordInputValidation() {
    updatePasswordInputValidity();
    updateBootstrapValidationClasses(passwordInput);
    passwordValidationErrorHook.innerText = passwordInput.validationMessage;
}

// Form events handlers
logInForm.addEventListener("submit", (event) => {
    performEmailInputValidation();
    performPasswordInputValidation();

    if (!logInForm.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    }
});

logInForm.addEventListener("reset", () => {
    emailInput.classList.remove("is-invalid");
    passwordInput.classList.remove("is-invalid");
});
