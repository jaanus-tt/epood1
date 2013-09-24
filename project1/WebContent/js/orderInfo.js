/**
*
* Javascript for Ajax queries
* Jaanus Ojasoo
*
*/
 
/// @par Implementation 
	var request;

function select_product(product_id) {
	show_form();
	get_product(product_id);
}

function show_form() {	
	if (document.layers) document.layers["response"].visibility="show";
	else document.getElementById("response").style.visibility="visible";
}

function get_product(product_id) {
	GetXmlHttpObject(); 

	var url = "c?mode=ordersearch&orderId=" + product_id;
		url = encodeURI(url);

	if(request!=null)
	{
	    request.onreadystatechange = ProcessRequest;
	    request.open("GET", url, true);
	    request.send(null);
	}
}
function GetXmlHttpObject()
{
    try
    {
        request=new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch(e)
    {
        try
        {
            request=new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch(oc)
        {
            request=null;
        }
    }

    if(!request && typeof XMLHttpRequest!="undefined")
    {
        request = new XMLHttpRequest();
	}
} 

/// @brief Checks if request is complete and HTTP call is successful
function ProcessRequest()
{
 	/// readyState of 4 signifies request is complete
    if (request.readyState == 4)
        {
        /// status of 200 signifies successful HTTP call
    	if (request.status == 200)
            {
            	//if (callback) 
                //callback(request.responseXML);
                if(request.responseText!="")
                {   
                	
                	document.getElementById("response").innerHTML=request.responseText;
  
                }
            }
            else
            {
            	document.getElementById("response").innerHTML = "Cannot get any data:<br>" + request.statusText;
            }
        }
}