import java.util.Scanner;

public class Duke {
	
	public static boolean isNumeric(String str) { 
        try {  
		    Integer.parseInt(str);  
		    return true;
	    } catch(NumberFormatException e){  
		    return false;  
		}  
	}
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inp, inpstrt, check1;
        Task[] lst = new Task[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int cnt = 0;
        while (true) {
        	try {
	            inp = in.nextLine();
	            if (inp.length() == 6) {
	                inpstrt = inp.substring(0,5);
	                check1 = inp.substring(5,6);
	            } else if (inp.length() == 7) {
	            	inpstrt = inp.substring(0,5);
	                check1 = inp.substring(5,7);
	            } else if (inp.length() == 8) {
	            	inpstrt = inp.substring(0,5);
	                check1 = inp.substring(5,8);
	            } else {
	            	inpstrt = "";
	                check1 = "";
	            }
	            if (inp.equals("bye")) {
	                System.out.println("Bye. Hope to see you again soon!");
	                break;
	            } else if (inp.equals("list")) {
	            	System.out.println("Here are the tasks in your list:");
	            	for (int i = 0; i < cnt; i++) {
	            		String cur = Integer.toString(i+1);
	            		System.out.println(cur + "." + lst[i].getPrtout());
	            	}
	            } else if (inpstrt.equals("done ") && isNumeric(check1)) { 
	            	int idx = Integer.parseInt(check1)-1;
	                lst[idx].setAsDone();
	                System.out.println("Nice! I've marked this task as done:");
	                System.out.println(lst[idx].getPrtout());
	            } else { 
	            	String cat = "", time = "", tsk = "";
	            	char curchar;
	            	for (int i = 0; i < inp.length(); i++) {
	            		curchar = inp.charAt(i);
	            		if (inp.charAt(i) == ' ') {
	            			break;
	            		}
	            		cat += curchar;
	            	}
	            	if (cat.equals("todo")) {
	            		if (inp.equals("todo")) {
	            			throw new BlankInputException(
	            					"OOPS!!! The description of a todo cannot be empty.");
	            		}
	            		tsk = inp.substring(5);
	            		if (tsk.isBlank()) {
	            			throw new BlankInputException(
	            					"OOPS!!! The description of a todo cannot be empty.");
	            		}
	                	ToDo newtd = new ToDo(inp.substring(5)); 
	                	lst[cnt] = newtd;
	                	
	            	}
	            	else if (cat.equals("deadline")) {
	            		int check = 0, breaki = -1;
	            		for (int i = 9; i < inp.length(); i++) {
	                		curchar = inp.charAt(i);
	                		if (check == 1) {
	                			if (i < breaki + 5) {
	                				continue;
	                			} else {
	                    		    time += curchar;
	                    		} 
	                		} if (i+1 < inp.length()) {
	                			if (inp.charAt(i) == ' ' && inp.charAt(i+1) == '/') {
	                			    check = 1;
	                			    breaki = i;
	                			}
	                		} if (check == 0) {
	                			tsk += curchar;
	                		}
	                	}
	            		if (tsk.isBlank()) {
	            			throw new BlankInputException(
	            					"OOPS!!! The description of a deadline cannot be empty.");
	            		}
	            		Deadline newdl = new Deadline(tsk, time); 
	            		lst[cnt] = newdl;
	            	} else if (cat.equals("event")) {
	            		int check = 0, breaki = -1;
	            		for (int i = 6; i < inp.length(); i++) {
	                		curchar = inp.charAt(i);
	                		if (check == 1) {
	                			if (i < breaki + 5) {
	                				continue;
	                			} else {
	                    		    time += curchar;
	                    		} 
	                		} if (i+1 < inp.length()) {
	                			if (inp.charAt(i) == ' ' && inp.charAt(i+1) == '/') {
	                			    check = 1;
	                			    breaki = i;
	                			}
	                		} if (check == 0) {
	                			tsk += curchar;
	                		}
	                	} 
	            		if (tsk.isBlank()) {
	            		throw new BlankInputException(
	        					"OOPS!!! The description of an event cannot be empty.");
	            	    }
	            		Event newevt = new Event(tsk, time); 
	            		lst[cnt] = newevt;
	            	} else {
	            		throw new UnknownInputException(
	            				"OOPS!!! I'm sorry, but I don't know what that means :-(");
	            	}
	            	System.out.println("Got it. I've added this task:");
	                System.out.println(" " + lst[cnt].getPrtout());
	                cnt++;
	                if (cnt == 1) {
	                    System.out.printf("Now you have %d task in the list.%n", cnt);
	                } else {
	                	System.out.printf("Now you have %d tasks in the list.%n", cnt);
	                }
	            }
        	} catch(BlankInputException m) {
	             System.out.println(m);
	        } catch (UnknownInputException m) {
	        	System.out.println(m);
	        }
        }
    }
}
