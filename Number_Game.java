package Intern;

import java.util.Random;
import java.util.Scanner;

class number{
    int random;
    int no;
    int noOfGuess=0;
    int chance = 1;
    final int MaxAttempts =7;

    public number(){
        Random rand = new Random();
        random = rand.nextInt(100);
    }
    public void guess() {
        Scanner sc = new Scanner(System.in);
       while(chance<=MaxAttempts){
        System.out.println("Enter your guess number: ");
        no = sc.nextInt();
        noOfGuess++;
            if (random == no) {
                System.out.println("YOU GOT RIGHT! ");
                System.out.println("Number was "+random);
                System.out.println("You have got correct number in " + noOfGuess + " attempts");
                break;
            } else if (random > no) {
                System.out.println("Too small, try little big!");
//                System.out.println("Play again!");
//                guess();
            } else if (random < no) {
                System.out.println("Too big, try little small");
//                System.out.println("Play again!");
//                guess();
            }
                chance++;
            if(chance==7){
                System.out.println("This is your last chance,Play with full courage!");
            }
            if(chance>MaxAttempts){
                System.out.println("Limit of attempts have been over!");
                System.out.println("Number was "+random);
            }
        }
    }
}
public class Number_Game {
    public static void main(String[] args) {

         number p1 = new number();
         p1.guess();
    }
}
