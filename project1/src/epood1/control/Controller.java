package epood1.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 16, 2013
 */
public interface Controller {
    public String service(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException;    
}
