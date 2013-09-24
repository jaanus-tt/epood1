<%@ page import="java.util.List"  %>  
<%@ page import="epood1.model.data.Product"  %> 
<%@ page import="epood1.model.data.ProductCatalog"  %>
<%@ page import="epood1.model.DAO.ProductDAO"  %>
<%@ page import="epood1.model.DAO.ProductCatalogDAO"%>

<jsp:useBean id="specialCatalog" scope="request" class="epood1.model.data.ProductCatalog" />
<jsp:include page="/jsp/header.jsp" />

<h4>Product search</h4>

<form method="get" action="c">
<input name="mode" type="hidden" value="product" />
<input name="search" type="hidden" value="1" />
<table>
  <tr>
    <td>Name</td>
    <td><input type="text" name="criteria" value="<%= (request.getParameter("criteria")== null)?"":request.getParameter("criteria") %>"/></td>
  </tr>
   <tr>
    <td>Description</td>
    <td><input type="text" name="description" value="<%= (request.getParameter("description")== null)?"":request.getParameter("description") %>"/></td>
  </tr>
  <tr>
    <td>Product catalog</td>
    <td><select name="cId">
      <option value="0">All</option>
      <%
      	List<ProductCatalog> catalogs = new ProductCatalogDAO().getAllCatalogs();
            String selected="";
            	for (ProductCatalog catalog : catalogs){ 
            		if (Integer.parseInt((request.getParameter("cId").trim())) == catalog.getProductCatalog())
            			selected= " selected ";
            		else
            			selected= "";
      %>
      		<option value="<%= catalog.getProductCatalog() %>" <%= selected %> ><%= catalog.getName() %></option>
      	<% } %>
    </select></td>
  </tr>
  <tr>
    <td>Product added</td>
    <td>
	    <input type="text" name="createdStart"  size="10" value="<%= (request.getParameter("createdStart")== null)?"":request.getParameter("createdStart") %>" />
	    - <input type="text" name="createdEnd" size="10" value="<%= (request.getParameter("createdEnd")== null)?"":request.getParameter("createdEnd") %>" />
    </td>
  </tr>
  <tr>
    <td>Product changed</td>
    <td>
	    <input type="text" name="updatedStart" size="10" value="<%= (request.getParameter("updatedStart")== null)?"":request.getParameter("updatedStart") %>" />
	    - <input type="text" name="updatedEnd" size="10" value="<%= (request.getParameter("updatedEnd")== null)?"":request.getParameter("updatedEnd") %>" />
    </td>
  </tr>
  <tr>
    <td>Price range</td>
    <td>
	   <input type="text" name="priceStart" size="10" value="<%= (request.getParameter("priceStart")== null)?"":request.getParameter("priceStart") %>" />
	   - <input type="text" name="priceEnd" size="10" value="<%= (request.getParameter("priceEnd")== null)?"":request.getParameter("priceEnd") %>" />
    </td>
  </tr>
</table>

<input type="submit" value="Search" /></form>

<table class="products">
<% List<Product> productList = specialCatalog.getProducts();
//	System.out.println(productList);
	if 	(productList.isEmpty()) 
	{
	 	out.println("Server yielded no results");
	}
	else
	{
		for (Product product : productList) 
			{    
	    	 out.println("<tr><td><a href='c?mode=product&productId="
	    		+ product.getProductId()+"'>"
	    		+ product.getName() +"</a></td><td>"
	    		+ product.getPrice() +"</td></tr>");
	   		}
     }
	
 %>
 </table>
<jsp:include page="/jsp/footer.jsp" />