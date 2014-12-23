<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--************************************************************************-->
<!--* Revenge of the Menu Bar Demo                                         *-->
<!--*                                                                      *-->
<!--* Copyright 2000-2004 by Mike Hall                                     *-->
<!--* Please see http://www.brainjar.com for terms of use.                 *-->
<!--************************************************************************-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/Styles/Main_Style.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/Styles/pagelayout_style.css" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="/common/default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
div.menuBar,
div.menuBar a.menuButton,
div.menu,
div.menu a.menuItem {
  font-family: "MS Sans Serif", Arial, sans-serif;
  font-size: 16pt;
  font-style: normal;
  font-weight: normal;
  color: #000000;
}
.div1 {
    float: left;
    background-color: black;
    color: rgb(0, 220, 98);
    font-size: 30px;
}

.div2 {
    float:right;
    text-align: right;
}
.dv4{
    background-color: blue;
    color: rgb(0, 220, 98);
}
div.menuBar {
  background-color: #d0d0d0;
  border: 2px solid;
  border-color: #f0f0f0 #909090 #909090 #f0f0f0;
  padding: 16px 16px 16px 16px;
  text-align: center;
}

div.menuBar a.menuButton {
  background-color: transparent;
  border: 1px solid #d0d0d0;
  color: #000000;
  cursor: default;
  left: 2px;
  margin: 0px;
  padding: 16px 16px 16px 16px;
  position: relative;
  text-decoration: none;
  top: 2px;
  z-index: 100;
}

div.menuBar a.menuButton:hover {
  background-color: transparent;
  border-color: #f0f0f0 #909090 #909090 #f0f0f0;
  color: #000000;
}

div.menuBar a.menuButtonActive,
div.menuBar a.menuButtonActive:hover {
  background-color: #a0a0a0;
  border-color: #909090 #f0f0f0 #f0f0f0 #909090;
  color: #ffffff;
  left: 2px;
  top: 2px;
    width:995px;
    height:20px;
	margin-left:5px;
}

div.menu {
  background-color: #d0d0d0;
  border: 2px solid;
  border-color: #f0f0f0 #909090 #909090 #f0f0f0;
  left: 2px;
  padding: 16px 16px 16px 16px;
  position: absolute;
  top: 2px;
  visibility: hidden;
  z-index: 101;
}

div.menu a.menuItem {
  color: #000000;
  cursor: default;
  display: block;
  padding: 3px 1em;
  text-decoration: none;
  white-space: nowrap;
}

div.menu a.menuItem:hover, div.menu a.menuItemHighlight {
  background-color: #000080;
  color: #ffffff;
}

div.menu a.menuItem span.menuItemText {}

div.menu a.menuItem span.menuItemArrow {
  margin-right: -.50em;
}

div.menu div.menuItemSep {
  border-top: 1px solid #909090;
  border-bottom: 1px solid #f0f0f0;
  margin: 4px 2px;
}
.footer{
height : 10px;

}
.content{
top:0px;
padding-top:0;
margin:auto;
position:relative;
width:950px;
height:400px;
}
#footer-bar {
      width:100%;
      background-color:#f40000;
      background-image: url(http://www.hentaireader.com/images/bar.gif);
      background-position:top center;
      background-repeat:no-repeat;
      text-align: right;
      margin-bottom: 10px;
}
#footer-bar ul {
        width: 950px;
        height: 30px;
        margin-left: auto;
        margin-right: auto;
        position: relative;
        left: 0px;
        top: 0px;
}
#footer-bar li {
        display: inline;
        line-height: 30px;
        margin-left: 6px;
}
#footer-bar li a {
    color: #FFFFFF;
}
#top
{
    width:1000px;
    height:35px;
    background-color:#fff;
	text-align:right;
	margin-left: auto
}
</style>
<script type="text/javascript">//<![CDATA[

//*****************************************************************************
// Do not remove this notice.
//
// Copyright 2000-2004 by Mike Hall.
// See http://www.brainjar.com for terms of use.
//*****************************************************************************

//----------------------------------------------------------------------------
// Code to determine the browser and version.
//----------------------------------------------------------------------------

