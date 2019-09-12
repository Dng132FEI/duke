import java.io.IOException;

public class Parser {
    
	TaskList tsklst;
	Storage storage;
	
	public Parser (TaskList lst, Storage sto) {
		this.tsklst = lst;
		this.storage = sto;
	}
	
    public void parseInput(String input) throws UnknownInputException, BlankInputException, InvalidInputException, IOException {
    	if (input.equals("list")) { // input == "list"
             tsklst.prtLst();
	    } else if (input.substring(0,5).equals("done ")) { // input == "done
	    	if (input.substring(5,input.length()).isBlank()) {
	    		throw new BlankInputException("OOPS!!! The list index for the done function cannot be empty.");
	    	}
		    int idx = Integer.parseInt(input.substring(5,input.length()))-1; // considering include exception
		    tsklst.done(idx);
	    } else if (input.substring(0,7).equals("delete ")) {
	    	if (input.substring(7,input.length()).isBlank()) {
	    		throw new BlankInputException("OOPS!!! The list index for the delete function cannot be empty.");
	    	}
	    	int idx = Integer.parseInt(input.substring(7,input.length()))-1; // considering include exception
	    	
		    tsklst.delete(idx);
	    } else if (input.substring(0,5).equals("find ")) {
            String keyword = input.substring(5,input.length());
            if (keyword.isBlank()) {
	    		throw new BlankInputException("OOPS!!! The search keyword cannot be empty.");
	    	}
            tsklst.find(keyword);
	    } else if (input.substring(0,5).equals("todo ")) { 
	        String tsk = input.substring(5,input.length());
	        if (tsk.isBlank()) {
	    		throw new BlankInputException("OOPS!!! The description of a deadline cannot be empty.");
	    	}
	        tsklst.newtd(tsk);
	    } else if (input.substring(0,9).equals("deadline ")) { // cat == "deadline"
	    	int slash = input.indexOf("/");
	    	if (slash == -1) {
	    		throw new InvalidInputException("Please key in a valid input in the format deadline task /by time");
	    	} else if (!input.substring(slash+1,slash+3).equals("by")) {
	    		System.out.println(input.substring(slash+1,slash+3));
	    		throw new InvalidInputException("Please key in a valid input in the format deadline task /by time");
	    	} 
	        String tsk = input.substring(9,slash-1);
	        
	        if (tsk.isBlank()) {
	    		throw new BlankInputException("OOPS!!! The description of a deadline cannot be empty.");
	    	}
	        String time = input.substring(slash+4,input.length());
	        tsklst.newdl(tsk, time);
	    } else if (input.substring(0,6).equals("event ")) {
	    	int slash = input.indexOf("/");
	    	if (slash == -1) {
	    		throw new InvalidInputException("Please key in a valid input in the format event task /by time");
	    	} else if (!input.substring(slash+1,slash+3).equals("at")) {
	    		throw new InvalidInputException("Please key in a valid input in the format event task /by time");
	    	}
	        String tsk = input.substring(6,slash-1);
	        System.out.println(tsk);
	        if (tsk.isBlank()) {
	    		throw new BlankInputException("OOPS!!! The description of a deadline cannot be empty.");
	    	}
	        String time = input.substring(slash+4,input.length());
	        tsklst.newevt(tsk, time);
	    } else {
	    	throw new UnknownInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
	    }
    }
}
