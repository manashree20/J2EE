package com.qsp.view;

import java.util.List;
import java.util.Scanner;

import com.qsp.controller.Employee_controller;
import com.qsp.model.Employee;

public class Employee_view {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("-----wellcome-------");
		do {
			System.out.println("1--> Insert employee");
			System.out.println("2--> Update the employee name by id");
			System.out.println("3--> Update sal by name");
			System.out.println("4--> Fetch employee by id");
			System.out.println("5--> Fetch by name");
			System.out.println("6--> Fetch all employe");
			System.out.println("7--> Delete employee id ");
			System.out.println("8--> Delete employee by name ");
			System.out.println("9--> Close Connection");
		
			
		switch(sc.nextInt())
		         {
			case 1: {
			          Employee e=new Employee();
			          System.out.println("Enter id");
			          e.setId(sc.nextInt());
			          System.out.println("Enter name ");
			          e.setName(sc.next());
			          System.out.println("Enter salary");
			          e.setSalary(sc.nextDouble());
			          Employee_controller.insert(e);
			          System.out.println("Inserted");
		            }
			        break;
			case 2: {
				      System.out.println("Enter id");
				      int id =sc.nextInt();
				      System.out.println("Enter new name");
				      String newName=sc.next();
				      boolean b = Employee_controller.updateNameById(id, newName);
				      if (b) {
						System.out.println("---Updated----");
					    } else 
					    {
                        System.out.println("---id is not present---");
					    }
                    }
			        break;
			case 3: {
			      System.out.println("Enter name");
			      String name =sc.next();
			      System.out.println("Enter salary");
			      double Newsal =sc.nextDouble();
			      boolean b = Employee_controller.updatesalByName(Newsal, name);
			      if (b) {
					System.out.println("---Updated----");
				    } else 
			      
				    {
                  System.out.println("---id is not present---");
				    }
              }
		        break;
			case 4: {
				     System.out.println("Enter id");
				     Employee e= Employee_controller.fetchById(sc.nextInt());
				     System.out.println(e);
                    }
			        break;
			case 5: {
			     System.out.println("Enter name");
			     Employee e= Employee_controller.fetchByname(sc.next());
			     System.out.println(e);
              }
		        break;
			case 6: {
			    List<Employee> li=Employee_controller.fetchAll();
			    for (Employee e : li) {
					System.out.println(e);
				   }  
               } 
		       break;
			case 7: {
				    System.out.println("Enter id");
				    boolean b = Employee_controller.deleteById(sc.nextInt());
				    if (b) {
						System.out.println("--Deleted---");
					        } 
				    else {
                        System.out.println("id is not present ");
					     }
                   }
			        break;
			case 8: {
			    System.out.println("Enter name");
			    boolean b = Employee_controller.deleteById(sc.next());
			    if (b) {
					System.out.println("--Deleted---");
				        } 
			    else {
                    System.out.println("names is not present ");
				     }
               }
		        break;
		        
			case 9:{
				Employee_controller.closeConnection();
				System.out.println("Connection is closed");
			}
			break;
			
		    default:
			    System.out.println("Wrong Info");
		     }
		   System.out.println("Enter Y to contimue");
		 } while ("Y".equalsIgnoreCase(sc.next()));
//    Employee e= new Employee();
//    e.setId(107);
//    e.setName("xyz");
//    e.setSalary(8999);
//    Employee_controller.insert(e);
//    System.out.println("inserted");
//    Employee e1=Employee_controller.fetchById(107);
//    System.out.println(e1.getName());
//    boolean b= Employee_controller.deleteById(101);
//    System.out.println(b);
	}

}
