import javax.swing.*;
import java.awt.*;

public class MainGUI {

    public void createGUI(){

        tabbedPane.addTab("Manage Programmes", null, manageProgrammePanel, null);
        tabbedPane.addTab("Manage Modules", null, manageModulePanel, null);
        tabbedPane.addTab("Manage Activities", null, manageActivityPanel, null);
        frame.add(tabbedPane);

        frame.setMinimumSize(new Dimension(700, 640));
        frame.setPreferredSize(new Dimension(frame.getMinimumSize()));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private ManageModuleGUI mmg = new ManageModuleGUI();
    private ManageActivityGUI mag = new ManageActivityGUI();

    private JFrame frame = new JFrame("Timetable System");
    private ManageProgrammeGUI mgh = new ManageProgrammeGUI();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private ManageProgrammeGUI mpg = new ManageProgrammeGUI();
    private JComponent manageProgrammePanel = mpg.programmeGUIHandler();
    private JComponent manageModulePanel = mmg.moduleGUIHandler();
    private JComponent manageActivityPanel = mag.activityGUIHandler();
    private JLabel textLbl = new JLabel("test");


}