function Browser() {

  var ua, s, i;

  this.isIE    = false;  // Internet Explorer
  this.isOP    = false;  // Opera
  this.isNS    = false;  // Netscape
  this.version = null;

  ua = navigator.userAgent;

  s = "Opera";
  if ((i = ua.indexOf(s)) >= 0) {
    this.isOP = true;
    this.version = parseFloat(ua.substr(i + s.length));
    return;
  }

  s = "Netscape6/";
  if ((i = ua.indexOf(s)) >= 0) {
    this.isNS = true;
    this.version = parseFloat(ua.substr(i + s.length));
    return;
  }

  // Treat any other "Gecko" browser as Netscape 6.1.

  s = "Gecko";
  if ((i = ua.indexOf(s)) >= 0) {
    this.isNS = true;
    this.version = 6.1;
    return;
  }

  s = "MSIE";
  if ((i = ua.indexOf(s))) {
    this.isIE = true;
    this.version = parseFloat(ua.substr(i + s.length));
    return;
  }
}

var browser = new Browser();

//----------------------------------------------------------------------------
// Code for handling the menu bar and active button.
//----------------------------------------------------------------------------

var activeButton = null;

// Capture mouse clicks on the page so any active button can be
// deactivated.

if (browser.isIE)
  document.onmousedown = pageMousedown;
else
  document.addEventListener("mousedown", pageMousedown, true);

function pageMousedown(event) {

  var el;

  // If there is no active button, exit.

  if (activeButton == null)
    return;

  // Find the element that was clicked on.

  if (browser.isIE)
    el = window.event.srcElement;
  else
    el = (event.target.tagName ? event.target : event.target.parentNode);

  // If the active button was clicked on, exit.

  if (el == activeButton)
    return;

  // If the element is not part of a menu, reset and clear the active
  // button.

  if (getContainerWith(el, "DIV", "menu") == null) {
    resetButton(activeButton);
    activeButton = null;
  }
}

function buttonClick(event, menuId) {

  var button;

  // Get the target button element.

  if (browser.isIE)
    button = window.event.srcElement;
  else
    button = event.currentTarget;

  // Blur focus from the link to remove that annoying outline.

  button.blur();

  // Associate the named menu to this button if not already done.
  // Additionally, initialize menu display.

  if (button.menu == null) {
    button.menu = document.getElementById(menuId);
    if (button.menu.isInitialized == null)
      menuInit(button.menu);
  }

  // Reset the currently active button, if any.

  if (activeButton != null)
    resetButton(activeButton);

  // Activate this button, unless it was the currently active one.

  if (button != activeButton) {
    depressButton(button);
    activeButton = button;
  }
  else
    activeButton = null;

  return false;
}

function buttonMouseover(event, menuId) {

  var button;

  // Find the target button element.

  if (browser.isIE)
    button = window.event.srcElement;
  else
    button = event.currentTarget;

  // If any other button menu is active, make this one active instead.

  if (activeButton != null && activeButton != button)
    buttonClick(event, menuId);
}

function depressButton(button) {

  var x, y;

  // Update the button's style class to make it look like it's
  // depressed.

  button.className += " menuButtonActive";

  // Position the associated drop down menu under the button and
  // show it.

  x = getPageOffsetLeft(button);
  y = getPageOffsetTop(button) + button.offsetHeight;

  // For IE, adjust position.

  if (browser.isIE) {
    x += button.offsetParent.clientLeft;
    y += button.offsetParent.clientTop;
  }

  button.menu.style.left = x + "px";
  button.menu.style.top  = y + "px";
  button.menu.style.visibility = "visible";

  // For IE; size, position and show the menu's IFRAME as well.

  if (button.menu.iframeEl != null)
  {
    button.menu.iframeEl.style.left = button.menu.style.left;
    button.menu.iframeEl.style.top  = button.menu.style.top;
    button.menu.iframeEl.style.width  = button.menu.offsetWidth + "px";
    button.menu.iframeEl.style.height = button.menu.offsetHeight + "px";
    button.menu.iframeEl.style.display = "";
  }
}

function resetButton(button) {

  // Restore the button's style class.

  removeClassName(button, "menuButtonActive");

  // Hide the button's menu, first closing any sub menus.

  if (button.menu != null) {
    closeSubMenu(button.menu);
    button.menu.style.visibility = "hidden";

    // For IE, hide menu's IFRAME as well.

    if (button.menu.iframeEl != null)
      button.menu.iframeEl.style.display = "none";
  }
}

//----------------------------------------------------------------------------
// Code to handle the menus and sub menus.
//----------------------------------------------------------------------------

function menuMouseover(event) {

  var menu;

  // Find the target menu element.

  if (browser.isIE)
    menu = getContainerWith(window.event.srcElement, "DIV", "menu");
  else
    menu = event.currentTarget;

  // Close any active sub menu.

  if (menu.activeItem != null)
    closeSubMenu(menu);
}

