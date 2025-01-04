import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        // Input username and password
        System.out.print("Enter your username: ");
        String username = sc.next();

        System.out.print("Enter your password: ");
        String password = sc.next();

        // Path to the users.json file
        String userPath = "./src/main/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(userPath));

        // Validate the user credentials
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;

            if (jsonObject.get("username").equals(username) && jsonObject.get("password").equals(password)) {
                String role = jsonObject.get("role").toString();

                if (role.equals("admin")) {
                    System.out.println("Welcome admin! Please create new questions in the question bank.");
                    Admin admin = new Admin();
                    admin.adminDashboard();
                } else if (role.equals("student")) {
                    System.out.println("Welcome " + username + " to the quiz!");
                    Student student = new Student();
                    student.studentDashboard();
                }
                return;
            }
        }

        System.out.println("Invalid username or password.");
        sc.close();
    }
}
