import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class Duke {
	
	public static boolean isNumeric(String str) { 
        try {  
		    Integer.parseInt(str);  
		    return true;
	    } catch(NumberFormatException e) {  
		    return false;  
		}
	}
	
	public static void loadLst(ArrayList<Task> lst) throws IOException {
		File file = new File("C:\\Users\\Fei Dong\\Desktop\\duke\\data\\duke.txt");
		Scanner sc = new Scanner(file);
		String curline = "", desc = "", time = "";
		char type, done;
		while (sc.hasNextLine()) { 
		    curline = sc.nextLine(); 
		    type = curline.charAt(0);
		    done = curline.charAt(2);
		    desc = "";
		    time = "";
		    int i = 4;
		    while (curline.charAt(i) != '|') {
		        desc += curline.charAt(i);
		        i++;
		    }
		    i++;
		    if (type == 'D' || type == 'E') {
			    while (curline.charAt(i) != '|') {
			        time += curline.charAt(i);
			        i++;
			    }
		    }
		    try {
			    if (type == 'T') {
			    	ToDo newtd = new ToDo(desc); 
	            	lst.add(newtd);
			    } else if (type == 'D') {
			    	Date javatime = getDateTime(time);
			    	Deadline newdl = new Deadline(desc, javatime); 
			    	lst.add(newdl);
			    } else if (type == 'E') {
			    	Date javatime = getDateTime(time);
			    	Event newevt = new Event(desc, javatime); 
			    	lst.add(newevt);
			    }
		    } catch (ParseException m) {
		    	System.out.println(m + ". Error during loading.");
		    }
		}
	}

	public static void saveLst(ArrayList<Task> lst) throws IOException {
		FileWriter fileWriter = new FileWriter("C:\\Users\\Fei Dong\\Desktop\\duke\\data\\duke.txt", false);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (int i = 0; i < lst.size(); i++) {
    		printWriter.printf(lst.get(i).getSave() + "%n");
    	}
		printWriter.close();
		fileWriter.close();
	}
	
	public static Date getDateTime(String strDateTime) throws ParseException { 
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy HHmm");
		sdfrmt.setLenient(false);
	    Date retDate = sdfrmt.parse(strDateTime);
	    return retDate;
	}
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inp, inpdone = "", check1 = "", inpdel = "", check2 = "";
        ArrayList<Task> lst = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        try {
            loadLst(lst);
        } catch(IOException m) {
        	System.out.println(m);
        }
        while (true) {
        	try {
	            inp = in.nextLine();
	            if (inp.length() == 6) {
	                inpdone = inp.substring(0,5);
	                check1 = inp.substring(5,6);
	            } else if (inp.length() == 7) {
	            	inpdone = inp.substring(0,5);
	                check1 = inp.substring(5,7);
	            } else if (inp.length() == 8) {
	            	inpdone = inp.substring(0,5);
	                check1 = inp.substring(5,8);
	                inpdel = inp.substring(0,7);
	                check2 = inp.substring(7,8);
	            } else if (inp.length() == 9) {
	            	inpdel = inp.substring(0,7);
	                check2 = inp.substring(7,9);
	            } else if (inp.length() == 10) {
	            	inpdel = inp.substring(0,7);
	                check2 = inp.substring(7,10);
	            } // initialize to check for "done" case
        	  

		        if (inp.equals("bye")) { // input == "bye"
		            System.out.println("Bye. Hope to see you again soon!");
		            break;
		        } else if (inp.equals("list")) { // input == "list"
		        	System.out.println("Here are the tasks in your list:");
		        	for (int i = 0; i < lst.size(); i++) {
		        		String cur = Integer.toString(i+1);
		        		System.out.println(cur + "." + lst.get(i).getPrtout());
		        	}
		        } else if (inpdone.equals("done ") && isNumeric(check1)) { // input == "done"
		        	int idx = Integer.parseInt(check1)-1;
		            lst.get(idx).setAsDone();
		            System.out.println("Nice! I've marked this task as done:");
		            System.out.println(lst.get(idx).getPrtout());
		            saveLst(lst);
		        } else { 
		        	String cat = "", time = "", tsk = "";
		        	char curchar;
		        	for (int i = 0; i < inp.length(); i++) {
		        		curchar = inp.charAt(i);
		        		if (inp.charAt(i) == ' ') {
		        			break;
		        		}
		        		cat += curchar; 
		        	} // find out what category it is
		        	if (cat.equals("todo")) { // cat == "todo"
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
		            	lst.add(newtd);	                	
		        	} else if (cat.equals("deadline")) { // cat == "deadline"
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
		            			} // after encountering " /" start reading the characters as the time
		            		} if (check == 0) {
		            			tsk += curchar;
		            		}
		            	} // obtain the task and deadline
		        		if (tsk.isBlank()) {
		        			throw new BlankInputException(
		        					"OOPS!!! The description of a deadline cannot be empty.");
		        		}
		        		Date javatime = getDateTime(time);
		        		Deadline newdl = new Deadline(tsk, javatime);
		        		lst.add(newdl);
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
		            			} // after encountering " /" start reading the characters as the time
		            		} if (check == 0) {
		            			tsk += curchar;
		            		}
		            	} // obtain the task and event time
		        		if (tsk.isBlank()) {
		        		throw new BlankInputException(
		    					"OOPS!!! The description of an event cannot be empty.");
		        	    }
		        		Date javatime = getDateTime(time);
		        		Event newevt = new Event(tsk, javatime); 
		        		lst.add(newevt);
		        	} else {
		        		throw new UnknownInputException(
		        				"OOPS!!! I'm sorry, but I don't know what that means :-(");
		        	}
		        	System.out.println("Got it. I've added this task:");
		            System.out.println(" " + lst.get(lst.size()-1).getPrtout());
		            if (lst.size() == 1) {
		                System.out.printf("Now you have %d task in the list.%n", lst.size());
		            } else {
		            	System.out.printf("Now you have %d tasks in the list.%n", lst.size());
		            }
		            saveLst(lst);
		        }
	        } catch(BlankInputException m) {
		         System.out.println(m);
			} catch (UnknownInputException m) {
				System.out.println(m);
			} catch (IOException m) {
				System.out.println(m);
			} catch (ParseException m) {
				System.out.println(m + ". Time should be in the format dd/mm/yyyy hhmm");
	        }  
		}
    }
}      	