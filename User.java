public class User implements java.io.Serializable {
	
	protected String username, password, first_name, last_name;
	
	public User() {
	}
	
	public String getUser() {
		return username;
	}
	
	public String getPass() {
		return password;
	}
	
	public String getFirst() {
		return first_name;
	}
	
	public String getLast() {
		return last_name;
	}

}
