import java.util.Scanner;
import java.io.*;

public class Duke {
	
	public static boolean isNumeric(String str) { 
        try {  
		    Integer.parseInt(str);  
		    return true;
	    } catch(NumberFormatException e){  
		    return false;  
		}  
	}
	
	public static int loadLst(Task lst[]) throws IOException {
		File file = new File("C:\\Users\\Fei Dong\\Desktop\\duke\\data\\duke.txt");
		Scanner sc = new Scanner(file);
		String curline = "", desc = "", time = "";
		int cnt = 0;
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
		    if (type == 'T') {
		    	ToDo newtd = new ToDo(desc); 
            	lst[cnt++] = newtd;
		    } else if (type == 'D') {
		    	Deadline newdl = new Deadline(desc, time); 
        		lst[cnt++] = newdl;
		    } else if (type == 'E') {
		    	Event newevt = new Event(desc, time); 
        		lst[cnt++] = newevt;
		    }
		}
		return cnt;
	}
	
	public static void saveLst(Task lst[], int cnt) throws IOException {
		FileWriter fileWriter = new FileWriter("C:\\Users\\Fei Dong\\Desktop\\duke\\data\\duke.txt", false);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (int i = 0; i < cnt; i++) {
    		printWriter.printf(lst[i].getSave() + "%n");
    	}
		printWriter.close();
		fileWriter.close();
	}
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inp, inpstrt, check1;
        Task[] lst = new Task[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int cnt = 0;
        try {
            cnt = loadLst(lst);
        } catch(IOException m) {
        	System.out.println(m);
        }
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
	                saveLst(lst, cnt);
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
	                saveLst(lst, cnt);
	            }
        	} catch(BlankInputException m) {
	             System.out.println(m);
	        } catch (UnknownInputException m) {
	        	System.out.println(m);
	        } catch (IOException m) {
	        	System.out.println(m);
	        }
        }
    }
}
