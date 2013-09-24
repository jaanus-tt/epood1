<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="epood1.model.data.Product"  %> 
<%@ page import="java.util.List"  %> 
<jsp:useBean id="ProductDAO" scope="page" class="epood1.model.DAO.ProductDAO" />
<jsp:useBean id="product" scope="request" class="epood1.model.data.Product" />

<jsp:include page="/jsp/header.jsp" />

<table align="left" class="products">
	<tr>
		<td><h3>${product.name}</h3></td>
		<td rowspan="4"><img src="images/toode${product.productId}.png"></td>
	</tr>
	<tr>
    	<td colspan="1">${product.description} <br/><br/></td>
    </tr>
	<tr>
    	<td align="left">Price: <b>${product.price}</b> </td>
    </tr>
    <tr>	
    	<td align="right" width="50%">
		    <%if (session.getAttribute( "user" ) != null) { %>
		    <form method="post" action="c?mode=cart">
		          Quantity: 
		          <input type="hidden" name="productId" value="${product.productId}" /> 
		          <input type="text" size="4" name="itemCount" maxlength="2" value="1" style="text-align: right" /> 
		          <input type="submit" value="Add to cart" />
		    </form>
    <%} %>
    	</td>
    </tr>
	
</table>
<jsp:include page="/jsp/footer.jsp" />