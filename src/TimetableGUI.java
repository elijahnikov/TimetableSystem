import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TimetableGUI
{

    public void createGUI(String code)
    {

        ArrayList<Modules> modules = (ArrayList<Modules>) ManageModuleGUI.mh.getModuleByProgramme(code);



        DefaultTableModel model = new DefaultTableModel(null, days) {};
        JTable timetable = new JTable(model);

        timetable.getColumnModel().getColumn(0).setPreferredWidth(5);
        timetable.getColumnModel().getColumn(1).setPreferredWidth(200);
        timetable.getColumnModel().getColumn(2).setPreferredWidth(200);
        timetable.getColumnModel().getColumn(3).setPreferredWidth(200);
        timetable.getColumnModel().getColumn(4).setPreferredWidth(200);
        timetable.getColumnModel().getColumn(5).setPreferredWidth(200);
        timetable.setRowHeight(30);
        timetable.setShowGrid(true);
        timetable.setGridColor(Color.lightGray);
        timetable.getTableHeader().setReorderingAllowed(false);
        timetable.getTableHeader().setResizingAllowed(false);


        timetable.getColumnModel().getColumn(0).setCellRenderer(
                new DefaultTableCellRenderer()
                {
                    public Component getTableCellRendererComponent(JTable table,
                                                                   Object value,
                                                                   boolean isSelected,
                                                                   boolean hasFocus,
                                                                   int row,
                                                                   int column)
                    {
                        setText(value.toString());
                        setBackground(Color.lightGray);
                        return this;
                    }
                });

        for (String time : times)
        {
            model.addRow(new Object[]{time});
        }

        for (int i = 0; i < modules.size(); i++)
        {
            model.setValueAt(modules.get(i).getName(), 2 + i, 2);
        }

//        model.setValueAt("COMP1814 (Lab)", 2, 2);
//        model.setValueAt("COMP1814 (Lab)", 3, 2);
//        model.setValueAt("COMP1814 (Lab)", 4, 2);
//        model.setValueAt("COMP1814 (Lab)", 5, 2);
//
//        model.setValueAt("COMP2814 (Lecture)", 5, 5);
//        model.setValueAt("COMP2814 (Lecture)", 6, 5);
//        model.setValueAt("COMP2814 (Lecture)", 7, 5);
//        model.setValueAt("COMP2814 (Lecture)", 8, 5);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        fileMenu.add(exitItem);

        ButtonGroup viewGroup = new ButtonGroup();
        viewGroup.add(viewByTermItem);
        viewGroup.add(viewByYearItem);
        viewMenu.add(viewByTermItem);
        viewMenu.add(viewByYearItem);

        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(1400, 800));
        frame.setResizable(false);
        frame.add(mainPanel);
        frame.add(new JScrollPane(timetable));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private final JFrame frame = new JFrame("Timetable Management System");
    private final JPanel mainPanel = new JPanel();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu viewMenu = new JMenu("View");

    private final JMenuItem exitItem = new JMenuItem("Exit");
    private final JRadioButtonMenuItem viewByYearItem = new JRadioButtonMenuItem("View by Year");
    private final JRadioButtonMenuItem viewByTermItem = new JRadioButtonMenuItem("View by Term");
    private final String[] times = {"09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30",
            "17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30"};
    private final String[] days = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

}
