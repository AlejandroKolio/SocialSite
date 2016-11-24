<%@ page import="model.User" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dao.UserDaoImp" %>
<%@ page import="dao.FollowerDao" %>
<%@ page import="dao.FollowerDaoImp" %>
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
    <link rel="stylesheet" href="/css/follow_unfollow.css">
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
                            <li><a href="user-private-profile.html">Profile</a></li>
                            <li><a href="user-private-messages.html">Messages</a></li>
                            <li><a href="login.html">Logout</a></li>
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
                    <img src="<%=avatar%>" alt="people" class="img-circle"/>
                    <h4><%= user.getFirstName() + " " + user.getLastName() %></h4>
                </div>
            </div>
            <h4 align="center" class="category">Account</h4>
            <ul class="sidebar-menu">
                <li class="active"><a href="/profile"><i class="icon-user-1"></i> <span>Profile</span></a></li>
                <li><a href="#"><i class="fa fa-camera-retro"></i> <span>Photos</span></a></li>
                <li><a href="/users"><i class="fa fa-group"></i> <span>Friends</span></a></li>
                <li><a href="#"><i class="icon-comment-fill-1"></i> <span>Messages</span></a></li>
                <li><a href="/logout"><i class="icon-unlock-fill"></i> <span>Logout</span></a></li>
            </ul>
        </div>
    </div>
    <!--end of sidebar left sidebar-size-2 sidebar-offset-0 sidebar-visible-desktop sidebar-visible-mobile sidebar-skin-dark-->

    <!-- Sidebar component with st-effect-1 (set on the toggle button within the navbar) -->
    <div class="sidebar sidebar-chat right sidebar-size-2 sidebar-offset-0 chat-skin-white sidebar-visible-mobile"
         id=sidebar-chat>
        <div class="split-vertical">

            <div class="chat-search">
                <input type="text" class="form-control" placeholder="Search"/>
            </div><!--end chat-search-->

            <ul class="chat-filter nav nav-pills ">
                <li class="active"><a href="#" data-target="li">All</a></li>
                <li><a href="#" data-target=".online">Online</a></li>
                <li><a href="#" data-target=".offline">Offline</a></li>
            </ul>

            <div class="split-vertical-body">
                <div class="split-vertical-cell">
                    <div data-scrollable>
                        <ul class="chat-contacts">
                            <li class="online" data-user-id="1">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/guy-6.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Jonathan S.</div>
                                            <small>"Free Today"</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="online away" data-user-id="2">
                                <a href="#">

                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/woman-5.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Mary A.</div>
                                            <small>"Feeling Groovy"</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="online" data-user-id="3">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left ">
                                            <span class="status"></span>
                                            <img src="/images/people/110/guy-3.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Adrian D.</div>
                                            <small>Busy</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="offline" data-user-id="4">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <img src="/images/people/110/woman-6.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Michelle S.</div>
                                            <small>Offline</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="offline" data-user-id="5">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <img src="/images/people/110/woman-7.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Daniele A.</div>
                                            <small>Offline</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="online" data-user-id="6">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/guy-4.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Jake F.</div>
                                            <small>Busy</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="online away" data-user-id="7">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/woman-6.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Jane A.</div>
                                            <small>"Custom Status"</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="offline" data-user-id="8">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/woman-8.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Sabine J.</div>
                                            <small>"Offline right now"</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="online away" data-user-id="9">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/woman-9.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Danny B.</div>
                                            <small>Be Right Back</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="online" data-user-id="10">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/woman-8.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">Elise J.</div>
                                            <small>My Status</small>
                                        </div>
                                    </div>
                                </a>
                            </li>

                            <li class="online" data-user-id="11">
                                <a href="#">
                                    <div class="media">
                                        <div class="pull-left">
                                            <span class="status"></span>
                                            <img src="/images/people/110/guy-3.jpg" width="40" class="img-circle"/>
                                        </div>
                                        <div class="media-body">
                                            <div class="contact-name">John J.</div>
                                            <small>My Status #1</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </ul><!--end of chat-contacts-->

                    </div><!--end of data scrollable-->
                </div><!--end of split-vertical-cell-->
            </div><!--end of split-vertical-body-->
        </div><!--end of split-vertical-->
    </div>
    <!--end of sidebar sidebar-chat right sidebar-size-2 sidebar-offset-0 chat-skin-white sidebar-visible-mobile-->

    <!--Chat Script-->
    <script id="chat-window-template" type="text/x-handlebars-template">

        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="chat-collapse" data-target="#chat-bill">
                <a href="#" class="close"><i class="fa fa-times"></i></a>
                <a href="#">
            <span class="pull-left">
                    <img src="{{ user_image }}" width="40">
                </span>
                    <span class="contact-name">{{user}}</span>
                </a>
            </div>
            <div class="panel-body" id="chat-bill">
                <div class="media">
                    <div class="media-left">
                        <img src="{{ user_image }}" width="25" class="img-circle" alt="people"/>
                    </div>
                    <div class="media-body">
                        <span class="message">Feeling Groovy?</span>
                    </div>
                </div>
                <div class="media">
                    <div class="media-left">
                        <img src="{{ user_image }}" width="25" class="img-circle" alt="people"/>
                    </div>
                    <div class="media-body">
                        <span class="message">Yep.</span>
                    </div>
                </div>
                <div class="media">
                    <div class="media-left">
                        <img src="{{ user_image }}" width="25" class="img-circle" alt="people"/>
                    </div>
                    <div class="media-body">
                        <span class="message">This chat window looks amazing.</span>
                    </div>
                </div>
                <div class="media">
                    <div class="media-left">
                        <img src="{{ user_image }}" width="25" class="img-circle" alt="people"/>
                    </div>
                    <div class="media-body">
                        <span class="message">Thanks!</span>
                    </div>
                </div>
            </div>
            <input type="text" class="form-control" placeholder="Type message..."/>
        </div>
    </script><!--end of Chat Script-->

    <!--Window for Chat-->
    <div class="chat-window-container"></div>

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

                                        <img src="<%=request.getAttribute("avatar")%>" alt="" class="img-circle">

                                        <h3><%=request.getAttribute("userName")%></h3>

                                        <%FollowerDao followerDao = (FollowerDao) request.getAttribute("followerDao");%>

                                        <%if(!followerDao.isFollower((Integer)request.getAttribute("userId"), user.getUserId())) {%>
                                            <form action="/follow/<%=(Integer)request.getAttribute("userId")%>;<%=user.getUserId()%>" method="post">
                                                <button class="btn btn-primary btn-follow">
                                                    <span class="follow"><i class="fa fa-chain"></i> Follow</span>
                                                </button>
                                            </form>
                                        <%} else { %>
                                        <form action="/unfollow/<%=(Integer)request.getAttribute("userId")%>;<%=user.getUserId()%>" method="post">
                                            <button class="btn btn-danger btn-follow">
                                                <span class="follow"><i class="fa fa-chain-broken"></i> Unfollow</span>
                                            </button>
                                        </form>
                                        <% } %>

                                        <%--
                                        <form action="/follow/<%=(Integer)request.getAttribute("userId")%>;<%=user.getUserId()%>" method="post">
                                        <button class="btn btn-primary btn-follow">
                                            <span class="follow"><i class="fa fa-user-plus"></i> Follow</span>
                                            <span class="unfollow">Unfollow</span>
                                            <span class="following">Following</span>
                                        </button>
                                        </form>
                                        --%>

                                        <%--<a href="" class="btn btn-success">Following <i class="fa fa-check-circle fa-fw"></i></a>--%>
                                        <%--<a href="#" class="btn btn-default btn-sm">Follow <i class="fa fa-share"></i></a>--%>
                                        <%--<a href=/follow/<%=user.getUserId()%>">Follow</a>--%>


                                    </div>

                                    <div class="profile-icons margin-none">
                                        <span><i class="fa fa-users"></i> 372</span>
                                        <span><i class="fa fa-photo"></i> 43</span>
                                        <span><i class="fa fa-video-camera"></i> 3</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="media-body">
                            <div class="panel panel-default share">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <a class="btn btn-primary" href="#"><i class="fa fa-envelope"></i> Send</a>
                                    </div>
                                    <input type="text" class="form-control share-text" placeholder="Write message...">
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

<%--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<script src="/js/follow_unfollow.js"></script>--%>

<script src="/js/vendor/all.js"></script>
<script src="/js/app/app.js"></script>
</body>
</html>
