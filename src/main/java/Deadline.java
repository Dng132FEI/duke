import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date byobj;
    protected String bystr;
    protected int format;

    public Deadline(String description, String by) {
        super(description);
        setDate(by);
    }
    
    public void setDate(String date) {
    	try {
    	    SimpleDateFormat inputformat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    	    inputformat.setLenient(false);
    	    byobj = inputformat.parse(date);
    	    format = 1; // date and time
    	} catch (ParseException pe) {
    		try {
	    		SimpleDateFormat inputformat = new SimpleDateFormat("dd/MM/yyyy");
	    	    inputformat.setLenient(false);
	    	    Date dateobj = inputformat.parse(date);
	    	    byobj = dateobj;
	    	    format = 2; // only date
    		} catch (ParseException pe2) {
    			bystr = date;
        	    format = 3; // other types; store as string
    		}
    	}
    }
    
    public String getPrtout() {
    	if (format == 1) {
    		SimpleDateFormat outputformat = new SimpleDateFormat("dd MMM yyyy, hh:mm aa");
    	 	String out = outputformat.format(byobj);
    	 	return "[D]" + super.getPrtout() + "(by: " + out + ")";
    	} else if (format == 2) {
    		SimpleDateFormat outputformat = new SimpleDateFormat("dd MMM yyyy");
    	 	String out = outputformat.format(byobj);
    	 	return "[D]" + super.getPrtout() + "(by: " + out + ")";
    	} else {
    		return "[D]" + super.getPrtout() + "(by: " + bystr + ")";
    		
    	}
     }

    public String getSave() {
    	String out;
 	    if (format == 1) {
 		    SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy HHmm");
	 	    out = outputformat.format(byobj);
	    } else if (format == 2) {
		    SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy");
	    	out = outputformat.format(byobj);
	    } else {
		    out = bystr;
	    }
 	   return "D|" + super.getSave() + out + "|";
   }
}