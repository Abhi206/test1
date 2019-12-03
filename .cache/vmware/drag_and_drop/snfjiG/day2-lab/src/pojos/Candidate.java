package pojos;

public class Candidate {
	private int id;
	private String name, politicalParty;
	private int votes;
	public Candidate(int id, String name, String politicalParty, int votes) {
		super();
		this.id = id;
		this.name = name;
		this.politicalParty = politicalParty;
		this.votes = votes;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPoliticalParty() {
		return politicalParty;
	}
	public int getVotes() {
		return votes;
	}
	@Override
	public String toString() {
		return "Voters [id=" + id + ", name=" + name + ", politicalParty=" + politicalParty + ", votes=" + votes + "]";
	}
	
}
