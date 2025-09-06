package billingSystem;

import java.util.Scanner;

public class Master {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		BMS b1 = new BMS();
		
		int choice;
        do {
            System.out.println("\n======== Electricity Billing System ========\n");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                	b1.adminLogin();            	
                    break;
                case 2:
					b1.userLogin();
                    break;
                case 3:
                    System.out.println("Exit! Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}	









