function removeItem(cartItem){
 		var items = [];
		items.push(cartItem);

 if (items.length == 0) {
    alert("You have nothing selected!");
  } else {

    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "c?mode=cart");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "checkedProducts");
    hiddenField.setAttribute("value", items.toString());

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
  }
	
}