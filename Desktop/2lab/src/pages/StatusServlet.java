package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VotingDaoImpl;
import pojos.Voter;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			VotingDaoImpl dao = new VotingDaoImpl();
			HttpSession hs = request.getSession();
			Voter v = (Voter) hs.getAttribute("Voter_details");
			pw.print("<h4>Hello " + v.getEmail() + " "
					+ dao.incVotesUpdateStatus(Integer.parseInt(request.getParameter("c_id")), v.getId())+"</h4>");
			hs.invalidate();
			pw.print("<h4><a href='login.html'>Logout</a></h4>");
		} catch (Exception e) {
			throw new ServletException("In status do-get" + getClass().getName(), e);
		}
	}

}
