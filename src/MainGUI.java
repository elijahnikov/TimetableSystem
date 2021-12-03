import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

        //choose which method of clash detection to use, kotlin or scala
        ActionListener clashAction = e ->
        {
            AbstractButton radio = (AbstractButton) e.getSource();
            switch (radio.getText())
            {
                case "Kotlin" -> clashDetection = "Kotlin";
                case "Scala" -> clashDetection = "Scala";
            }
        };

        kotlinClash.addActionListener(clashAction);
        scalaClash.addActionListener(clashAction);

        //close program menu action
        //TO-DO: IMPLEMENT SAVING TO FILE ON CLOSE
        exitItem.addActionListener(e -> System.exit(0));

        tabbedPane.addTab("Programmes", null, manageProgrammePanel, null);
        tabbedPane.addTab("Modules", null, manageModulePanel, null);
        tabbedPane.addTab("Activities", null, manageActivityPanel, null);
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(2, false);
        frame.add(tabbedPane);

        //add buttons to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(clashMenu);

        //add items to file menu
        fileMenu.add(saveToFileItem);
        fileMenu.add(saveToDBItem);
        fileMenu.add(exitItem);

        //add items to edit menu
        editMenu.add(loadFromFileItem);
        editMenu.add(loadFromDBItem);

        //add items to clash menu
        clashMenu.add(kotlinClash);
        clashMenu.add(scalaClash);
        clashGrp.add(kotlinClash);
        clashGrp.add(scalaClash);
        kotlinClash.setSelected(true);

        frame.setJMenuBar(menuBar);

        frame.setMinimumSize(new Dimension(700, 740));
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
    private final JMenu clashMenu = new JMenu("Detection");

    private final JMenuItem saveToFileItem = new JMenuItem("Save to File");
    private final JMenuItem saveToDBItem = new JMenuItem("Save to DB");
    private final JMenuItem exitItem = new JMenuItem("Exit");

    private final JMenuItem loadFromFileItem = new JMenuItem("Load from File");
    private final JMenuItem loadFromDBItem = new JMenuItem("Load from DB");

    private final JRadioButtonMenuItem kotlinClash = new JRadioButtonMenuItem("Kotlin");
    private final JRadioButtonMenuItem scalaClash = new JRadioButtonMenuItem("Scala");
    private final ButtonGroup clashGrp = new ButtonGroup();

    private final JFrame frame = new JFrame("Timetable System");
    public final static JTabbedPane tabbedPane = new JTabbedPane();
    private final ManageProgrammeGUI mpg = new ManageProgrammeGUI();
    private final JComponent manageProgrammePanel = mpg.programmeGUIHandler();
    private final JComponent manageModulePanel = mmg.moduleGUIHandler();
    private final JComponent manageActivityPanel = mag.activityGUIHandler();

    public static String clashDetection = "Kotlin";
    private Persistence fileP;
    private Persistence dbP;

}
