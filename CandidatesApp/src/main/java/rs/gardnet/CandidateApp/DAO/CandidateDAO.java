package rs.gardnet.CandidateApp.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.gardnet.CandidateApp.model.Candidate;

public class CandidateDAO {
	
	private final String url = "jdbc:postgresql://localhost:5432/CandidatesDB";
	private String username = "candidateApp";
	private String password = "Candidate0759!";
	
	private static final String INSERT_CANDIDATE_SQL = "INSERT INTO candidates" + " (first_name, last_name, jmbg, birth_year, email, phone, note, employed, modification_date) VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_CANDIDATE_BY_ID = "SELECT id, first_name, last_name, jmbg, birth_year, email, phone, note, employed, modification_date FROM candidates WHERE id = ?";
	private static final String SELECT_ALL_CANDIDATES = "SELECT * FROM candidates";
	private static final String DELETE_CANDIDATE_SQL = "DELETE FROM candidates WHERE id = ?;";
	private static final String UPDATE_CANDIDATE_SQL = "UPDATE candidates SET first_name = ?, last_name = ?, jmbg = ?, birth_year = ?, email = ?, phone = ?, note = ?, employed = ?, modification_date = ? WHERE id = ?;";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("------------------------------------------");
		
		return connection;
	}
	
	// Insert candidate
	public boolean insertCandidate(Candidate candidate) throws SQLException {
		
		boolean rowInserted;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CANDIDATE_SQL)) {
			
			preparedStatement.setString(1, candidate.getFirstName());
			preparedStatement.setString(2, candidate.getLastName());
			preparedStatement.setString(3, candidate.getJmbg());
			preparedStatement.setShort(4, candidate.getBirthYear());
			preparedStatement.setString(5, candidate.getEmail());
			preparedStatement.setString(6, candidate.getPhone());
			preparedStatement.setString(7, candidate.getNote());
			preparedStatement.setBoolean(8, candidate.getEmployed());
			preparedStatement.setDate(9, new java.sql.Date(candidate.getModificationDate().getTime()));
			
			preparedStatement.executeUpdate();
			
			rowInserted = true;
			
			connection.close();
		} catch (Exception e) {
			rowInserted = false;
			e.printStackTrace();
		}
		
		return rowInserted;
	}
	
	// Update candidate
	public boolean updateCandidate(Candidate candidate) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CANDIDATE_SQL);) {
			
			preparedStatement.setString(1, candidate.getFirstName());
			preparedStatement.setString(2, candidate.getLastName());
			preparedStatement.setString(3, candidate.getJmbg());
			preparedStatement.setShort(4, candidate.getBirthYear());
			preparedStatement.setString(5, candidate.getEmail());
			preparedStatement.setString(6, candidate.getPhone());
			preparedStatement.setString(7, candidate.getNote());
			preparedStatement.setBoolean(8, candidate.getEmployed());
			preparedStatement.setDate(9, new java.sql.Date(candidate.getModificationDate().getTime()));
			preparedStatement.setInt(10, candidate.getId());
			
			rowUpdated = preparedStatement.executeUpdate() > 0;
			
			connection.close();
		}
		
		return rowUpdated;
		
	}
	
	//select candidate by id
	public Candidate selectCandidate(int id) {
		
		 Candidate candidate = null;
		 
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CANDIDATE_BY_ID);) {
					
					preparedStatement.setInt(1, id);
					//System.out.println(preparedStatement);
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						String firstName = rs.getString("first_name");
						String lastName = rs.getString("last_name");
						String jmbg = rs.getString("jmbg");
						short birthYear = rs.getShort("birth_year");
						String email = rs.getString("email");
						String phone = rs.getString("phone");
						String note = rs.getString("note");
						Boolean employed = rs.getBoolean("employed");
						Date modificationDate = rs.getDate("modification_date");
						candidate = new Candidate(id, firstName, lastName, jmbg, birthYear, email, phone, note, employed, modificationDate);
					}
					
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return candidate;
	}
	
	//select candidates
	public List<Candidate> selectAllCandidates() {
		
		List<Candidate> candidates = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CANDIDATES);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String jmbg = rs.getString("jmbg");
				short birthYear = rs.getShort("birth_year");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String note = rs.getString("note");
				Boolean employed = rs.getBoolean("employed");
				Date modificationDate = rs.getDate("modification_date");
				candidates.add(new Candidate(id, firstName, lastName, jmbg, birthYear, email, phone, note, employed, modificationDate));
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return candidates;
	}
	
	//delete candidate
	public boolean deleteCandidate(int id) throws SQLException {
		
		boolean rowDeleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CANDIDATE_SQL);) {
			preparedStatement.setInt(1,  id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowDeleted;
	}

}
