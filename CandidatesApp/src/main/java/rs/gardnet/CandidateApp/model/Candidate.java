package rs.gardnet.CandidateApp.model;

import java.util.Date;


public class Candidate {

	private int id;
	private String firstName;
	private String lastName;
	private String jmbg;
	private short birthYear;
	private String email;
	private String phone;
	private String note;
	private Boolean employed;
	private Date modificationDate;
	
	public Candidate() {
		super();
	}
	
	public Candidate(int id, String firstName, String lastName, String jmbg, short birthYear, String email,
			String phone, String note, Boolean employed, Date modificationDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.birthYear = birthYear;
		this.email = email;
		this.phone = phone;
		this.note = note;
		this.employed = employed;
		this.modificationDate = modificationDate;
	}

	public Candidate(String firstName, String lastName, String jmbg, short birthYear, String email, String phone,
			String note, Boolean employed, Date modificationDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.birthYear = birthYear;
		this.email = email;
		this.phone = phone;
		this.note = note;
		this.employed = employed;
		this.modificationDate = modificationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public short getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(short birthYear) {
		this.birthYear = birthYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public Boolean getEmployed() {
		return employed;
	}
	
	public void setEmployed(Boolean employed) {
		this.employed = employed;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", jmbg=" + jmbg
				+ ", birthYear=" + birthYear + ", email=" + email + ", phone=" + phone + ", note=" + note
				+ ", employed=" + employed + ", modificationDate=" + modificationDate + "]";
	}
	
}
