import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Login {
	
	public static void ReadFile(){ // create a method that reads the file with courses data
		String file = "File/MyUniversityCoursesFile.csv";
		String line;
		String[] info = new String[8];
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			while ((line = br.readLine()) != null) {
				int x = 0;
				StringTokenizer s = new StringTokenizer(line,",");
				while(s.hasMoreTokens()) {
					info[x] = s.nextToken();
					x++;
					if (x == 8)
						break;
				}
				Course c = new Course(info[0], info[1], info[5], info[7], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[6]));
				Data.courses.add(c);
			}
			br.close();
		}
		
		catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file " + file + "!");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file " + file + "!");                  
	    }
	}
	
	public static void StudentSerialize() {
		
		try {
			FileOutputStream fos = new FileOutputStream("File/Students.ser");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(Data.students);
			
			oos.close();
			fos.close();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
	
	}
	
	public static void CourseSerialize() {
		
		try {
			FileOutputStream fos = new FileOutputStream("File/Courses.ser");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(Data.courses);
			
			oos.close();
			fos.close();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
	
	}
	
	public static ArrayList<Student> StudentDeserialize() {
		
		try{
		      FileInputStream fis = new FileInputStream("File/Students.ser");
		      
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      ArrayList<Student> obj = (ArrayList<Student>)ois.readObject();
		      ois.close();
		      fis.close();
		      return obj;
		    }
		    catch(IOException | ClassNotFoundException ex) {
		       return Data.students; // if there is an error reading the SER file, just return the already saved data in the Data class
		    }
	}
	
	public static ArrayList<Course> CourseDeserialize() {
		
		try{
		      FileInputStream fis = new FileInputStream("File/Courses.ser");
		      
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      ArrayList<Course> obj = (ArrayList<Course>)ois.readObject();
		      ois.close();
		      fis.close();
		      return obj;
		    }
		    catch(IOException | ClassNotFoundException ex) { // if there is a problem reading the SER file
		       if (Data.courses.size() == 0) { // if there is no data in the courses list, then read the CSV file and return the data
		    	   ReadFile();
			       return Data.courses;
		       } else {
		    	   return Data.courses;
		       }
		    }
		
	}

}
