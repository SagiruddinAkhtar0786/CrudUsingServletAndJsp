package JDBCFILE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;

//DAO : Data Access Object
public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "Sagir@2580";



	private static final String INSERT_USER_SQL = "INSERT INTO users" + "(name , email , country) VALUES " + "(?,?,?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id= ?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id=?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?, email = ? , country = ?  where id = ?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
//	create or insert User
	public void insertUser(User user) throws SQLException {
//		establishing a connection
		try (Connection connection = getConnection();
//				create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);){
				preparedStatement.setString(1,user.getName());
				preparedStatement.setString(2,user.getEmail());
				preparedStatement.setString(3, user.getCountry());
//				executed the querry/update the Querry
				preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}			
	}
//	update user
	
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);){
				
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getCountry());
				preparedStatement.setInt(4, user.getId());
				
				rowUpdated = preparedStatement.executeUpdate()  > 0;
			
		}
		return rowUpdated;
	}
	
	
//	select user by id
	public User selectUser(int id) throws SQLException {
		User user = null;
//		establishing a connection
		try(Connection connection = getConnection();
//				create a statement using connection object
				PreparedStatement Statement = connection.prepareStatement(SELECT_USER_BY_ID);){
			Statement.setInt(1, id);
				System.out.println("PreparedStatemet : "+Statement);
				
//				executed the querry/update the Querry
				ResultSet rs = Statement.executeQuery();
				
//				Process the result Set
				while(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String country = rs.getString("country");
					user = new User(id, name, email,country);
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;		
	}
	
	
//	select users
	
	public List<User> selectAllUser() throws SQLException {
		List<User> users = new ArrayList<>();
		User user = null;
//		establishing a connection
		try(Connection connection = getConnection();
//				create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
				
				System.out.println("PreparedStatemet : "+preparedStatement);
				
//				executed the querry/update the Querry
				ResultSet rs = preparedStatement.executeQuery();
				
//				Process the result Set
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String country = rs.getString("country");
					
					users.add( new User(id, name, email,country));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;		
	}
//	delete users
	
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
		
	}
}
