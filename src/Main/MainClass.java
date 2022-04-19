package Main;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.revature.foundation_project.hotel_management.Dao.ReceptionistDaoImplement;

public class MainClass {
	public static void main(String args[]) throws Exception {
		ReceptionistDaoImplement r = new ReceptionistDaoImplement();
		Scanner scan = new Scanner(System.in);
		System.out.print("\nEnter LoginID: ");
		String rLoginID = scan.next();
		System.out.print("\nEnter LoginPassword: ");
		String rPassword = scan.next();
		if (r.receptionistLogin(rLoginID, rPassword)) {
			do {
				Menu.optionsMenu();
				int choice = scan.nextInt();
				switch (choice) {
				case 1:
					r.seeAvailableRooms();
					break;
				case 2:
					String guestId = guestIdVerification();
					System.out.print("\nEnter Guest Name: ");
					String guestName = scan.next();
					guestName += scan.nextLine();
					System.out.print("\nEnter Guest Mobile Number: ");
					long guestMobile = scan.nextLong();
					System.out.print("\nEnter Guest Address: ");
					String guestAddress = scan.next();
					System.out.print("\nAsk Guest to Enter Guest Password: ");
					String guestPassword = scan.next();
					r.guestRegistration(guestId, guestName, guestMobile, guestAddress, guestPassword);
					break;
				case 3:
					System.out.print("\nEnter GuestID: ");
					String guestId2 = scan.next();
					int flag = r.checkGuest(guestId2);
					if (flag == 1) {
						System.out.print("\nEnter Room Number: ");
						int roomNo = scan.nextInt();
						int flag2 = r.checkRoom(roomNo);
						if (flag2 == 1) {
							System.out.print("\nEnter ID Proof: ");
							String idProof = scan.next();
							System.out.print("\nEnter CheckIn Date(Format: YYYY-MM-DD): ");
							String checkInDate = scan.next();
							checkInDate += " 00:00:00";
							System.out.print("\nEnter CheckOut Date(Format: YYYY-MM-DD): ");
							String checkOutDate = scan.next();
							checkOutDate += " 00:00:00";
							System.out.print("\nEnter Reason For Stay: ");
							String reasonForStay = scan.next();
							reasonForStay += scan.nextLine();
							r.bookRoom(guestId2, roomNo, idProof, checkInDate, checkOutDate, reasonForStay);
						}
					}
					break;
				case 4:
					System.out.println("\nEnter Guest ID: ");
					String guestId3 = scan.next();
					String totalAmount = r.guestBill(guestId3);
					System.out.println("The total bill for " + guestId3 + ": =" + totalAmount);
					break;
				case 5:
					r.guestServices();
					break;
				case 6:
					List<List<String>> ls = r.bookingInfo();
					System.out.println(
							"Booking_id\tGuest_id\tRoom_No\t\tID_Proof\tCheckIn_Date\tCheckOut_Date\tReason_For_Stay");
					for (int i = 0; i < ls.size(); i++) {
						for (int j = 0; j < ls.get(i).size(); j++)
							System.out.print(ls.get(i).get(j) + "\t\t");
						System.out.println();
					}
					break;
				case 7:
					scan.close();
					System.out.println("Bye Bye...!!!");
					System.exit(0);
				default:
					System.out.println("Sorry! I didn't get you...");
				}
			} while (true);
		} else
			System.out.println("Sorry! You are not the correct one...!!!");
	}

	public static String guestIdVerification() {
		Scanner scan = new Scanner(System.in);
		int flag = 1;

		String guestId = "";
		do {
			int flag2 = 1;
			System.out.println("(Note: GuestID must 4 to 5 characters and must start with \'G\' follwed by digits.)\n"
					+ "Enter Guest ID:");
			guestId = scan.next();
			if (guestId.length() < 4) {
				System.out.println("Oh ho! Guest ID is not valid. Check Again!");
				flag2 = 0;
			} else if (guestId.charAt(0) != 'G') {
				System.out.println("Oh ho! Guest ID is not valid. Check Again!");
				flag2 = 0;
			}
			for (int i = 1; i < guestId.length(); i++) {
				if (!Character.isDigit(guestId.charAt(i))) {
					System.out.println("Oh ho! Guest ID is not valid. Check Again!");
					flag2 = 0;
					break;
				}
				if (i == guestId.length() - 1)
					flag = 0;
			}
			if (flag2 == 0) {
				System.out.println("Enter \'1\' to Continue or \'0\' to goto Main Menu");
				int coe = scan.nextInt();
				if (coe == 0)
					flag = 0;
				else
					flag = 1;
			}
		} while (flag == 1);
		//scan.close();
		return guestId;
	}
}
