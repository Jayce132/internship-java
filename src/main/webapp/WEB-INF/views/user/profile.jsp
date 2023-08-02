<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Profile</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.min.css">

</head>
<body>

<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <%@include file="../sidebar.jsp" %>

        <main class="d-flex flex-column col">

            <header>
                <h4 class="fs-3 fw-normal">Edit Profile</h4>
            </header>

            <form id="edit-profile-form" method="post" action="/user/profile" novalidate>

                <!-- Modal -->
                <div class="modal fade" id="edit-profile-modal" data-bs-backdrop="static"
                     data-bs-keyboard="false"
                     tabindex="-1"
                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-body">
                                Are you sure you want to save the changes?
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary col-2" data-bs-dismiss="modal">Yes
                                </button>
                                <a type="button" class="btn btn-secondary col-2" href="/">No</a>
                            </div>
                        </div>
                    </div>
                </div>

                <%--Form Inputs--%>
                <div class="row g-3">
                    <div class="col-12">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email"
                               value="${currentUser.email}"
                               readonly
                               style="background-color: rgb(233,236,239); color: rgb(109,117,125)">
                    </div>

                    <div class="col-12 col-lg-6">
                        <label for="edit-profile-first-name-input" class="form-label">First name</label>
                        <input type="text" class="form-control" id="edit-profile-first-name-input"
                               name="firstName"
                               value="${currentUser.firstName}"
                               minlength="3" required>
                        <div class="invalid-feedback" id="first-name-validation-error-text">

                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <label for="edit-profile-last-name-input" class="form-label">Last name</label>
                        <input type="text" class="form-control" id="edit-profile-last-name-input"
                               name="lastName"
                               value="${currentUser.lastName}"
                               minlength="3" required>
                        <div class="invalid-feedback" id="last-name-validation-error-text">

                        </div>
                    </div>

                    <hr class="my-4">
                    <button type="button" id="edit-profile-save-button" class="w-50 btn btn-primary btn-lg"
                    >Save
                    </button>

                </div>
            </form>
        </main>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/profile/profile-page.js"></script>

</body>
</html>