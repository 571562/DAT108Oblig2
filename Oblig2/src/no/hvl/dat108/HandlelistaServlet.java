package no.hvl.dat108;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

/**
 * 
 * @author herbo & sondr
 *
 */

@WebServlet("/handlelista")
public class HandlelistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Vare> liste = new ArrayList<>();
		HttpSession sesjon = request.getSession(false);

		if (sesjon == null) {
			response.sendRedirect("loginhandler?timeOut");
		} else {
			HandleVogn vogn = (HandleVogn) sesjon.getAttribute("liste");

			response.setContentType("text/html; charset=ISO-8859-1");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Handlelista</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<fieldset><form method=\"post\" action=\"handlelista\">");
			out.println("<legend><b>Mi Handlelista</b></legend>");
			out.println("<p><input type=\"submit\" value=\"Legg til i lista\"/>");
			out.println("<input type=\"text\" name=\"vare\" placeholder=\"Namn på vare...\"/></p>");

			for (Vare vare : vogn.getVarer()) {
				out.println(
						"<p><table><form method=\"post\" action=\"handlelista\"><input type=\"hidden\" name=\"slett\" value=\""
								+ vare.getNamn() + "\"><input type=\"submit\" name=\"" + vare.getNamn()
								+ "\" value=\"Slett\"/>" + vare.getNamn() + "</form></table></p>");
			}
			out.println("</form></fieldset></body></html>");

		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesjon = request.getSession(false);
		PrintWriter out = response.getWriter();

		if (sesjon == null) {
			response.sendRedirect("loginhandler?timeOut");
		} else {

		HandleVogn vogn = (HandleVogn) sesjon.getAttribute("liste");
		String vare = request.getParameter("vare");
		String slett = request.getParameter("slett");
		String vEscaped = escapeHtml(vare);
		String sEscaped = escapeHtml(slett);

	
		if (vEscaped != null && vare.length() > 0) {
			vogn.addVare(vEscaped);
			System.out.println(vEscaped = Character.toUpperCase(vEscaped.charAt(0)) + vEscaped.substring(1)
					+ " er lagt til i lista!"); // Dette har eg med slik at eg ser at alt blir gjort rett!

		} else if (slett != null && slett.length() > 0) {
			vogn.removeVare(sEscaped);
			System.out.println(sEscaped = Character.toUpperCase(sEscaped.charAt(0)) + sEscaped.substring(1)
					+ " er sletta frå lista!"); // Dette har eg med slik at eg ser at alt blir gjort rett!

		}

		response.sendRedirect("handlelista");
	}
}
}
