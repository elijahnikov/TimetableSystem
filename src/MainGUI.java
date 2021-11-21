import javax.swing.*;
import java.awt.*;

public class MainGUI {

    public void createGUI(){

        //save to file menu action
        saveToFileItem.addActionListener(e ->
        {

            try
            {
                fileP = new FilePersistence();

                fileP.save(
                        ManageProgrammeGUI.ph.getProgrammeList(),
                        ManageModuleGUI.mh.getModulesList(),
                        ManageActivityGUI.ah.getActivityList()
                );

            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

        });

        //save to db menu action
        saveToDBItem.addActionListener(e ->
        {
            try
            {
                dbP = new DBPersistence();

                dbP.save(
                        ManageProgrammeGUI.ph.getProgrammeList(),
                        ManageModuleGUI.mh.getModulesList(),
                        ManageActivityGUI.ah.getActivityList()
                );
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        //load from csv file menu action
        loadFromFileItem.addActionListener(e ->
        {

            try
            {
                fileP = new FilePersistence();
                fileP.load();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        //close program menu action
        //TO-DO: IMPLEMENT SAVING TO FILE ON CLOSE
        exitItem.addActionListener(e ->
        {
            System.exit(0);
        });

        tabbedPane.addTab("Programmes", null, manageProgrammePanel, null);
        tabbedPane.addTab("Modules", null, manageModulePanel, null);
        tabbedPane.addTab("Activities", null, manageActivityPanel, null);
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(2, false);
        frame.add(tabbedPane);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        fileMenu.add(saveToFileItem);
        fileMenu.add(saveToDBItem);
        fileMenu.add(exitItem);

        editMenu.add(loadFromFileItem);
        editMenu.add(loadFromDBItem);

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
    public final static JTabbedPane tabbedPane = new JTabbedPane();
    private final ManageProgrammeGUI mpg = new ManageProgrammeGUI();
    private final JComponent manageProgrammePanel = mpg.programmeGUIHandler();
    private final JComponent manageModulePanel = mmg.moduleGUIHandler();
    private final JComponent manageActivityPanel = mag.activityGUIHandler();

    private Persistence fileP;
    private Persistence dbP;

}
