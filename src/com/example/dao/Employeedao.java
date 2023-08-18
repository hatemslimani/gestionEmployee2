package com.example.dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.employees.Employee;

public class Employeedao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/gestEmployee";
	static final String USER = "root";
	static final String PASS = "hatem";
	public Employee getEmployee(long id) throws Exception {
		ResultSet rs = null;
		Employee e = null;
		try {
			Connection conn = null;
			Statement stmt = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * from Employee Where id= " + id;
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				e = new Employee(rs.getString("name"), rs.getString("lastName"), rs.getString("birthDate"),
						rs.getString("role"), rs.getString("department"), rs.getString("email"), rs.getInt("id"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return e;
	}

	public List<Employee> getAll() {

		ResultSet rs = null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			Connection conn = null;
			Statement stmt = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * from Employee ";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee e = new Employee(rs.getString("name"), rs.getString("lastName"), rs.getString("birthDate"),
						rs.getString("role"), rs.getString("department"), rs.getString("email"), rs.getInt("id"));
				employees.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return employees;
	}

	public void addEmployee(Employee obj) {
		try {
			Connection conn = null;
			Statement stmt = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "Insert into Employee(name,lastName,birthDate,role,department,email) Values (";
			sql += "'" + obj.getName() + "'";
			sql += ",'" + obj.getLastName() + "'";
			sql += ",'" + obj.getBirthDate() + "'";
			sql += ",'" + obj.getRole() + "'";
			sql += ",'" + obj.getDepartment() + "'";
			sql += ",'" + obj.getEmail() + "'";
			sql += ")";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void deleteEmployee(int pidEmp) {

		try {
			Connection conn = null;
			Statement stmt = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "Delete from Employee where id= " + pidEmp;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
