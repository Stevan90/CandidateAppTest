package rs.gardnet.CandidateApp.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.postgresql.util.PSQLException;

import rs.gardnet.CandidateApp.DAO.CandidateDAO;
import rs.gardnet.CandidateApp.model.Candidate;
import rs.gardnet.CandidateApp.model.Response;

@Path("/candidate")
public class CandidateServiceImpl implements CandidateService{
	
	private CandidateDAO candidateDAO;
	
	public CandidateServiceImpl() {
		this.candidateDAO = new CandidateDAO();
	}

	@Override
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCandidate(Candidate candidate) {
		Response response = new Response();
		System.out.println(candidate.toString());
		try {
			candidate.setModificationDate(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			if(candidate.getId() == 0) {
				response.setStatus(candidateDAO.insertCandidate(candidate));
				if(response.isStatus()) {
					response.setMessage("Candidate created successfully");
				} else {
					response.setMessage("Candidate not created");
				}
			} else {
				response.setStatus(candidateDAO.updateCandidate(candidate));
				if(response.isStatus()) {
					response.setMessage("Candidate update successfully");
				} else {
					response.setMessage("Candidate not updated");
				}
			}
			return response;
		} catch (SQLException e) {
			e.printStackTrace();
			return response;
		}
	}

	@Override
	@DELETE
	@Path("/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCandidate(@javax.ws.rs.PathParam("id") int id) {
		Response response = new Response();
		System.out.println(id);
		try {
			candidateDAO.deleteCandidate(id);
			response.setStatus(true);
			response.setMessage("Candidate Deleted Successfully");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Candidate Doesn't Exists");
			return response;
		}
	}

	@Override
	@GET
	@Path("/{id}/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Candidate getCandidate(@javax.ws.rs.PathParam("id") int id) {
		try {
			return candidateDAO.selectCandidate(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Candidate[] getAllCandidates() {
		List<Candidate> cl = candidateDAO.selectAllCandidates();
		Candidate[] candidates = new Candidate[cl.size()];
		int i = 0;
		for(Candidate c : cl) {
			candidates[i] = c;
			i++;
		}
		return candidates;
	}

}
