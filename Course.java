import java.util.ArrayList;

public class Course implements java.io.Serializable {
	
	private String name, id, instructor, location;
	private int max, current, section;
	private ArrayList<String> list; // list of names that are registered in the course
	
	public Course() {
	}
	
	public Course(String name, String id, String instructor, String location, int max, int current, int section) {
		this.name = name;
		this.id = id;
		this.instructor = instructor;
		this.location = location;
		this.max = max;
		this.current = current;
		this.section = section;
		this.list = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getInstructor() {
		return instructor;
	}

	public String getLocation() {
		return location;
	}

	public int getMax() {
		return max;
	}

	public int getCurrent() {
		return current;
	}

	public int getSection() {
		return section;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	public void addStudent(String name) { // method that adds the name of a student when registered
		list.add(name);
		current++;
	}
	
	public void removeStudent(String name) { // method that removes the name of a student when withdrawn
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(name)) {
				list.remove(i);
				current--;
			}
		}
	}
	
	public String printCourseInfo() {
		return "Course name: " + this.name + "\n" + "Course ID: " + this.id + "\n" + "Instructor: " + this.instructor + "\n" +
		"Section: " + this.section + "\n" + "Location: " + this.location + "\n" + "Current number of students: " + this.current + "\n" +
		"Maximum number of students: " + this.max;
	}
	
	public void printCourseStudents() { // print out the names of students registered in the course
		System.out.println("These are the students currently registered in this course:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
}
