import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

public class ManageModuleGUI
{

    public JComponent moduleGUIHandler()
    {
        //add module button
        addBtn.addActionListener(e ->
        {
            CreateData cd = new CreateData();
            cd.createModule(
                    nameField,
                    codeField,
                    programmeSelect,
                    termSelect,
                    yearSelect,
                    mainPanel,
                    mh,
                    model
            );

            nameField.setText("");
            codeField.setText("");
        });

        //changing year select combo box depending on which type of programme is selected
        //postgraduate = 1 year
        //undergraduate = 3 years
        programmeSelect.addActionListener (e ->
        {
            Programme p = ManageProgrammeGUI.ph.getProgramme(Objects.requireNonNull(programmeSelect.getSelectedItem()).toString());
            if (p != null)
            {
                if (p.getType().equals("Undergraduate"))
                {
                    yearSelect.setModel(undergraduateYearSelectModel);
                }
                else
                {
                    yearSelect.setModel(postgraduateYearSelectModel);
                }
            }
        });

        //sets initial model for year select as undergraduate
        yearSelect.setModel(undergraduateYearSelectModel);

        //sizing
        centerPanel.setMinimumSize(new Dimension(300, 400));
        centerPanel.setMaximumSize(centerPanel.getMinimumSize());
        centerPanel.setPreferredSize(centerPanel.getMinimumSize());

        topPanel.setMinimumSize(new Dimension(500, 330));
        topPanel.setMaximumSize(topPanel.getMinimumSize());
        topPanel.setPreferredSize(topPanel.getMinimumSize());

        nameField.setPreferredSize(new Dimension(150, 30));
        codeField.setPreferredSize(new Dimension(150, 30));

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

        //name label
        topc.gridx = 0;
        topc.gridy = 0;
        topPanel.add(nameLbl, topc);
        nameLbl.setBorder(new EmptyBorder(0,0,0,10));
        topc.insets = new Insets(0, 0, 5, 0);
        //name field
        topc.gridx = 1;
        topc.gridy = 0;
        topPanel.add(nameField, topc);

        //code label
        topc.gridx = 0;
        topc.gridy = 1;
        topPanel.add(codeLbl, topc);
        //code field
        topc.gridx = 1;
        topc.gridy = 1;
        topPanel.add(codeField, topc);

        //programme select
        topc.gridx = 0;
        topc.gridy = 2;
        topPanel.add(programmeLbl, topc);
        topc.gridx = 1;
        topc.gridy = 2;
        topPanel.add(programmeSelect, topc);

        //term select
        topc.gridx = 0;
        topc.gridy = 3;
        topPanel.add(termLbl, topc);
        topc.gridx = 1;
        topc.gridy = 3;
        topPanel.add(termSelect, topc);

        //year select
        topc.gridx = 0;
        topc.gridy = 4;
        topPanel.add(yearLbl, topc);
        topc.gridx = 1;
        topc.gridy = 4;
        topPanel.add(yearSelect, topc);

        //add btn
        topc.gridx = 1;
        topc.gridy = 5;
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

    public static ModuleHandler mh = new ModuleHandler();

    //variable declarations
    GridBagConstraints mainc = new GridBagConstraints();
    GridBagConstraints topc = new GridBagConstraints();

    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();

    JScrollPane tableScroll;

    JButton addBtn = new JButton("Add");

    JTextField nameField = new JTextField();
    JLabel nameLbl = new JLabel("Module Name");

    JTextField codeField = new JTextField();
    JLabel codeLbl = new JLabel("Module Code");

    public static DefaultComboBoxModel<String> selectModel = new DefaultComboBoxModel<>();
    JComboBox<String> programmeSelect = new JComboBox<>(selectModel);
    JLabel programmeLbl = new JLabel("Programme");

    String[] terms = {"Term 1: Sep-Dec", "Term 2: Jan-Apr"};
    JComboBox<String> termSelect = new JComboBox<>(terms);
    JLabel termLbl = new JLabel("Term");

    Integer[] years = {1, 2, 3};
    JComboBox<Integer> yearSelect = new JComboBox<>();
    JLabel yearLbl = new JLabel("Year");
    DefaultComboBoxModel<Integer> undergraduateYearSelectModel = new DefaultComboBoxModel<>(new Integer[]{1, 2, 3});
    DefaultComboBoxModel<Integer> postgraduateYearSelectModel = new DefaultComboBoxModel<>(new Integer[]{1});


    public static JTable table;
    public static DefaultTableModel model;
    String[] column = {"ID", "Module Name", "Module Code", "Programme", "Term", "Year"};

}
