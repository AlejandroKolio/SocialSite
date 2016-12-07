<%@ page import="model.User" %>
<%@ page import="dao.FollowerDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <img src="<%=avatar%>" alt="people" class="img-circle" width="110"/>
                    <h4><%= user.getFirstName() + " " + user.getLastName() %>
                    </h4>
                </div>
            </div>
            <h4 align="center" class="category">Account</h4>
            <ul class="sidebar-menu">
                <li class="active"><a href="/profile"><i class="icon-user-1"></i> <span> Profile</span></a></li>
                <li><a href="/posts"><i class="fa fa-fw fa-th-list"></i> <span> Posts</span></a></li>
                <li><a href="/users"><i class="fa fa-group"></i> <span> People</span></a></li>
                <li><a href="/Logout"><i class="icon-unlock-fill"></i> <span> Logout</span></a></li>
            </ul>
        </div>
    </div>
    <!--end of sidebar left sidebar-size-2 sidebar-offset-0 sidebar-visible-desktop sidebar-visible-mobile sidebar-skin-dark-->



    <!--content push wrapper-->
    <div class="st-pusher" id="content">
        <!-- this is the wrapper for the content -->
        <div class="st-content">


            <!-- extra div for emulating position:fixed of the menu -->
            <div class="st-content-inner">

                <div class="container-fluid">

                    <div class="media media-grid media-clearfix-xs">
                        <div class="media-left">

                            <div class="width-250 width-auto-xs">
                                <div class="panel panel-default widget-user-1 text-center">
                                    <div class="avatar">

                                        <img src="<%=request.getAttribute("avatar")%>" alt="" class="img-circle"
                                             width="110">

                                        <h3><%=request.getAttribute("userName")%>
                                        </h3>

                                        <%FollowerDao followerDao = (FollowerDao) request.getAttribute("followerDao");%>

                                        <%if (!followerDao.isFollower((Integer) request.getAttribute("userId"), user.getUserId())) {%>
                                        <form action="/follow/<%=(Integer)request.getAttribute("userId")%>;<%=user.getUserId()%>"
                                              method="post">
                                            <button class="btn btn-primary btn-follow">
                                                <span class="follow"><i class="fa fa-chain"></i> Follow</span>
                                            </button>
                                        </form>
                                        <%} else { %>
                                        <form action="/unfollow/<%=(Integer)request.getAttribute("userId")%>;<%=user.getUserId()%>"
                                              method="post">
                                            <button class="btn btn-danger btn-follow">
                                                <span class="follow"><i class="fa fa-chain-broken"></i> Unfollow</span>
                                            </button>
                                        </form>
                                        <% } %>

                                    </div>

                                    <div class="profile-icons margin-none">
                                        <span><i
                                                class="fa fa-users"></i> <%=request.getAttribute("followerCounter")%></span>
                                        <span><i
                                                class="fa fa-twitter"></i> <%=request.getAttribute("postCounter")%></span>
                                    </div>


                                    <div class="panel-heading">
                                        Contact
                                    </div>
                                    <ul class="icon-list icon-list-block">
                                        <li><i class="fa fa-envelope fa-fw"></i> <a href="#">${user.email}</a></li>
                                    </ul>


                                </div>
                            </div>
                        </div>
                        <div class="media-body">

                            <div class="timeline row" data-toggle="isotope"
                                 style="position: relative; height: 5866.5px;">

                                <div class="col-xs-12 col-md-6 col-lg-7 item"
                                     style="position: absolute; left: 0px; top: 0px;">

                                    <%if (followerDao.isFollower((Integer) request.getAttribute("userId"), user.getUserId())) {%>

                                    <c:forEach items="${requestScope.posts}" var="post">
                                        <div class="timeline-block">
                                            <div class="panel panel-default">

                                                <!--HEADER-->
                                                <div class="panel-heading">
                                                    <div class="media">
                                                        <div class="media-left">
                                                            <a href="">
                                                                <img src="${post.user.avatar}" class="media-object" width="50">
                                                            </a>
                                                        </div>

                                                        <div class="media-body">
                                                            <form action="/postlike/${post.postId}/${post.user.userId}/<%=user.getUserId()%>" method="post">
                                                                <button type="submit" class="btn btn-primary btn-xs pull-right"><i class="fa fa-fw fa-thumbs-o-up fa-2x"></i> ${post.likes}</button>
                                                            </form>
                                                            <form action="/postdislike/${post.postId}/${post.user.userId}/<%=user.getUserId()%>" method="post">
                                                                <button type="submit" class="btn btn-primary btn-xs pull-right"><i class="fa fa-fw fa-thumbs-o-down fa-2x"></i> ${post.dislikes}</button>
                                                            </form>
                                                            <a href="">${post.user.firstName} ${post.user.lastName}</a>
                                                            <span>${post.date}</span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="panel-body">
                                                    <c:if test="${post.picture != 'null'}">
                                                        <img src="${post.picture}" class="img-responsive">
                                                    </c:if>
                                                    <p>${post.body}</p>
                                                </div>

                                                <div class="view-all-comments">
                                                    <a href="#">
                                                        <i class="fa fa-comments-o"></i> View all
                                                    </a>
                                                    <span> Comments</span>
                                                </div>

                                                <ul class="comments">

                                                    <c:forEach items="${post.comments}" var="comment">
                                                        <li class="media">
                                                            <div class="media-left">
                                                                <a href="#">
                                                                    <img src="${comment.user.avatar}"
                                                                         class="media-object" width="50">
                                                                </a>
                                                            </div>

                                                            <div class="media-body">
                                                                <div class="pull-right dropdown" data-show-hover="li"
                                                                     style="display: none;">
                                                                    <a href="#" data-toggle="dropdown"
                                                                       class="toggle-button">
                                                                        <i class="fa fa-pencil"></i>
                                                                    </a>

                                                                    <ul class="dropdown-menu" role="menu">
                                                                        <li><a href="#">Edit</a></li>
                                                                        <li><a href="#">Delete</a></li>
                                                                    </ul>
                                                                </div>

                                                                <a href="/user/${comment.userId}/posts"
                                                                   class="comment-author pull-left">
                                                                        ${comment.user.firstName} ${comment.user.lastName}
                                                                </a><br>
                                                                <span>${comment.commentBody}</span>
                                                                <div class="comment-date">${comment.time}</div>
                                                            </div>

                                                        </li>
                                                    </c:forEach>

                                                    <li class="comment-form">
                                                        <div class="input-group">
                                                            <form action="/posts/${post.postId}/comment/<%=request.getAttribute("userId")%>"
                                                                  method="post">
                                                                <div class="input-group-btn">
                                                                    <textarea name="newComment"
                                                                              class="form-control share-text" rows="1"
                                                                              placeholder="Comment..."
                                                                              style="width: 75%; height: 25px;"></textarea>

                                                                    <button style="position: initial; margin: 5px;"
                                                                            class="btn btn-primary btn-sm pull-right"
                                                                            type="submit"><i
                                                                            class="fa fa-comment-o"></i>
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:forEach>

                                    <% } else {%>
                                    <p style="color: #b2b2b2">cannot see posts, you are not follower yet</p>
                                    <% } %>
                                </div>
                            </div>

                        </div>
                    </div>


                </div>

                <div id="ascrail2000" class="nicescroll-rails"
                     style="width: 5px; z-index: auto; cursor: default; position: absolute; top: 71px; left: 1220px; height: 39px; display: none;">
                    <div style="position: relative; top: 0px; float: right; width: 5px; height: 0px; background-color: rgb(22, 174, 159); border: 0px; background-clip: padding-box; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;"></div>
                </div>
                <div id="ascrail2000-hr" class="nicescroll-rails"
                     style="height: 5px; z-index: auto; top: 105px; left: 280px; position: absolute; cursor: default; display: none;">
                    <div style="position: absolute; top: 0px; height: 5px; width: 0px; background-color: rgb(22, 174, 159); border: 0px; background-clip: padding-box; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;">
                    </div>
                </div>
            </div>
            <!-- /st-content-inner -->


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
</html>
