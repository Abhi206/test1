package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VotingDaoImpl;
import pojos.Voter;


@WebServlet("/validate")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VotingDaoImpl dao;

	
	public void init() throws ServletException {
		try {
			dao = new VotingDaoImpl();
		} catch (Exception e) {
			throw new ServletException("In init " + getClass().getName(), e);
		}
	}

	
	public void destroy() {
		try {
			dao.cleanUp();
		} catch (SQLException e) {
			throw new RuntimeException("In destroy " + getClass().getName());
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()) {
			Voter v = dao.authenticateVoter(request.getParameter("em"), request.getParameter("pass"));
			if(v == null)
				pw.print("<h4> Invalid Login , Pls  <a href='login.html'>Retry</a></h4>");
			else {
				  HttpSession hs = request.getSession();
				  hs.setAttribute("Voter_details", v);
				  response.sendRedirect("details");
			
			}
		} catch (Exception e) {
			throw new ServletException("error in do-get : "+getClass().getName(),e);
		}
	}

}
