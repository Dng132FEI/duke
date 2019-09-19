package User;

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
    
    public void deleted(String delitm, int n) {
    	System.out.println("Noted! I've removed this task:");
    	System.out.println(" "+delitm);
    	printNumTask(n);
    }
    
    public void printNumTask(int n) {
    	if (n == 1) {
            System.out.printf("Now you have %d task in the list.%n", n);
        } else {
        	System.out.printf("Now you have %d tasks in the list.%n", n);
        }
    }

	public void prtLst(String itmlst) {
		System.out.println("Here are the tasks in your list:");
		System.out.printf(itmlst);
	}
	
	public void found(String itmlst) {
		System.out.println("Here are the matching tasks in your list:");
		System.out.printf(itmlst);
	}

	public void done(String itmstr) {
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(" "+itmstr);
		
	}

	public void added(String itmstr, int n) {
		System.out.println("Got it. I've added this task:");
    	System.out.println(" "+itmstr);
    	printNumTask(n);
	}
}
