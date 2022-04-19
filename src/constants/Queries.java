package constants;

public class Queries {
	public static final String SEE_AVAILABLE_ROOMS="SELECT ROOM_NUMBER FROM ROOMS WHERE STATUS=?";
	public static final String GUEST_REGISTRATION="INSERT INTO GUEST(GUEST_ID,GUEST_NAME,"
			+ "GUEST_MOBILE_NO,GUEST_ADDRESS,PASSWORD) VALUES(?,?,?,?,?)";
	public static final String INSERT_BOOKING_INFO="INSERT INTO BOOKING_INFORMATION(GUEST_ID,ROOM_NUMBER,"
			+ "ID_PROOF,CHECKIN_DATE,CHECKOUT_DATE,REASON_FOR_STAY) "
			+ "VALUES((select guest_id from guest where guest_id=?),"
			+ "(select room_number from rooms where room_number=?),?,?,?,?)";
	public static final String UPDATE_ROOM_STATUS="update rooms set status=? where room_number=?";
	public static final String INSERT_BILL="INSERT INTO BILL(GUEST_ID,ROOM_NUMBER,SERVICES_USED,PRICE) "
			+ "VALUES((SELECT GUEST_ID FROM GUEST WHERE GUEST_ID=?),"
			+ "(SELECT ROOM_NUMBER FROM ROOMS WHERE ROOM_NUMBER=?),?,?)";
	public static final String SELECT_GUESTID="select guest_id from guest where guest_id=?";
	public static final String GUEST_BILL="SELECT SUM(PRICE) AS SUM FROM BILL WHERE GUEST_ID=?";
	public static String SELECT_BOOKING_INFO="SELECT * FROM BOOKING_INFORMATION";
	public static String GET_ROOM_STATUS="SELECT STATUS FROM ROOMS WHERE ROOM_NUMBER=?";
	public static String CALL_LOGIN_PROCEDURE1=" set @return_value = 0";
	public static String CALL_LOGIN_PROCEDURE2="call hotel_management.new_procedure(?,?,?)";
	public static String CALL_LOGIN_PROCEDURE3="select @return_value";
	
}
