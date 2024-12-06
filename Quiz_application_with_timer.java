package Intern;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz{

    int correct=0,wrong=0;
    char userAns,right;
    Scanner sc = new Scanner(System.in);

    public void Questions(){

        System.out.println("1.What is the capital of France?");
        System.out.println("a) Berlin\nb) Madrid\nc) Paris\nd) Rome");
        System.out.println("Choose correct option:");
        right = 'c';
        userAns = sc.next().charAt(0);
        if(userAns==right){
            correct++;
        }else{
            wrong++;
        }
        System.out.printf("Correct:%d and Wrong:%d\n",correct,wrong);
//        System.out.println("2.Which planet is known as the Red Planet?");
//        System.out.println("a) Earth\nb) Mars\nc) Jupiter\nd) Venus");
//
//        System.out.println("3.Which is the smallest continent by land area?");
//        System.out.println("a) Europe\nb) Antarctica\nc) Australia\nd) South America");
//
//        System.out.println("4.What is the chemical symbol for water?");
//        System.out.println("a) H₂O\nb) CO₂\nc) O₂\nd) NaCl");
//
//        System.out.println("5.Which book series is written by J.K. Rowling?");
//        System.out.println("a) The Hunger Games\nb) Twilight\nc) Harry Potter\nd) Percy Jackson");
//
//        System.out.println("6.What gas do plants produce during photosynthesis?");
//        System.out.println("a) Oxygen\nb) Carbon dioxide\nc) Nitrogen\nd) Hydrogen");
//
//        System.out.println("7.What is the largest ocean on Earth?");
//        System.out.println("a) Atlantic Ocean\nb) Indian Ocean\nc) Arctic Ocean\nd) Pacific Ocean");
//
//        System.out.println("8.What is the center of an atom called?");
//        System.out.println("a) Proton\nb) Neutron\nc) Nucleus\nd) Electron");
    }
    public void secondFunction(){
        System.out.println("hello");
    }
}


public class Quiz_application_with_timer {
    public static void main(String[] args) {
//        Timer timer = new Timer();
        Quiz q1 = new Quiz();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//            }
//        }, 3000);

          q1.Questions();
//        try {
//            Thread.sleep(2000); // Delay for 2000 milliseconds (2 seconds)
//        } catch (InterruptedException e) {
//            System.out.println("Sleep interrupted: " + e.getMessage());
//        }

        q1.secondFunction();

    }
}
/*
import java.util.Scanner;

class Quiz {

    int correct = 0, wrong = 0;
    char userAns, right;
    Scanner sc = new Scanner(System.in);

    public void Questions() {
        String[] questions = {
                "1. What is the capital of France?\n a) Berlin\n b) Madrid\n c) Paris\n d) Rome",
                "2. What is 5 + 3?\n a) 6\n b) 8\n c) 7\n d) 9"
        };
        char[] answers = {'c', 'b'}; // Add correct answers here

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.println("Choose the correct option (30 seconds):");

            boolean answered = runWithTimeLimit(() -> {
                userAns = sc.next().charAt(0);
            }, 30000); // 30 seconds time limit

            if (answered) {
                if (userAns == answers[i]) {
                    correct++;
                } else {
                    wrong++;
                }
            } else {
                System.out.println("Time's up! Moving to the next question.");
                wrong++;
            }

            System.out.printf("Correct: %d and Wrong: %d\n", correct, wrong);
        }
    }

    public boolean runWithTimeLimit(Runnable task, int timeLimitMillis) {
        Thread taskThread = new Thread(task);
        taskThread.start();

        try {
            taskThread.join(timeLimitMillis); // Wait for the task to complete or timeout
            if (taskThread.isAlive()) {
                taskThread.interrupt(); // Interrupt if still running
                return false; // Indicate that the task was not completed in time
            }
        } catch (InterruptedException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return true; // Task completed in time
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.Questions();
    }
}

 */

