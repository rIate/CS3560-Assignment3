import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;

public class LastUserUpdate implements Visitor{
    private List<Date> listTimes = new ArrayList<Date>();
    private DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");

    public String sortTimes(){
        Collections.sort(listTimes);
        Date timeStamp = listTimes.get(0);
        return timeStamp.toString();
    }

    @Override
    public void visit(Group group) {

    }

    @Override
    public void visit(User user){
        try {
            listTimes.add(dateFormatter.parse(user.getTimeStamp()));
        }catch (ParseException e){
            System.err.println(e);
        }
    }
}
