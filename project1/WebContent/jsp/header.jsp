<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="epood1.model.data.Product"  %> 
<%@ page import="epood1.model.data.ProductCatalog"  %> 
<%@ page import="epood1.model.DAO.ProductDAO"  %>
<%@ page import="epood1.model.DAO.ProductCatalogDAO"%>
<%@ page import="java.util.List"  %> 
<%@ page import="epood1.model.data.Order"  %>
<jsp:useBean id="ProductCatalogDAO" scope="page" class="epood1.model.DAO.ProductCatalogDAO" />
<jsp:useBean id="specialCatalog" scope="request" class="epood1.model.data.ProductCatalog" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<title>IDU0200 EPOOD1</title>
<body>
<table align="center" width="959px">
  <tr>
    
    <% if(session.getAttribute( "user" ) != null){ %>
	<td align="left">
	<table width="959px">
		<tr>
			<td align="Left">
	         <span class="menu"> Welcome: <b><%  out.println(session.getAttribute("username"));%></b> | 
              <a href="c?mode=ordersearch">Order history</a> | 
              <a href="c?mode=cart">
					<img align="bottom" src="images/shopping_cart.png" Alt="Cart"> ( <%
                 if (session.getAttribute("current_order") == null)
        		   out.println("0");
        	   else
        	   	   out.println(((Order) session.getAttribute("current_order")).getOrderItems().size()); %>)</a> | 
              <a href="c?mode=logout">Logoff</a>
              </span>
            </td> 
          <!--  search -->  
          <!-- c?mode=product&search=1&criteria=toode&productCategoryId=&created=&updated=&priceStart=&priceEnd= -->
            <td align="right" rowspan=2>
				<form method="get" action="c">
					<input name="mode" type="hidden" value="product" />
					<input name="search" type="hidden" value="1" />
					<input name="cId" type="hidden" value=
					<%= (request.getParameter("catalogId")==null)? "0" : request.getParameter("catalogId") %> />
					<input type="text" name="criteria" />
					<input type="submit" value="Search" />
				</form>  
				<span class="menu">  
				 <a href="c?mode=product&form=1&cId=<%= (request.getParameter("catalogId")==null)? "0" : request.getParameter("catalogId") %>">
				 Advanced search</span></a>        
            </td> 
          <!-- end of search -->  
        </tr>
     </table>
    <%} else { %>
    <td align="right">
    <jsp:include page="/jsp/login.jsp" />
    <%} %>	
    
    </td>
  </tr>
  <tr>
  	<td>
  	<img src="images/epood_banner.jpg" Alt="banner">
  	</td>
  </tr>	
</table>


<table align="center" width="959px">
  <tbody>
    <tr>
	<td valign="top">
	
	<!-- Product categories -->
	<div id="rounded">
		<table class="product_catalog">
		<tr>
			<th><span class="product_head">Product Catalog:</span></th>
		</tr>
		
		<tr>
			<td>* <a href='c?mode=product'>All Products</a></td>
		</tr>
		
		<%
		List<ProductCatalog> productList = null;
		try {
	    	productList =  ProductCatalogDAO.getAllCatalogs();
	    	if (productList != null)
	     		for (ProductCatalog catalog : productList) {    
	    	 	 	out.println("<tr><td>* <a href='c?mode=product&catalogId="
	    			  +catalog.getProductCatalog()+"'>"+ catalog.getName() +"</a></tr></td>");
	     		}
	 	} catch(Exception ex) { 
	 		out.println("Error occured");
			if (productList == null) {
				out.println("Query return 0 values  " + productList);
				out.println(ex.getMessage());
			}
		}
	         
	 	%>
		 </table>
	</div>
 	<!-- end of  Product categories -->
 	</td>
 	
 	<!-- content -->
    <td width="80%" align="left" valign="top">
    
    
    