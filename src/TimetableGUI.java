import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TimetableGUI
{

    public void createGUI(String code)
    {

        //action listener for change of year
        ActionListener yearAction = e ->
        {
            AbstractButton radio = (AbstractButton) e.getSource();
            switch (radio.getText())
            {
                case "Year 1" -> {
                    //if changed to year 1, clear table and call mapToTimetableRows again
                    //repeat for other years and terms
                    year = 1;
                    clearTable();
                    tmr = new TimeRowMap();
                    tmr.mapToTimetableRows(code, term, year);
                }
                case "Year 2" -> {
                    year = 2;
                    clearTable();
                    tmr = new TimeRowMap();
                    tmr.mapToTimetableRows(code, term, year);
                }
                case "Year 3" -> {
                    year = 3;
                    clearTable();
                    tmr = new TimeRowMap();
                    tmr.mapToTimetableRows(code, term, year);
                }
            }
        };

        //action listener for change of term
        ActionListener termAction = e ->
        {
            AbstractButton radio = (AbstractButton) e.getSource();
            switch (radio.getText())
            {
                case "Term 1" -> {
                    term = 1;
                    clearTable();
                    tmr = new TimeRowMap();
                    tmr.mapToTimetableRows(code, term, year);
                }
                case "Term 2" -> {
                    term = 2;
                    clearTable();
                    tmr = new TimeRowMap();
                    tmr.mapToTimetableRows(code, term, year);
                }
                case "Term 3" -> {
                    term = 3;
                    clearTable();
                    tmr = new TimeRowMap();
                    tmr.mapToTimetableRows(code, term, year);
                }
            }
        };

        //exit listener, exits the timetable but not entire application
        exitItem.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
        });

        model = new DefaultTableModel(null, days) {};
        timetable = new JTable(model)
        {
            //override function for setting color for cells containing activities
            //code referenced and modified from: https://coderedirect.com/questions/354017/jtable-set-cell-color-at-specific-value
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col)
            {
                Component c = super.prepareRenderer(renderer, row, col);
                Object value = getModel().getValueAt(row, col);
                if (value != null)
                {
                    //activities can be of different names, regex checks if cell starts with a character a through z
                    if (!Arrays.asList(times).contains(value.toString()) && !value.toString().equals(""))
                    {
                        c.setBackground(new Color(161, 197, 255));
                        c.setFont(c.getFont().deriveFont(Font.BOLD, 14f));
                    }
                    //set time column to a light gray to differentiate from other cells
                    else if (Arrays.asList(times).contains(value.toString()))
                    {
                        c.setBackground(Color.lightGray);
                    }
                    else
                    {
                        c.setBackground(Color.white);
                    }
                }
                else
                {
                    c.setBackground(Color.white);
                }

                return c;
            }
            //______________________________________________________________________________________________________________________
        };

        timetable.getColumnModel().getColumn(0).setPreferredWidth(5);
        timetable.getColumnModel().getColumn(1).setPreferredWidth(300);
        timetable.getColumnModel().getColumn(2).setPreferredWidth(300);
        timetable.getColumnModel().getColumn(3).setPreferredWidth(300);
        timetable.getColumnModel().getColumn(4).setPreferredWidth(300);
        timetable.getColumnModel().getColumn(5).setPreferredWidth(300);
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

        //adds all the times to the first column
        for (String time : times)
        {
            model.addRow(new Object[]{time});
        }


        //if programme is undergraduate show all years in menu bar
        //if programme is postgraduate show 1 year in menu bar
        Programme p = ManageProgrammeGUI.ph.getProgramme(code);
        if (p != null)
        {
            if (p.getType().equals("Undergraduate"))
            {
                yearMenu.add(year1Item);
                yearMenu.add(year2Item);
                yearMenu.add(year3Item);
            }
            else
            {
                yearMenu.add(year1Item);
            }
        }

        yearGrp.add(year1Item);
        yearGrp.add(year2Item);
        yearGrp.add(year3Item);

        termGrp.add(term1Item);
        termGrp.add(term2Item);
        termGrp.add(term3Item);

        year1Item.setSelected(true);
        term1Item.setSelected(true);

        year1Item.addActionListener(yearAction);
        year2Item.addActionListener(yearAction);
        year3Item.addActionListener(yearAction);

        term1Item.addActionListener(termAction);
        term2Item.addActionListener(termAction);
        term3Item.addActionListener(termAction);

        menuBar.add(fileMenu);
        menuBar.add(yearMenu);
        menuBar.add(termMenu);

        fileMenu.add(exitItem);

        termMenu.add(term1Item);
        termMenu.add(term2Item);
        termMenu.add(term3Item);

        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(1920, 800));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(mainPanel);
        frame.add(new JScrollPane(timetable));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tmr = new TimeRowMap();
        //default to first term and first year
        tmr.mapToTimetableRows(code, 1, 1);
    }

    //function to go through all rows and columns and clear cells
    public void clearTable()
    {
        for (int i = 0; i < model.getRowCount(); i++) {
            for(int j = 1; j < model.getColumnCount(); j++) {
                model.setValueAt("", i, j);
            }
        }
    }
    private TimeRowMap tmr;

    private final JFrame frame = new JFrame("Timetable Management System");
    private final JPanel mainPanel = new JPanel();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu yearMenu = new JMenu("Year");
    private final JMenu termMenu = new JMenu("Term");

    private Integer year = 1;
    private Integer term = 1;

    public static DefaultTableModel model;
    public static JTable timetable;

    private final ButtonGroup yearGrp = new ButtonGroup();
    private final ButtonGroup termGrp = new ButtonGroup();

    private final JMenuItem exitItem = new JMenuItem("Exit");
    private final JRadioButtonMenuItem year1Item = new JRadioButtonMenuItem("Year 1");
    private final JRadioButtonMenuItem year2Item = new JRadioButtonMenuItem("Year 2");
    private final JRadioButtonMenuItem year3Item = new JRadioButtonMenuItem("Year 3");

    private final JRadioButtonMenuItem term1Item = new JRadioButtonMenuItem("Term 1");
    private final JRadioButtonMenuItem term2Item = new JRadioButtonMenuItem("Term 2");
    private final JRadioButtonMenuItem term3Item = new JRadioButtonMenuItem("Term 3");

    private final String[] times = {"09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30",
            "17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30"};
    private final String[] days = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

}
