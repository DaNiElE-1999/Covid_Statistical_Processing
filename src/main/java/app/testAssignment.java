package app;

import impl.ProcessCSVFile;

import java.util.Scanner;
public class testAssignment {

    public static void main(String[] args) throws Exception {

        test();
    }

    public static void test() throws Exception {
        boolean test = true;

        while (test) {
            System.out.println("Want to proceed? (Type y if so)");
            Scanner in = new Scanner(System.in);
            String input =in.next();
            if (input.equals("y")) {
                ProcessCSVFile.go();
            }
            else {
                System.out.println("You chose to exit program!");
                test = false;
            }
        }
    }

}
