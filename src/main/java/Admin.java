
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {

    // Method to display the admin dashboard for adding quiz questions
    public void adminDashboard() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        // Path to the quiz.json file
        String quizPath = "./src/main/resources/quiz.json";

        // Parse the existing quiz.json file to load questions
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(quizPath));

        while (true) {
            // Input quiz details from the admin
            System.out.println("Input your question:");
            String question = sc.nextLine();

            System.out.println("Input option 1:");
            String option1 = sc.nextLine();

            System.out.println("Input option 2:");
            String option2 = sc.nextLine();

            System.out.println("Input option 3:");
            String option3 = sc.nextLine();

            System.out.println("Input option 4:");
            String option4 = sc.nextLine();

            System.out.println("What is the answer key?");
            String answerKey = sc.nextLine();

            // Maintain order using LinkedHashMap
            Map<String, String> questionMap = new LinkedHashMap<>();
            questionMap.put("question", question);
            questionMap.put("option 1", option1);
            questionMap.put("option 2", option2);
            questionMap.put("option 3", option3);
            questionMap.put("option 4", option4);
            questionMap.put("answerkey", answerKey);

            // Convert the map to a JSON object and add to the JSON array
            JSONObject questionObj = new JSONObject(questionMap);
            jsonArray.add(questionObj);

            System.out.println("Saved successfully! Do you want to add more questions? (press 's' for start and 'q' for quit)");
            String pressKey = sc.nextLine();

            // Exit the loop if 'q' is pressed
            if (pressKey.equalsIgnoreCase("q")) {
                break;
            }
        }

        // Write the updated questions back to quiz.json
        FileWriter writer = new FileWriter(quizPath);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

        sc.close();
    }
}
