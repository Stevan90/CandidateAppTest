package rs.gardnet.CandidateApp.service;

import rs.gardnet.CandidateApp.model.Candidate;
import rs.gardnet.CandidateApp.model.Response;

public interface CandidateService {

	public Response addCandidate(Candidate candidate);
	
	public Response deleteCandidate(int id);
	
	public Candidate getCandidate(int id);
	
	public Candidate[] getAllCandidates();
}
