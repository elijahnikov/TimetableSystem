import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainGUI {

    public void createGUI(){

        saveToFileItem.addActionListener(e -> {

            try  {
                fileP = new FilePersistence();

                fileP.save(
                        ManageProgrammeGUI.ph.getProgrammeList(),
                        ManageModuleGUI.mh.getModulesList(),
                        ManageActivityGUI.ah.getActivityList()
                );

            } catch (Exception ex){
                ex.printStackTrace();
            }

        });

        saveToDBItem.addActionListener(e -> {

        });

        loadFromFileItem.addActionListener(e -> {

            try {
                fileP = new FilePersistence();

                fileP.load(
                        "programmes.csv",
                        "modules.csv",
                        "activities.csv"
                );
                System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        tabbedPane.addTab("Manage Programmes", null, manageProgrammePanel, null);
        tabbedPane.addTab("Manage Modules", null, manageModulePanel, null);
        tabbedPane.addTab("Manage Activities", null, manageActivityPanel, null);
        frame.add(tabbedPane);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        fileMenu.add(saveToFileItem);
        fileMenu.add(exitItem);
        editMenu.add(loadFromFileItem);

        frame.setJMenuBar(menuBar);

        frame.setMinimumSize(new Dimension(700, 640));
        frame.setPreferredSize(new Dimension(frame.getMinimumSize()));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private ManageModuleGUI mmg = new ManageModuleGUI();
    private ManageActivityGUI mag = new ManageActivityGUI();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
    private JMenuItem saveToFileItem = new JMenuItem("Save to File");
    private JMenuItem saveToDBItem = new JMenuItem("Save to DB");
    private JMenuItem exitItem = new JMenuItem("Exit");
    private JMenuItem loadFromFileItem = new JMenuItem("Load from File");

    private JFrame frame = new JFrame("Timetable System");
    private ManageProgrammeGUI mgh = new ManageProgrammeGUI();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private ManageProgrammeGUI mpg = new ManageProgrammeGUI();
    private JComponent manageProgrammePanel = mpg.programmeGUIHandler();
    private JComponent manageModulePanel = mmg.moduleGUIHandler();
    private JComponent manageActivityPanel = mag.activityGUIHandler();
    private JLabel textLbl = new JLabel("test");

    private Persistence fileP;

}
