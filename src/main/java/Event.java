import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }
    
    public String getPrtout() {
 	   SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy HHmm");
 	   String out = outputformat.format(at);
 	   String res = "[E]" + super.getPrtout() + " (at: " + out + ")";
 	   return res;
    }
   
   public String getSave() {
	   SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy HHmm");
 	   String out = outputformat.format(at);
	   String res = "E|" + super.getSave() + out + "|";
	   return res;
   }
   
}
