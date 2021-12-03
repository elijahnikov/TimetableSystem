import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ManageActivityGUI
{

    public JComponent activityGUIHandler()
    {

        //add button
        addBtn.addActionListener(e ->
        {

            //gets input from spinner and HH:mm format
            String dateInput = de.getFormat().format(spinner.getValue());

            //module code from combobox
            String moduleCode = (String) moduleSelect.getSelectedItem();

            //parse selected time into hour and min
            int hour = Integer.parseInt(dateInput.substring(0, 2));
            int min = Integer.parseInt(dateInput.substring(3, 5));
            int length = lengthSelect.getSelectedIndex() + 1;

            //if roomfield input is empty
            if (roomField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Please ensure all fields are filled.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            //if validate date function returns any errors (true)
            else if (valid.validateDate(hour, min, length))
            {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Please ensure the duration of the activity is between the hours of 09:00 and 21:00" +
                                "\nGiven times must be at half past or on the hour (e.g. 12:00 or 12:30)",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            //clash detection with kotlin
            else if (MainGUI.clashDetection.equals("Kotlin") &&
                    kotlinClash.checkForClashes(
                            dateInput,
                            valid.getEndTime(hour, min, length),
                            Objects.requireNonNull(daySelect.getSelectedItem()).toString(),
                            Objects.requireNonNull(moduleCode),
                            roomField.getText()
                    ).size() > 0)
            {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        kotlinClash.clashesToString(),
                        "Error: Kotlin",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            //clash detection with scala
            else if (MainGUI.clashDetection.equals("Scala") &&
                    scalaClash.checkForClashes(
                            dateInput,
                            valid.getEndTime(hour, min, length),
                            Objects.requireNonNull(daySelect.getSelectedItem()).toString(),
                            moduleCode,
                            roomField.getText()
                    ).size() > 0)
            {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        scalaClash.clashesToString(),
                        "Error: Scala",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else {
                CreateData cd = new CreateData();
                cd.createActivity(
                        roomField,
                        typeSelect,
                        moduleSelect,
                        dateInput,
                        lengthSelect,
                        daySelect,
                        ah,
                        model
                );

                roomField.setText("");
            }
        });

        spinner.setEditor(de);

        //sizing
        centerPanel.setMinimumSize(new Dimension(300, 380));
        centerPanel.setMaximumSize(centerPanel.getMinimumSize());
        centerPanel.setPreferredSize(centerPanel.getMinimumSize());

        topPanel.setMinimumSize(new Dimension(500, 350));
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
    public static ActivityHandler ah = new ActivityHandler();
    DateValidation valid = new DateValidation();
    ClashDetection kotlinClash = new ClashDetection();
    ClashDetectionScala scalaClash = new ClashDetectionScala();

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
    JComboBox<String> typeSelect = new JComboBox<>(activityTypes);
    JLabel typeLbl = new JLabel("Activity Type");

    public static DefaultComboBoxModel<String> selectModel = new DefaultComboBoxModel<>();
    JComboBox<String> moduleSelect = new JComboBox<>(selectModel);
    JLabel moduleLbl = new JLabel("Module");

    //date spinner
    Date date = new Date();
    SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
    JSpinner spinner = new JSpinner(sm);
    JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm");
    JLabel startLbl = new JLabel("Time Start");

    JComboBox<Integer> lengthSelect = new JComboBox<>(new Integer[]{1, 2, 3});
    JLabel lengthLbl = new JLabel("Length (hrs)");

    JComboBox<String> daySelect = new JComboBox<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
    JLabel dayLbl = new JLabel("Day of week");

    public static JTable table;
    public static DefaultTableModel model;
    String[] column = {"ID", "Room", "Activity Type", "Module", "Time Start", "Time End", "Day"};

}
