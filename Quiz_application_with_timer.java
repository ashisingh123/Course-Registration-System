package Intern;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.*;

class Quiz {
    private int correct = 0, wrong = 0;

    public Character getUserInputWithTimeout(int timeLimitMillis) throws TimeoutException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Character> future = executor.submit(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                return (char) reader.read();
            } catch (Exception e) {
                return null;
            }
        });

        try {
            return future.get(timeLimitMillis, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw e;
        } finally {
            executor.shutdown();
        }
    }

    public void Questions() {
        String[] Q = {
                "1. What is the capital of France?\na) Berlin\nb) Madrid\nc) Paris\nd) Rome",
                "2. Which planet is known as the Red Planet?\na) Earth\nb) Mars\nc) Jupiter\nd) Venus",
                "3. Which is the smallest continent by land area?\na) Europe\nb) Antarctica\nc) Australia\nd) South America",
                "4. What is the chemical symbol for water?\na) H₂O\nb) CO₂\nc) O₂\nd) NaC",
                "5. Which book series is written by J.K. Rowling?\na) The Hunger Games\nb) Twilight\nc) Harry Potter\nd) Percy Jackson",
                "6. What gas do plants produce during photosynthesis?\na) Oxygen\nb) Carbon dioxide\nc) Nitrogen\nd) Hydrogen",
                "7. What is the largest ocean on Earth?\na) Atlantic Ocean\nb) Indian Ocean\nc) Arctic Ocean\nd) Pacific Ocean",
                "8. What is the center of an atom called?\na) Proton\nb) Neutron\nc) Nucleus\nd) Electron"
        };

        char[] right = {'c', 'b', 'c', 'a', 'c', 'a', 'd', 'c'};

        for (int i = 0; i < Q.length; i++) {
            System.out.println(Q[i]);
            System.out.println("Choose the correct option (within 15 seconds):");

            try {
                Character userAns = getUserInputWithTimeout(15000);
                if (userAns != null && userAns == right[i]) {
                    correct++;
                } else {
                    wrong++;
                }
            } catch (TimeoutException e) {
                System.out.println("Time's up! Moving to the next question.");
                wrong++;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                wrong++;
            }
        }
        System.out.printf("Correct: %d and Wrong: %d\n", correct, wrong);
    }
}

public class Quiz_application_with_timer {
    public static void main() {
        Quiz q1 = new Quiz();
        q1.Questions();
    }
}
