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
    <link href="/css/vendor/all.css" rel="stylesheet">
    <link href="/css/app/app.css" rel="stylesheet">
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
                            <img src="${avatar}" alt="User" class="img-circle" width="40"/>
                            <%= user.getFirstName() + " " + user.getLastName() %>
                            <span class="caret"></span>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Profile</a></li>
                                <li><a href="#">Messages</a></li>
                                <li><a href="Logout">Logout</a></li>
                            </ul>
                        </a>

                        <!--Notifications-->
                        <li class="dropdown notifications updates hidden-xs hidden-sm">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-bell-o"></i>
                            </a>


                            <ul class="dropdown-menu" role="notification" style="overflow-y: scroll">

                                <li class="dropdown-header">Notifications</li>

                                <c:forEach items="${requestScope.latestPosts}" var="latestPosts">
                                <li class="media">
                                    <div class="media-left">
                                        <img src="${latestPosts.friend.avatar}" alt="people" class="img-circle" width="30">
                                    </div>
                                    <div class="media-body">
                                        <a href="#">${latestPosts.friend.firstName} ${latestPosts.friend.lastName} </a> posted on timeline.
                                        <br>
                                        <span class="text-caption text-muted">${latestPosts.date}</span>
                                    </div>
                                </li>
                                </c:forEach>

                            </ul>


                        </li>
                        <!--End of Notifications-->

                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Messages</a></li>
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


                    <img src="${avatar}" alt="people" class="img-circle" width="110"/>

                    <h4><%= user.getFirstName() + " " + user.getLastName() %></h4>

                    <form action="/upload/<%=user.getUserId()%>" method="post" enctype="multipart/form-data">
                        <label for="fusk" style="color: antiquewhite">Click me first to change photo</label>
                        <input id="fusk" type="file" name="photo" style="display: none;">
                        <br/>
                        <button type="submit" value="Upload File" class="btn btn-info btn-xs"><i class="fa fa-download"></i> Upload</button>
                    </form>

                </div>
            </div>
            <h4 align="center" class="category">Account</h4>
            <ul class="sidebar-menu">
                <li class="active"><a href="/profile"><i class="icon-user-1"></i> <span>Profile</span></a></li>
                <li><a href="/posts"><i class="fa fa-fw fa-th-list"></i> <span> Posts</span></a></li>
                <li><a href="/users"><i class="fa fa-group"></i> <span> People</span></a></li>
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
                    <div class="row">

                        <!--SHARING THOUGHTS-->
                        <div class="col-md-6">
                            <div class="panel-body">
                                <div class="timeline-block">

                                    <div class="panel panel-default share clearfix-xs">
                                        <div class="panel-heading panel-heading-gray title">What's new</div>

                                        <form action="/post/newpost" method="post" >
                                            <div class="panel-body">
                                                <textarea name="status" class="form-control share-text" rows="3" placeholder="Share your thoughts..." autofocus style="margin: 0px -7px 0px 0px; height: 60px; width: 338px;"></textarea>
                                            </div>
                                            <div class="panel-footer share-buttons">
                                                <a href="#"><i class="fa fa-photo"></i></a>
                                                <button type="submit" class="btn btn-primary btn-xs pull-right display-none"> Post</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div><!--END OF THOUGHTS-->

                    </div>
                </div><!--container-fluid-->
            </div><!--end of st-content-inner-->

        </div><!--end of st-content-->
    </div><!--end of content push wrapper-->
    <!-- Footer -->
    <footer class="footer">
        <strong>Social Network</strong> v1.0.0 &copy; Final Project 2016
    </footer><!-- // Footer -->
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
</html>