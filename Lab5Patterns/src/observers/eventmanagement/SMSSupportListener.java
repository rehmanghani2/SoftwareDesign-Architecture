package observers.eventmanagement;
import java.io.File;
/**
 *
 * @author Ghani
 */
public class SMSSupportListener implements EventListener{
    private String SMS;

    public SMSSupportListener(String SMS) {
        this.SMS = SMS;
    }

    @Override
    public void update(String eventType, File file) {
        if(SMS.length()>160){            
        System.out.printf("SMS [" + SMS + "]: is Too Long. Someone has performed " + eventType + 
                " on file " + file.getName()+ "\n Give a valid  default SMS ");
      //  throw new Exception("Give a valid Default SMS");
        }
        else  {          
            System.out.println("SMS IS SENT TO PHONE");
        }
    }
}
