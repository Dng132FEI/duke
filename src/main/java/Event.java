import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date atobj;
    protected String atstr;
    protected int format;

    public Event(String description, String at) {
        super(description);
        setDate(at);
    }
    
    public void setDate(String date) {
    	try {
    	    SimpleDateFormat inputformat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    	    inputformat.setLenient(false);
    	    atobj = inputformat.parse(date);
    	    format = 1; // date and time
    	} catch (ParseException pe) {
    		try {
	    		SimpleDateFormat inputformat = new SimpleDateFormat("dd/MM/yyyy");
	    	    inputformat.setLenient(false);
	    	    Date dateobj = inputformat.parse(date);
	    	    atobj = dateobj;
	    	    format = 2; // only date
    		} catch (ParseException pe2) {
    			atstr = date;
        	    format = 3; // other types; store as string
    		}
    	}
    }
    
    public String getPrtout() {
    	if (format == 1) {
    		SimpleDateFormat outputformat = new SimpleDateFormat("dd MMM yyyy, hh:mm aa");
    	 	String out = outputformat.format(atobj);
    	 	return "[E]" + super.getPrtout() + "(at: " + out + ")";
    	} else if (format == 2) {
    		SimpleDateFormat outputformat = new SimpleDateFormat("dd MMM yyyy");
    	 	String out = outputformat.format(atobj);
    	 	return "[E]" + super.getPrtout() + "(at: " + out + ")";
    	} else {
    		return "[E]" + super.getPrtout() + "(at: " + atstr + ")";
    	}
     }

    public String getSave() {
    	String out;
 	    if (format == 1) {
 		    SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy HHmm");
	 	    out = outputformat.format(atobj);
	    } else if (format == 2) {
		    SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy");
	    	out = outputformat.format(atobj);
	    } else {
		    out = atstr;
	    }
 	   return "E|" + super.getSave() + out + "|";
   }
}
