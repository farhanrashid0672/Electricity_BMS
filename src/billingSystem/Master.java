package billingSystem;

import java.util.Scanner;

public class Master {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		BMS b1 = new BMS();
		
		//b1.connection();
		//b1.createDB();
		//b1.createTable();
		//while(true) {
			System.out.println("Press 1 for Register new consumer");
			System.out.println("Press 2 for View all consumer");
			System.out.print("Enter your choice : ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
			
			case 1 : b1.insertValue();
			break;
			
			case 2 : b1.viewConsumer();
			break;
			
			default : System.out.println("Invalid choice!");
			
			}
			
		//}

	}

}
