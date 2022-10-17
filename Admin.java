import java.util.Scanner;
import java.io.*;

public class Admin extends User implements AdminInterface {
	
	Scanner input = new Scanner(System.in);
	
	public Admin() {
		super.username = "Admin";
		super.password = "Admin001";
	}

	public void create_course() { // ask the user for inputs, create the course, then add it to the data
		System.out.println("What is the course name?");
		String name = input.nextLine();
		System.out.println("What is the course ID?");
		String id = input.next();
		System.out.println("Who is the instructor?");
		input.nextLine();
		String instructor = input.nextLine();
		System.out.println("What is the maximum number of students for this course?");
		int max = input.nextInt();
		int current = 0;
		System.out.println("What is the section of this course?");
		int section = input.nextInt();
		System.out.println("What is the location of this course?");
		input.nextLine();
		String location = input.nextLine();
		Course c = new Course(name, id, instructor, location, max, current, section);
		Data.courses.add(c);
		System.out.println("Course was successfully created!");
	}

	public void delete_course() { // ask the user for inputs, find the course in the data, then remove it
		System.out.println("What is the course ID?");
		String id = input.next();
		System.out.println("What is the section?");
		int section = input.nextInt();
		for (int i = 0; i < Data.courses.size(); i++) {
			if (id.equals(Data.courses.get(i).getId()) && (section == Data.courses.get(i).getSection())){
				Data.courses.remove(i);
				System.out.println("Course was successfully removed!");
				break;
			} 
		}
	}

	public void edit_course() { // ask the user for inputs, find the course, then edit accordingly
		System.out.println("What is the course ID?");
		String id = input.next();
		System.out.println("What is the section?");
		int section = input.nextInt();
		for (int i = 0; i < Data.courses.size(); i++) {
			if (id.equals(Data.courses.get(i).getId()) && (section == Data.courses.get(i).getSection())){
				System.out.println("Which of the following would you like to edit?");
				System.out.println("1) Maximum number of students");
				System.out.println("2) Instructor");
				System.out.println("3) Section");
				System.out.println("4) Location");
				int option = input.nextInt();
				if (option == 1) {
					System.out.println("What is the new maximum number of students?");
					int max = input.nextInt();
					Data.courses.get(i).setMax(max);
					System.out.println("Information successfully altered!");
					break;
				}
				if (option == 2) {
					System.out.println("Who is the new instructor?");
					input.nextLine();
					String instructor = input.nextLine();
					Data.courses.get(i).setInstructor(instructor);
					System.out.println("Information successfully altered!");
					break;
				}
				if (option == 3) {
					System.out.println("What is the new section?");
					int new_section = input.nextInt();
					Data.courses.get(i).setSection(new_section);
					System.out.println("Information successfully altered!");
					break;
				}
				if (option == 4) {
					System.out.println("What is the new location?");
					input.nextLine();
					String location = input.nextLine();
					Data.courses.get(i).setLocation(location);
					System.out.println("Information successfully altered!");
					break;
				}
			} 	
		}
	}

	public void display_course() { // ask the user for inputs, find the course, then print the information
		System.out.println("What is the course ID?");
		String id = input.next();
		System.out.println("What is the section?");
		int section = input.nextInt();
		for (int i = 0; i < Data.courses.size(); i++) {
			if (id.equals(Data.courses.get(i).getId()) && (section == Data.courses.get(i).getSection())){
				System.out.println(Data.courses.get(i).printCourseInfo());
			} 
		}
	}

	public void register_student() { // ask the user for inputs, create a student, then add it to the data
		System.out.println("What is the student's first name?");
		String first_name = input.next();
		System.out.println("What is the student's last name?");
		String last_name = input.next();
		System.out.println("What is the student's username?");
		String username = input.next();
		System.out.println("What is the student's password?");
		String password = input.next();
		Student stu = new Student(username, password, first_name, last_name);
		Data.students.add(stu);
		System.out.println("This student has been officially registered!");
		input.nextLine();
	}
	
	public void view_courses() {
		for (int i = 0; i < Data.courses.size(); i++) {
			System.out.println(Data.courses.get(i).printCourseInfo());
		}
	}

	public void view_fullCourses() { // print the course's information if maximum = current
		for (int i = 0; i < Data.courses.size(); i++) {
			if (Data.courses.get(i).getMax() == Data.courses.get(i).getCurrent()) {
				System.out.println(Data.courses.get(i).printCourseInfo());
			}
		}
	}

	public void file_fullCourses() {
		String file_name = "File/FullCourses.txt";
		try {
			FileWriter fileWriter = new FileWriter(file_name);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < Data.courses.size(); i++) {
				if (Data.courses.get(i).getMax() == Data.courses.get(i).getCurrent()) {
					bufferedWriter.write(Data.courses.get(i).printCourseInfo());
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		}
		catch (IOException ex) {
			System.out.println( "Error writing file '" + file_name + "'");
			ex.printStackTrace();
		}
	}
	
	public void view_registeredStudents() { // ask the user for inputs, find the course, then print the list of names registered
		System.out.println("What is the course ID?");
		String id = input.next();
		System.out.println("What is the section?");
		int section = input.nextInt();
		for (int i = 0; i < Data.courses.size(); i++) {
			if (Data.courses.get(i).getId().equals(id) && (section == Data.courses.get(i).getSection())) {
				Data.courses.get(i).printCourseStudents();
			}
		}
		input.nextLine();
	}

	public void view_studentCourses() {
		System.out.println("What is the student's full name?");
		String name = input.nextLine();
		for (int i = 0; i < Data.students.size(); i++) {
			String full_name = Data.students.get(i).getFirst() + " " + Data.students.get(i).getLast();
			if (name.equals(full_name)) { 
				Data.students.get(i).view_myCourses();
			}
		}
	}

	public void sort_courses() {
		for (int i = 0; i < Data.courses.size(); i++) {
			for (int j = Data.courses.size() - 1; j > i; j--) {
				if (Data.courses.get(i).getCurrent() > Data.courses.get(j).getCurrent()) {
					Course temp = Data.courses.get(i);
					Data.courses.set(i, Data.courses.get(j));
					Data.courses.set(j, temp);
				}
			}
		}
	}
}
