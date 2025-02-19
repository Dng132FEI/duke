package Tasks;

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
    
    public String getStatusNum() {
    	return (isDone ? "1" : "0");
    }
    
    public void setAsDone() {
    	this.isDone = true;
    }
    
    public void setNotDone() {
    	this.isDone = false;
    }
    
   public String getPrtout() {
	   String res = "[" + this.getStatusIcon() + "] " + this.getDescription();
	   return res;
   }
   
   public String getSave() {
	   String res = this.getStatusNum() + "|" + this.getDescription() + "|";
	   return res;
   }
   
}
