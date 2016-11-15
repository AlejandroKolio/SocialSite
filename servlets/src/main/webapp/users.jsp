<%@ page import="model.User" %>
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
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle user" data-toggle="dropdown">
                            <img src="images/people/110/guy-5.jpg" alt="User" class="img-circle" width="40"/> Alexander
                            Shakhov <span class="caret"></span>
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
                    <img src="images/people/110/guy-5.jpg" alt="people" class="img-circle"/><h4>Alexander Shakhov</h4>
                </div>
            </div>
            <h4 align="center" class="category">Account</h4>
            <ul class="sidebar-menu">
                <li class="active"><a href="index.html"><i class="icon-user-1"></i> <span>Profile</span></a></li>
                <li><a href="photos.html"><i class="fa fa-camera-retro"></i> <span>Photos</span></a></li>
                <li><a href="users.html"><i class="fa fa-group"></i> <span>Friends</span></a></li>
                <li><a href="messages.html"><i class="icon-comment-fill-1"></i> <span>Messages</span></a></li>
                <li><a href="login.html"><i class="icon-unlock-fill"></i> <span>Logout</span></a></li>
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
                                            <img src="images/people/110/guy-6.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/woman-5.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/guy-3.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/woman-6.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/woman-7.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/guy-4.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/woman-6.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/woman-8.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/woman-9.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/woman-8.jpg" width="40" class="img-circle"/>
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
                                            <img src="images/people/110/guy-3.jpg" width="40" class="img-circle"/>
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
                                        <img src="images/people/110/guy-3.jpg" alt="" class="img-circle">

                                        <%User user = (User) session.getAttribute("User");%>
                                        <h3><%= user.getFirstName() + " " + user.getLastName() %></h3>

                                        <a href="#" class="btn btn-success">Following <i
                                                class="fa fa-check-circle fa-fw"></i></a>
                                    </div>
                                    <div class="profile-icons margin-none">
                                        <span><i class="fa fa-users"></i> 372</span>
                                        <span><i class="fa fa-photo"></i> 43</span>
                                        <span><i class="fa fa-video-camera"></i> 3</span>
                                    </div>
                                    <div class="panel-body">
                                        <div class="expandable expandable-indicator-white expandable-trigger">
                                            <div class="expandable-content">
                                                <p>Hi! I'm Adrian the Senior UI Designer at
                                                    <strong>MOSAICPRO</strong>. We hope you enjoy the design and quality
                                                    of Social.</p>
                                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut autem
                                                    delectus dolorum necessitatibus neque odio quam quas qui quod
                                                    soluta? Aliquid eius esse minima.</p>
                                                <div class="expandable-indicator"><i></i></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Contact -->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Contact
                                    </div>
                                    <ul class="icon-list icon-list-block">
                                        <li><i class="fa fa-envelope fa-fw"></i> <a href="#"><%=user.getEmail()%></a></li>
                                        <li><i class="fa fa-facebook fa-fw"></i> <a href="#">/facebook</a></li>
                                    </ul>
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

                            <div class="tabbable">
                                <ul class="nav nav-tabs" tabindex="0" style="overflow: hidden; outline: none;">
                                    <li class="active"><a href="#home" data-toggle="tab"><i
                                            class="fa fa-fw fa-picture-o"></i> Photos</a></li>
                                    <li class=""><a href="#profile" data-toggle="tab"><i class="fa fa-fw fa-folder"></i>
                                        Albums</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade active in" id="home">
                                        <img src="images/place1.jpg" alt="image">
                                        <img src="images/place2.jpg" alt="image">
                                        <img src="images/food1.jpg" alt="image">
                                    </div>
                                    <div class="tab-pane fade" id="profile">
                                        <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin
                                            coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next
                                            level wes anderson artisan four loko farm-to-table craft beer twee. Qui
                                            photo
                                            booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts
                                            ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore
                                            aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna
                                            velit sapiente
                                            labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit,
                                            sustainable jean shorts beard ut DIY ethical culpa terry richardson
                                            biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui
                                            sapiente accusamus tattooed
                                            echo park.</p>
                                    </div>
                                    <div class="tab-pane fade" id="dropdown1">
                                        <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out
                                            mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.
                                            Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard
                                            locavore carles
                                            etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings
                                            gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever
                                            gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog.
                                            Scenester cred
                                            you probably haven't heard of them, vinyl craft beer blog stumptown.
                                            Pitchfork sustainable tofu synth chambray yr.</p>
                                    </div>
                                    <div class="tab-pane fade" id="dropdown2">
                                        <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party
                                            before they sold out master cleanse gluten-free squid scenester freegan
                                            cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf
                                            cliche high life
                                            echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they
                                            sold out farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral,
                                            mustache readymade thundercats keffiyeh craft beer marfa ethical. Wolf
                                            salvia freegan,
                                            sartorial keffiyeh echo park vegan.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="panel panel-default">
                                        <div class="panel-heading panel-heading-gray">
                                            <a href="#" class="btn btn-white btn-xs pull-right"><i
                                                    class="fa fa-pencil"></i></a>
                                            <i class="fa fa-fw fa-info-circle"></i> About
                                        </div>
                                        <div class="panel-body">
                                            <ul class="list-unstyled profile-about margin-none">
                                                <li class="padding-v-5">
                                                    <div class="row">
                                                        <div class="col-sm-4"><span
                                                                class="text-muted">Date of Birth</span></div>
                                                        <div class="col-sm-8">12 January 1990</div>
                                                    </div>
                                                </li>
                                                <li class="padding-v-5">
                                                    <div class="row">
                                                        <div class="col-sm-4"><span class="text-muted">Job</span></div>
                                                        <div class="col-sm-8">Specialist</div>
                                                    </div>
                                                </li>
                                                <li class="padding-v-5">
                                                    <div class="row">
                                                        <div class="col-sm-4"><span class="text-muted">Gender</span>
                                                        </div>
                                                        <div class="col-sm-8">Male</div>
                                                    </div>
                                                </li>
                                                <li class="padding-v-5">
                                                    <div class="row">
                                                        <div class="col-sm-4"><span class="text-muted">Lives in</span>
                                                        </div>
                                                        <div class="col-sm-8">Miami, FL, USA</div>
                                                    </div>
                                                </li>
                                                <li class="padding-v-5">
                                                    <div class="row">
                                                        <div class="col-sm-4"><span class="text-muted">Credits</span>
                                                        </div>
                                                        <div class="col-sm-8">249</div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="panel panel-default">
                                        <div class="panel-heading panel-heading-gray">
                                            <div class="pull-right">
                                                <a href="#" class="btn btn-primary btn-xs">Add <i
                                                        class="fa fa-plus"></i></a>
                                            </div>
                                            <i class="icon-user-1"></i> Friends
                                        </div>
                                        <div class="panel-body">
                                            <ul class="img-grid">
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/guy-6.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/woman-3.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/guy-2.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/guy-9.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/woman-9.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li class="clearfix">
                                                    <a href="#">
                                                        <img src="images/people/110/guy-4.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/guy-1.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/woman-4.jpg" alt="image">
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/people/110/guy-6.jpg" alt="image">
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading panel-heading-gray">
                                    <i class="fa fa-bookmark"></i> Bookmarks
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <a href="#" class="h5 margin-none">Climb a Mountain</a>
                                                    <div class="text-muted">
                                                        <small><i class="fa fa-calendar"></i> 24/10/2014</small>
                                                    </div>
                                                </div>
                                                <a href="#">
                                                    <img src="images/place1-full.jpg" alt="image"
                                                         class="img-responsive">
                                                </a>
                                                <div class="panel-body">
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor
                                                        impedit ipsum laborum maiores tempore veritatis....</p>
                                                    <div>
                                                        <div class="pull-right">
                                                            <a href="#" class="btn btn-primary btn-xs">read</a>
                                                        </div>

                                                        <a href="#" class="text-muted"> <i class="fa fa-comments"></i> 6</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body text-center">
                                                    <a href="#" class="h5 margin-none">Vegetarian Pizza</a>
                                                    <p class="text-muted"><i class="fa fa-calendar"></i> 24/10/2014</p>
                                                    <span class="fa fa-star text-primary"></span>
                                                    <span class="fa fa-star text-primary"></span>
                                                    <span class="fa fa-star text-primary"></span>
                                                    <span class="fa fa-star text-primary"></span>
                                                    <span class="fa fa-star-o"></span>
                                                </div>
                                                <a href="#">
                                                    <img src="images/food1-full.jpg" alt="image" class="img-responsive">
                                                </a>
                                                <div class="panel-body">
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor
                                                        impedit ipsum laborum maiores tempore veritatis....</p>
                                                    <div>
                                                        <div class="pull-right">
                                                            <a href="#" class="btn btn-primary btn-xs">read</a>
                                                        </div>
                                                        <a href="#" class="text-muted"> <i class="fa fa-comments"></i> 6</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="pull-right">
                                                        <a href="#" class="btn btn-success btn-xs"><i
                                                                class="fa fa-check-circle"></i></a>
                                                    </div>
                                                    <a href="#" class="h5">Win a Holiday</a>
                                                    <div class="text-muted">
                                                        <small><i class="fa fa-calendar"></i> 24/10/2014</small>
                                                    </div>
                                                </div>
                                                <a href="#">
                                                    <img src="images/place2-full.jpg" alt="image"
                                                         class="img-responsive">
                                                </a>
                                                <ul class="icon-list icon-list-block">
                                                    <li><i class="fa fa-calendar fa-fw"></i> <a href="#">1 Week</a></li>
                                                    <li><i class="fa fa-users fa-fw"></i> <a href="#"> 2 People</a></li>
                                                    <li><i class="fa fa-map-marker fa-fw"></i> <a href="#">Miami, FL,
                                                        USA</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
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

<script src="js/vendor/all.js"></script>
<script src="js/app/app.js"></script>
</body>
</html>
