<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="rootContextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Register</title>

    <link rel="stylesheet" href="${rootContextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootContextPath}/static/css/global.css">
</head>

<body>
<div class="container-fluid mt-5" unselectable="on"
     onselectstart="return false;" style="cursor: context-menu">
    <div class="row justify-content-center">
        <div class="col-10 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
            <div class="card">
                <div class="card-header text-center">
                    <h1>Register</h1>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${email != null}">
                            <div class="alert alert-danger my-3" role="alert">

                                    ${email}
                            </div>
                        </c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                    <form method="post" action="${rootContextPath}/register" id="register-form" novalidate
                          modelAttribute="user">
                        <div class="row mb-3">
                            <!-- First name input -->
                            <div class="col-6">
                                <label for="first-name-input" class="form-label label-required">First Name</label>
                                <input type="text" class="form-control" id="first-name-input" name="firstName" required
                                       minlength="3" onselectstart="return true;"
                                       onmousedown="return true" style="cursor: auto"/>
                                <!--Error hook -->
                                <div class="invalid-feedback" id="first-name-validation-error-hook"></div>
                            </div>

                            <!-- Last name input -->
                            <div class="col-6">
                                <label for="last-name-input" class="form-label label-required">Last Name</label>
                                <input type="text" class="form-control" id="last-name-input" name="lastName" required
                                       minlength="3" onselectstart="return true;"
                                       onmousedown="return true" style="cursor: auto"/>
                                <!--Error hook -->
                                <div class="invalid-feedback" id="last-name-validation-error-hook"></div>
                            </div>
                        </div>

                        <!-- Email input -->
                        <div class="mb-3">
                            <label for="email-input" class="form-label label-required">Email</label>
                            <input type="email" class="form-control" id="email-input" name="email" required
                                   onselectstart="return true;"
                                   onmousedown="return true" style="cursor: auto"/>
                            <!--Error hook -->
                            <div class="invalid-feedback" id="email-validation-error-hook"></div>
                        </div>

                        <!-- Password input -->
                        <div class="mb-3">
                            <label for="password-input" class="form-label label-required">Password</label>
                            <input type="password" class="form-control" id="password-input" name="password" required
                                   onselectstart="return true;"
                                   onmousedown="return true" style="cursor: auto"/>
                            <!--Error hook -->
                            <div class="invalid-feedback" id="password-validation-error-hook"></div>
                        </div>

                        <!-- Confirm password input -->
                        <div class="mb-3">
                            <label for="confirm-password-input" class="form-label label-required">Confirm
                                Password</label>
                            <input type="password" class="form-control" id="confirm-password-input"
                                   name="confirmPassword" required onselectstart="return true;"
                                   onmousedown="return true" style="cursor: auto"/>
                            <!--Error hook -->
                            <div class="invalid-feedback" id="confirm-password-validation-error-hook"></div>
                        </div>

                        <!-- Accept terms and conditions check -->
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="accepted-terms-and-conditions-check"
                                   name="acceptedTermsAndConditions" required onselectstart="return true;"
                                   onmousedown="return true" style="cursor: auto"/>
                            <label class="form-check-label" for="accepted-terms-and-conditions-check">Accept&nbsp;<a
                                    class="text-decoration-none" href="#">Terms and Conditions</a></label>
                            <div class="invalid-feedback"
                                 id="accepted-terms-and-conditions-validation-error-hook"></div>
                        </div>

                        <!-- Form buttons -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary mb-3" id="register-button" disabled>Register
                            </button>
                            <button type="reset" class="btn btn-secondary">Cancel</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <a href="${rootContextPath}/login" class="text-decoration-none"><strong>Already have an account? Login!</strong></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${rootContextPath}/static/js/bootstrap.bundle.min.js"></script>
<script src="${rootContextPath}/static/js/auth/register.page.js"></script>
</body>
</html>