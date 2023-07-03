import java.util.HashSet;
import java.util.Set;

public class ValidateID implements Visitor{
    private Set<String> uniqueIds = new HashSet<>();
    private static boolean isValid;

    ValidateID(){
        // assume initially everything is valid
        isValid = true;
    }

    public String validate(){
        if(isValid){
            return "Valid";
        }
        return "Invalid";
    }

    public void duplicate(String id){
        if(uniqueIds.contains(id)){
            isValid = false;
        }
        uniqueIds.add(id);
    }

    public void checkFormat(String id){
        if(id.contains(" ")){
            isValid = false;
        }
    }

    @Override
    public void visit(Group group) {
        String groupId = group.getGroupId();
        duplicate(groupId);
        checkFormat(groupId);
    }

    @Override
    public void visit(User user) {
        String userId = user.getUserId();
        duplicate(userId);
        checkFormat(userId);
    }
}
