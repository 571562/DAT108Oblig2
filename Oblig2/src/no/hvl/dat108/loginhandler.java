package no.hvl.dat108;

/**
 * @author herbo & sondr
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginhandler
 */
@WebServlet(name="loginhandler" , urlPatterns="/loginhandler")
public class loginhandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sesjon = request.getSession(false);
		PrintWriter out = response.getWriter();
		
		if( sesjon != null) {
			sesjon.invalidate();
		} else {
			sesjon = request.getSession(true);
			sesjon.setMaxInactiveInterval(30);
			sesjon.setAttribute("password", request.getParameter("passord"));
		}
		
		response.setContentType("text/html; charset=ISO-8859-1");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Logg inn</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"loginhandler\" method=\"post\">");
		out.println("<fieldset>");
		out.println("<legend><b>Skriv inn passordet ditt</b></legend>");
		out.println("<p><input type=\"password\" name=\"passord\" placeholder=\"Passord\"/></p>");
		out.println("<p><input type=\"submit\" value=\"Logg inn\"/></p>");
		out.println("</fieldset></form></body></html>");

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String pwin = request.getParameter("passord");
				
		String pw = this.getInitParameter("pass");
		
		if(!pwin.equals(pw)) {
			out.println("<html><body>");
			out.println("<form action=\"loginhandler\" method=\"post\">");
			out.println("<fieldset>");
			out.println("<legend><b>Skriv inn passordet ditt</b></legend>");
			out.println("<p>Passordet du skreiv inn var feil. Prøv på nytt:</p>");
			out.println("<p><input type=\"password\" name=\"passord\" placeholder=\"Passord\"/></p>");
			out.println("<p><input type=\"submit\" value=\"Logg inn\"/></p>");
			out.println("</fieldset></form></body></html>");
		} else {
			HttpSession sesjon = request.getSession(false);
			if(sesjon != null) {
				sesjon.invalidate();
			} else {
				sesjon = request.getSession(true);
				sesjon.setMaxInactiveInterval(30);
				sesjon.setAttribute("password", request.getParameter("passord"));
			}
			
			response.sendRedirect("handlelista");
		}

		
	}

}
