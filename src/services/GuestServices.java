package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import com.revature.foundation_project.hotel_management.receptionist.MySqlAccess;
import constants.Queries;

public class GuestServices {
	Connection conn=MySqlAccess.getConnection();
	public void guestServices() throws Exception {
		System.out.println("Choose from below options:");
		Scanner sc=new Scanner(System.in);
		int flag=1;
		int flag2=1;
		do {
		System.out.println("1. Food");
		System.out.println("2. Beverges");
		System.out.println("3. Laundry");
		System.out.println("4. Cab");
		System.out.println("5. Exit");
		int choice=sc.nextInt();
		if(choice==5) {
			break;
		}
		System.out.print("\nEnter GuestID: ");
		String guestId=sc.next();
		System.out.println("\nEnter RoomNo: ");
		int roomNo=sc.nextInt();
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.SELECT_GUESTID);
		preparedStatement.setString(1, guestId);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.next()==false) {
			System.out.println("Oh no! user not registered..."); // check whether user exist or not
		}
		else {
		switch(choice) {
		case 1:
			System.out.println("\nEnter quantity: ");
			int members=sc.nextInt();
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Food");
			preparedStatement.setInt(4, 500*members);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			preparedStatement.close();
			break;
		case 2:
			System.out.println("\nEnter quantity: ");
			int members1=sc.nextInt();
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Beverges");
			preparedStatement.setInt(4, 250*members1);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			preparedStatement.close();
			break;
		case 3:
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Laundry");
			preparedStatement.setInt(4, 300);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			preparedStatement.close();
			break;
		case 4:
			preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
			preparedStatement.setString(1, guestId);
			preparedStatement.setInt(2, roomNo);
			preparedStatement.setString(3, "Cab");
			preparedStatement.setInt(4, 1000);
			preparedStatement.executeUpdate();
			System.out.println("Hey! Service Added Successfully");
			preparedStatement.close();
			break;
		case 5:
			flag=0;
			sc.close();
		default:
			System.out.println("Sorry! I didn't get you...");
		}
		}
		}while(flag==1);
	}

}
