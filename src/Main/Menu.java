package Main;

public class Menu {
		public static void optionsMenu() 
		{
			System.out.println("\n\t\t\tReceptionist");
			for(int i=0;i<2;i++) {
				if(i==1)
					System.out.println("\t\tWelcome to The Hotel Grand");
				for(int j=0;j<35;j++)
				System.out.print("--");
				System.out.println();
			}
			System.out.println("\t\t1. See Available Rooms");
			System.out.println("\t\t2. Guest Registration");
			System.out.println("\t\t3. Booking By Receptionist (10% OFF)");
			System.out.println("\t\t4. Total Bill of Guest ");
			System.out.println("\t\t5. Add Services for Guest");
			System.out.println("\t\t6. Booking Info ");
			System.out.println("\n\t\t Press \"7\" for exit...\n");
			for(int i=0;i<2;i++) {
				for(int j=0;j<35;j++)
				System.out.print("--");
				System.out.println();
			}
			System.out.print("Enter Your Choice: ");
	}

}
