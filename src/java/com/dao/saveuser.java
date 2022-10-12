
package com.dao;
import com.helper.connection_provider;
import com.entities.user;
import java.sql.*;

public class saveuser 
{
    private Connection conuser=null;
    public saveuser(Connection con)
    {
        this.conuser = con;
    }
    public boolean saveUserData(user u)
    {
        boolean status = false;
        try{
        String query = "insert into user(firstname,lastname,email,password,role,college) values (?,?,?,?,?,?)";
//        String query = "insert into user values(firstname,lastname,email,password,role) values (?,?,?,?,?)";
        PreparedStatement ps = conuser.prepareStatement(query);
        ps.setString(1, u.getFirstname());
        ps.setString(2, u.getLastname());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getPassword());
        ps.setString(5, u.getRole());
        ps.setString(6, u.getCollege());
        int c = ps.executeUpdate();
        if(c!=0)
        {
            status = true;
        }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return status;
    }
    public user getUserData(String email,String password)
    {
        user u = null;
        try
        {
            String query = "select * from user where email=? and password=?";
            PreparedStatement ps = conuser.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                u = new user();
                String firstname = rs.getString("firstname");
                u.setFirstname(firstname);
                String lastname = rs.getString("lastname");
                u.setLastname(lastname);
                String role = rs.getString("role");
                u.setRole(role);
                String college = rs.getString("college");
                u.setCollege(college);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return u;
    }
}
