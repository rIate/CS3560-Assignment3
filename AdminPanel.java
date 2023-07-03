import javax.swing.*;
import javax.swing.text.DefaultTextUI;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame implements UIBuild{
    // main frame
    JFrame frame;

    // user controller
    UserController userController;

    // treeview
    JScrollPane treeview;
    private JTree tree;

    // buttons
    private JButton adduser;
    private JButton addGroup;
    private JButton userView;
    private JButton userTotal;
    private JButton messageTotal;
    private JButton groupTotal;
    private JButton positivePercentage;
    private JButton validateIds;
    private JButton lastUserUpdate;

    // text fields
    private JTextField userId;
    private JTextField groupId;

    // labels
    private JLabel userIdLabel;
    private JLabel groupIdLabel;

    private JButton[] buttons;

    // static instance
    private static AdminPanel INSTANCE = new AdminPanel();

    public AdminPanel(){
        UIbuilder();
    }

    public static AdminPanel getInstance(){
        // double check lock for multi threading
        if (INSTANCE == null) {
            synchronized (AdminPanel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AdminPanel();
                }
            }
        }
        return INSTANCE;
    }

    public void UIbuilder(){
        userController = new UserController();
        treeManager();
        buttonManager();
        labelManager();
        textManager();
        frameManager();

    }

    public void frameManager(){
        // initializer frame
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mini Twitter");
        frame.setResizable(false);
        frame.setLayout(null);

        frame.add(treeview);
        frame.add(adduser);
        frame.add(addGroup);

        frame.add(userId);
        frame.add(groupId);

        frame.add(userIdLabel);
        frame.add(groupIdLabel);

        frame.add(lastUserUpdate);

        frame.add(validateIds);

        frame.add(userView);

        frame.add(userTotal);
        frame.add(messageTotal);

        frame.add(groupTotal);
        frame.add(positivePercentage);

        frame.setSize(715,330);
        frame.setVisible(true);

    }

    public void treeManager(){
        // initialize tree nodes
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        tree = new JTree(root);
        treeview = new JScrollPane(tree);
        treeview.setBounds(5,5,245,275);
    }

    public void textManager(){
        // user ID label
        userId = new JTextField();
        userId.setBounds(395, 5, 150, 35);

        // group ID label
        groupId = new JTextField();
        groupId.setBounds(395, 41, 150, 35);
    }

    public void labelManager(){
        userIdLabel = new JLabel();
        userIdLabel.setText("User ID");
        userIdLabel.setBounds(340, 5, 150, 35);

        groupIdLabel = new JLabel();
        groupIdLabel.setText("Group ID");
        groupIdLabel.setBounds(337, 41, 150, 35);
    }

//    public void addGroup(){
//        TreePath[] tp = tree.getSelectionPaths();
//        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
//        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
//        boolean setText = false;
//
//        if(userController.checkGroupRepeat(groupId.getText())){
//            // checks if group already exists
//            JOptionPane.showMessageDialog(frame, "Error: User group already exists");
//            groupId.setText("");
//            return;
//        }
//
//        if(tp != null){
//            // selection paths are not empty
//            for(TreePath tree: tp){
//                if(!groupId.getText().trim().equals("") && ((DefaultMutableTreeNode)tree.getLastPathComponent()).getAllowsChildren()){
//                    // text field is not empty and the specified path in the tree allows children leafs
//                    // add folder to tree, come back to this
//                    //userController.addGroup(selectedNode, groupId.getText());
//                    userController.addGroupToGroup(groupId.getText(), selectedNode);
//                    model.reload();
//                    groupId.setText("");
//                    setText = true;
//                }
//            }
//        }else if (tp == null && !groupId.getText().trim().equals("")){
//            // user has not selected path
//            // insert user into root by default
//            //            model.insertNodeInto(newGroup, rootNode, rootNode.getChildCount());
//            //userController.addGroup(rootNode, groupId.getText());
//            userController.addGroup(groupId.getText(), rootNode);
//            model.reload();
//            groupId.setText("");
//            setText = true;
//        }
//
//        if(groupId.getText().trim().equals("") && !setText){
//            // completely empty input field
//            JOptionPane.showMessageDialog(frame, "Error: No user group to add !");
//        }
//
//    }

