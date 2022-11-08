package rs.gardnet.CandidateApp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.gardnet.CandidateApp.DAO.CandidateDAO;
import rs.gardnet.CandidateApp.model.Candidate;

/**
 * Servlet implementation class CandidateServlet
 */
@WebServlet("/")
public class CandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CandidateDAO candidateDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidateServlet() {

    	this.candidateDAO = new CandidateDAO();
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertCandidate(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				errorPage(request, response, "Insert Candidate Error");
			}
			break;
		case "/delete":
			try {
				deleteCandidate(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				errorPage(request, response, "Delete Candidate Error");
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				errorPage(request, response, "Edit Candidate Error");
			}
			break;
		case "/update":
			try {
				updateCandidate(request, response);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
				errorPage(request, response, "Edit Candidate Error");
			}
			break;
		case "/download":
			try {
				downloadCSV(request, response);
			} catch (Exception e) {
				errorPage(request, response, "Download Error");
			}
			break;
		default:
			// handle list
			try {
				listCandidates(request, response);
			} catch (SQLException e) { 
				e.printStackTrace();
				errorPage(request, response, "Database Error");
			}
			
			break;
		}
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidate-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertCandidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, ServletException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String jmbg = request.getParameter("jmbg");
		short birthYear = Short.parseShort(request.getParameter("birthYear"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String note = request.getParameter("note");
		Boolean employed = Boolean.parseBoolean(request.getParameter("employed"));
		Date modificationDate = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());// new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("modificationDate"));
		Candidate candidate = new Candidate(firstName, lastName, jmbg, birthYear, email, phone, note, employed, modificationDate);
		if(candidateDAO.insertCandidate(candidate))
			response.sendRedirect("list");
		else
			errorPage(request, response, "Insert Candidate Error");
	}
	
	private void deleteCandidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(candidateDAO.deleteCandidate(id))
			response.sendRedirect("list");
		else
			errorPage(request, response, "Delete Candidate Error");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Candidate candidate = candidateDAO.selectCandidate(id);
		if(candidate == null) {
			errorPage(request, response, "Edit Candidate Error");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("candidate-form.jsp");
			request.setAttribute("candidate", candidate);
			dispatcher.forward(request, response);
		}
	}
	
	private void updateCandidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String jmbg = request.getParameter("jmbg");
		short birthYear = Short.parseShort(request.getParameter("birthYear"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String note = request.getParameter("note");
		Boolean employed = Boolean.parseBoolean(request.getParameter("employed"));
		Date modificationDate = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());// new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("modificationDate"));
		
		Candidate candidate = new Candidate(id, firstName, lastName, jmbg, birthYear, email, phone, note, employed, modificationDate);
		if(candidateDAO.updateCandidate(candidate))
			response.sendRedirect("list");
		else
			errorPage(request, response, "Edit Candidate Error");
	}
	
	private void listCandidates(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, NullPointerException {
		List<Candidate> listCandidates = candidateDAO.selectAllCandidates();
		if(listCandidates == null) {
			errorPage(request, response, "Database Error");
		} else {
			request.setAttribute("listCandidates", listCandidates);
			RequestDispatcher dispatcher = request.getRequestDispatcher("candidate-list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void downloadCSV(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, NullPointerException {
		int BUFFER = 1024;
		
		response.setBufferSize(BUFFER);
		StringBuilder string = new StringBuilder();
		List<Candidate> candidates = candidateDAO.selectAllCandidates();
		for ( Candidate candidate : candidates) {
			string.append(candidate.getId());
			string.append(",");
			string.append(candidate.getFirstName());
			string.append(",");
			string.append(candidate.getLastName());
			string.append(",");
			string.append(candidate.getJmbg());
			string.append(",");
			string.append(candidate.getBirthYear());
			string.append(",");
			string.append(candidate.getEmail());
			string.append(",");
			string.append(candidate.getPhone());
			string.append(",");
			string.append(candidate.getNote());
			string.append(",");
			string.append(candidate.getEmployed());
			string.append(",");
			string.append(candidate.getModificationDate());
			string.append("\n");
		}
		byte bytes[] = string.toString().getBytes();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + "\"" + "candidates" + ".csv\"");
	    response.getOutputStream().write(bytes);
	    response.setContentLength(bytes.length);
	}
	
	public void errorPage(HttpServletRequest request, HttpServletResponse response, String em) throws ServletException, IOException {
		request.setAttribute("errorMessage", em);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");
		dispatcher.forward(request, response);
	}
}
