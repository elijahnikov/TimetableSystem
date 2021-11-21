import javax.swing.*

class CreateData {

    //class to create data from button clicks in gui
    //separating gui from logic

    fun createProgramme(
        nameField: JTextField,
        codeField: JTextField,
        postBtn: JRadioButton,
        underBtn: JRadioButton,
        mainPanel: JPanel
    )
    {

        if (codeField.text.equals("") || nameField.text.equals("") || (!postBtn.isSelected && !underBtn.isSelected))
        {
            JOptionPane.showMessageDialog(
                mainPanel,
                "Please ensure all fields are filled.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        else
        {

            val type: String = if (postBtn.isSelected)
            {
                "Postgraduate"
            } else {
                "Undergraduate"
            }

            //create programme instance
            val p: Programme = ManageProgrammeGUI.ph.createProgramme(
                nameField.text,
                codeField.text,
                type
            )

            //add instance to list of programmes
            ManageProgrammeGUI.ph.addProgramme(p)

            //add instance details to table

            //add instance details to table
            ManageProgrammeGUI.model.addRow(
                arrayOf<Any>(
                    ManageProgrammeGUI.ph.programmeList[ManageProgrammeGUI.ph.programmeList.size - 1].toString()
                        .replace("Programme@", ""),
                    ManageProgrammeGUI.ph.programmeList[ManageProgrammeGUI.ph.programmeList.size - 1].name,
                    ManageProgrammeGUI.ph.programmeList[ManageProgrammeGUI.ph.programmeList.size - 1].code,
                    ManageProgrammeGUI.ph.programmeList[ManageProgrammeGUI.ph.programmeList.size - 1].type,
                    "Open"
                )
            )

            //add programme codes from instance to combobox in module class
            ManageModuleGUI.selectModel.addElement(
                ManageProgrammeGUI.ph.programmeList[ManageProgrammeGUI.ph.programmeList.size - 1].code
            )

            //enable module pane when a programme is created
            if (ManageProgrammeGUI.ph.programmeList.size > 0)
            {
                MainGUI.tabbedPane.setEnabledAt(1, true)
            }

        }

    }

    fun createModule(
        nameField: JTextField,
        codeField: JTextField,
        programmeSelect: JComboBox<String>,
        termSelect: JComboBox<String>,
        yearSelect: JComboBox<Int>,
        mainPanel: JPanel
    )
    {

        val programmeCode: String = programmeSelect.selectedItem as String

        if (codeField.text.equals("") || nameField.text.equals("") || programmeSelect.selectedItem == null)
        {
            JOptionPane.showMessageDialog(
                mainPanel,
                "Please ensure all fields are filled",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        else
        {

            //create module instance
            val m: Modules = ManageModuleGUI.mh.createModule(
                ManageProgrammeGUI.ph.getProgramme(programmeCode)!!,
                nameField.text,
                codeField.text,
                programmeCode,
                termSelect.selectedIndex + 1,
                yearSelect.selectedItem as Int
            )

            //add module instance to list of modules
            ManageModuleGUI.mh.addModule(m)

            //add instance details to table
            ManageModuleGUI.model.addRow(
                arrayOf<Any>(
                    ManageModuleGUI.mh.modulesList[ManageModuleGUI.mh.modulesList.size - 1].toString()
                        .replace("Modules@", ""),
                    ManageModuleGUI.mh.modulesList[ManageModuleGUI.mh.modulesList.size - 1].name,
                    ManageModuleGUI.mh.modulesList[ManageModuleGUI.mh.modulesList.size - 1].code,
                    ManageModuleGUI.mh.modulesList[ManageModuleGUI.mh.modulesList.size - 1].programmeCode,
                    ManageModuleGUI.mh.modulesList[ManageModuleGUI.mh.modulesList.size - 1].term,
                    ManageModuleGUI.mh.modulesList[ManageModuleGUI.mh.modulesList.size - 1].year
                )
            )

            //add module codes from instance to combobox in activity class
            ManageActivityGUI.selectModel.addElement(ManageModuleGUI.mh.modulesList[ManageModuleGUI.mh.modulesList.size - 1].code)

            //enable activity pane when a module is created
            if (ManageModuleGUI.mh.modulesList.size > 0)
            {
                MainGUI.tabbedPane.setEnabledAt(2, true)
            }
        }

    }

    //
    fun createActivity(
        roomField: JTextField,
        typeSelect: JComboBox<String>,
        moduleSelect: JComboBox<String>,
        dateInput: String,
        lengthSelect: JComboBox<Int>,
        daySelect: JComboBox<String>,
        mainPanel: JPanel
    )
    {

        val moduleCode: String = moduleSelect.selectedItem as String

        val hour: Int = dateInput.substring(0, 2).toInt()
        val min: Int = dateInput.substring(3, 5).toInt()
        val length: Int = lengthSelect.selectedIndex + 1

        //perform validation checks on input and perform clash detection

        if (roomField.text.equals(""))//if room input is empty
        {
            JOptionPane.showMessageDialog(
                mainPanel,
                "Please ensure all fields are filled.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        else if (valid.validateDate(hour, min, length))//check for incorrect times, e.g. before 9 am or after 9pm
        {
            JOptionPane.showMessageDialog(
                mainPanel,
                "Please ensure the duration of the activity is between the hours of 09:00 and 21:00",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        else if (
            clashes.checkForClashes(
                dateInput,
                valid.getEndTime(hour, min, length),
                daySelect.selectedItem.toString(),
                moduleCode,
                ManageModuleGUI.mh.modulesList,
                ManageActivityGUI.ah.activityList
            ).size > 0
        )
        {
            JOptionPane.showMessageDialog(
                mainPanel,
                clashes.clashesToString(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
        }
        else
        {
            val a: Activity = ManageActivityGUI.ah.createActivity(
                ManageModuleGUI.mh.getModule(moduleCode)!!,
                roomField.text,
                typeSelect.selectedItem as String,
                moduleCode,
                dateInput,
                valid.getEndTime(hour, min, length),
                daySelect.selectedItem as String
            )

            ManageActivityGUI.ah.addActivity(a)

            //add instance details to table
            ManageActivityGUI.model.addRow(
                arrayOf<Any>(
                    ManageActivityGUI.ah.activityList[ManageActivityGUI.ah.activityList.size - 1].toString()
                        .replace("Activity@", ""),
                    ManageActivityGUI.ah.activityList[ManageActivityGUI.ah.activityList.size - 1].room,
                    ManageActivityGUI.ah.activityList[ManageActivityGUI.ah.activityList.size - 1].type,
                    ManageActivityGUI.ah.activityList[ManageActivityGUI.ah.activityList.size - 1].moduleCode,
                    ManageActivityGUI.ah.activityList[ManageActivityGUI.ah.activityList.size - 1].start,
                    ManageActivityGUI.ah.activityList[ManageActivityGUI.ah.activityList.size - 1].end,
                    ManageActivityGUI.ah.activityList[ManageActivityGUI.ah.activityList.size - 1].day
                )
            )
        }
    }

    private val valid = DateValidation()
    private val clashes = ClashDetection()
}