//    public void addUser(){
//        TreePath[] tp = tree.getSelectionPaths();
//        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
//        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//        DefaultMutableTreeNode newUser = new DefaultMutableTreeNode(userId.getText());
//        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
//        boolean setText = false;
//
//        if(userController.checkUserExists(userId.getText())){
//            JOptionPane.showMessageDialog(frame, "Error: User already exists !");
//            userId.setText("");
//            return;
//        }
//
//        if(tp != null){
//            // selection paths are not empty (adding to a group)
//            for(TreePath tree: tp){
//                if(!userId.getText().trim().equals("") && ((DefaultMutableTreeNode)tree.getLastPathComponent()).getAllowsChildren()){
//                    // text field is not empty and the specified path in the tree allows children leafs
//                    userController.addLeafToGroup(userId.getText(), selectedNode);
//                    model.reload();
//                    userId.setText("");
//                    setText = true;
//                }
//            }
//        }else if ((tp == null || (selectedNode == rootNode)) && !userId.getText().trim().equals("")){
//            // user has not selected path
//            // insert user into root by default
//            userController.addLeaf(userId.getText(), rootNode);
//            model.reload();
//            userId.setText("");
//            setText = true;
//        }
//
//        if(userId.getText().trim().equals("") && !setText){
//            // completely empty input field case
//            JOptionPane.showMessageDialog(frame, "Error: No user to add !");
//        }
//
//    }

    public void addGroup(){
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        Boolean emptyField = groupId.getText().trim().equals("");

        if(emptyField){
            JOptionPane.showMessageDialog(frame, "Error: Empty Group !");
            groupId.setText("");
            return;
        }

        if(userController.checkGroupExists(groupId.getText())){
            JOptionPane.showMessageDialog(frame, "Error: Group already exists !");
            groupId.setText("");
            return;
        }

        if(tree.getSelectionPaths() == null){
            userController.addGroup(groupId.getText(), rootNode);
            reloadField(model);
            return;
        }

        if(selectedNode.isRoot()){
            userController.addGroup(groupId.getText(), rootNode);
            reloadField(model);
            return;
        }

        if(selectedNode != null && selectedNode.getAllowsChildren()){
            userController.addGroupToGroup(groupId.getText(), selectedNode);
            reloadField(model);
        }

    }

    public void addUser(){
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        Boolean emptyField = userId.getText().trim().equals("");

        if(emptyField){
            JOptionPane.showMessageDialog(frame, "Error: Empty User !");
            userId.setText("");
            return;
        }

        if(userController.checkUserExists(userId.getText())){
            JOptionPane.showMessageDialog(frame, "Error: User already exists !");
            userId.setText("");
            return;
        }

        if(tree.getSelectionPaths() == null){
            userController.addLeaf(userId.getText(), rootNode);
            reloadField(model);
            return;
        }

        if(selectedNode.isRoot()){
            userController.addLeaf(userId.getText(), rootNode);
            reloadField(model);
            return;
        }

        if(selectedNode != null){
            // add user to group
            if(selectedNode.getAllowsChildren()){
                userController.addLeafToGroup(userId.getText(), selectedNode);
                reloadField(model);
            }
        }
    }

    public void reloadField(DefaultTreeModel model){
        model.reload();
        userId.setText("");
        groupId.setText("");
    }

    public void displayTotalUsers(){
        UserCount userVisitor = new UserCount();
        userController.iterateComponentCount(userVisitor);
        JOptionPane.showMessageDialog(frame, "Total Users: " + userVisitor.getCount());
    }

    public void displayTotalGroups(){
        GroupCount groupVisitor = new GroupCount();
        userController.iterateComponentCount(groupVisitor);
        JOptionPane.showMessageDialog(frame, "Total Groups: " + groupVisitor.getCount());
    }

    public void displayMessageTotal(){
        MessageCount messageCount = new MessageCount();
        userController.iterateComponentCount(messageCount);
        JOptionPane.showMessageDialog(frame, "Total Messages: " + messageCount.getMessageCount());
    }

    public void displayPositivePercent(){
        PositiveCount positiveVisitor = new PositiveCount();
        userController.iterateComponentCount(positiveVisitor);
        JOptionPane.showMessageDialog(frame, "Total Messages: " + positiveVisitor.calculatePercent());
    }

    public void openUserView(){
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        // if the selected node is not empty and is in fact a user (check by checking if node allows children)

        if(selectedNode != null && !selectedNode.getAllowsChildren()){
            User selectedUser = (User)selectedNode.getUserObject();
            System.out.println(selectedUser.getCreationTime());
            new UserUI(selectedUser).setVisible(true);
        }else {
            JOptionPane.showMessageDialog(frame, "Error: Select user from tree!");
        }

    }

    public void checkValidIds(){
        ValidateID validateVisitor = new ValidateID();
        userController.iterateComponentCount(validateVisitor);
        System.out.println(validateVisitor.validate());
        JOptionPane.showMessageDialog(frame, "Users/Groups IDs : " + validateVisitor.validate());
    }

    public void findLastUpdate(){
        LastUserUpdate userUpdateVisitor = new LastUserUpdate();
        userController.iterateComponentCount(userUpdateVisitor);
        JOptionPane.showMessageDialog(frame, "Last Updated Tweet Time: " + userUpdateVisitor.sortTimes());
    }

    public void buttonManager(){
        // add user button
        adduser = new JButton();
        adduser.setText("Add User");
        adduser.setBounds(550, 5, 150, 35);
        adduser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        // add group button
        addGroup = new JButton();
        addGroup.setText("Add Group");
        addGroup.setBounds(550, 41, 150, 35);
        addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGroup();
            }
        });

        // open user view button
        userView = new JButton();
        userView.setText("Open User View");
        userView.setBounds(395, 85, 305, 35);
        userView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserView();
            }
        });

        // user total button
        userTotal = new JButton();
        userTotal.setText("User Total");
        userTotal.setBounds(390, 200, 150, 35);
        userTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTotalUsers();
            }
        });

        // message total button
        messageTotal = new JButton();
        messageTotal.setText("Message Total");
        messageTotal.setBounds(390, 240, 150, 35);
        messageTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessageTotal();
            }
        });


        // show group total
        groupTotal = new JButton();
        groupTotal.setText("Group Total");
        groupTotal.setBounds(550, 200, 150, 35);
        groupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTotalGroups();
            }
        });

        // show positive percentage
        positivePercentage = new JButton();
        positivePercentage.setText("Positive Percentage");
        positivePercentage.setBounds(550, 240, 150, 35);
        positivePercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPositivePercent();
            }
        });

        // display unique group names and user names & check spacing format
        validateIds = new JButton();
        validateIds.setText("Check Valid Users/Groups");
        validateIds.setBounds(395, 125, 305, 35);
        validateIds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkValidIds();
            }
        });

        lastUserUpdate = new JButton();
        lastUserUpdate.setText("Last Update User");
        lastUserUpdate.setBounds(395, 163, 305, 35);
        lastUserUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findLastUpdate();
            }
        });
    }
}
