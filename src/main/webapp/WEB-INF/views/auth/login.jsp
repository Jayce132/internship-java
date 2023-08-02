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
    <title>Login</title>

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
                    <h1>Login</h1>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${errorMessage !=null}">
                            <!-- Modal -->
                            <div class="modal fade" id="error-modal" data-bs-backdrop="static" data-bs-keyboard="false"
                                 tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="staticBackdropLabel"
                                                style="color: darkred"> ${errorMessage}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                    <form method="post" action="${rootContextPath}/login" id="log-in-form" novalidate>
                        <!-- Email input -->
                        <div class="mb-3">
                            <label for="email-input" class="form-label label-required">Email</label>
                            <input type="email" class="form-control" id="email-input" name="email" required
                                   maxlength="256" onselectstart="return true;"
                                   onmousedown="return true" style="cursor: auto"/>
                            <!--Error hook -->
                            <div class="invalid-feedback" id="email-validation-error-hook"></div>
                        </div>

                        <!-- Password input -->
                        <div class="mb-3">
                            <label for="password-input" class="form-label label-required">Password</label>
                            <input type="password" class="form-control" id="password-input" name="password" required
                                   maxlength="256" onselectstart="return true;"
                                   onmousedown="return true" style="cursor: auto"/>
                            <!--Error hook -->
                            <div class="invalid-feedback" id="password-validation-error-hook"></div>
                        </div>

                        <!-- Remember me check -->
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me" onselectstart="return true;"
                                   onmousedown="return true" style="cursor: auto"/>
                            <label class="form-check-label" for="remember-me">Remember Me</label>
                        </div>

                        <!-- Form buttons -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary mb-3" id="log-in-button">Login</button>
                            <button type="reset" class="btn btn-secondary">Cancel</button>
                        </div>
                    </form>

                    <div class="text-center mt-3">
                        <a href="${rootContextPath}/register" class="text-decoration-none"><strong>Don't have an account
                            yet?</strong></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/auth/login.page.js"></script>
</body>
</html>

