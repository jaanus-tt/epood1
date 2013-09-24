<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="epood1.model.data.Order"  %>    
<%@ page import="epood1.model.DAO.OrderDAO"  %>
<%@ page import="java.util.List"  %> 

<jsp:include page="/jsp/header.jsp" />
<script src="js/orderInfo.js" type="text/javascript"></script>

<%
	List<Order> orders = new OrderDAO().getOrdersForCustomer((Integer)session.getAttribute("user"));

if (orders.size()==0) {
	out.println("You haven't order anything yet");
} else {
%>

<br/>
Your previous orders<br/><br/>
<table class="products">
	<tr>
        <th>No.</th>
        <th>Order date</th>
        <th>Order address</th>
        <th>Total</th>
    </tr>
<% for(Order customerOrder : orders) { %>
	<tr>
	<% out.println("<td><a href='javascript:select_product("+ customerOrder.getOrder()+")'>"+customerOrder.getOrder()+"</a></td>");%>
	<td><%= customerOrder.getCustomerConfirmed() %></td>
	<td><%= customerOrder.getShippingAddress().getAddress() %></td>
	<td><%= customerOrder.getOrderTotalPrice() %></td>
	</tr>
<%} %>
<%} %>
</table>
<br/>

<div ID="response" style="visibility:hidden;"></div>

<jsp:include page="/jsp/footer.jsp" />