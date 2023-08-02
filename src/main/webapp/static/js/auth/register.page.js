"use strict";

const registerForm = document.getElementById("register-form");
const registerButton = document.getElementById("register-button");

const firstNameInput = document.getElementById("first-name-input");
const lastNameInput = document.getElementById("last-name-input");
const emailInput = document.getElementById("email-input");
const passwordInput = document.getElementById("password-input");
const confirmPasswordInput = document.getElementById("confirm-password-input");
const acceptedTermsAndConditionsCheck = document.getElementById("accepted-terms-and-conditions-check");

// Validation error hooks - the elements inside which error messages will be displayed
const firstNameValidationErrorHook = document.getElementById("first-name-validation-error-hook");
const lastNameValidationErrorHook = document.getElementById("last-name-validation-error-hook");
const emailValidationErrorHook = document.getElementById("email-validation-error-hook");
const passwordValidationErrorHook = document.getElementById("password-validation-error-hook");
const confirmPasswordValidationErrorHook = document.getElementById("confirm-password-validation-error-hook");


// Disable the register button unless the user accepts the terms and conditions
acceptedTermsAndConditionsCheck.addEventListener("input", () => {
    registerButton.disabled = !acceptedTermsAndConditionsCheck.checked;
});

// Functions used for invalidating/attaching validation errors to input fields
function updateFirstNameInputValidity() {
    if (firstNameInput.validity.valueMissing)
        firstNameInput.setCustomValidity("The first name is required.");
    else if (firstNameInput.value.length < 3)
        firstNameInput.setCustomValidity("The first name must contain at least 3 characters.");
    else if (firstNameInput.value.length > 256)
        firstNameInput.setCustomValidity("Character limit exceeded!");
    else
        firstNameInput.setCustomValidity("");
}

function updateLastNameInputValidity() {
    if (lastNameInput.validity.valueMissing)
        lastNameInput.setCustomValidity("The last name is required.");
    else if (lastNameInput.value.length < 3)
        lastNameInput.setCustomValidity("The last name must contain at least 3 characters.");
    else if (lastNameInput.value.length > 256)
        lastNameInput.setCustomValidity("Character limit exceeded!");
    else
        lastNameInput.setCustomValidity("");
}

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
    else if (passwordInput.value.length < 8)
        passwordInput.setCustomValidity("The password must be at least 8 characters long!");
    else if (passwordInput.value.length > 32)
        passwordInput.setCustomValidity("The password must have a maximum length of 32 characters!");
    else if (passwordInput.value.search(/[ !"#$%&'()*+,\-./:;<=>?@[\\\]^_`{|}~]/) == -1)
        passwordInput.setCustomValidity("The password must have at least a special character!");
    else if (passwordInput.value.search(/[0-9]/) == -1)
        passwordInput.setCustomValidity("The password must have at least one numeric character!");
    else if (passwordInput.value.search(/[A-Z]/) == -1)
        passwordInput.setCustomValidity("The password must have at least an upper case letter!");
    else if (passwordInput.value.search(/[a-z]/) == -1)
        passwordInput.setCustomValidity("The password must have at least a lower case letter!");
    else if (hasWhiteSpace(passwordInput.value))
        passwordInput.setCustomValidity("The password must not have whitespaces!");
    else
        passwordInput.setCustomValidity("");
}

function updateConfirmPasswordInputValidity() {
    if (confirmPasswordInput.validity.valueMissing)
        confirmPasswordInput.setCustomValidity("Password confirmation is required.");
    else if (confirmPasswordInput.value !== passwordInput.value)
        confirmPasswordInput.setCustomValidity("The passwords do not match!");
    else
        confirmPasswordInput.setCustomValidity("");
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

function performConfirmPasswordInputValidation() {
    updateConfirmPasswordInputValidity();
    updateBootstrapValidationClasses(confirmPasswordInput);
    confirmPasswordValidationErrorHook.innerText =
        confirmPasswordInput.validationMessage;
}
const hasWhiteSpace = (s) => {
    let temp = s.trim();
    return temp !== s;

};
// Form events handlers
registerForm.addEventListener("submit", (event) => {
    performFirstNameInputValidation();
    performLastNameInputValidation();
    performEmailInputValidation();
    performPasswordInputValidation();
    performConfirmPasswordInputValidation();

    if (!registerForm.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    }
});

registerForm.addEventListener("reset", () => {
    firstNameInput.classList.remove("is-invalid");
    lastNameInput.classList.remove("is-invalid");
    emailInput.classList.remove("is-invalid");
    passwordInput.classList.remove("is-invalid");
    confirmPasswordInput.classList.remove("is-invalid");

});




