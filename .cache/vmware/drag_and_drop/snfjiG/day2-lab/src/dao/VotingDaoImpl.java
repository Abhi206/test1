package dao;

import static utils.DBUtils.fetchConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Candidate;
import pojos.Voter;


public class VotingDaoImpl implements IVotingDao {

	Connection cn;
	PreparedStatement pst1, pst2, pst3, pst4;

	public VotingDaoImpl() throws ClassNotFoundException, SQLException {
		cn = fetchConnection();
		pst1 = cn.prepareStatement("select * from voters where email=? and password =?");
		pst2 = cn.prepareStatement("select * from candidates");
		pst3 = cn.prepareStatement("update candidates set votes = votes+1 where id =?");
		pst4 = cn.prepareStatement("update voters set status = true where id=?");
	}
	
	public void cleanUp() throws SQLException {
		if(pst1 != null)
			pst1.close();
		if(pst2 != null)
			pst2.close();
		if(pst3 != null)
			pst3.close();
		if(pst4 != null)
			pst4.close();
		if(cn != null)
			cn.close();
	}

	@Override
	public Voter authenticateVoter(String email, String password) throws Exception {
		pst1.setString(1, email);
		pst1.setString(2, password);
		try(ResultSet rs = pst1.executeQuery()) {
			if(rs.next())
				return new Voter(rs.getInt(1), email, password, rs.getString(4));
		}
		return null;
	}

	@Override
	public List<Candidate> getCandidateList() throws Exception {
		List<Candidate> list = new ArrayList<Candidate>();
		try(ResultSet rs = pst2.executeQuery()) {
			while(rs.next())
				list.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		return list;
	}

	@Override
	public String incVotesUpdateStatus(int candidateId, int voterId) throws Exception {
		pst3.setInt(1, candidateId);
		pst4.setInt(1, voterId);
		int counter = pst3.executeUpdate();
		int counter1 = pst4.executeUpdate();
		if(counter != 0 && counter1 != 0)
			return "Vote inserted";
		return "Vote insertion failed";
	}

}
