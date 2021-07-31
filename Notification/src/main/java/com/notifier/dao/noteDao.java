package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.notifier.model.Note;
import com.notifier.model.User;
import com.notifier.utils.JDBCUtils;

public class noteDao {
    
	private String insertnote = "insert into note (endDate, noteDescription, notename, remainderDate,status,startDate,noteBook_id )"
			+ "values (?, ?, ?,?,?,?,?);";
	
	private String update_note=  "update notes set endDate=?, noteDescription=?, noteName=?, remainderDate=?,status=?,startDate=? where noteBook_id=?";
	
	private String notebook_id = "select * from notebook where user_id=?";
	
	private String note= "select * from note where noteBook_id=?";
	
	public void insertNote(Note note) throws SQLException
	{
		
		try(Connection con = JDBCUtils.getConnection(); 
				PreparedStatement statement = con.prepareStatement(insertnote)){
			statement.setDate(1, JDBCUtils.getSQLDate(note.getEndDate()));
			statement.setString(2, note.getNoteDescription());
			statement.setString(3, note.getNotename());
			statement.setDate(4, JDBCUtils.getSQLDate(note.getRemainderDate()));
			statement.setBoolean(5, note. getStatus());
			statement.setDate(6, JDBCUtils.getSQLDate (note. getStartDate()));
			statement.setInt(7, note. getId());
			
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Insertion status : "+ status);
		}
	}
	
	public void upNote(Note note , int id) throws SQLException
	{
		
		try(Connection con = JDBCUtils.getConnection(); 
				PreparedStatement statement = con.prepareStatement(update_note)){
				
					statement.setDate(1, JDBCUtils.getSQLDate(note.getEndDate()));
					statement.setString(2, note.getNoteDescription());
					statement.setString(3, note.getNotename());
					statement.setDate(4, JDBCUtils.getSQLDate(note.getRemainderDate()));
					statement.setBoolean(5,note.getStatus());
					statement.setDate(6, JDBCUtils.getSQLDate(note.getStartDate()));
					statement.setInt(7, id);
		}
		}
	public int notebookId(User u) throws SQLException
	{
		int id=0;
		System.out.println("notebook id");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(notebook_id)){
			statement.setInt(1, u.getUser_id());
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 id =  rs.getInt(1);
				} 
		}
		return id;
	
}
	public  String startdate(int id) throws SQLException{
		String s_date="";
		System.out.println("note start date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 s_date =  rs.getString(7);
				} 
		}
		
		return s_date;
	
}
	public  String enddate(int id) throws SQLException{
		String e_date="";
		System.out.println("note start date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 e_date =  rs.getString(2);
				} 
		}
		
		return e_date;
	
}
	
	public  String desc(int id) throws SQLException{
		String des="";
		System.out.println("note start date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 des =  rs.getString(3);
				} 
		}
		
		return des;
	
}
	public  String remdate(int id) throws SQLException{
		String r_date="";
		System.out.println("note start date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 r_date =  rs.getString(5);
				} 
		}
		
		return r_date;
	
}
	public  String status(int id) throws SQLException{
		String s="";
		System.out.println("note start date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 s =  rs.getString(6);
				} 
		}
		
		return s;
	
}
	public  String notename(int id) throws SQLException{
		String nname="";
		
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 nname =  rs.getString(4);
				} 
		}
		
		return nname;
	
}
	}

