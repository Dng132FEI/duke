import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inp;
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while (true) {
            inp = in.nextLine();
            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(inp);
        }
    }
}