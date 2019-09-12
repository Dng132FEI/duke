import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class Storage {
	
	String Filepath;
	
	public Storage(String path) {
		this.Filepath = path;
	}
	
	public static Date getDateTime(String strDateTime) throws ParseException { 
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy HHmm");
		sdfrmt.setLenient(false);
	    Date retDate = sdfrmt.parse(strDateTime);
	    return retDate;
	}
	
	public ArrayList<Task> load() throws IOException {
		ArrayList<Task> lst = new ArrayList<Task>();
		File file = new File(
				"C:\\Users\\Fei Dong\\Documents\\NUS_Computing\\CS2113T\\duke\\data\\duke.txt");
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
		    } if (type == 'T') {
		    	ToDo newtd = new ToDo(desc); 
		    	if (done == '1') {
		    		newtd.setAsDone();
		    	}
            	lst.add(newtd);
		    } else if (type == 'D') {
		    	Deadline newdl = new Deadline(desc, time); 
		    	if (done == '1') {
		    		newdl.setAsDone();
		    	}
		    	lst.add(newdl);
		    } else if (type == 'E') {
		    	Event newevt = new Event(desc, time); 
		    	if (done == '1') {
		    		newevt.setAsDone();
		    	}
		    	lst.add(newevt);
		    }
		}
		return lst;
	}

	public void saveLst(ArrayList<Task> lst) throws IOException {
		FileWriter fileWriter = new FileWriter(
				"C:\\Users\\Fei Dong\\Documents\\NUS_Computing\\CS2113T\\duke\\data\\duke.txt", false);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (int i = 0; i < lst.size(); i++) {
    		printWriter.printf(lst.get(i).getSave() + "%n");
    	}
		printWriter.close();
		fileWriter.close();
	}
   
}
