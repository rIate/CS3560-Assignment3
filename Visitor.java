import java.text.ParseException;

public interface Visitor {
    /**
     * Visitor interface
     */
    void visit(Group group);
    void visit(User user);
}