function menuItemMouseover(event, menuId) {

  var item, menu, x, y;

  // Find the target item element and its parent menu element.

  if (browser.isIE)
    item = getContainerWith(window.event.srcElement, "A", "menuItem");
  else
    item = event.currentTarget;
  menu = getContainerWith(item, "DIV", "menu");

  // Close any active sub menu and mark this one as active.

  if (menu.activeItem != null)
    closeSubMenu(menu);
  menu.activeItem = item;

  // Highlight the item element.

  item.className += " menuItemHighlight";

  // Initialize the sub menu, if not already done.

  if (item.subMenu == null) {
    item.subMenu = document.getElementById(menuId);
    if (item.subMenu.isInitialized == null)
      menuInit(item.subMenu);
  }

  // Get position for submenu based on the menu item.

  x = getPageOffsetLeft(item) + item.offsetWidth;
  y = getPageOffsetTop(item);

  // Adjust position to fit in view.

  var maxX, maxY;

  if (browser.isIE) {
    maxX = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft) +
      (document.documentElement.clientWidth != 0 ? document.documentElement.clientWidth : document.body.clientWidth);
    maxY = Math.max(document.documentElement.scrollTop, document.body.scrollTop) +
      (document.documentElement.clientHeight != 0 ? document.documentElement.clientHeight : document.body.clientHeight);
  }
  if (browser.isOP) {
    maxX = document.documentElement.scrollLeft + window.innerWidth;
    maxY = document.documentElement.scrollTop  + window.innerHeight;
  }
  if (browser.isNS) {
    maxX = window.scrollX + window.innerWidth;
    maxY = window.scrollY + window.innerHeight;
  }
  maxX -= item.subMenu.offsetWidth;
  maxY -= item.subMenu.offsetHeight;

  if (x > maxX)
    x = Math.max(0, x - item.offsetWidth - item.subMenu.offsetWidth
      + (menu.offsetWidth - item.offsetWidth));
  y = Math.max(0, Math.min(y, maxY));

  // Position and show it.

  item.subMenu.style.left       = x + "px";
  item.subMenu.style.top        = y + "px";
  item.subMenu.style.visibility = "visible";

  // For IE; size, position and show the menu's IFRAME as well.

  if (item.subMenu.iframeEl != null)
  {
    item.subMenu.iframeEl.style.left    = item.subMenu.style.left;
    item.subMenu.iframeEl.style.top     = item.subMenu.style.top;
    item.subMenu.iframeEl.style.width   = item.subMenu.offsetWidth + "px";
    item.subMenu.iframeEl.style.height  = item.subMenu.offsetHeight + "px";
    item.subMenu.iframeEl.style.display = "";
  }

  // Stop the event from bubbling.

  if (browser.isIE)
    window.event.cancelBubble = true;
  else
    event.stopPropagation();
}

function closeSubMenu(menu) {

  if (menu == null || menu.activeItem == null)
    return;

  // Recursively close any sub menus.

  if (menu.activeItem.subMenu != null) {
    closeSubMenu(menu.activeItem.subMenu);


    // Hide the sub menu.
    menu.activeItem.subMenu.style.visibility = "hidden";

    // For IE, hide the sub menu's IFRAME as well.

    if (menu.activeItem.subMenu.iframeEl != null)
      menu.activeItem.subMenu.iframeEl.style.display = "none";

    menu.activeItem.subMenu = null;
  }

  // Deactivate the active menu item.

  removeClassName(menu.activeItem, "menuItemHighlight");
  menu.activeItem = null;
}

//----------------------------------------------------------------------------
// Code to initialize menus.
//----------------------------------------------------------------------------

