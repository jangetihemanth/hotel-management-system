package com.revature.foundation_project.hotel_management.Dao;
import constants.Queries;
import constants.RoomPrices;
import java.util.*;
import java.sql.*;
import com.revature.foundation_project.hotel_management.receptionist.MySqlAccess;
import java.util.Scanner;
import services.GuestServices;
public class ReceptionistDaoImplement implements ReceptionistDao {
	
	Connection conn=MySqlAccess.getConnection();
	Statement s=MySqlAccess.getStatement();
	
	public boolean receptionistLogin(String rLoginID, String rPassword) throws Exception{
		CallableStatement cs = conn.prepareCall(Queries.CALL_LOGIN_PROCEDURE1);
		cs.executeUpdate();
		cs=conn.prepareCall(Queries.CALL_LOGIN_PROCEDURE2);
		cs.setString(1,rLoginID);
		cs.setString(2, rPassword );
		cs.registerOutParameter(3, Types.INTEGER);
		cs.executeQuery();
		return cs.getInt(3)==1;
	}
	
	@Override
	public void seeAvailableRooms() throws Exception {
	PreparedStatement preparedStatement=conn.prepareStatement(Queries.SEE_AVAILABLE_ROOMS);
	preparedStatement.setString(1, "AVAILABLE");
	ResultSet rs=preparedStatement.executeQuery();
	System.out.println("This rooms are currently Available:");
	while(rs.next()) {
		int roomNo=rs.getInt("Room_Number");
		if(roomNo>=101 && roomNo<=105)
		System.out.println(roomNo+" - Normal");
		if(roomNo>=201 && roomNo<=205)
			System.out.println(roomNo+" - Deluxe");
		if(roomNo>=301 && roomNo<=305)
			System.out.println(roomNo+" - Super Deluxe");
		if(roomNo>=401 && roomNo<=405)
			System.out.println(roomNo+" - Executive Suite");
		
	}
	}

	
	@Override
	public void guestRegistration(String guestId,String guestName,
			long guestMobileNumber,String guestAddress,String password) throws Exception {
	PreparedStatement preparedStatement=conn.prepareStatement(Queries.GUEST_REGISTRATION);//register guest
	preparedStatement.setString(1, guestId);
	preparedStatement.setString(2, guestName);
	preparedStatement.setLong(3, guestMobileNumber);
	preparedStatement.setString(4, guestAddress);
	preparedStatement.setString(5, password);
	preparedStatement.executeUpdate();
	System.out.println("Hey! Guest Added Successfully!");
	}

	
	@Override
	public void bookRoom(String guestId,int roomNo,String idProof,String checkInDate,
			String checkOutDate,String reasonForStay) throws Exception {
		ResultSet rs=s.executeQuery("select status from rooms where room_number="+roomNo);
		rs.next();
		if(rs.getString("Status").equals("Available")) { // if room available
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.UPDATE_ROOM_STATUS);//update room status to booked
		preparedStatement.setString(1, "Booked");
		preparedStatement.setInt(2, roomNo);
		preparedStatement.executeUpdate();
		preparedStatement=conn.prepareStatement(Queries.INSERT_BOOKING_INFO);//update booking_information
		preparedStatement.setString(1, guestId);
		preparedStatement.setInt(2, roomNo);
		preparedStatement.setString(3, idProof);
		preparedStatement.setString(4, checkInDate);
		preparedStatement.setString(5, checkOutDate);
		preparedStatement.setString(6, reasonForStay);
		preparedStatement.executeUpdate();
		preparedStatement=conn.prepareStatement(Queries.INSERT_BILL);
		preparedStatement.setString(1, guestId);
		preparedStatement.setInt(2, roomNo);
		preparedStatement.setString(3, "Room");
		preparedStatement.setDouble(4, checkPrice(roomNo));
		preparedStatement.executeUpdate();
		System.out.println("Hey! Room booked Successfully!");
		//}
		
		}
	}

	
	
	@Override
	public void guestServices() throws Exception {
		GuestServices gs=new GuestServices();
		gs.guestServices();
	}

	
	
	@Override
	public String guestBill(String guestId) throws Exception{
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.GUEST_BILL);
		preparedStatement.setString(1, guestId);
		ResultSet rs=preparedStatement.executeQuery();
		rs.next();
		return rs.getString("SUM");
	}

	
	
	@Override
	public List<List<String>> bookingInfo() throws Exception {
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.SELECT_BOOKING_INFO);
		ResultSet rs=preparedStatement.executeQuery();
		List<List<String>> al=new ArrayList<>();
		while(rs.next()) {
			List<String> a=new ArrayList<>();
			a.add(rs.getString("booking_id"));
			a.add(rs.getString("guest_id"));
			a.add(rs.getString("room_number"));
			a.add(rs.getString("id_proof"));
			a.add(rs.getString("checkin_date"));
			a.add(rs.getString("checkout_date"));
			a.add(rs.getString("reason_for_stay"));
			al.add(a);
			
		}
		return al;
	}
	
	
	
	public double checkPrice(int roomNo) {
		double price=0;
		if(roomNo>=101 && roomNo<=105)
			price=RoomPrices.NORMAL_ROOM_PRICE;
		if(roomNo>=201 && roomNo<=205)
			price=RoomPrices.DELUXE_ROOM_PRICE;
		if(roomNo>=301 && roomNo<=305)
			price=RoomPrices.SUPER_DELUXE_ROOM_PRICE;
		if(roomNo>=401 && roomNo<=405)
			price=RoomPrices.EXECUTIVE_SUITE_PRICE;
		return price;
	}
	
	
	public int checkGuest(String guestId)throws Exception {
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.SELECT_GUESTID);
		preparedStatement.setString(1, guestId);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.next()==false) {
			System.out.println("Oh no! user not registered..."); // check whether user exist or not
			return 0;
		}
		else
			return 1;
	}
	
	
	public int checkRoom(int roomNo) throws Exception {
		PreparedStatement preparedStatement=conn.prepareStatement(Queries.GET_ROOM_STATUS);
		preparedStatement.setInt(1, roomNo);
		ResultSet rs=preparedStatement.executeQuery();
		rs.next();
		if(!rs.getString("Status").equals("Available")) { // if room not available
			System.out.println("Oh Ho! The room you are looking for is not available right now...");
			return 0;
		}
		return 1;
	}

}
