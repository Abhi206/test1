package pojos;

public class Voter {
	private int id;
	private String email, password,status;
	public Voter(int id, String email, String password, String status) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", email=" + email + ", status=" + status + "]";
	}
	
}
