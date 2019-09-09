public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
   public String getPrtout() {
	   String res = "[D]" + super.getPrtout() + " (by: " + by + ")";
	   return res;
   }
   
   public String getSave() {
	   String res = "D|" + super.getSave() + by + "|";
	   return res;
   }
   
}
