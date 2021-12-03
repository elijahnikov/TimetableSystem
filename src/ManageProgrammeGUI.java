import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ManageProgrammeGUI
{

    public JComponent programmeGUIHandler()
    {

        //button action(s)
        underBtn.setActionCommand("Undergraduate");
        postBtn.setActionCommand("Postgraduate");

        //add programme button
        addBtn.addActionListener(e ->
        {
            CreateData cd = new CreateData();
            cd.createProgramme(
                    nameField,
                    codeField,
                    postBtn,
                    underBtn,
                    mainPanel,
                    ph,
                    model
            );

            nameField.setText("");
            codeField.setText("");
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

        //center panel layout-------------------------------------------
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        model = new DefaultTableModel(null, column);

        table = new JTable(model);
        table.getColumn("Timetable").setCellRenderer(new ButtonRenderer());
        table.getColumn("Timetable").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        table.setRowHeight(24);

        tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(670, 350));
        centerPanel.add(tableScroll);
        //--------------------------------------------------------------

        return mainPanel;
    }

    public static ProgrammeHandler ph = new ProgrammeHandler();

    //variable declarations
    GridBagConstraints mainc = new GridBagConstraints();
    GridBagConstraints topc = new GridBagConstraints();

    JPanel mainPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();

    JScrollPane tableScroll;

    JButton addBtn = new JButton("Add");

    JTextField nameField = new JTextField();
    JLabel nameLbl = new JLabel("Programme Name");

    JTextField codeField = new JTextField();
    JLabel codeLbl = new JLabel("Programme Code");

    JRadioButton postBtn = new JRadioButton("Postgraduate");
    JRadioButton underBtn = new JRadioButton("Undergraduate");
    ButtonGroup typeGroup = new ButtonGroup();
    JLabel typeLbl = new JLabel("Type");

    public static TimetableGUI tg;
    public static JTable table;
    public static DefaultTableModel model;
    String[] column = {"ID", "Programme Name", "Programme Code", "Type", "Timetable"};

    //____________________________________________________________________________________________________________
    //button renderer code for inserting button in each row
    //code referenced from: http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
    static class ButtonRenderer extends JButton implements TableCellRenderer
    {

        public ButtonRenderer()
        {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else
            {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    static class ButtonEditor extends DefaultCellEditor
    {

        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox)
        {
            super(checkBox);
            button = new JButton("Open");
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column)
        {
            if (isSelected)
            {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else
            {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue()
        {
            int column = 2;
            int row = table.getSelectedRow();
            String value = table.getModel().getValueAt(row, column).toString();

            if (isPushed)
            {
                tg = new TimetableGUI();
                tg.createGUI(value);
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing()
        {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
    //____________________________________________________________________________________________________________


}

