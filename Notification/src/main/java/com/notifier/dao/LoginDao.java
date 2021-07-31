package com.notifier.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.notifier.model.LoginBean;
import com.notifier.model.User;
import com.notifier.utils.JDBCUtils;

public class LoginDao {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement("select * from user where email = ? and password = ? ")) {
            preparedStatement.setString(1, loginBean.getEmail());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
    
    
    public int getId_(LoginBean loginBean) throws ClassNotFoundException {
        int id_=0;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email = ? and password = ? ")) {
            preparedStatement.setString(1, loginBean.getEmail());
            preparedStatement.setString(2, loginBean.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
 				 id_ =  rs.getInt(1);
 			}

         } catch (SQLException e) {
             // process sql exception
             JDBCUtils.printSQLException(e);
         }
         return id_;
     }
    public String getname(User u) throws ClassNotFoundException {
       String name="";

        try (Connection connection = JDBCUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement("select * from user where user_id=? ")) {
            preparedStatement.setInt(1, u.getUser_id());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
            	name= rs.getString(2);
            }

        } catch (SQLException e) {
            // process sql exception
            JDBCUtils.printSQLException(e);
        }
        return name;
    }
    public String getmobile(User u) throws ClassNotFoundException {
        String mobile="";

         try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
             .prepareStatement("select * from user where user_id=? ")) {
             preparedStatement.setInt(1, u.getUser_id());
             ResultSet rs = preparedStatement.executeQuery();
             if(rs.next())
             {
             	mobile= rs.getString(3);
             }

         } catch (SQLException e) {
             // process sql exception
             JDBCUtils.printSQLException(e);
         }
         return mobile;
     }
    public String getemail(User u) throws ClassNotFoundException {
        String email="";

         try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
             .prepareStatement("select * from user where user_id=? ")) {
             preparedStatement.setInt(1, u.getUser_id());
             ResultSet rs = preparedStatement.executeQuery();
             if(rs.next())
             {
             	email= rs.getString(4);
             }

         } catch (SQLException e) {
             // process sql exception
             JDBCUtils.printSQLException(e);
         }
         return email;
     }
    public String getpass(User u) throws ClassNotFoundException {
        String pass="";

         try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
             .prepareStatement("select * from user where user_id=? ")) {
             preparedStatement.setInt(1, u.getUser_id());
             ResultSet rs = preparedStatement.executeQuery();
             if(rs.next())
             {
             	pass= rs.getString(5);
             }

         } catch (SQLException e) {
             // process sql exception
             JDBCUtils.printSQLException(e);
         }
         return pass;
     }
    
}
        
        
        