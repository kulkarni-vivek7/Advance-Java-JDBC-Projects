package jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagement {

	int patient_id, age;
	String name, phno, disease, admission_date;
	String gender;

	PreparedStatement ps = null;

	ResultSet rs = null;

	Scanner sc = new Scanner(System.in);

	Connection4 c = new Connection4();

	public HospitalManagement() {
		// TODO Auto-generated constructor stub
	}

	public HospitalManagement(int patient_id, String name, int age, String gender, String phno, String disease,
			String admission_date) {

		this.patient_id = patient_id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phno = phno;
		this.disease = disease;
		this.admission_date = admission_date;
	}

	public void displayPatients() {
		try {
			ps = c.c.prepareStatement("select * from hms");

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getString(4)
						+ "  " + rs.getString(5) + "  " + rs.getString(6) + "  " + rs.getDate(7) + "\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void addPatients() {
		try {
			ps = c.c.prepareStatement("insert into hms values(?,?,?,?,?,?,?)");

			ps.setInt(1, patient_id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, String.valueOf(gender.charAt(0)));
			ps.setString(5, phno);
			ps.setString(6, disease);
			ps.setDate(7, Date.valueOf(admission_date));

			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void updateDiseaseById() {
		ArrayList<Integer> al = new ArrayList<>();
		ResultSet rs = null;

		try {
			ps = c.c.prepareStatement("select * from hms");
			rs = ps.executeQuery();
			while (rs.next())
				al.add(rs.getInt(1));

			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Enter the Patient id");
		patient_id = sc.nextInt();

		for (int i = 0; i < al.size(); i++) {
			if (patient_id == al.get(i)) {
				System.out.println("\nEnter The Disease Name");
				disease = sc.next();
				try {
					ps = c.c.prepareStatement("update hms set disease = ? where patient_id = ?");
					ps.setString(1, disease);
					ps.setInt(2, patient_id);

					ps.executeUpdate();
					System.out.println("Disease Name Updated Sucessfullyn\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updatePhnoByIdName() {
		ArrayList<Integer> a1 = new ArrayList<>();
		ArrayList<String> a2 = new ArrayList<>();
		ResultSet rs = null;

		try {
			ps = c.c.prepareStatement("select * from hms");
			rs = ps.executeQuery();
			while (rs.next())
				a1.add(rs.getInt(1));

			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			ps = c.c.prepareStatement("select * from hms");
			rs = ps.executeQuery();
			while (rs.next())
				a2.add(rs.getString(2));

			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Enter the Patient id");
		patient_id = sc.nextInt();

		System.out.println("Enter the Patient name");
		name = sc.next();

		for (int i = 0; i < a1.size() && i < a2.size(); i++) {
			if (patient_id == a1.get(i) && name.equalsIgnoreCase(a2.get(i))) {
				System.out.println("\nEnter The Phone Number");
				phno = sc.next();
				try {
					ps = c.c.prepareStatement("update hms set phone = ? where patient_id = ? and name = ?");
					ps.setString(1, phno);
					ps.setInt(2, patient_id);
					ps.setString(3, name);

					ps.executeUpdate();
					System.out.println("Phone Number Updated Sucessfully\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void displayPatientByDisease() {
		try {

			System.out.println("\nEnter The Disease Name");
			disease = sc.next();

			ps = c.c.prepareStatement("select * from hms where disease = ?");

			ps.setString(1, disease);

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getString(4)
						+ "  " + rs.getString(5) + "  " + rs.getString(6) + "  " + rs.getDate(7) + "\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void displayPatientByName() {
		try {

			System.out.println("\nEnter The Patient Name");
			name = sc.next();

			ps = c.c.prepareStatement("select * from hms where name = ?");

			ps.setString(1, name);

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getString(4)
						+ "  " + rs.getString(5) + "  " + rs.getString(6) + "  " + rs.getDate(7) + "\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void deletePatients() {
		ArrayList<Integer> al = new ArrayList<>();

		try {
			ps = c.c.prepareStatement("select * from hms");
			rs = ps.executeQuery();
			while (rs.next())
				al.add(rs.getInt(1));

			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Enter the Patient id");
		patient_id = sc.nextInt();

		for (int i = 0; i < al.size(); i++) {
			if (patient_id == al.get(i)) {
				try {
					ps = c.c.prepareStatement("delete from hms where patient_id = ?");

					ps.setInt(1, patient_id);

					ps.executeUpdate();

					System.out.println("Patient deleted successfully\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void searchPatients() {
		
		System.out.println("Enter the letters of patient name to search");
		String ch = sc.next();

		try {

			ps = c.c.prepareStatement("select * from hms where substring(name,1," + ch.length() + ") = ?");

			ps.setString(1, ch);

			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getString(4)
				+ "  " + rs.getString(5) + "  " + rs.getString(6) + "  " + rs.getDate(7) + "\n");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		if(rs != null)
        {
        	try {
				rs.close();
			} 
        	catch (Exception e) {
				// TODO: handle exception
        		e.printStackTrace();
			}
        }
        
        if(ps != null)
        {
        	try {
				ps.close();
			} 
        	catch (Exception e) {
				// TODO: handle exception
        		e.printStackTrace();
			}
        }
        
        if(c.c != null)
        {
        	try {
				c.c.close();
			} 
        	catch (Exception e) {
				// TODO: handle exception
        		e.printStackTrace();
			}
        }
	}

}
