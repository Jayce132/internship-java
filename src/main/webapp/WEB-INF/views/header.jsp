<%@include file="./logout-modal.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="${pageContext.request.contextPath}/static/css/header.css" rel="stylesheet" />

<header class="p-3 mb-4 mb-md-5 border-bottom">
    <div class="container-md d-flex flex-wrap align-items-center justify-content-between">
        <!-- Left navigation -->
        <nav>
            <ul class="d-none d-md-flex nav">
                <li><a href="/" class="nav-link px-2 link-dark">Products</a></li>
            </ul>
        </nav>

        <!-- Link that toggles the left navigation for mobile devices-->
        <a class="btn btn-primary d-md-none" data-bs-toggle="collapse" href="#mobileNav" role="button">
            <i class="fa-solid fa-bars"></i>
        </a>

        <!-- Product search bar -->
        <form class="flex-grow-1 flex-md-grow-0 ps-3 ps-md-0 w-50" id="search-form">
            <input type="search" class="form-control" placeholder="Search for a product..." aria-label="Search"
                   id="search-input">
        </form>

        <!-- Right navigation -->
       <nav class="d-flex">
           <a href="/cart/details" class="d-flex align-items-center link-dark text-decoration-none ms-3 me-md-4" id="cart-container">
               <span>Cart</span>
               <i class="fa-solid fa-cart-shopping fa-xl ms-1"></i>

               <c:if test="${cartItemsCount > 0}">
                   <span id="cart-total" class="text-bg-danger text-center">${cartItemsCount}</span>
               </c:if>
           </a>

           <!-- Account navigation -->
           <div class="dropdown text-end d-none d-md-flex">
               <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
                  data-bs-toggle="dropdown" aria-expanded="false">
                   <span class="pe-1">Profile</span>
                   <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
               </a>

               <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                   <li><a class="dropdown-item" href="/user/edit-profile">Edit Profile</a></li>
                   <li><a class="dropdown-item" href="/order">Orders</a></li>
                   <li>
                       <hr class="dropdown-divider">
                   </li>
                   <li><a role="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Log Out</a></li>
               </ul>
           </div>
       </nav>
    </div>

    <!-- Navigation for mobile devices -->
    <nav class="d-md-none collapse mt-3" id="mobileNav">
        <ul class="nav d-flex flex-column align-items-start">
            <!-- Left navigation -->
            <li><a href="/" class="nav-link link-dark">Products</a></li>

            <!-- Profile navigation -->
            <li>
                <a class="nav-link link-dark" data-bs-toggle="collapse" href="#ProfileMobileNav" role="button">
                    Profile <i class="fa-solid fa-caret-down ps-1"></i>
                </a>
            </li>
            <li>
                <nav class="collapse" id="ProfileMobileNav" style="text-indent: 20px">
                    <ul class="nav d-flex flex-column">
                        <li><a href="/user/edit-profile" class="nav-link link-dark">Edit Profile</a></li>
                        <li><a href="/order" class="nav-link link-dark">Orders</a></li>
                        <li><a role="button" class="nav-link link-dark" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Log Out</a></li>
                    </ul>
                </nav>
            </li>
        </ul>
    </nav>
</header>

<script src="${pageContext.request.contextPath}/static/js/header.js"></script>