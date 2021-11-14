import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TimetableGUI {

    public void createGUI(){

        model = new DefaultTableModel(null, days) {};
        timetable = new JTable(model);

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
                new DefaultTableCellRenderer() {
                    public Component getTableCellRendererComponent(JTable table,
                                                                   Object value,
                                                                   boolean isSelected,
                                                                   boolean hasFocus,
                                                                   int row,
                                                                   int column) {
                        setText(value.toString());
                        setBackground(Color.lightGray);
                        return this;
                    }
                });

        for (int i = 0; i < times.length; i++){
            model.addRow(new Object[] { times[i] });
        }

        model.setValueAt("COMP1814 (Lab)", 2, 2);
        model.setValueAt("COMP1814 (Lab)", 3, 2);
        model.setValueAt("COMP1814 (Lab)", 4, 2);
        model.setValueAt("COMP1814 (Lab)", 5, 2);

        model.setValueAt("COMP2814 (Lecture)", 5, 5);
        model.setValueAt("COMP2814 (Lecture)", 6, 5);
        model.setValueAt("COMP2814 (Lecture)", 7, 5);
        model.setValueAt("COMP2814 (Lecture)", 8, 5);

        manageItem.addActionListener(e -> {
            MainGUI mmg = new MainGUI();
        });

        menuBar.add(fileMenu);
        menuBar.add(manageMenu);
        menuBar.add(viewMenu);

        fileMenu.add(exitItem);
        manageMenu.add(manageItem);

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

    private JFrame frame = new JFrame("Timetable Management System");
    private JPanel mainPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu manageMenu = new JMenu("Manage");
    private JMenu viewMenu = new JMenu("View");

    private JMenuItem exitItem = new JMenuItem("Exit");
    private JMenuItem manageItem = new JMenuItem("Manage Programmes/Modules/Activities");
    private JRadioButtonMenuItem viewByYearItem = new JRadioButtonMenuItem("View by Year");
    private JRadioButtonMenuItem viewByTermItem = new JRadioButtonMenuItem("View by Term");
    private String[] times = {"09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30",
            "17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00"};
    private String[] days = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private JTable timetable;
    private DefaultTableModel model;
    private JTable headerTable;



}
