package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;


public class Database{

	String url;
	Connection con;
	ResultSet RS;

	Map<Character,Integer> grades = new HashMap<Character,Integer>();
	
	Database(String url){this.url = url;}
	
	public Connection getConnection()  {
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Connection Success!");
			return con;
		} 
		catch (SQLException e) {
		System.out.println("Connection Failed!");
		e.printStackTrace();
		
	}
		return null;
	}
	class Schedule{
		String sqlTable;
		String sqlInsert;
		
		Schedule(String table, String insert){
			try {
			this.sqlTable = table;
			PreparedStatement psTable = con.prepareStatement(sqlTable);
			psTable.executeUpdate();
			System.out.println("Table created");}
			catch(SQLException e) {}
			
			this.sqlInsert = insert;
			try {
			PreparedStatement psInsert = con.prepareStatement(sqlInsert);
			psInsert.executeUpdate();}
			catch(SQLException e) {}}
		public void insert(String insert) {
			try {
				PreparedStatement psInsert = con.prepareStatement(insert);
				psInsert.executeUpdate();
			} catch (SQLException e) {}
		}
		public void delete(String delete) {
			try {
				PreparedStatement psInsert = con.prepareStatement(delete);
				psInsert.executeUpdate();} 
				catch (SQLException e) {}
		}
		public void update(String update) {
			try {
				PreparedStatement psInsert = con.prepareStatement(update);
				psInsert.executeUpdate();} 
				catch (SQLException e) {}
		}
	
	}	
	class Students{
		String sqlTable;
		String sqlInsert;
		
		Students(String table, String insert){	
			this.sqlTable = table;
			try {
			PreparedStatement psTable = con.prepareStatement(sqlTable);
			psTable.executeUpdate();}
			catch(SQLException e) {}
			this.sqlInsert = insert;
			try {
			PreparedStatement psInsert = con.prepareStatement(sqlInsert);
			psInsert.executeUpdate();}
			catch(SQLException e) {}}
		public void insert(String insert) {	
			try {
				PreparedStatement psInsert = con.prepareStatement(insert);
				psInsert.executeUpdate();} 
				catch (SQLException e) {}
		}
		public void delete(String delte) {
			try {
				PreparedStatement psInsert = con.prepareStatement(delte);
				psInsert.executeUpdate();} 
				catch (SQLException e) {}
		}

	}
	class Classes{
		String sqlTable;
		String sqlInsert;
		
		Classes(String table, String insert){
			
			this.sqlTable = table;
			try {
			PreparedStatement psTable = con.prepareStatement(sqlTable);
			psTable.executeUpdate();
			}
			catch(SQLException e) {}
			
			this.sqlInsert = insert;
			try {
			PreparedStatement psInsert = con.prepareStatement(sqlInsert);
			psInsert.executeUpdate();
			}
			catch(SQLException e) {}
			}
		public void insert(String insert) {
			
			try {
				PreparedStatement psInsert = con.prepareStatement(insert);
				psInsert.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		public void delete(String delete) {
			
			try {
				PreparedStatement psInsert = con.prepareStatement(delete);
				psInsert.executeUpdate();
			} catch (SQLException e) {}
		}
		
	}
	class Courses{
		String sqlTable;
		String sqlInsert;
		
		Courses(String table, String insert){
			this.sqlTable = table;
			try {
			PreparedStatement psTable = con.prepareStatement(sqlTable);
			psTable.executeUpdate();}
			catch(SQLException e) {}
			this.sqlInsert = insert;
			try {
			PreparedStatement psInsert = con.prepareStatement(sqlInsert);
			psInsert.executeUpdate();}
			catch(SQLException e) {}
			}
	}
		
	class AggregateGrades{
		String sqlTable;
		String sqlInsert;
		
		AggregateGrades(String table, String insert) {
			this.sqlTable = table;
			try {
			PreparedStatement psTable = con.prepareStatement(sqlTable);
			psTable.executeUpdate();
			}
			catch(SQLException e) {}
			
			this.sqlInsert = insert;
			try {
			PreparedStatement psInsert = con.prepareStatement(sqlInsert);
			psInsert.executeUpdate();
			}
			catch(SQLException e) {}
			
		}
		
		public Map<Character,Integer>getAgregateGrades(String sqlQuery){
			Map<Character, Integer>mapAgregateGrades = new HashMap<Character, Integer>();
			String sqlQueryAggregate = sqlQuery;
	
			try {
				PreparedStatement psQuery = con.prepareStatement(sqlQueryAggregate);
				ResultSet RS = psQuery.executeQuery();
				while(RS.next()) {
					mapAgregateGrades.put(RS.getString("grade").charAt(0),RS.getInt("numberStudents"));
				}
			}
			catch(SQLException e) {}
			return mapAgregateGrades;
			
		}
		
	}
	
}