function menuInit(menu) {

  var itemList, spanList;
  var textEl, arrowEl;
  var itemWidth;
  var w, dw;
  var i, j;

  // For IE, replace arrow characters.

  if (browser.isIE) {
    menu.style.lineHeight = "2.5ex";
    spanList = menu.getElementsByTagName("SPAN");
    for (i = 0; i < spanList.length; i++)
      if (hasClassName(spanList[i], "menuItemArrow")) {
        spanList[i].style.fontFamily = "Webdings";
        spanList[i].firstChild.nodeValue = "4";
      }
  }

  // Find the width of a menu item.

  itemList = menu.getElementsByTagName("A");
  if (itemList.length > 0)
    itemWidth = itemList[0].offsetWidth;
  else
    return;

  // For items with arrows, add padding to item text to make the
  // arrows flush right.

  for (i = 0; i < itemList.length; i++) {
    spanList = itemList[i].getElementsByTagName("SPAN");
    textEl  = null;
    arrowEl = null;
    for (j = 0; j < spanList.length; j++) {
      if (hasClassName(spanList[j], "menuItemText"))
        textEl = spanList[j];
      if (hasClassName(spanList[j], "menuItemArrow")) {
        arrowEl = spanList[j];
      }
    }
    if (textEl != null && arrowEl != null) {
      textEl.style.paddingRight = (itemWidth 
        - (textEl.offsetWidth + arrowEl.offsetWidth)) + "px";

      // For Opera, remove the negative right margin to fix a display bug.

      if (browser.isOP)
        arrowEl.style.marginRight = "0px";
    }
  }

  // Fix IE hover problem by setting an explicit width on first item of
  // the menu.

  if (browser.isIE) {
    w = itemList[0].offsetWidth;
    itemList[0].style.width = w + "px";
    dw = itemList[0].offsetWidth - w;
    w -= dw;
    itemList[0].style.width = w + "px";
  }

  // Fix the IE display problem (SELECT elements and other windowed controls
  // overlaying the menu) by adding an IFRAME under the menu.

  if (browser.isIE) {
    var iframeEl = document.createElement("IFRAME");
    iframeEl.frameBorder = 0;
    iframeEl.src = "javascript:false;";
    iframeEl.style.display = "none";
    iframeEl.style.position = "absolute";
    iframeEl.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
    menu.iframeEl = menu.parentNode.insertBefore(iframeEl, menu);
  }

  // Mark menu as initialized.

  menu.isInitialized = true;
}

//----------------------------------------------------------------------------
// General utility functions.
//----------------------------------------------------------------------------

function getContainerWith(node, tagName, className) {

  // Starting with the given node, find the nearest containing element
  // with the specified tag name and style class.

  while (node != null) {
    if (node.tagName != null && node.tagName == tagName &&
        hasClassName(node, className))
      return node;
    node = node.parentNode;
  }

  return node;
}

function hasClassName(el, name) {

  var i, list;

  // Return true if the given element currently has the given class
  // name.

  list = el.className.split(" ");
  for (i = 0; i < list.length; i++)
    if (list[i] == name)
      return true;

  return false;
}

function removeClassName(el, name) {

  var i, curList, newList;

  if (el.className == null)
    return;

  // Remove the given class name from the element's className property.

  newList = new Array();
  curList = el.className.split(" ");
  for (i = 0; i < curList.length; i++)
    if (curList[i] != name)
      newList.push(curList[i]);
  el.className = newList.join(" ");
}
function getPageOffsetLeft(el) {
  var x;
  // Return the x coordinate of an element relative to the page.
  x = el.offsetLeft;
  if (el.offsetParent != null)
    x += getPageOffsetLeft(el.offsetParent);

  return x;
}

function getPageOffsetTop(el) {

  var y;

  // Return the x coordinate of an element relative to the page.

  y = el.offsetTop;
  if (el.offsetParent != null)
    y += getPageOffsetTop(el.offsetParent);
  return y;
}
//]]></script>
</head>
<body style="height:100%" id="content">
<div id="top">

<div>
    <div class="div1">Registration Module  System</div>
    <div class="div2"> <span>Welcome : <font color='red'><c:out value="${userName}"></c:out></font> [ <a href="profileForm.action">My Profile</a> | <a href="#">Help</a> | <a href="#">About</a> ]</span></div>
 </div>
        </div>
<div id="demoBox">
<%-- <img src="<%=request.getContextPath()%>/images/newLogo2.jpg" style="height: 120px; width: 1350px"> --%>
</div>
<!-- Menu bar. -->
<div class="menuBar" style="width:95%;">
<a class="menuButton"    href="#"    onclick="return buttonClick(event, 'homeNewMenu');" 
onmouseover="buttonMouseover(event, 'homeNewMenu');">Home </a>

<a class="menuButton"    href=""    onclick="return buttonClick(event, 'userInfoMenu');" 
onmouseover="buttonMouseover(event, 'userInfoMenu');">UserInformation</a>

<a class="menuButton"    href=""    onclick="return buttonClick(event, 'expenseInfo');" 
onmouseover="buttonMouseover(event, 'expenseInfo');">Expense Information</a>

<a class="menuButton"    href=""    onclick="return buttonClick(event, 'taskMenu');" 
onmouseover="buttonMouseover(event, 'taskMenu');">Tasks</a>
<a class="menuButton"  href=""  onclick="return buttonClick(event, 'reportMenu');"
    onmouseover="buttonMouseover(event, 'reportMenu');">Report System</a>
 <a class="menuButton"
    href=""
    onclick="return buttonClick(event, 'contactUS');"
    onmouseover="buttonMouseover(event, 'contactUS');"
