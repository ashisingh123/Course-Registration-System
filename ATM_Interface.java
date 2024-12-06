package Intern;
import java.util.Scanner;

class atm{
    public void withdrawing(double amount){
    }
    public void deposit(double amount){
    }
    public void check_balance(){
    }
}
class bank_account extends atm {
    double amount = 12000.56;

    @Override
        public void withdrawing ( double withdrawingAmount){
            if (withdrawingAmount > amount) {
                System.out.println("Insufficient Balance!");
            } else {
                amount -= withdrawingAmount;
                System.out.println("Successfully withdraw: " + withdrawingAmount);
                System.out.println("New Balance is: " + amount);
            }
        }

        @Override
        public void deposit ( double Depositamount){
            amount += Depositamount;
            System.out.println("Successfully deposited: " + amount);
        }

        @Override
        public void check_balance () {
            System.out.println("Your Balance is: " + amount);
        }
    }
public class ATM_Interface {
    public static void main(String[] args) {
        bank_account m1 = new bank_account();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");
        Boolean exit = false;

        while (!exit){
            System.out.println("Choose the option:");
            System.out.println("1: Withdraw\n2: Deposit\n3: Check balance\n4: Exit");
            switch (sc.nextInt()) {
                case 1: {
                    System.out.println("Enter amount want to withdraw:");
                    m1.withdrawing(sc.nextInt());
                    break;
                }
                case 2: {
                    System.out.println("Enter amount you want to deposit:");
                    m1.deposit(sc.nextInt());
                    break;
                }
                case 3: {
                    m1.check_balance();
                    break;
                }
                case 4:{
                    exit=true;
                    System.out.println("Thank you for using the ATM!\nHave a nice day!");
                    break;
                }
                default:
                    System.out.println("Invalid choice! Choose again");
            }
        }
    }
}
