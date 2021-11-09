import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageProgrammeGUI {

    public JComponent programmeGUIHandler(){

        //button action(s)
        underBtn.setActionCommand("Undergraduate");
        postBtn.setActionCommand("Postgraduate");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //check if input fields are empty
                if (codeField.getText().equals("") || nameField.getText().equals("")){
                    JOptionPane.showMessageDialog(mainPanel,
                            "Please ensure all fields are filled.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (postBtn.isSelected()) {
                        type = "Postgraduate";
                    } else if (underBtn.isSelected()){
                        type = "Undergraduate";
                    }
                    programmeCode = codeField.getText();
                    programmeName = nameField.getText();

                    model.addRow(new Object[]{"TEMPID", "", "", "", ""});

                }

                p = ph.createProgramme(
                        nameField.getText(),
                        codeField.getText(),
                        "Postgraduate"
                );

                ph.addProgramme(p);
            }
        });

        testBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        //add radio buttons to group
        typeGroup.add(underBtn);
        typeGroup.add(postBtn);

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

        //radio button label
        topc.gridx = 0;
        topc.gridy = 2;
        topPanel.add(typeLbl, topc);
        //radio buttons
        topc.gridx = 1;
        topc.gridy = 2;
        topPanel.add(underBtn, topc);
        topc.gridx = 2;
        topc.gridy = 2;
        topc.insets = new Insets(0, 0, 10, 0);
        topPanel.add(postBtn, topc);

        //add button
        topc.gridx = 1;
        topc.gridy = 4;
        topPanel.add(addBtn, topc);

        topc.gridx = 2;
        topc.gridy = 4;
        topPanel.add(testBtn, topc);

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

    Programme p;
    ProgrammeHandler ph = new ProgrammeHandler();

    //variable declarations
    GridBagConstraints mainc = new GridBagConstraints();
    GridBagConstraints topc = new GridBagConstraints();

    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();

    JScrollPane tableScroll;

    JButton addBtn = new JButton("Add");
    JButton testBtn = new JButton("Test");

    JTextField nameField = new JTextField();
    JLabel nameLbl = new JLabel("Programme Name");

    JTextField codeField = new JTextField();
    JLabel codeLbl = new JLabel("Programme Code");

    JRadioButton postBtn = new JRadioButton("Postgraduate");
    JRadioButton underBtn = new JRadioButton("Undergraduate");
    ButtonGroup typeGroup = new ButtonGroup();
    JLabel typeLbl = new JLabel("Type");

    JTable table;
    DefaultTableModel model;
    String[] column = {"ID", "Programme Code", "Programme Name", "Type", ""};

    TimetableGUI tg = new TimetableGUI();

    //user inputs
    String programmeName;
    String programmeCode;
    String type;
}