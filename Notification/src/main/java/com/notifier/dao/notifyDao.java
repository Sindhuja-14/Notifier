package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.notifier.model.NoteBook;
import com.notifier.model.User;
import com.notifier.utils.JDBCUtils;

public class notifyDao {
	private String insertUser = "insert into user (userName, mobileNumber, email, password)"
			+ "values (?, ?, ?,?);";
	private  String updateUser = "update user set userName = ?, mobileNumber= ?, email =?, password =?"
			+ "where id=?;";
	private String noteBook ="insert into  notebook  (user_id,notebook_name)"
			+ "values (?, ?);";
	private String notename= "select * from notebook where user_id=?";
	
	private String update_nb= "update notebook set notebook_name=? where user_id=?;";
	
	
	public void inserUser(User user) throws SQLException
	{
		
		try(Connection con = JDBCUtils.getConnection(); 
				PreparedStatement statement = con.prepareStatement(insertUser)){
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getMobileNumber());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Insertion status : "+ status);
		}
	}
	
	public void updateUser(User user) throws SQLException
	{
		
		try(Connection con = JDBCUtils.getConnection(); 
				PreparedStatement statement = con.prepareStatement( updateUser)){
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getMobileNumber());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Insertion status : "+ status);
		}
	}
	public void addNoteBook(NoteBook notebook) throws SQLException
	{
		
		try(Connection con = JDBCUtils.getConnection(); 
				PreparedStatement statement = con.prepareStatement(noteBook)){
			statement.setInt(1, notebook.getId_());
			statement.setString(2, notebook.getNotebook_name());
			
			
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Insertion status : "+ status);
		}
	}
	
	public String book(User u) throws SQLException
	{
		 String note_name="";
	
		try(Connection con = JDBCUtils.getConnection(); 
				PreparedStatement statement = con.prepareStatement(notename)){
			statement.setInt(1, u.getUser_id());
			ResultSet rs= statement.executeQuery();
			if(rs.next())
			{
				note_name= rs.getString(3);
			}
		}
		return note_name;
	}
	
	public void updatenb(NoteBook nb) throws SQLException
	{
		
		try(Connection con = JDBCUtils.getConnection(); 
				PreparedStatement statement = con.prepareStatement( update_nb)){
			statement.setInt(2, nb.getId_());
			statement.setString(1, nb.getNotebook_name());
			
			
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Insertion status : "+ status);
		}
	}
	
}
