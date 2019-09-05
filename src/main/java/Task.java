import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getDescription() {
    	return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }
    
    public void setAsDone() {
    	this.isDone = true;
    }
    
    public void setNotDone() {
    	this.isDone = false;
    }

}
