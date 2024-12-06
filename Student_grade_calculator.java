package Intern;

import java.util.Scanner;
class marks{
    int eng,hindi,maths,science,sum;
    float avg;

    public void mainFunc(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your marks obtained out of(100)");
        System.out.println("In ENGLISH:");
        eng = sc.nextInt();
        System.out.println("In HINDI:");
        hindi = sc.nextInt();
        System.out.println("In MATHS:");
        maths = sc.nextInt();
        System.out.println("In SCIENCE:");
        science = sc.nextInt();

        sum = eng+maths+hindi+science;
        avg= (float) sum/4;
//        System.out.println(avg);

        if(avg<32){
            System.out.println("Total Marks: "+sum+","+"Average is: "+avg+","+"Sorry You are failed!");
        }else{
            if(avg>90){
                System.out.println(sum+" "+avg+" "+"You got 'A+'");
            }else if (avg>80 && avg<=89){
                System.out.println(sum+" "+avg+" "+"You got 'A'");
            } else if (avg>60 && avg<=79){
                System.out.println(sum+" "+avg+" "+"You got 'B'");
            } else if(avg>=33 && avg<59){
                System.out.println(sum+" "+avg+" "+"You got 'C'");
            }
        }
    }
}
public class Student_grade_calculator {
    public static void main(String[] args) {
               marks m1 = new marks();
               m1.mainFunc();
    }
}
