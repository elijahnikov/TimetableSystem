import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ManageModuleGUI {

    public JComponent moduleGUIHandler(){

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //check if input fields are empty
                if (codeField.getText().equals("") || nameField.getText().equals("") || programmeSelect.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(mainPanel,
                            "Please ensure all fields are filled.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    //get programme class instance from jcombobox select
                    p = ph.getProgramme(programmeSelect.getSelectedItem().toString());
                    String programmeCode = null;
                    if (p != null) {
                        programmeCode = p.getCode();
                    }

                    //create module instance
                    m = mh.createModule(
                            p,
                            nameField.getText(),
                            codeField.getText(),
                            termSelect.getSelectedIndex() + 1,
                            Integer.parseInt(String.valueOf(yearSelect.getSelectedItem()))
                    );

                    //add instance to list of modules
                    mh.addModule(m);

                    //add instance details to table
                    model.addRow(new Object[]{
                            mh.getModuleList().get(mh.getModuleList().size()-1).toString().replace("Module@", ""),
                            mh.getModuleList().get(mh.getModuleList().size()-1).getCode(),
                            mh.getModuleList().get(mh.getModuleList().size()-1).getName(),
                            programmeCode,
                            mh.getModuleList().get(mh.getModuleList().size()-1).getTerm(),
                            mh.getModuleList().get(mh.getModuleList().size()-1).getYear()
                    });

                }


            }
        });

        //sizing
        centerPanel.setMinimumSize(new Dimension(300, 350));
        centerPanel.setMaximumSize(centerPanel.getMinimumSize());
        centerPanel.setPreferredSize(centerPanel.getMinimumSize());

        topPanel.setMinimumSize(new Dimension(500, 250));
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

    Module m;
    Programme p;
    ModuleHandler mh = new ModuleHandler();
    ProgrammeHandler ph = ManageProgrammeGUI.ph;

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

    public static DefaultComboBoxModel selectModel = new DefaultComboBoxModel();
    JComboBox programmeSelect = new JComboBox(selectModel);
    JLabel programmeLbl = new JLabel("Programme");

    String[] terms = {"Term 1: Sep-Dec", "Term 2: Jan-Apr"};
    JComboBox<String> termSelect = new JComboBox<String>(terms);
    JLabel termLbl = new JLabel("Term");

    Integer[] years = {1, 2, 3};
    JComboBox<Integer> yearSelect = new JComboBox<Integer>(years);
    JLabel yearLbl = new JLabel("Year");

    JTable table;
    DefaultTableModel model;
    String[] column = {"ID", "Module Code", "Module Name", "Programme", "Term", "Year"};

    TimetableGUI tg = new TimetableGUI();

    String moduleName;
    String moduleCode;
    String programme;
    String term;
    Integer year;

}
