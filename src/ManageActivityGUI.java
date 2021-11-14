import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManageActivityGUI {


    public JComponent activityGUIHandler(){

        addBtn.addActionListener(e -> {

            String dateInput = de.getFormat().format(spinner.getValue());
            int hour = Integer.parseInt(dateInput.substring(0, 2));
            int min = Integer.parseInt(dateInput.substring(3, 5));
            int length = lengthSelect.getSelectedIndex() + 1;

            if (roomField.getText().equals("")){
                JOptionPane.showMessageDialog(mainPanel,
                        "Please ensure all fields are filled.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (val.validateDate(hour, min, length)){
                JOptionPane.showMessageDialog(mainPanel,
                        "Please ensure the duration of the activity is between the hours of 09:00 and 21:00",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println(moduleSelect.getSelectedItem().toString());
                m = ManageModuleGUI.mh.getModule(moduleSelect.getSelectedItem().toString());
                String moduleCode = null;
                if (m != null){
                    moduleCode = m.getCode();
                }
                System.out.println(moduleCode);

                //create activity instance
                a = ah.createActivity(
                        m,
                        roomField.getText(),
                        typeSelect.getSelectedItem().toString(),
                        dateInput,
                        val.getEndTime(hour, min, length),
                        daySelect.getSelectedItem().toString()
                );

                System.out.println(a);

                //add instance to list of activities
                ah.addActivity(a);

                //add instance details to table
                model.addRow(new Object[]{
                        ah.getActivityList().get(ah.getActivityList().size()-1).toString().replace("Activity@", ""),
                        ah.getActivityList().get(ah.getActivityList().size()-1).getRoom(),
                        ah.getActivityList().get(ah.getActivityList().size()-1).getType(),
                        moduleCode,
                        ah.getActivityList().get(ah.getActivityList().size()-1).getStart(),
                        ah.getActivityList().get(ah.getActivityList().size()-1).getEnd(),
                        ah.getActivityList().get(ah.getActivityList().size()-1).getDay()
                });

                System.out.println("here");
            }

            System.out.println(dateInput);
            System.out.println(val.getEndTime(hour, min, length));
        });

        spinner.setEditor(de);

        //sizing
        centerPanel.setMinimumSize(new Dimension(300, 350));
        centerPanel.setMaximumSize(centerPanel.getMinimumSize());
        centerPanel.setPreferredSize(centerPanel.getMinimumSize());

        topPanel.setMinimumSize(new Dimension(500, 250));
        topPanel.setMaximumSize(topPanel.getMinimumSize());
        topPanel.setPreferredSize(topPanel.getMinimumSize());

        roomField.setPreferredSize(new Dimension(150, 30));

        //main panel layout---------------------------------------------
        //adding panels to main panel
        mainPanel.setLayout(new GridBagLayout());
        mainc.gridx = 0;
        mainc.gridy = 0;
        mainc.fill = GridBagConstraints.BOTH;
        mainc.weightx = 1;
        mainc.weighty = 1;
        mainPanel.add(topPanel, mainc);

        mainc.gridx = 0;
        mainc.gridy = 1;
        mainc.gridheight = 1;
        mainc.gridwidth = 2;
        mainc.fill = GridBagConstraints.HORIZONTAL;
        mainc.weighty = 0;
        mainc.weightx = 1;
        mainPanel.add(centerPanel, mainc);

        //top panel layout-------------------------------------------
        topPanel.setLayout(new GridBagLayout());
        topc.anchor = GridBagConstraints.LINE_START;

        //name field
        topc.gridx = 0;
        topc.gridy = 0;
        topPanel.add(roomLbl, topc);
        roomLbl.setBorder(new EmptyBorder(0,0,0,10));
        topc.insets = new Insets(0, 0, 5, 0);
        topc.gridx = 1;
        topc.gridy = 0;
        topPanel.add(roomField, topc);

        //type select
        topc.gridx = 0;
        topc.gridy = 1;
        topPanel.add(typeLbl, topc);
        topc.gridx = 1;
        topc.gridy = 1;
        topPanel.add(typeSelect, topc);

        //module select
        topc.gridx = 0;
        topc.gridy = 2;
        topPanel.add(moduleLbl, topc);
        topc.gridx = 1;
        topc.gridy = 2;
        topPanel.add(moduleSelect, topc);

        //start select
        topc.gridx = 0;
        topc.gridy = 3;
        topPanel.add(startLbl, topc);
        topc.gridx = 1;
        topc.gridy = 3;
        topPanel.add(spinner, topc);

        //hours select
        topc.gridx = 0;
        topc.gridy = 4;
        topPanel.add(lengthLbl, topc);
        topc.gridx = 1;
        topc.gridy = 4;
        topPanel.add(lengthSelect, topc);

        //day select
        topc.gridx = 0;
        topc.gridy = 5;
        topPanel.add(dayLbl, topc);
        topc.gridx = 1;
        topc.gridy = 5;
        topPanel.add(daySelect, topc);

        //add btn
        topc.gridx = 1;
        topc.gridy = 6;
        topPanel.add(addBtn, topc);

        //center panel layout-------------------------------------------
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        model = new DefaultTableModel(null, column);
        table = new JTable(model);
        table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        table.setRowHeight(24);
        tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(670, 350));
        centerPanel.add(tableScroll);
        //--------------------------------------------------------------

        return mainPanel;
    }

    Activity a;
    public static ActivityHandler ah = new ActivityHandler();

    Modules m;

    DateValidation val = new DateValidation();

    //variable declarations
    GridBagConstraints mainc = new GridBagConstraints();
    GridBagConstraints topc = new GridBagConstraints();

    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();

    JScrollPane tableScroll;

    JButton addBtn = new JButton("Add");

    JTextField roomField = new JTextField();
    JLabel roomLbl = new JLabel("Room");

    String[] activityTypes = {"Lab", "Tutorial", "Lecture", "Seminar"};
    JComboBox<String> typeSelect = new JComboBox<String>(activityTypes);
    JLabel typeLbl = new JLabel("Activity Type");

    public static DefaultComboBoxModel selectModel = new DefaultComboBoxModel();
    JComboBox moduleSelect = new JComboBox(selectModel);
    JLabel moduleLbl = new JLabel("Module");

    //date spinner
    final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
    JSpinner spinner = new JSpinner(sm);
    JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm");
    JLabel startLbl = new JLabel("Time Start");

    JComboBox<Integer> lengthSelect = new JComboBox<Integer>(new Integer[]{1, 2, 3});
    JLabel lengthLbl = new JLabel("Length (hrs)");

    JComboBox<String> daySelect = new JComboBox<String>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
    JLabel dayLbl = new JLabel("Day of week");

    JTable table;
    DefaultTableModel model;
    String[] column = {"ID", "Room", "Activity Type", "Module", "Time Start", "Time End", "Day"};

    TimetableGUI tg = new TimetableGUI();

    String moduleName;
    String moduleCode;
    String programme;
    String term;
    Integer year;


}
