package jdbc;

import java.util.Scanner;

public class HospitalDriver {

	Scanner sc = new Scanner(System.in);

	public HospitalManagement addPatientDetails() {

		System.out.println("Enter Patient id");
		int id = sc.nextInt();

		System.out.println("Enter Patient Name");
		String name = sc.next();

		System.out.println("Enter Patient age");
		int age = sc.nextInt();

		System.out.println("Enter Patient Gender");
		String gender = sc.next();

		System.out.println("Enter Patient Phone Number");
		String phno = sc.next();

		System.out.println("Enter Patient Disease Name");
		String disease = sc.next();

		System.out.println("Enter Patient Admission Date");
		String admission = sc.next();

		HospitalManagement hd = new HospitalManagement(id, name, age, gender, phno, disease, admission);

		return hd;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HospitalDriver h = new HospitalDriver();
		HospitalManagement patiDet = new HospitalManagement();

		System.out.println(
				"Select an Option\n--------------------------\n1. Register Patient\n2. Update Disease By Id\n3. Update Phono Number By Id and name\n4. Fetch All Patient Details\n5. Fetch Patient Details by Disease\n6. Fetch Patient Details by name\n7. Delete Patient record by id\n8. Search Patients\n9. Exit");
		
		int ch = new Scanner(System.in).nextInt();
		
		while (ch != 9) {
			switch (ch) {
			case 1 -> {
                patiDet = h.addPatientDetails();
                patiDet.addPatients();
                System.out.println("Patient added Successfully\n");
            }
			
			case 2 -> patiDet.updateDiseaseById();
			
			case 3 -> patiDet.updatePhnoByIdName();
			
			case 4 -> patiDet.displayPatients();
			
			case 5 -> patiDet.displayPatientByDisease();
			
			case 6 -> patiDet.displayPatientByName();
			
			case 7 -> patiDet.deletePatients();
			
			case 8 -> patiDet.searchPatients();

			default -> System.out.println("Invalid Choice!!!");
			}
			
			System.out.println(
					"Select an Option\n--------------------------\n1. Register Patient\n2. Update Disease By Id\n3. Update Phono Number By Id and name\n4. Fetch All Patient Details\n5. Fetch Patient Details by Disease\n6. Fetch Patient Details by name\n7. Delete Patient record by id\n8. Search Patients\n9. Exit");
			
			ch = new Scanner(System.in).nextInt();
		}
		
		patiDet.closeConnection();
		System.out.println("Thank you for using our Application :) :)\nVisit Again :)");
	}

}
