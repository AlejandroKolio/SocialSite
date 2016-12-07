<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<!DOCTYPE html>
<html class="st-layout ls-top-navbar ls-bottom-footer show-sidebar sidebar-l2" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Profile</title>
    <link href="css/vendor/all.css" rel="stylesheet">
    <link href="css/app/app.css" rel="stylesheet">
</head>

<body>
<!-- Wrapper required for sidebar transitions -->
<div class="st-container">
    <!-- Fixed navbar -->
    <div class="navbar navbar-main navbar-primary navbar-fixed-top" role="navigation">
        <div class="container-fluid">

            <div class="navbar-header">

                <a class="navbar-brand" href="#"><p align="center">Social Network</p></a>
            </div><!--end of navbar-header-->

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="main-nav">

                <ul class="nav navbar-nav">
                    <!-- User -->

                    <%User user = (User) session.getAttribute("User");%>
                    <%String avatar = user.getAvatar();%>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle user" data-toggle="dropdown">

                            <img src="<%=avatar%>" alt="User" class="img-circle" width="40"/>

                            <%= user.getFirstName() + " " + user.getLastName() %>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/profile">Profile</a></li>
                            <li><a href="Logout">Logout</a></li>
                        </ul>
                    </li><!--end of User-->
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden-xs">
                        <a href="#sidebar-chat" data-toggle="sidebar-menu" data-effect="st-effect-1">
                            <i class="fa fa-comments"></i>
                        </a>
                    </li>
                </ul>
            </div><!--end collapse navbar-collapse-->
        </div><!--container-fluid-->
    </div><!--end of navbar-main-->

    <!-- Sidebar component with st-effect-1 (set on the toggle button within the navbar) -->
    <div class="sidebar left sidebar-size-2 sidebar-offset-0 sidebar-visible-desktop sidebar-visible-mobile sidebar-skin-dark"
         id="sidebar-menu">
        <div data-scrollable>
            <div class="sidebar-block">
                <div class="profile">

                    <img src="${requestScope.avaPath}" alt="people" class="img-circle" width="110"/>
                    <h4><%= user.getFirstName() + " " + user.getLastName() %>
                    </h4>

                </div>
            </div>
            <h4 align="center" class="category">Account</h4>
            <ul class="sidebar-menu">
                <li><a href="/profile"><i class="icon-user-1"></i> <span> Profile</span></a></li>
                <li><a href="/posts"><i class="fa fa-fw fa-th-list"></i> <span> Posts</span></a></li>
                <li class="active"><a href="/users"><i class="fa fa-group"></i> <span> People</span></a></li>
                <li><a href="Logout"><i class="icon-unlock-fill"></i> <span> Logout</span></a></li>
            </ul>
        </div>
    </div>
    <!--end of sidebar left sidebar-size-2 sidebar-offset-0 sidebar-visible-desktop sidebar-visible-mobile sidebar-skin-dark-->


    <!--content push wrapper-->
    <div class="st-pusher" id="content">
        <!-- this is the wrapper for the content -->
        <div class="st-content">
            <!--extra div for emulating position:fixed of the menu-->
            <div class="st-content-inner">

                <div class="container-fluid">
                    <h4>List of All Users</h4>

                    <div class="tabbable">

                        <ul class="nav nav-tabs" tabindex="1" style="overflow: hidden; outline: none;">
                            <li class="active"><a href="#home" data-toggle="tab" aria-expanded="true"><i
                                    class="fa fa-fw fa-users"></i> People</a></li>
                            <li class=""><a href="#followers" data-toggle="tab" aria-expanded="false"><i
                                    class="fa fa-fw fa-user"></i> My Followers</a></li>
                            <li class=""><a href="#leaders" data-toggle="tab" aria-expanded="false"><i
                                    class="fa fa-fw fa-user"></i> Leaders</a></li>
                        </ul>


                        <div class="tab-content">
                            <div id="home" class="tab-pane active">
                                <!-- Progress table -->
                                <div class="table-responsive">
                                    <table class="table v-middle">

                                        <thead>
                                        <tr>
                                            <th width="1"></th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Posts</th>
                                        </tr>
                                        </thead>

                                        <tbody id="responsive-table-body">
                                        <c:forEach items="${requestScope.users}" var="user">

                                            <c:if test="${user.userId != currentUserId}">
                                                <tr>
                                                    <td></td>
                                                    <td><img src="${user.avatar}" width="40" class="img-circle">
                                                        <a href="/user/${user.userId}/posts">  ${user.firstName} ${user.lastName}</a>
                                                    </td>
                                                    <td><a href="#">${user.email}</a></td>
                                                    <td><i class="fa fa-fw fa-twitter"></i><span class="muted"></span>
                                                            ${user.posts}
                                                    </td>
                                                </tr>
                                            </c:if>

                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                            </div>

                            <div id="followers" class="tab-pane">
                                <!-- Progress table -->
                                <div class="table-responsive">
                                    <table class="table v-middle">

                                        <thead>
                                        <tr>
                                            <th width="1"></th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Posts</th>
                                        </tr>
                                        </thead>

                                        <tbody id="responsive-table">
                                        <c:forEach items="${requestScope.following}" var="following">
                                            <tr>
                                                <td></td>
                                                <td><img src="${following.avatar}" width="40" class="img-circle">
                                                    <a href="/user/${following.userId}/posts">  ${following.firstName} ${following.lastName}</a>
                                                </td>
                                                <td><a href="#">${following.email}</a></td>
                                                <td><i class="fa fa-fw fa-twitter"></i><span class="muted"></span>
                                                        ${following.posts}
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                            </div>

                            <div id="leaders" class="tab-pane">
                                <!-- Progress table -->
                                <div class="table-responsive">
                                    <table class="table v-middle">

                                        <thead>
                                        <tr>
                                            <th width="1"></th>
                                            <th>Name</th>
                                            <th>Post #</th>
                                            <th>Likes Quantity</th>
                                        </tr>
                                        </thead>

                                        <tbody id="leader-table">
                                        <c:forEach items="${requestScope.leaders}" var="leader">
                                            <tr>
                                                <td></td>
                                                <td><img src="${leader.key.friend.avatar}" width="40" class="img-circle"><a href="/user/${leader.key.friend.userId}/posts">${leader.key.friend.firstName} ${leader.key.friend.lastName}</a></td>
                                                <td><a href="#">${leader.key.postId}</a></td>
                                                <td><i class="fa fa-fw fa-thumbs-o-up"></i><span class="muted"></span>${leader.value}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                            </div>


                        </div>
                    </div>
                    <table>
                        <tr>
                            <br>
                        </tr>
                    </table>
                </div>

            </div><!--end of st-content-inner-->

        </div><!--end of st-content-->
    </div><!--end of content push wrapper-->
    <!-- Footer -->
    <footer class="footer">
        <strong>Social Network</strong> v1.0.0 &copy; Final Project 2016
    </footer>
    <!-- // Footer -->
</div><!--end of st-container-->

<!-- Inline Script for colors and config objects; used by various external scripts; -->
<script>
    var colors = {
        "danger-color": "#e74c3c",
        "success-color": "#81b53e",
        "warning-color": "#f0ad4e",
        "inverse-color": "#2c3e50",
        "info-color": "#2d7cb5",
        "default-color": "#6e7882",
        "default-light-color": "#cfd9db",
        "purple-color": "#9D8AC7",
        "mustard-color": "#d4d171",
        "lightred-color": "#e15258",
        "body-bg": "#f6f6f6"
    };
    var config = {
        theme: "social-2",
        skins: {
            "default": {
                "primary-color": "#16ae9f"
            },
            "orange": {
                "primary-color": "#e74c3c"
            },
            "blue": {
                "primary-color": "#4687ce"
            },
            "purple": {
                "primary-color": "#af86b9"
            },
            "brown": {
                "primary-color": "#c3a961"
            },
            "default-nav-inverse": {
                "color-block": "#242424"
            }
        }
    };
</script>

<script src="/js/vendor/all.js"></script>
<script src="/js/app/app.js"></script>
</body>
