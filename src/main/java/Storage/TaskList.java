package Storage;

import Tasks.Task;
import Tasks.ToDo;
import Tasks.Deadline;
import Tasks.Event;
import Storage.TaskList;
import User.Ui;

import java.io.IOException;
import java.util.ArrayList;


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
    	String itmlst = "";
	    for (int i = 0; i < lst.size(); i++) {
	    	String cur = Integer.toString(i+1);
	    	itmlst += cur+"."+lst.get(i).getPrtout() + "\n";
	    }
	    iout.prtLst(itmlst);
    }
	
	public void done(int idx) {
	    lst.get(idx).setAsDone();
	    iout.done(lst.get(idx).getPrtout());
	}
	
    public void delete(int idx) {
    	try {
    		iout.deleted(lst.get(idx).getPrtout(), lst.size()-1);
        	lst.remove(idx);
        	storage.saveLst(lst);
    	} catch (IOException e) {
        	iout.showError(e,"");
        }
	}
    
    public void find(String keyword) {
    	String itmlst = "";
    	for (int i = 0; i < lst.size(); i++) {
    		if (lst.get(i).getDescription().contains(keyword)) {
    			String cur = Integer.toString(i+1);
    			itmlst += cur + "." + lst.get(i).getPrtout() + "\n";
    		}
    	}
    	iout.found(itmlst);
	}
    
    public void newtd(String tsk) {
    	try {
        	ToDo newtd = new ToDo(tsk); 
        	lst.add(newtd);	 
        	iout.added(newtd.getPrtout(), lst.size());
        	storage.saveLst(lst);
    	} catch (IOException e) {
        	iout.showError(e,"");
        }
    }
    
    public void newdl(String tsk, String time) {
    	try {
	    	Deadline newdl = new Deadline(tsk,time); 
	    	lst.add(newdl);
	    	iout.added(newdl.getPrtout(), lst.size());
	    	storage.saveLst(lst);
    	} catch (IOException e) {
        	iout.showError(e,"");
        }
    }
    
    public void newevt(String tsk, String time) {
        try {
        	Event newevt = new Event(tsk,time); 
        	lst.add(newevt);
        	iout.added(newevt.getPrtout(), lst.size());
            storage.saveLst(lst);
    	} catch (IOException e) {
        	iout.showError(e,"");
        }
    }
    
    public Task getItem(int idx) {
    	return lst.get(idx);
    }
    
    public int count() {
    	return lst.size();
    }

}
