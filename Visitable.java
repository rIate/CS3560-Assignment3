import java.text.ParseException;

public interface Visitable {
    void accept(Visitor v);
}
