import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }
    
   public String getPrtout() {
	   SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy hhmm");
	   String out = outputformat.format(by);
	   String res = "[D]" + super.getPrtout() + " (by: " + out + ")";
	   return res;
   }
   
   public String getSave() {
	   SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy hhmm");
	   String out = outputformat.format(by);
	   String res = "D|" + super.getSave() + out + "|";
	   return res;
   }
   
}
