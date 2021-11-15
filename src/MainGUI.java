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
            try {
                dbP = new DBPersistence();

                dbP.save(
                        ManageProgrammeGUI.ph.getProgrammeList(),
                        ManageModuleGUI.mh.getModulesList(),
                        ManageActivityGUI.ah.getActivityList()
                );
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        loadFromFileItem.addActionListener(e -> {

            try {
                fileP = new FilePersistence();
                fileP.load();



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
        fileMenu.add(saveToDBItem);
        fileMenu.add(exitItem);

        editMenu.add(loadFromDBItem);
        editMenu.add(loadFromFileItem);

        frame.setJMenuBar(menuBar);

        frame.setMinimumSize(new Dimension(700, 640));
        frame.setPreferredSize(new Dimension(frame.getMinimumSize()));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private final ManageModuleGUI mmg = new ManageModuleGUI();
    private final ManageActivityGUI mag = new ManageActivityGUI();

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu editMenu = new JMenu("Edit");

    private final JMenuItem saveToFileItem = new JMenuItem("Save to File");
    private final JMenuItem saveToDBItem = new JMenuItem("Save to DB");
    private final JMenuItem exitItem = new JMenuItem("Exit");

    private final JMenuItem loadFromFileItem = new JMenuItem("Load from File");
    private final JMenuItem loadFromDBItem = new JMenuItem("Load from DB");


    private final JFrame frame = new JFrame("Timetable System");
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final ManageProgrammeGUI mpg = new ManageProgrammeGUI();
    private final JComponent manageProgrammePanel = mpg.programmeGUIHandler();
    private final JComponent manageModulePanel = mmg.moduleGUIHandler();
    private final JComponent manageActivityPanel = mag.activityGUIHandler();
    private final JLabel textLbl = new JLabel("test");

    private Persistence fileP;
    private Persistence dbP;

}
