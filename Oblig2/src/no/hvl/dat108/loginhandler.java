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
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

/**
 * Servlet implementation class loginhandler
 */
@WebServlet(name = "loginhandler", urlPatterns = "/loginhandler")
public class loginhandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean feilPassord = request.getParameter("feilPassord") != null;
		boolean timeOut = request.getParameter("timeOut") != null;
		
		HttpSession sesjon = request.getSession(false);
		PrintWriter out = response.getWriter();
		String tid = getInitParameter("TimeOut");
		Integer time = Integer.valueOf(tid);
		
		if( sesjon != null) {
			sesjon.invalidate();
		} else {
			sesjon = request.getSession(true);
			
			sesjon.setMaxInactiveInterval(time);
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
		if(feilPassord) {
			out.println("<p>Passordet du skreiv inn var feil. Prøv på nytt:</p>");
		} else if(timeOut) {
			out.println("<p>Du har vert inaktiv for lenge... Logg inn på nytt:</p>");
		}
		out.println("<p><input type=\"password\" name=\"passord\" placeholder=\"Passord\"/></p>");
		out.println("<p><input type=\"submit\" value=\"Logg inn\"/></p>");
		out.println("</fieldset></form></body></html>");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pwin = request.getParameter("passord");
		String pwEscaped = escapeHtml(pwin);

		String pw = this.getInitParameter("pass");
		String tid = getInitParameter("TimeOut");
		Integer time = Integer.valueOf(tid);

		if (!pwEscaped.equals(pw)) {
			response.sendRedirect("loginhandler?feilPassord");
		} else {
			HttpSession sesjon = request.getSession(false);
			if (sesjon != null) {
				sesjon.invalidate();
			} 
				sesjon = request.getSession(true);
				sesjon.setMaxInactiveInterval(time);
				sesjon.setAttribute("liste", new HandleVogn());
				response.sendRedirect("handlelista");
				
			}

		}

	}


