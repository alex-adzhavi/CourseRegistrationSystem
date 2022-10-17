import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentInterface, java.io.Serializable {
	
	transient Scanner input; // make it transient so that it doesn't get serialized
	
	private ArrayList<Course> course_list;
	
	public Student() {
	}
	
	public Student(String username, String password, String first_name, String last_name) {
		super.username = username;
		super.password = password;
		super.first_name = first_name;
		super.last_name = last_name;
		course_list = new ArrayList<Course>(); // store the list of courses (objects) for a student
	}
	
	public void student_view_courses() {
		for (int i = 0; i < Data.courses.size(); i++) {
			System.out.println(Data.courses.get(i).printCourseInfo());
		}
	}

	public void view_availableCourses() { // print the course information if current < max
		for (int i = 0; i < Data.courses.size(); i++) {
			if (Data.courses.get(i).getCurrent() < Data.courses.get(i).getMax()) {
				System.out.println(Data.courses.get(i).printCourseInfo());
			}
		}
	}

	public void register_course() { // ask the user for inputs, find the course, then register in a course
		input = new Scanner(System.in);
		System.out.println("What is your full name?");
		String name = input.nextLine();
		System.out.println("What is the course name?");
		String course_name = input.nextLine();
		System.out.println("What is the section?");
		int section = input.nextInt();
		for (int i = 0; i < Data.courses.size(); i++) {
			if (Data.courses.get(i).getName().equals(course_name) && Data.courses.get(i).getSection() == section) {
				Data.courses.get(i).addStudent(name);
				course_list.add(Data.courses.get(i));
			}
		}
		System.out.println("You were successfully registered!");
	}

	public void withdraw_course() { // ask the user for inputs, find the course, then withdraw from the course
		input = new Scanner(System.in);
		System.out.println("What is your full name?");
		String name = input.nextLine();
		System.out.println("What is the course name?");
		String course_name = input.nextLine();
		System.out.println("What is the section?");
		int section = input.nextInt();
		for (int i = 0; i < Data.courses.size(); i++) {
			if ((Data.courses.get(i).getName().equals(course_name)) && (Data.courses.get(i).getSection() == section)) {
				Data.courses.get(i).removeStudent(name);
				for (int j = 0; j < course_list.size(); j++) {
					if (course_list.get(j).getId().equals(Data.courses.get(i).getId()) && course_list.get(j).getSection() == section)
						course_list.remove(j);
				}
				System.out.println("You were successfully withdrawn!");
			}
		}
	}

	public void view_myCourses() {
		for (int i = 0; i < course_list.size(); i++) {
			System.out.println(course_list.get(i).printCourseInfo());
		}
	}
}
