import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Data {

	static String usernamePC = System.getProperty("user.name");
	static File dir;
	static File file;

	public static List<User> putAllRegUsersToList() {
		List<User> list = new LinkedList<>();
		checkFolder();
		checkFile();
		list = fillList();

		return list;
	}

	public static void putNewUserToData(User user) {
		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("C:/Users/" + usernamePC + "/Documents/loginApp/data.txt", true));
			out.println(user.getUsername());
			out.println(user.getPassword());
			out.println(user.getHint());
			out.println(user.getState());
			out.println(user.getGender());
			out.println();
			
		//	System.out.println("uspio");

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void checkFolder() {

		dir = new File("C:/Users/" + usernamePC + "/Documents", "loginApp");

		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	private static void checkFile() {

		file = new File("C:/Users/" + usernamePC + "/Documents/loginApp/data.txt");

		if (!file.exists()) {
			newFile();
		}

	}

	private static void newFile() {

		try {
			file.createNewFile();
		
		} catch (Exception e) {
			System.err.println("Fatalna iznimka, nedostatak resursa, rušim program.");
			System.exit(-1);
		}

	}

	private static List<User> fillList() {

		List<User> list = new LinkedList<>();
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (sc.hasNextLine()) {
			String name = sc.nextLine();
			String pass = sc.nextLine();
			String hint = sc.nextLine();
			String country = sc.nextLine();
			String gender = sc.nextLine();
			sc.nextLine();

			User usr = new User(name, pass, hint, country, gender);
			list.add(usr);
		}
		sc.close();

		return list;
	}

}
