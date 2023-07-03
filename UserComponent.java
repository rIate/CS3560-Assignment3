import java.awt.*;

/**
 * Composite Pattern
 *
 * Component for adding SubGroups and Leaves
 */
public interface UserComponent {
    public UserComponent getComponent();
    // adding user components only applies to groups
    public void addUserComponent(UserComponent newUserComponent);
    public void print();
}
