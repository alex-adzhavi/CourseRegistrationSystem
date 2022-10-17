import java.util.Scanner;

public class CRS {
	
	static Scanner input = new Scanner(System.in);
	
	static Admin admin = new Admin(); // there is only one admin
	
	public static void AdminMenu() {
		while (true) {
			System.out.println("Please select one of the following:");
			System.out.println("1) Course Management");
			System.out.println("2) Reports");
			System.out.println("3) Exit");
			int choice = input.nextInt();
			if (choice == 1) {
				while(true) {
					System.out.println("1) Create a new course");
					System.out.println("2) Delete a course");
					System.out.println("3) Edit a course");
					System.out.println("4) Display information for a given course");
					System.out.println("5) Register a student");
					System.out.println("6) Exit");
					int option = input.nextInt();
					if (option == 1)
						admin.create_course();
					if (option == 2)
						admin.delete_course();
					if (option == 3)
						admin.edit_course();
					if (option == 4)
						admin.display_course();
					if (option == 5) 
						admin.register_student();
					if (option == 6)
						break;
				}
			}
			if (choice == 2) {
				while(true) {
					System.out.println("1) View all courses");
					System.out.println("2) View all courses that are full");
					System.out.println("3) Write to a file the list of courses that are full");
					System.out.println("4) View the names of the students being registered in a specific course");
					System.out.println("5) View the list of courses that a given student is being registered on");
					System.out.println("6) Sort courses based on the current number of student registered");
					System.out.println("7) Exit");
					int option = input.nextInt();
					if (option == 1)
						admin.view_courses();
					if (option == 2)
						admin.view_fullCourses();
					if (option == 3)
						admin.file_fullCourses();
					if (option == 4)
						admin.view_registeredStudents();
					if (option == 5) 
						admin.view_studentCourses();
					if (option == 6)
						admin.sort_courses();
					if (option == 7)
						break;
				}
			}
			if (choice == 3) { // when exiting the program, serialize both data points
				System.out.println("Thank you! Have a nice day!");
				Login.CourseSerialize();
				Login.StudentSerialize();
				break;
			}
		}
			
	}
	
	public static void StudentMenu(Student stu) {
		while (true) {
			System.out.println("Please select one of the following:");
			System.out.println("Course Management");
			System.out.println("1) View all courses");
			System.out.println("2) View all courses that are not full");
			System.out.println("3) Register on a course");
			System.out.println("4) Withdraw from a course");
			System.out.println("5) View all courses that the current student is being registered in");
			System.out.println("6) Exit");
			int option = input.nextInt();
			if (option == 1)
				stu.student_view_courses();
			if (option == 2)
				stu.view_availableCourses();
			if (option == 3)
				stu.register_course();
			if (option == 4)
				stu.withdraw_course();
			if (option == 5) 
				stu.view_myCourses();
			if (option == 6) { // when exiting the program, serialize both data points
				Login.CourseSerialize();
				Login.StudentSerialize();
				System.out.println("Thank you! Have a nice day!");
				break;
			}
				
		}	
	}

	public static void main(String[] args) {
		
		Data.students = Login.StudentDeserialize(); // before starting the program, deserialize both data points
		Data.courses = Login.CourseDeserialize();
		
		System.out.println("Welcome to the Course Registration System!");
		System.out.println("Please select one of the following:");
		System.out.println("1) Admin");
		System.out.println("2) Student");
		System.out.println("3) Exit");
		
		int option = input.nextInt();
		
		if (option == 1) {
			while (true) {
				System.out.println("Please enter your username:");
				String username = input.next();
				System.out.println("Please enter your password:");
				String password = input.next();
				if (username.equals(admin.getUser()) && password.equals(admin.getPass())) {
					System.out.println("Login Successfull!");
					AdminMenu();
					break;
				} else {
					System.out.println("You have entered incorrect information, please try again!");
					continue;
				}
			}
		}
		
		if (option == 2) {
			boolean status = true;
			while (status) {
				System.out.println("Please enter your username:");
				String username = input.next();
				System.out.println("Please enter your password:");
				String password = input.next();
				for (int i = 0; i < Data.students.size(); i++) {
					if (username.equals(Data.students.get(i).getUser()) && password.equals(Data.students.get(i).getPass())) {
						System.out.println("Login successfull!");
						Student stu = Data.students.get(i);
						StudentMenu(stu);
						status = false;
						break;					
					}
				}
				
				if (status == true)
					System.out.println("You have entered incorrect information, please try again!");
			}
		}
		
		if (option == 3) {
			System.out.println("Thank you! Have a nice day!");
		}
		
	}

}
