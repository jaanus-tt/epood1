<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List"  %>     
<%@ page import="epood1.model.data.Product"  %> 
<%@ page import="epood1.model.DAO.ProductDAO"  %> 
<jsp:useBean id="ProductCatalogDAO" scope="page" class="epood1.model.DAO.ProductCatalogDAO" />
<jsp:useBean id="catalog" scope="request" class="epood1.model.data.ProductCatalog" />

<jsp:include page="/jsp/header.jsp" />

<div class="crumps">
	<%
		List<Product> productList = null;
		if (catalog.getProducts().size() == 0){
			productList =  new ProductDAO().getAllProducts();
			out.println("Catalog: All Products");
		} else {
			productList =  catalog.getProducts();
			out.println("Catalog: "+ catalog.getName());
		}
	%>
  </div></br>
  	
  <%  try {
 		  out.println("<table align='left' class='products'>");
 		  out.println("<tr><th>Thumb</th><th>Product</th><th>Description</th><th>Price</th></tr>");
      for (Product product : productList) {    
    	  out.println("<tr>"
    			  +"<td><img src='images/toode" + product.getProductId() +"_thumb.png' alt='Pilt"+ product.getProductId() +"'></td>"
    			  +"<td><a href='c?mode=product&productId=" + product.getProductId()+"'>" + product.getName() +"</a></td>"
    			  +"<td>" + product.getDescription().substring(0,Math.min(product.getDescription().length(),30)) +"...</td>"
    			  +"<td>" + product.getPrice() +"</td>"
    			  +"</tr>");
      }
 	} catch(Exception ex) { 
		out.println("Error");
		if (productList == null) {
			out.println("No products currently available");
		}
	}
         
 	%>
 	</table>

<jsp:include page="/jsp/footer.jsp" />

