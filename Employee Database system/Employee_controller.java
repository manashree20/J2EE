package com.qsp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qsp.model.Employee;

public class Employee_controller {
	static Connection con;
	static String url = "jdbc:postgresql://localhost:5432/qsp";
	static String user = "postgres";
	static String pass = "root";
	static {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println(con);
            } 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insert(Employee e)
   {
	try {
		PreparedStatement ps=con.prepareStatement("insert into employee values(?,?,?)");
		ps.setInt(1, e.getId());
		ps.setString(2, e.getName());
		ps.setDouble(3, e.getSalary());
		ps.executeUpdate();
	    } 
	 catch (SQLException e1){
        e1.printStackTrace();
	    }
    }

	public static boolean updateNameById(int id, String name) 
	{
	  Employee emp = fetchById(id);
	  if (emp.getName() != null) 
	    {
            try {
				PreparedStatement ps=con.prepareStatement("update employee set name=? where id=?");
				ps.setString(1, name);
				ps.setInt(2, id);
				ps.executeUpdate();
			  } 
            catch (SQLException em) {
			 em.printStackTrace();
			  }
            return true;
		}
	  else 
	    {
		  return false;
	    }
	}
	public static boolean updatesalByName(double salary, String name) 
	{
	  Employee emp = fetchByname(name);
	  if (emp.getName() != null) 
	    {
            try {
				PreparedStatement ps=con.prepareStatement("update employee set salary=? where name=?");
				ps.setString(2, name);
				ps.setDouble(1, salary);
				ps.executeUpdate();
			  } 
            catch (SQLException em) {
			 em.printStackTrace();
			  }
            return true;
		}
	  else 
	    {
		  return false;
	    }
	}
	

	public static Employee fetchById(int id)
   {
	   Employee e=new Employee();
	   try{
		PreparedStatement ps = con.prepareStatement("select * from employee where id=?");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			e.setId(rs.getInt("id"));
			e.setName(rs.getString("name"));
			e.setSalary(rs.getDouble("salary"));
		}
	} catch (SQLException e1) { 
		e1.printStackTrace();
    }
	 return e;
   }
	
	public static Employee fetchByname(String name)
	   {
		   Employee e=new Employee();
		   try{
			PreparedStatement ps = con.prepareStatement("select * from employee where name=?");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
			}
		} catch (SQLException e1) { 
			e1.printStackTrace();
	    }
		 return e;
	   }
	  
	   
	public static boolean deleteById(int id)
	{
		Employee e =fetchById(id);
		if (e.getName() !=null) 
		{
			try {
				PreparedStatement ps= con.prepareStatement("delete from employee where id=?");
				ps.setInt(1, id);
				ps.executeUpdate();
			   } 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean deleteById(String name)
	{
		Employee e =fetchByname(name);
		if (e.getName() !=null) 
		{
			try {
				PreparedStatement ps= con.prepareStatement("delete from employee where name=?");
				ps.setString(1, name);
				ps.executeUpdate();
			   } 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public static List<Employee> fetchAll()
	{
		List<Employee> li=new ArrayList<Employee>();
		try {
			PreparedStatement ps=con.prepareStatement("select * from employee");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Employee emp=new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSalary(rs.getDouble("salary"));
				li.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public static void closeConnection() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}