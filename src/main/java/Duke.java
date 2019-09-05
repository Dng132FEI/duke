import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inp;
        ArrayList<String> lst = new ArrayList<String>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while (true) {
            inp = in.nextLine();
            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } if (inp.equals("list")) {
            	int len = lst.size();
            	for (int i = 0; i < len; i++) {
            		String cur = Integer.toString(i+1);
            		System.out.println(cur + ". " + lst.get(i));
            	}
            } else {
            	lst.add(inp);
                System.out.println("added: " + inp);
            }
        }
    }
}