>ContactsUs</a>

<a class="menuButton"
    href=""
    onclick="return buttonClick(event, 'profileMenu');"
    onmouseover="buttonMouseover(event, 'profileMenu');"
>Profile</a>

<a class="menuButton"
    href=""
    onclick="return buttonClick(event, 'logoutMenu');"
    onmouseover="buttonMouseover(event, 'logoutMenu');"
>
Logout</a>
</div>
<!-- Main menus. -->
<div id=contactUS class="menu"
     onmouseover="menuMouseover(event)">
<a class="menuItem" href="contactUs.do">ContactUS</a>

</div>


<div id="homeMenu" class="menu"
     onmouseover="menuMouseover(event)">
<a class="menuItem" href="newUser.action">Add Share</a>
<a class="menuItem" href="blank.html">Edit Share</a>
<a class="menuItem" href="blank.html">Delete Share</a>
<a class="menuItem" href="<%=request.getContextPath()%>/viewshares.spring">View All Shares</a>
</div>


<div id="userInfoMenu" class="menu"
     onmouseover="menuMouseover(event)">
<a class="menuItem" href="createUserForm.action">CreateUser</a>
<a class="menuItem" href="blank.html">EditUser</a>
<a class="menuItem" href="blank.html">SearchUser</a>
<a class="menuItem" href="Doctors.action">DeleteUser</a>
<a class="menuItem" href="askAppoint.action">ModifyUser</a>
<a class="menuItem" href="askAppoint.action">ModifyUserPasswrd</a>
</div>
<div id="reportMenu" class="menu"
     onmouseover="menuMouseover(event)">
<a class="menuItem" href="reports.action">DisconnectReconnectApprovalReport</a>
<a class="menuItem" href="reports.action">RunApprovalsReport</a>
<a class="menuItem" href="blank.html">ReadingHistoryReport</a>
<a class="menuItem" href="<%=request.getContextPath()%>/viewshares.spring">CustomerDetailsReport</a>
</div>

<div id="rateAction" class="menu"
     onmouseover="menuMouseover(event)">
<a class="menuItem" href="newUser.action">RateView</a>
<a class="menuItem" href="rateForm.action">AddTierRate</a>
<a class="menuItem" href="blank.html">DeleteTierFromListRate</a>
<a class="menuItem" href="newUser.action">SaveRate</a>
<a class="menuItem" href="blank.html">SearchTierRate</a>
<a class="menuItem" href="blank.html">EditRate</a>
<a class="menuItem" href="<%=request.getContextPath()%>/viewshares.spring">DeleteRate</a>
<a class="menuItem" href="<%=request.getContextPath()%>/viewshares.spring">SaveEditTier</a>
</div>

<div id="expenseInfo" class="menu"
     onmouseover="menuMouseover(event)">
<a class="menuItem" href="<%= request.getContextPath()%>/expense/addExpense.jsp">Add ITEM</a>
<a class="menuItem" href="<%= request.getContextPath()%>/expense/addExpense.jsp">VIEW ITEMS</a>
<a class="menuItem" href="<%= request.getContextPath()%>/expense/addExpense.jsp">MODIFY ITEM</a>
<a class="menuItem" href="<%= request.getContextPath()%>/expense/addExpense.jsp">DELETE ITEM</a>
</div>


<div id="taskMenu" class="menu">
<a class="menuItem" href="<%=request.getContextPath()%>/contacts/addContact.jsp">TaskInfoView</a>
<a class="menuItem" href="blank.html">Add Task</a>
<a class="menuItem" href="blank.html">View Task History</a>
</div>

<div id="profileMenu" class="menu">
<a class="menuItem" href="changePasswordDisplay.action">ChangePassword</a>
</div>

<div id="toolsMenu" class="menu"  onmouseover="menuMouseover(event)">
<a class="menuItem" href="<%=request.getContextPath()%>/post/createPost.jsp">Create post</a>
<a class="menuItem" href="blank.html">Update Post</a>
<a class="menuItem" href="blank.html">Delete Post</a>
<a class="menuItem" href="<%=request.getContextPath()%>/viewposts.spring">View All Posts</a>
</div>

<div id="logoutMenu" class="menu"     onmouseover="menuMouseover(event)"> <a class="menuItem" href="logout.action">LogOut</a>
</div>
<hr />
</body>
</html>