import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class TaskList {
	
    String byemsg = "Bye. Hope to see you again soon!";
    ArrayList<Task> lst = new ArrayList<Task>();
    Ui iout;
    Storage storage;
    
    public TaskList (ArrayList<Task> inplst, Ui inui, Storage sto) {
    	this.lst = inplst;
    	this.iout = inui;
    	this.storage = sto;
    }
	
    public void prtLst() {
    	iout.printLine("Here are the tasks in your list:");
	    for (int i = 0; i < lst.size(); i++) {
	    	String cur = Integer.toString(i+1);
		    iout.printLine(cur+"."+lst.get(i).getPrtout());
	    }
    }
	
	public void done(int idx) {
		iout.printLine("Nice! I've marked this task as done:");
	    iout.printLine(lst.get(idx).getPrtout());
	}
	
    public void delete(int idx) throws IOException {
    	iout.printLine("Noted! I've removed this task:");
    	iout.printLine(lst.get(idx).getPrtout());
    	lst.remove(idx);
    	iout.printNumTask(lst.size());
    	storage.saveLst(lst);
	}
    
    public void find(String keyword) {
    	iout.printLine("Here are the matching tasks in your list:");
    	for (int i = 0; i < lst.size(); i++) {
    		if (lst.get(i).getDescription().contains(keyword)) {
    			String cur = Integer.toString(i+1);
    			iout.printLine(cur + "." + lst.get(i).getPrtout());
    		}
    	}
	}
    
    public void newtd(String tsk) throws IOException {
    	ToDo newtd = new ToDo(tsk); 
    	lst.add(newtd);	         
    	iout.printLine("Got it. I've added this task:");
    	System.out.printf(" "+newtd.getPrtout());
    	iout.printNumTask(lst.size());
    	storage.saveLst(lst);
    	
    }
    
    public void newdl(String tsk, String time) throws IOException {
    	Deadline newdl = new Deadline(tsk,time); 
    	lst.add(newdl);
    	System.out.println("Got it. I've added this task:");
    	System.out.println(" "+newdl.getPrtout());
    	iout.printNumTask(lst.size());
    	storage.saveLst(lst);
    }
    
    public void newevt(String tsk, String time) throws IOException {
    	Event newevt = new Event(tsk,time); 
    	lst.add(newevt);
    	System.out.println("Got it. I've added this task:");
    	iout.printLine(" "+newevt.getPrtout());
        iout.printNumTask(lst.size());
        storage.saveLst(lst);
    }
    
    public Task getItem(int idx) {
    	return lst.get(idx);
    }
    
    public int count() {
    	return lst.size();
    }

}
