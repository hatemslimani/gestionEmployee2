/* Copyright � 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.employees;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.Employeedao;
  
@WebServlet(
        name = "EmployeeServlet",
        urlPatterns = {"/employee"}
)
public class EmployeeServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
    	String action = req.getParameter("searchAction");
        System.out.println("Get Method : Lancement Get avec  "+ action);
        
        if (action!=null){
            switch (action) {           
            case "searchById":
                searchEmployeeById(req, resp);
                break;           
            case "searchByName":
                searchEmployeeByName(req, resp);
                break;
            }
        }else{
        	// Premi�re solution avec la Classe EmployeeList
             List<Employee> result = employeeService.getAllEmployees();
         // DEuxi�me solution avec acces au base de donn�es
          //  List<Employee> result = new  Employeedao().getAll();
            
            
            forwardListEmployees(req, resp, result);
        }
    }

    private void searchEmployeeById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idEmployee = Integer.valueOf(req.getParameter("idEmployee"));
        Employee employee = null;
        try {
        	// Premi�re solution avec la Classe EmployeeList
            employee = employeeService.getEmployee(idEmployee);
            // DEuxi�me solution avec acces au base de donn�es
           /*  
     		try {
     			employee = new Employeedao().getEmployee(idEmployee);
     			
     		} catch (Exception e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}*/
     		
        } catch (Exception ex) {
            Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("employee", employee);
        req.setAttribute("action", "edit");
        
         
        String nextJSP ="/jsp/new-employee.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }
    
    private void searchEmployeeByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String employeeName = req.getParameter("employeeName");
        List<Employee> result = employeeService.searchEmployeesByName(employeeName);  
        
        System.out.println("searchEmployeeByName "+ employeeName);
        forwardListEmployees(req, resp, result);
    }

    @SuppressWarnings("rawtypes")
	private void forwardListEmployees(HttpServletRequest req, HttpServletResponse resp, List employeeList)
            throws ServletException, IOException {
    	 
      String nextJSP = "/jsp/list-employees.jsp";
      
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
          
          
     //     System.out.println("Chemin actuelle : "+ getServletContext().getRealPath("/"));
          
          
          
        req.setAttribute("employeeList", employeeList);
        dispatcher.forward(req, resp);
    }   
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
  
        
        
        System.out.println("Post Method : Lancement Get avec  "+ action);
       
        switch (action) {
            case "add":
                addEmployeeAction(req, resp);
                break;
            case "edit":
                editEmployeeAction(req, resp);
                break;            
            case "remove":
                removeEmployeeByName(req, resp);
                break;            
        }

    }

    private void addEmployeeAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthDate");
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");
        Employee employee = new Employee(name, lastName, birthday, role, department, email);
        long idEmployee = employeeService.addEmployee(employee);
        new Employeedao().addEmployee(employee);
        List<Employee> employeeList = employeeService.getAllEmployees();//new Employeedao().getAll();   //
        
        
        req.setAttribute("idEmployee", idEmployee);
        String message = "The new employee has been successfully created.";
        req.setAttribute("message", message);
        forwardListEmployees(req, resp, employeeList);
    }

    private void editEmployeeAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthDate");
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");
        long idEmployee = Integer.valueOf(req.getParameter("idEmployee"));
        
        
     // Premi�re solution avec la Classe EmployeeList
         Employee employee = new Employee(name, lastName, birthday, role, department, email, idEmployee);
         employee.setId(idEmployee);
        // DEuxi�me solution avec acces au base de donn�es
       /* Employee employee=null;
		try {
			employee = new Employeedao().getEmployee(idEmployee);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        */
       
        boolean success = employeeService.updateEmployee(employee);
        String message = null;
        if (success) {
            message = "The employee has been successfully updated.";
        }
        List<Employee> employeeList = employeeService.getAllEmployees();
        req.setAttribute("idEmployee", idEmployee);
        req.setAttribute("message", message);
        forwardListEmployees(req, resp, employeeList);
    }  

    private void removeEmployeeByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idEmployee = Integer.valueOf(req.getParameter("idEmployee"));
        employeeService.deleteEmployee(idEmployee);
            String message = "The employee has been successfully removed.";
            req.setAttribute("message", message);
        List<Employee> employeeList = employeeService.getAllEmployees();
        forwardListEmployees(req, resp, employeeList);
    }

}
