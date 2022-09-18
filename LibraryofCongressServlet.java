/*
 * @(#)LibraryofCongressServlet.java	1.00 21/11/11
 * 
 */

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;


/**
 * LibraryofCongressServlet.  Inspired by SnoopServlet, incorporates the Libary of Congress Reader.
 * headers that were sent by the client, plus any HTTPS information
 * which is accessible.
 *
 * @version 	1.00
 * @author 	Haris Bhatti
 */
public
class LibraryofCongressServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
    {
	PrintWriter	out;

	res.setContentType("text/html");
	out = res.getWriter ();

	out.println("<html>");
	out.println("<head><title>Library of Congress Servlet</title></head>");
	out.println("<body style='text-align:center;font-size:200%;font-family:courier;background-color:white;color:black;'>");

	  /**
      * @param	HashMap	creates a hashmap taking in specific information
      * @param Info.GiveMeWeb	specifies the number of results and the year to use in the querystring
      */
	
	HashMap<String, LibraryOfCongressItem>webdata = Info.GiveMeWeb(10, 1977);
	LibraryOfCongressItem selecteditem = webdata.get(req.getParameter("TitleButtonGroup"));

	out.println("Title: " + selecteditem.title);
	out.println("<br>");
	out.println("Contributors: " + selecteditem.contributor);
	out.println("<br>");
	out.println("Year: " + selecteditem.date);
	
	    Statement stmnt;
    try {
      Connection con = new OracleConnection().getConnection("hbhat198", "Camper13");
      stmnt=con.createStatement();
      
      // Need to use unique names (Area messed things up!)
      String createString="CREATE TABLE Movies (title VARCHAR2(30), "+
		"contributor VARCHAR2 (70), date NUMBER)";
      stmnt.executeUpdate(createString);
      for (LibraryOfCongressItem item:webdata.values()) {
        // Form the string representing the insertion statement for this state
        // Note use of ' to delimit strings in the SQL statement
        String StateSQLString = ("INSERT INTO Movies VALUES('" +item.title 
	  + "','" + item.contributor + "'," + item.date + ")");
        stmnt.executeUpdate(StateSQLString);
      } // for
      // We can remove the table (use when table needs to go)
       stmnt.executeUpdate("DROP TABLE Movies");
      stmnt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
 
	
	out.println("</body></html>");
    }
	
	

    private void print (PrintWriter out, String name, String value)
    {
	out.print(" " + name + ": ");
	out.println(value == null ? "&lt;none&gt;" : value);
    }

    private void print (PrintWriter out, String name, int value)
    {
	out.print(" " + name + ": ");
	if (value == -1) {
	    out.println("&lt;none&gt;");
	} else {
	    out.println(value);
		
		

		
		
		
	}
	
	
	
	
    }
	
  /**
  * @param	destroy	drops the hash table at the end of finding the data
  */
	
	public void destroy(){
		
	try{
		Statement stmnt;
		Connection con = new OracleConnection().getConnection("hbhat198", "Camper13");
        stmnt=con.createStatement();
		stmnt.executeUpdate("DROP TABLE Movies");
	}
	catch (Exception e)
	
	{
		e.printStackTrace();
	}
	
}

}