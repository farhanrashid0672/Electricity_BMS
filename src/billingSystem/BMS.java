package billingSystem;

import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class BMS {
	
	Scanner sc = new Scanner(System.in);

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

            String q = "CREATE DATABASE ebms";

            smt.execute(q);

            System.out.println("Database Created");

            conn.close();
            
        } catch(Exception e) {
        	
            System.out.println("Error " + e);  
        }	
	}
	
	public void createTable() {
		try {		
            String url = "jdbc:mysql://localhost:3306/ebms";
            String user = "root";
            String pass = "080672";

            Connection conn = DriverManager.getConnection(url, user, pass);

            Statement smt = conn.createStatement();

            String q = "CREATE TABLE venders ("
            		+ "vender_id VARCHAR(30) PRIMARY KEY, "
            		+ "name VARCHAR(30) NOT NULL, "
            		+ "phone BIGINT, "
            		+ "email VARCHAR(50) UNIQUE, "
            		+ "address VARCHAR(100), "
            		+ "service_area VARCHAR(30) "
            		+ ")";
       
            smt.execute(q);

            System.out.println("Table Created");

            conn.close();
            
        } catch(Exception e) {
        	
            System.out.println("Error " + e);       
        }	
	}
	
	
	// ============ ADMIN ============
	
	
	public void adminLogin() {
		try {
			System.out.print("Enter Admin Username: ");
	        String user = sc.next();
	        System.out.print("Enter Admin Password: ");
	        String pass = sc.next();
	        
	        if(user.equals("admin") && pass.equals("admin123")) {
	        	System.out.println("\nAdmin Login SuccessfullY!");
	        	adminMenu();
	        } else {
	        	System.out.println("Username or Password Not Match!");
	        }
		} catch(Exception e) {
			System.out.println("Error " + e);
		}
	}
	
	public void adminMenu() {
		while(true) {
	        System.out.println("\n------ Admin Menu ------");
	        System.out.println("1. Register New Consumer");
	        System.out.println("2. Generate Bill");
	        System.out.println("3. View Consumer");
	        System.out.println("4. View All Consumers");
	        System.out.println("5. View Consumer Bill");
	        System.out.println("6. View All Consumers Bill");
	        System.out.println("7. View Complains");
	        System.out.println("8. Update Complains");
	        System.out.println("9. Logout");
	        System.out.print("Enter your choice: ");
	        int option = sc.nextInt();

	        if(option == 1) {
                System.out.println("\n------ Please Register New Consumer------");
                registerConsumer();
                
                int choice;
                while (true) {
                    System.out.println("------ Register New Consumer------");
                    System.out.println("Press 1 for Continue...");
                    System.out.println("Press 2 for exit");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();

                    if (choice == 1) {
                    	registerConsumer();
                    } else if(choice == 2) {
                    	adminMenu();
                    } else {
                    	return;
                    }
                }
	        } else if(option == 2) {
                System.out.println("\n------ Bill Generate ------:");
                billGenerate();
	        } else if(option == 3) {
                System.out.println("\n------ View Customer------:");
                viewConsumer();
	        } else if(option == 4) {
                System.out.println("\n ------ View All Customers ------");
                //viewAllConsumer();
	        } else if(option == 5) {
                System.out.println("\n ------ View Customer Bill ------");
                //viewBill();
	        } else if(option == 6) {
                System.out.println("\n ------ View All Customer Bills ------");
                //viewAllBill();
	        } else if(option == 7) {
                System.out.println("\n ------ View Complains ------");
                //viewComplain();
	        } else if(option == 8) {
                System.out.println("\n ------ Update Complains ------");
                //updateComplain();
	        } else if(option == 9) {
                System.out.println("Admin Logout ....");
                return;
	        } else {
                System.out.println("Invalid choice!");
            }
		}
	}
	
	public void registerConsumer() {
		try {
            String url = "jdbc:mysql://localhost:3306/ebms";
            String user = "root";
            String pass = "080672";

            Connection conn = DriverManager.getConnection(url, user, pass);

            String q = "INSERT INTO customers (consumer_id, consumer_no, name, father_name, phone, aadhar, email, city, dist, state, pin_code, registration_date, connection_type, area_type, require_kw) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?)";
            
            PreparedStatement psmt = conn.prepareStatement(q);
            
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Enter Consumer_Id : ");
            String consumer_id = bfr.readLine();
            psmt.setString(1, consumer_id);
            
            System.out.print("Enter Consumer Number : ");
            String consumer_no = bfr.readLine();
            psmt.setString(2, consumer_no);
            
            System.out.print("Enter your Name : ");
            String name = bfr.readLine();
            psmt.setString(3, name);
            
            System.out.print("Enter your father's Name : ");
            String father_name = bfr.readLine();
            psmt.setString(4, father_name);
            
            System.out.print("Enter your mobile_no : ");
            String phone = bfr.readLine();
            psmt.setString(5, phone);
            
            System.out.print("Enter your Aadhar_no : ");
            String aadhar = bfr.readLine();
            psmt.setString(6, aadhar);
            
            System.out.print("Enter your Email : ");
            String email = bfr.readLine();
            psmt.setString(7, email);
            
            System.out.print("Enter your City : ");
            String city = bfr.readLine();
            psmt.setString(8, city);
            
            System.out.print("Enter your District : ");
            String dist = bfr.readLine();
            psmt.setString(9, dist);
            
            System.out.print("Enter your State : ");
            String state = bfr.readLine();
            psmt.setString(10, state);
            
            System.out.print("Enter your Pin_Code : ");
            String pin_code = bfr.readLine();
            psmt.setString(11, pin_code);
            
            String connection_type = "";
            while (true) {
                System.out.println("Select Connection Type:");
                System.out.println("1. Residential");
                System.out.println("2. Commercial");
                System.out.println("3. Industrial");
                System.out.print("Enter choice (1-3): ");
                int choice = sc.nextInt();
                sc.nextLine(); // newline

                if (choice == 1) {
                    connection_type = "Residential";
                    break;
                } else if (choice == 2) {
                    connection_type = "Commercial";
                    break;
                } else if (choice == 3) {
                    connection_type = "Industrial";
                    break;
                } else {
                    System.out.println("Invalid choice! Please select 1-3.");
                }
            }
            psmt.setString(12, connection_type);
            
            String area_type = "";
            while (true) {
                System.out.println("Select Area Type:");
                System.out.println("1. Rural");
                System.out.println("2. Urban");
                System.out.print("Enter choice (1-2): ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    area_type = "Rural";
                    break;
                } else if (choice == 2) {
                    area_type = "Urban";
                    break;
                } else {
                    System.out.println("Invalid choice! Please select 1 or 2.");
                }
            }
            psmt.setString(13, area_type);
            
            System.out.print("Enter Required KW : ");
            String require_kw = bfr.readLine();
            psmt.setString(14, require_kw);
            
            // Capture Code
            
            int random=0, capture, sum=0;
	        Random rand = new Random();

	        System.out.print("Capture Code: ");
	        
	        for (int i = 0; i < 3; i++) {
	            random = rand.nextInt(10); 
	            System.out.print(random);
	            sum=sum+random;
	        }
	        
	        System.out.println("");
	        System.out.print("Enter the Sum of capture code = ");
	        
	        capture = sc.nextInt();
	        
	        if(sum == capture){
	            psmt.executeUpdate();	            
	            System.out.println("Capture Code Matched"); 	            
	            System.out.println("New Consumer Registered Successfully!\n");            
	        } else {
	            System.out.println("Capture Code Not Matched");
	            System.out.println("No! Data Inserted");
	        }
	        
            conn.close();
            
        } catch(Exception e) {       	
            System.out.println("Error " + e);     
        }
	}
	
	public void billGenerate() {
		try {
            String url = "jdbc:mysql://localhost:3306/ebms";
            String user = "root";
            String pass = "080672";

            Connection conn = DriverManager.getConnection(url, user, pass);

            String q = "INSERT INTO bills (consumer_id, consumer_no, month, curr_unit, prev_unit, unit_consumed, amount, status) VALUES ((SELECT consumer_id FROM customers WHERE consumer_id=?), ?, ?, ?, ?, ?, ?, 'Unpaid')";
            
            PreparedStatement psmt = conn.prepareStatement(q);
            
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
       
            System.out.print("Enter Consumer_Id : ");
            String consumer_id = bfr.readLine();
            psmt.setString(1, consumer_id);
            
            System.out.print("Enter Consumer Number : ");
            String consumer_no = bfr.readLine();
            psmt.setString(2, consumer_no);
            
            System.out.print("Enter Month : ");
            String month = bfr.readLine();
            psmt.setString(3, month);
            
            System.out.print("Enter Current Unit : ");
            int curr_unit = Integer.parseInt(bfr.readLine());
            psmt.setInt(4, curr_unit);
            
            System.out.print("Enter Previus Unit : ");
            int prev_unit = Integer.parseInt(bfr.readLine());
            psmt.setInt(5, prev_unit);
            
            int unit_consumed = curr_unit - prev_unit;
            psmt.setInt(6, unit_consumed);
            
            double amount = 0;
             
            if(unit_consumed <= 300) {
            	
            	amount = unit_consumed * 7.0;
            
            } else if(unit_consumed <= 500) {
            	
            	amount = unit_consumed * 9.0;   
            	
            } else {
            	
            	amount = unit_consumed * 12.0;
            }
            
            psmt.setDouble(7, amount);
           
            psmt.executeUpdate();
            
            System.out.println("Bill Generated Successfully!");
            
            conn.close();
            
        } catch(Exception e) {
        	
            System.out.println("Error " + e);
            
        }
	}
	
	public void viewConsumer() {
		try {
            String url = "jdbc:mysql://localhost:3306/ebms";
            String user = "root";
            String pass = "080672";

            Connection conn = DriverManager.getConnection(url, user, pass);
            
            String q = "SELECT * FROM customers WHERE consumer_no=?";
           
            PreparedStatement psmt = conn.prepareStatement(q);
            
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Enter Consumer Number: ");
            String consumer_no = bfr.readLine();
            psmt.setString(1, consumer_no);
            
            ResultSet rst = psmt.executeQuery();
            
            if (rst.next()) {
            	 System.out.println("Consumer ID: " + rst.getString("consumer_id"));
            	 System.out.println("Consumer Number: " + rst.getString("consumer_no"));
                 System.out.println("Name: " + rst.getString("name"));
                 System.out.println("Father: " + rst.getString("father_name"));
                 System.out.println("Phone: " + rst.getString("phone"));
                 System.out.println("Aadhar: " + rst.getString("aadhar"));
                 System.out.println("Email: " + rst.getString("email"));
                 System.out.println("City: " + rst.getString("city"));
                 System.out.println("District: " + rst.getString("dist"));
                 System.out.println("State: " + rst.getString("state"));
                 System.out.println("Pin: " + rst.getString("pin_code"));
                 System.out.println("Registered: " + rst.getString("registration_date"));
                 System.out.println("Connection Type: " + rst.getString("connection_type"));
                 System.out.println("Area Type: " + rst.getString("area_type"));
                 System.out.println("Require KW: " + rst.getString("require_kw"));
            } else {
            	System.out.println("Consumer not found!");
            }

            conn.close();
            
        } catch(Exception e) {   	
            System.out.println("Error " + e);
        }
	}
	
	public void viewAllConsumers() {
		try {
			 String url = "jdbc:mysql://localhost:3306/ebms";
	         String user = "root";
	         String pass = "080672";

	         Connection conn = DriverManager.getConnection(url, user, pass);
	         
	         conn.close();
	            
		} catch(Exception e) {   	
            System.out.println("Error " + e);
        }
		
	}
	
	public void viewConsumerBill() {
		
	}
	
	public void viewAllConsumerBill() {
		
	}
	
	public void viewComplain() {
		
	}
	
	public void updateComplain() {
		
	}
	
	
	// ============= USER =============
	
	
	public void userLogin() {
		try {
			String url = "jdbc:mysql://localhost:3306/ebms";
	        String user = "root";
	        String pass = "080672";

	        Connection conn = DriverManager.getConnection(url, user, pass);
			
			System.out.print("Enter username (Email) : ");
			String username = sc.next();
			
			System.out.print("Enter your password (Consumer No.) : ");
			String password = sc.next();
			
			String q = "SELECT * FROM customers WHERE email = '"+username+"' and consumer_no = '"+password+"' ";
		    Statement smt = conn.createStatement();
		    ResultSet rst= smt.executeQuery(q);
			
			boolean found = false;
			while (rst.next()) {
				String uname = rst.getString("email");
				String upass = rst.getString("consumer_no");
				if(username.equals(uname) && password.equals(upass)) {
				
					System.out.println("\nUser Login Successfully!");
					userMenu();
					found = true;
					break;
				}
			}
			if(!found) {
				System.out.println("Username or Password Not Match!");				}
		
			conn.close();
		
		} catch(Exception e) {
			System.out.println("Error " + e);
		}
	}
	
	public void userMenu() {
		try {
			while(true) {
			    System.out.println("\n------ User Menu ------");
			    System.out.println("1. View Profile");
			    System.out.println("2. View Bill");
			    System.out.println("3. View Bill Status");
			    System.out.println("4. Pay Bill");
			    System.out.println("5. Register Complain");
			    System.out.println("6. View Complain Status");
			    System.out.println("7. Logout");
			    System.out.print("Enter your choice: ");
			    int option = sc.nextInt();

			    if(option == 1) {
			        System.out.println("View profile :");
			        viewProfile();  
			    } else if(option == 2) {
			        System.out.println("View Bill:");
			        viewBill();
			    } else if(option == 3) {
			        System.out.println("View Bill Status");
			        viewBillStatus();
			    } else if(option == 4) {
			        System.out.println("Pay Bill");
			        payBill();
			    } else if(option == 5) {
			        System.out.println("Register Complain");
			        registerComplain();
			    } else if(option == 6) {
			        System.out.println("View complain status");
			        viewComplainStatus();
			    } else if(option == 7) {
			        System.out.println("User Logout");
			        return;
			    } else {
			        System.out.println("Invalid choice!");
			    }
			}
		} catch(Exception e) {
			System.out.println("Error " + e);
		}	
	}
	
	public void viewProfile() {
		try {
			String url = "jdbc:mysql://localhost:3306/ebms";
			String user = "root";
			String pass = "080672";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.print("Enter Consumer Number : ");
			String consumer_no = sc.next();
			String query = "SELECT * FROM customers WHERE consumer_no=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, consumer_no);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("\n Profile:");
                System.out.println("Consumer No: " + rs.getString("consumer_no"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Father's Name: " + rs.getString("father_name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Address: " + rs.getString("city") + ", " + rs.getString("state"));
                System.out.println("Connection Type: " + rs.getString("connection_type"));
            }
			conn.close();
			
		} catch(Exception e) {
			
			System.out.println("Error" + e);
		}
	}
	
	public void viewBill() {
		try {
			String url = "jdbc:mysql://localhost:3306/ebms";
			String user = "root";
			String pass = "080672";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.print("Enter Consumer Number : ");
			String consumer_no = sc.next();
			String q = "SELECT * FROM bills WHERE consumer_no=?";
            PreparedStatement pst = conn.prepareStatement(q);
            pst.setString(1, consumer_no);
            ResultSet rs = pst.executeQuery();
            System.out.println("\nView Bills:");
            while (rs.next()) {
            	System.out.println("Consumer_No.: " + rs.getString("consumer_no"));
                System.out.println("Month: " + rs.getString("month"));
                System.out.println("Units: " + rs.getInt("unit_consumed"));
                System.out.println("Amount: Rs. " + rs.getDouble("amount"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("-----------------------------");
            }
			
			conn.close();
			
		} catch(Exception e) {
			
			System.out.println("Error" + e);
		}
	}
	
	public void viewBillStatus() {
		
	}
	
	public void payBill() {
		try {
			
			double bill_amount = 0;
			
			String url = "jdbc:mysql://localhost:3306/ebms";
			String user = "root";
			String pass = "080672";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			System.out.print("Enter Consumer Number : ");
			String consumer_no = sc.next();
			
			String q = "SELECT consumer_no, month, amount FROM bills WHERE consumer_no=?";
            PreparedStatement pst = conn.prepareStatement(q);
            
            pst.setString(1, consumer_no);
            ResultSet rs = pst.executeQuery();
            
            System.out.println("\nView Bills:");
            while (rs.next()) {
            	System.out.println("Consumer_No.: " + rs.getString("consumer_no"));
                System.out.println("Month: " + rs.getString("month"));
                System.out.println("Amount: Rs. " + rs.getDouble("amount"));
                System.out.println("");
                bill_amount=rs.getDouble("amount");
            }
            
            String payType = "";
            
            while (true) {
            	System.out.println("Select Payment Mode");
                System.out.println("1. ATM-Card");
                System.out.println("2. UPI");
                System.out.print("Enter choice (1 or 2): ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                int cardNo = 6072;
                int cvv = 123;
                
                if (choice == 1) {
                    payType = "ATM-Card";
                    
                    System.out.print("Enter Last 4 digit of your Card: ");
                    int cardNum = sc.nextInt();
                    System.out.print("Enter CVV Number: ");
                    int cvvNum = sc.nextInt();
                    
                    if(cardNum == cardNo && cvvNum == cvv) {
                    	System.out.print("Enter Payable Amount Rs: ");
                    	double amt = sc.nextDouble();
                    	
                    	//double bill_amount;
                    	
						if(amt == bill_amount) {
                    		System.out.println("Payment Succeddfully");
                    		
                    		String query = "UPDATE bills SET status = 'paid' WHERE consumer_no = ?";
                        	PreparedStatement psmt = conn.prepareStatement(query);
                        	psmt.setString(1, consumer_no);
                        	psmt.executeUpdate();
                    	}
						else {
							System.out.println("Invalid Amount / Payment fail");
						}
                    	
                    	}
                    else {
                    	System.out.println("Invalid ATM No / Payment fail");
                    }
                    
                    break;
                } else if (choice == 2) {
                    payType = "UPI";
                    break;
                } else {
                    System.out.println("Invalid choice! Please select 1 or 2.");
                }
            }
			
			conn.close();
			
		} catch(Exception e) {
			
			System.out.println("Error" + e);
		}
	}
	
	public void registerComplain() {
		
	}
	
	public void viewComplainStatus() {
		
	}
	
	
}
