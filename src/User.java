
public class User {
	private String username;
	private String password;
	private String hint;
	private String gender;
	private String state;

	public User(String username, String password, String hint, String country, String gender) {
		this.username = username;
		this.password = password;
		this.hint = hint;
		this.state = country;
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public String getState() {
		return state;
	}

	public String getGender() {
		return gender;
	}

	public String getPassword() {
		return password;
	}

	public String getHint() {
		return hint;
	}
}
