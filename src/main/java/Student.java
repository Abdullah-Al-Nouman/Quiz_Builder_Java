import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Student {

    // Method to display the student dashboard for taking quizzes
    public void studentDashboard() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // Path to the quiz.json file
        String quizPath = "./src/main/resources/quiz.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(quizPath));

        while (true) {
            int count = 0;
            HashSet<Integer> hashSet = new HashSet<>(); // Avoid duplicate questions

            System.out.println("We will throw you 10 questions.");
            System.out.println("Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' for start.");
            System.out.print("You entered: ");
            if (!sc.nextLine().equalsIgnoreCase("s")) {
                return;
            }

            // Display 10 random questions
            for (int i = 0; i < 10; i++) {
                int ranQns;
                do {
                    ranQns = random.nextInt(jsonArray.size());
                } while (hashSet.contains(ranQns));

                hashSet.add(ranQns);

                JSONObject jsonObject = (JSONObject) jsonArray.get(ranQns);
                System.out.println("\n[Question " + (i + 1) + "] " + jsonObject.get("question"));
                System.out.println("1. " + jsonObject.get("option 1"));
                System.out.println("2. " + jsonObject.get("option 2"));
                System.out.println("3. " + jsonObject.get("option 3"));
                System.out.println("4. " + jsonObject.get("option 4"));

                System.out.print("\nYour answer: ");
                int ans = Integer.parseInt(sc.nextLine());

                // Check if the answer is correct
                if (ans == Integer.parseInt(jsonObject.get("answerkey").toString())) {
                    count++;
                }
            }

            // Display the result
            scorePoint(count);

            System.out.println("\nWould you like to start again? Press 's' for start or 'q' for quit.");
            String pressKey = sc.nextLine();

            if (pressKey.equalsIgnoreCase("q")) {
                break;
            }
        }

        sc.close();
    }

    // Method to calculate and display the score
    public static void scorePoint(int count) {
        if (count >= 8) {
            System.out.println("Excellent! You have got " + count + " out of 10.");
        } else if (count >= 5) {
            System.out.println("Good. You have got " + count + " out of 10.");
        } else if (count >= 2) {
            System.out.println("Very poor! You have got " + count + " out of 10.");
        } else {
            System.out.println("Very sorry you are failed. You have got " + count + " out of 10.");
        }
    }
}

