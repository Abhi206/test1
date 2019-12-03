package dao;

import java.util.List;

import pojos.Candidate;
import pojos.Voter;


public interface IVotingDao {
	Voter authenticateVoter(String email, String password) throws Exception;
	List<Candidate> getCandidateList() throws Exception;
	String incVotesUpdateStatus(int candidateId, int voterId) throws Exception;
}
