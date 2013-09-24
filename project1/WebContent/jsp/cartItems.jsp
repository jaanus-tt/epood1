<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="epood1.model.data.CustomerAddress"  %>
<%@ page import="epood1.model.data.OrderItem"  %> 
<%@ page import="epood1.model.data.Order"  %>
<%@ page import="epood1.model.DAO.CustomerDAO"  %>
<%@ page import="java.util.List"  %> 
<jsp:include page="/jsp/header.jsp" />

<script src="js/remove.js" type="text/javascript"></script>
<%
	Order Order = null;
	Double total = 0.00;
if (session.getAttribute("current_order")!=null)
	Order = (Order) session.getAttribute("current_order");
if (Order == null || Order.getOrderItems().size() == 0) {
	out.println("Your cart is empty.");
} else {
%>
<div>
  <form action="c?mode=cart&clearAll=all" method="post">
    <input type="submit" value="Empty Cart">
  </form>
</div>
<br>

<table class="products">
  <tr>
    <th>Product name</th>
    <th>Quantity</th>
    <th>Price</th>
    <th>Row-total</th>
    <th></th>
  </tr>
  <%
  	for (OrderItem item : Order.getOrderItems()) {
  %>
  	<tr>
      <td><a href='c?mode=product&productId=<%=item.getProduct().getProductId() %>'>
      <%=item.getProduct().getName()%></a>
      </td>
      <td>
        <form action="c?mode=cart" method="post">
          <input name="itemCount" type="text" size="2" maxlength="2" value="<%= item.getItemCount() %>" /> 
          <input name="productId" type="hidden" value="<%= item.getProduct().getProductId() %>" />
          <input type="submit" value="Update" />
        </form>
      </td>
      <td> <%=item.getProduct().getPrice()%>  </td>
      <td> <%=(item.getProduct().getPrice() * item.getItemCount())%>  </td>
       <td>
      	<img src="images/remove.gif" Alt="remove"  onclick="removeItem(<%= item.getProduct().getProductId() %>);" />
      </td>
      
      </tr>
  <%
  	total += item.getProduct().getPrice() * item.getItemCount();
    }
  %>
</table>
<div align="right">  <br/> Total: <b><%=total%></b></div>
<div>
  
  <form action="c?mode=cart&submit=1" method="post">
  Notes:<br>
   <textarea rows="3" cols="25" name="note"></textarea>
   <br>
  <br/>Please select the shipping address: <br/>
    <select name="customerAddressId">
      <%
      	List<CustomerAddress> customerAddr = new CustomerDAO().getCustomerAddresses( (Integer) session.getAttribute("user"));
            for (CustomerAddress address : customerAddr) {
      %>
      		<option value="<%= address.getCustomerAddress() %>"><%= address.getAddress() %></option>
      <%} %>
    </select> 
    
    </br>
    <input type="submit" value="Confirm order">
  </form>
</div>
<%} %>

<jsp:include page="/jsp/footer.jsp" />