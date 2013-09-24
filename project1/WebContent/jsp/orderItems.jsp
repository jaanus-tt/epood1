<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="epood1.model.data.OrderItem"  %>  

<jsp:useBean id="order" scope="request" class="epood1.model.data.Order" />

<table class="order_head">
  <tr>
    <td class="head">Order details:</b></td>
  </tr>
  <tr>
    <td>Order number: <b>${order.order}</b></td>
  </tr>
  <tr>
    <td>Date ordered: <b>${order.customerConfirmed}</b></td>
  </tr>
  <tr>
    <td>Order address: <b>${order.shippingAddress.address}</b></td>
  </tr>
  <tr>
    <td>Order total price: <b>${order.orderTotalPrice}</b></td>
  </tr>
</table>
<br />
<table class="order">
  <tr>
    <th>Product</th>
    <th>Quantity</th>
    <th>Orig Price</th>
    <th>YOUR prize</th>
    <th>Ale</th>
    <th>Sub-total</th>
  </tr>
	<% for (OrderItem orderItem : order.getOrderItems()) { %>
		<tr>
			<td><a href='c?mode=product&productId=<%= orderItem.getProductId() %>'>
			<%= orderItem.getProduct().getName() %></a></td>
			<td><%= orderItem.getItemCount() %></td>
			<td><%= orderItem.getProduct().getPrice() %></td>
			<td><%= orderItem.getItemPrice() %></td>
			<td><%= order.getAle(orderItem.getProductId()) %>%</td>
			<td><%= order.getRowTotalPrice(orderItem.getProductId()) %></td>
		</tr>	
	<%} %>
	<tr>
		<td colspan="6" align="right"><b>Total:</b>  <%= order.getOrderTotalPrice() %></td>
	</tr>
</table>
Note: <%= order.getNote() %>
