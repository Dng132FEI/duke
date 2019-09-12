import java.util.Scanner;

public class Ui {
	
    protected String curLine;
    
    public Ui() {
        this.curLine = "";
    }
    
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    public String readCommand() {
    	Scanner in = new Scanner(System.in);
    	String read = in.nextLine();
        return read;
    }
    
    public void printLine(String prtLine) {
    	System.out.println(prtLine);
    }
    
    public void showError(Exception ex, String msg) {
    	System.out.println(ex+". "+msg);
    }
    
    public void printNumTask(int n) {
    	if (n == 1) {
            System.out.printf("Now you have %d task in the list.%n", n);
        } else {
        	System.out.printf("Now you have %d tasks in the list.%n", n);
        }
    }
    
   
}
