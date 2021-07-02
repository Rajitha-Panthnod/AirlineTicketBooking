import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Airline {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
	
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Rajitha@123");
			st = con.createStatement();
			st.execute("insert into air_flight values(3173,'MH370','xyz','HYDERABAD','NIRMAL','06:30:00','07:15:00','0:45:00')");
			st.execute("insert into air_flight values(3178,'MH17','abc','NIRMAL','HYDERABAD','08:00:00','09:00:00','01:00:00');");
			st.execute("insert into air_flight values(3172,'AR342','FGH','ADILABAD','CHENNAI','11:30:00','13:00:00','01:30:00');");
			st.execute("insert into air_flight values(3071,'JT564','str','THIRUPATHI','BENGULOOR','08:00:00','10:00:00','02:00:00');");
			st.execute("insert into air_flight values(3170,'DT345','gst','MUMBAI','BENGULOOR','21:00:00','22:30:00','01:30:00');");/			st.execute("alter table passenger_profile auto_increment=274;");
			st.execute("alter table ticket_info auto_increment = 45555;");
			System.out.println("1.Register\n2.Login");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				Registration();
				break;
			case 2:
				System.out.println("Enter mobile number: ");
				int mnum = sc.nextInt();
				System.out.println("Enter password: ");
				String pass = sc.next();
				rs = st.executeQuery("select * from passengerprofile1;");
				while (rs.next()) {
					int compare = rs.getInt("mobile_number");
					String compareTo = rs.getString("password");
					if (mnum == compare && pass.equals(compareTo)) {
						System.out.println("1.Booking\n2.Postponing\n3.History");
						int ch = sc.nextInt();
						switch (ch) {
						case 1:
							Booking();
							Payment();
							break;
						case 2:
							Postpone();
							break;
						case 3:
							History();
							break;
						default:
							System.out.println("Invalid choice");
							break;
						}
					} else {
						System.out.println("No details found");
						break;
					}
				}
			default:
				System.out.println("Invalid choice");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			sc.close();
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void Registration() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Rajitha@123");
			st = con.createStatement();
			System.out.println("Enter First-Name: ");
			String fname = sc.next();
			System.out.println("Enter Last-Name: ");
			String lname = sc.next();
			System.out.println("Enter Address: ");
			String address = sc.next();
			System.out.println("Enter Mobile Number: ");
			int mnumber = sc.nextInt();
			System.out.println("Enter Email: ");
			String email = sc.next();
			System.out.println("Enter password: ");
			String password = sc.next();
			String profile = "insert into passengerprofile(first_name,last_name,address,mobile_number,email_id,password)"
					+ "values(?,?,?,?,?)";
			pst = con.prepareStatement(profile);
			pst.setString(2, password);
			pst.setString(3, fname);
			pst.setString(4, lname);
			pst.setString(5, address);
			pst.setInt(6, mnumber);
			pst.setString(7, email);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void History() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Rajitha@9652");
			st = con.createStatement();
			System.out.println("Details of flight...");
			rs = st.executeQuery("select * from ticket_info");
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getInt(2) + "\t");
			System.out.print(rs.getInt(3) + "\t");
			System.out.print(rs.getDate(4) + "\t");
			System.out.print(rs.getString(5) + "\t");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Postpone() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Rajitha@123");
			st = con.createStatement();
			System.out.println("1.Postpone Date");
			int j = sc.nextInt();
			if (j == 1) {
				System.out.println("Enter flight_id: ");
				int id = sc.nextInt();
				System.out.println("Enter the new date: ");
				int date = sc.nextInt();
				st.executeQuery("Alter table ticket_info1 departure_date=" + date + "where flight_id =" + id);
				st.executeQuery("alter table flight_details1 departure_date=" + date + "where flight_id=" + id);
			} else
				System.out.println("Invalid Choice: ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void FlightDetails() {
		PreparedStatement pst = null; 
		ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Rajitha@123");
			st = con.createStatement();
			String sql = "select * from air_flight1";
			pst = con.prepareStatement(sql);
			rs=pst.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("flight_id") + "  ");
				System.out.print(rs.getString(2) + "  ");
				System.out.print(rs.getString(3) + "  ");
				System.out.println(rs.getString(4) + "  ");
				System.out.print(rs.getString(5) + "  ");
				System.out.print(rs.getTime(6) + "  ");
				System.out.print(rs.getTime(7) + "  ");
				System.out.print(rs.getTime(8) + "  "+"\n");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Payment() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Rajitha@123");
			st = con.createStatement();
			System.out.println("Payment Details");
			System.out.println("Enter card number: ");
			int card = sc.nextInt();
			System.out.println("Enter card type: ");
			String type = sc.next();
			System.out.println("Enter expire month: ");
			int month = sc.nextInt();
			System.out.println("Enter expire year: ");
			int year = sc.nextInt();
			String cardDetails = "insert into credit_card_details1(profile_id,card_number,card_type,expire_month,expire_year)"
					+ "values(?,?,?,?)";
			rs = st.executeQuery("select * from passengerprofile1");
			pst = con.prepareStatement(cardDetails);
			pst.setInt(2, card);
			pst.setString(3, type);
			pst.setInt(4, month);
			pst.setInt(5, year);
			pst.setInt(1, rs.getInt("profile_id"));
			pst.executeUpdate();
			System.out.println("Payment sucessfull");
			String info = "insert into ticket_info1(flight_id,departure_date)" + "values(?,?)";
			pst = con.prepareStatement(info);
			rs = st.executeQuery("select * from flight_details1");
			pst.setInt(3, rs.getInt("flight_id"));
			pst.setDate(4, rs.getDate("departure_date"));
			rs = st.executeQuery("select * from passengerprofile1");
			pst.setInt(2, rs.getInt("profile_id"));
			pst.setString(5, "Active");
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Booking() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Rajitha@123");
			 st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("select * from air_flight1;");
			double random = ThreadLocalRandom.current().nextDouble(3746,9999);
			FlightDetails();
			System.out.println("Enter your choice: ");
			int c = sc.nextInt();
			rs.absolute(c);
			String filling = "insert into flight_details1(flight_id,departure_date,price)" + "values(?,?,?)";
			pst = con.prepareStatement(filling);
			pst.setInt(1, rs.getInt("flight_id"));
			pst.setDate(2, java.sql.Date.valueOf("2021-08-05"));
			pst.setDouble(3,random);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}