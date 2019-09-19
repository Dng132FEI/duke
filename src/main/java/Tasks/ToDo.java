package Tasks;

public class ToDo extends Task {

    public ToDo(String description) { 
        super(description);
    }
    
   public String getPrtout() {
	   String res = "[T]" + super.getPrtout();
	   return res;
   }
   
   public String getSave() {
	   String res = "T|" + super.getSave();
	   return res;
   }
}
