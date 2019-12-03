package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VotingDaoImpl;
import pojos.Candidate;
import pojos.Voter;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/details")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()) {
			VotingDaoImpl dao = new VotingDaoImpl();
			List<Candidate> candidates=dao.getCandidateList();
			if(candidates == null)
				pw.print("<h4>No candidate found</h4>");
			else {
				 HttpSession hs = request.getSession();
				 Voter v = (Voter)hs.getAttribute("Voter_details");
				 if(v.getStatus().equals("1")) {
					 pw.print("You have already voted <br>");
					 pw.print("<h4> Another Login  <a href='login.html'>Retry</a></h4>");
				 }
				 else {
				pw.print("<form action=status>");
				for(Candidate c  : candidates)
					pw.print("<h4><input type=radio name=c_id value="+c.getId()+">"+c.getName()+" "+c.getPoliticalParty()+"<br>");
				pw.print("<input type=submit value='Vote'>");
				pw.print("</form>");
				pw.print("<h4><a href='login.html'>Logout</a></h4>");
//				hs.setAttribute("Candidate_details", candidates);
				 }
			}
		}catch (Exception e) {
			throw new ServletException("In details do-get "+getClass().getName(), e);
		}
	}

}
