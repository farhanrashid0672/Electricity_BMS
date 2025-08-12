package billingSystem;

import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class BMS {

	public void connection() {
		try {
			
			String url = "jdbc:mysql://localhost:3306/";
			String user = "root";
			String pass = "080672";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			System.out.println("Database Connected");
			
			conn.close();
			
		} catch(Exception e) {
			
			System.out.println("Error" + e);
			
		}
		
	}

	public void createDB() {
		
		try {
			
            String url = "jdbc:mysql://localhost:3306";
            String user = "root";
            String pass = "080672";

            Connection conn = DriverManager.getConnection(url, user, pass);

            Statement smt = conn.createStatement();

            String q = "CREATE DATABASE EBMS";

            smt.execute(q);

            System.out.println("Database Created");

            conn.close();
            
        } catch(Exception e) {
        	
            System.out.println("Error " + e);
            
        }
		
	}

	public void createTable() {
		
		try {
			
            String url = "jdbc:mysql://localhost:3306/EBMS";
            String user = "root";
            String pass = "080672";

            Connection conn = DriverManager.getConnection(url, user, pass);

            Statement smt = conn.createStatement();

            String q = "CREATE TABLE new_consumer ("
            		+ "Full_Name varchar(30) not null,"
            		+ "Father_Name varchar(30),"
            		+ "Gender varchar(10),"
            		+ "Email varchar(30) unique,"
            		+ "Mobile_no bigint,"
            		+ "Aadhar_no bigint,"
            		+ "Address varchar(50),"
            		+ "Consumer_no varchar(20)"
            		+ ")";

            smt.execute(q);

            System.out.println("Table Created");

            conn.close();
            
        } catch(Exception e) {
        	
            System.out.println("Error " + e);
            
        }
		
	}

	public void insertValue() {
		
		try {
			
            String url = "jdbc:mysql://localhost:3306/ebms";
            String user = "root";
            String pass = "080672";

            Connection conn = DriverManager.getConnection(url, user, pass);

            String q = "INSERT INTO new_consumer"
            		+ "(Full_Name, Father_Name, Gender, Email, Address, Consumer_no)"
            		+ "VALUES"
            		+ "(?, ?, ?, ?, ?, ?)";
            
            PreparedStatement psmt = conn.prepareStatement(q);
            
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Enter your full name : ");
            String Full_Name = bfr.readLine();
            psmt.setString(1, Full_Name);
            
            System.out.print("Enter your father's name : ");
            String Father_Name = bfr.readLine();
            psmt.setString(2, Father_Name);
            
            System.out.print("Enter gender : ");
            String Gender = bfr.readLine();
            psmt.setString(3, Gender);
            
            System.out.print("Enter your Email : ");
            String Email = bfr.readLine();
            psmt.setString(4, Email);
            
//            System.out.print("Enter your mobile_no : ");
//            long Mobile_no = bfr.read();
//            psmt.setLong(5, Mobile_no);
//            
//            System.out.print("Enter your Aadhar_no : ");
//            long Aadhar_no = bfr.read();
//            psmt.setLong(6, Aadhar_no);
            
            System.out.print("Enter your Address : ");
            String Address = bfr.readLine();
            psmt.setString(5, Address);
            
            System.out.print("Enter Consumer Number : ");
            String Consumer_no = bfr.readLine();
            psmt.setString(6, Consumer_no);
            
            // Capture code
            
//            int random=0, capture, sum=0;
//            
//	        Scanner sc = new Scanner(System.in);
//	        Random rand = new Random();
//
//	        System.out.print("Captcha Code: ");
//	        
//	        for (int i = 0; i < 3; i++) {
//	        	
//	            random = rand.nextInt(10); 
//	            
//	            System.out.print(random);
//	            
//	            sum=sum+random;
//	        }
//	        
//	        System.out.println("");
//	        
//	        System.out.print("Enter the Sum of capture code = ");
//	        
//	        capture = sc.nextInt();
//	        
//	        if(sum == capture){
//	        	
	            psmt.executeUpdate();
//	            
//	            System.out.println("Captcha Code Matched"); 
//	            System.out.println("Value Inserted");
//	        } else {
//	            System.out.println("Captcha Code Not Matched");
//	            System.out.println("No! Data Inserted");
//	        }

            conn.close();
            
        } catch(Exception e) {
        	
            System.out.println("Error " + e);
            
        }
		
	}

	

}
