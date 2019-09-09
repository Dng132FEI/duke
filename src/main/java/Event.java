public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    
   public String getPrtout() {
	   String res = "[E]" + super.getPrtout() + " (at: " + at + ")";
	   return res;
   }
   
}
