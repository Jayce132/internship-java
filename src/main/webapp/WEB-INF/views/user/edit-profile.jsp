<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Profile</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>

    <!-- Fontawesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">

    <!-- Custom styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
</head>
<body>

<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <%@include file="../sidebar.jsp" %>

        <main class="d-flex flex-column col">
            <header class="mb-3">
                <h4 class="fs-3 fw-normal">Edit Profile</h4>
            </header>

            <!-- Confirmation modal -->
            <div class="modal fade" id="edit-profile-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            Are you sure you want to save the changes?
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" data-bs-dismiss="modal" form="edit-profile-form">Yes</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Edit profile form -->
            <form id="edit-profile-form" method="post" action="/user/edit-profile" novalidate>
                <div class="row mb-0 mb-md-3">
                    <!-- First name input -->
                    <div class="cold-12 mb-3 col-md-6 mb-md-0">
                        <label for="first-name-input" class="form-label label-required">First Name</label>
                        <input type="text" class="form-control" id="first-name-input" name="firstName" required minlength="3" placeholder="Jane" value="${user.firstName}" />
                        <!--Error hook -->
                        <div class="invalid-feedback" id="first-name-validation-error-hook"></div>
                    </div>

                    <!-- Last name input -->
                    <div class="cold-12 mb-3 col-md-6 mb-md-0">
                        <label for="last-name-input" class="form-label label-required">Last Name</label>
                        <input type="text" class="form-control" id="last-name-input" name="lastName" required minlength="3" placeholder="Doe" value="${user.lastName}" />
                        <!--Error hook -->
                        <div class="invalid-feedback" id="last-name-validation-error-hook"></div>
                    </div>
                </div>

                <div class="row mb-0 mb-md-3">
                    <!-- Email input -->
                    <div class="cold-12 mb-3 col-md-6 mb-md-0">
                        <label for="email-input" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email-input" name="email" readonly disabled placeholder="janedoe@example.com" value="${user.email}" />
                        <!--Error hook -->
                        <div class="invalid-feedback" id="email-validation-error-hook"></div>
                    </div>

                    <!-- Phone input -->
                    <div class="cold-12 mb-3 col-md-6 mb-md-0">
                        <label for="phone-number-input" class="form-label">Phone</label>
                        <input type="tel" class="form-control" id="phone-number-input" name="phoneNumber" placeholder="+40712345678" value="${user.phoneNumber}" />
                        <!--Error hook -->
                        <div class="invalid-feedback" id="phone-number-validation-error-hook"></div>
                    </div>
                </div>

                <!-- Address input -->
                <div class="mb-3">
                    <label for="address-input" class="form-label">Address</label>
                    <input
                            type="text"
                            class="form-control"
                            id="address-input"
                            name="address"
                            placeholder="County Gorj, City Motru, Str. Nowhere, No. 42, Building A7, Apt. 1408"
                            value="${user.address}"
                    />
                    <!--Error hook -->
                    <div class="invalid-feedback" id="shipping-address-validation-error-hook"></div>
                </div>
            </form>

            <div class="d-flex justify-content-end">
                <button type="button" id="edit-profile-save-button" class="btn btn-primary" disabled style="width: 100px">Save</button>
            </div>
        </main>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/edit-profile.js"></script>
</body>
</html>