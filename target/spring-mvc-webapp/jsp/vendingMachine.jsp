<%-- 
    Document   : vendingMachine
    Created on : Mar 23, 2020, 9:22:05 AM
    Author     : Jeff
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- Styling -->
        <style>
           div {
              text-align: center;
           }
           body {
              padding: 10px;
           }
           h3, h1 {
              text-align: center;
           }
           .insertMoney {
              width: 100px;
           }
           .item {
              height: 170px;
              width: 170px;
              display: inline-block;
              border-style: solid;
              padding-left: 20px;
              margin: 10px 30px 10px 30px;
              float: left;
           }
           .itemHolder {
              margin: auto;
              display: inline-block;
           }
           #squareContainer {
              float: left;
              width: 70%;
           }
           #forms {
              float: left;
              width: 30%;
           }
           input, button {
              margin: 10px 5px 0px 5px;
           }

        </style>
    </head>
    <body>
        <div class="container" style="margin:auto">
            <h1>Vending Machine</h1>
            <hr/>
            <div class="row">
               <!-- Div to hold vending machine inventory and item details. -->
               <div id="squareContainer">
                  <div class="itemHolder" id="inventory">
                      <c:forEach var="currentItem" items="${itemsList}">
                        <div class="col-md-4 item">
                            <div class="row" style="text-align:left">
                               <c:out value="${currentItem.itemId}"/>
                            </div>
                            <div class="row">
                                <a href="selectItem?itemId=${currentItem.itemId}">
                                    <c:out value="${currentItem.name}"/>
                                </a>                               
                            </div>
                            <br>
                            <div class="row">
                               $<c:out value="${currentItem.price}"/>
                            </div>
                            <br>
                            <br>
                            <div class="row">
                               Quantity Left: <c:out value="${currentItem.qty}"/>
                            </div>
                        </div>
                      </c:forEach>
                  </div>
               </div>

               <!-- Div to hold "Total $ in", "Messages", and "Change" forms. -->
               <div id="forms">
                  <div>
                     <h3>Total $ In</h3>
                     <form class="form-horizontal" id="getMoney">
                        <input type="text" id="customerBalance" value="<c:out value="${currentBalance}"/>" disabled>
                        <div>
                           <button type="button" id="addDollar" onclick="location.href='${pageContext.request.contextPath}/addMoney?amount=1.00'">Add Dollar</button>
                           <button type="button" id="addQuarter" onclick="location.href='${pageContext.request.contextPath}/addMoney?amount=0.25'">Add Quarter</button>
                        </div>
                        <div>
                           <button type="button" id="addDime" onclick="location.href='${pageContext.request.contextPath}/addMoney?amount=0.10'">Add Dime</button>
                           <button type="button" id="addNickel" onclick="location.href='${pageContext.request.contextPath}/addMoney?amount=0.05'">Add Nickel</button>
                        </div>
                     </form>
                     <hr />
                  </div>
                  <div>
                     <h3>Messages</h3>
                     <form class="form-horizontal" id="itemSelect" role="form" method="POST" action="makePurchase">
                         <input type="text" name="messages" id="messages" style="width:90%" value="<c:out value="${message}"/>" disabled>
                        <br>
                        <label style="margin-top: 20px" for="itemChoice" class="control-label">Item: </label>
                        <input type="text" name="itemChoice" style="width:10%" value="<c:out value="${selectedItemId}"/>" disabled>
                        <div>
                           <input type="submit" id="makePurchase" value="Make Purchase"/>
                        </div>
                     </form>
                     <hr />
                  </div>
                  <div>
                     <h3>Change</h3>
                     <form class="form-horizontal" id="change" role="form" method="POST" action="returnChange">
                        <input type="text" id="changeAmount" style="width:90%" value="<c:out value="${change}"/>" disabled>
                        <div>
                           <input type="submit" id="changeReturn" value="Change Return"/>
                        </div>
                     </form>
                  </div>
               </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
