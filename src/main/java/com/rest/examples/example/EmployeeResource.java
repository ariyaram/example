package com.rest.examples.example;

import io.swagger.annotations.SwaggerDefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SwaggerDefinition()
@Path("/employee")

public class EmployeeResource {
	
	static File datFile = null;
	static List<Employee> dataList = null;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		return dataList;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("id") int id) {
		if (dataList != null) {

			for (Employee emp : dataList) {
				if (emp.getId() == id) {
					return emp;
				}
			}
		}
		return null;
	}
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addEmployee(Employee e){
		dataList.add(e);
		return "Post success! ";
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateEmployee(Employee e){
		if(dataList != null){
			Iterator<Employee> empIte = dataList.iterator();
			while (empIte.hasNext()) {
				Employee emp = empIte.next();
				if(emp.getId().intValue() == e.getId().intValue()){
					empIte.remove();
					dataList.add(e);
					return "Update Scussess!";
				}
			}
		}
		
		return null;
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public String deleteEmployee(@PathParam("id") int id){
		if(dataList != null){
			Iterator<Employee> empIte = dataList.iterator();
			while (empIte.hasNext()) {
				Employee emp = empIte.next();
				if(emp.getId().intValue() == id){
					empIte.remove();
					return "delete Scussess!";
				}
			}
		}
		
		return null;
	}
	
	static{
		
		dataList =  new ArrayList<Employee>();
		
		Employee emp = new Employee(1, "Jhon");
		dataList.add(emp);
		
		emp = new Employee(2, "Robert");
		dataList.add(emp);
		
		emp = new Employee(3, "Steve");
		dataList.add(emp);
	}

	public static void main(String[] a){
		System.out.println(dataList);
	}
	
}