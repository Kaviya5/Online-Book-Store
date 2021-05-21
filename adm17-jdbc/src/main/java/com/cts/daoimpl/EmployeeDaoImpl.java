package com.cts.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cts.dataAccessOutput.EmployeeDao;
import com.cts.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	
	//database info
	private final String db_username="root";
	private final String db_password="root";
	private final String db_driver="com.mysql.jdbc.Driver";
	private final String db_url="jdbc:mysql://localhost:3306/adm07";
	
	Connection connectionObj=null;
	PreparedStatement pStatement=null;
	
	
	public void addEmployee(Employee empObj) {
		
		try {
			//step-1: load the driver
			Class.forName(db_driver);
			
			//step-2: get the connection
			connectionObj=DriverManager.getConnection(db_url, db_username, db_password);
			
			//step-3: Write your Queries
			String insertQuery="insert into employee_table2 values(?,?,?,?)";
			pStatement=connectionObj.prepareStatement(insertQuery);
			
			/*empObj.setEmpId(1122);
			empObj.setEmpName("Rajini");
			empObj.setSalary(150000.00);
			empObj.setAge(55);*/
			
			pStatement.setInt(1, empObj.getEmpId());
			pStatement.setString(2, empObj.getEmpName());
			pStatement.setDouble(3, empObj.getSalary());
			pStatement.setInt(4, empObj.getAge());
			
			//step-4: execute Statements
			pStatement.executeUpdate();
			System.out.println("Data has been successfully inserted.");
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally {
			//step-5: close the pstatement and connection
			if(pStatement!=null) {
				try {
					pStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connectionObj!=null) {
				try {
					connectionObj.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
