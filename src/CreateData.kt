import javax.swing.*
import javax.swing.table.DefaultTableModel

class CreateData {

    //class to create data from button clicks in gui
    //separating gui from logic

    fun createProgramme(
        nameField: JTextField,
        codeField: JTextField,
        postBtn: JRadioButton,
        underBtn: JRadioButton,
        mainPanel: JPanel,
        programmeHandler: ProgrammeHandler,
        model: DefaultTableModel
    )
    {
        //if code field and name field are empty, if programme type is not selected
        if (codeField.text == "" || nameField.text == "" || (!postBtn.isSelected && !underBtn.isSelected))
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
            //set programme type
            val type: String = if (postBtn.isSelected)
            {
                "Postgraduate"
            } else {
                "Undergraduate"
            }

            //create programme instance
            val p: Programme = programmeHandler.createProgramme(
                nameField.text,
                codeField.text,
                type
            )

            //add instance to list of programmes
            programmeHandler.addProgramme(p)

            //add instance details to table
            model.addRow(
                arrayOf<Any>(
                    programmeHandler.programmeList[programmeHandler.programmeList.size - 1].toString()
                        .replace("Programme@", ""),
                    programmeHandler.programmeList[programmeHandler.programmeList.size - 1].name,
                    programmeHandler.programmeList[programmeHandler.programmeList.size - 1].code,
                    programmeHandler.programmeList[programmeHandler.programmeList.size - 1].type,
                    "Open"
                )
            )

            //add programme codes from instance to combobox in module class
            ManageModuleGUI.selectModel.addElement(
                programmeHandler.programmeList[programmeHandler.programmeList.size - 1].code
            )

            //enable module pane when a programme is created
            if (programmeHandler.programmeList.size > 0)
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
        mainPanel: JPanel,
        moduleHandler: ModuleHandler,
        model: DefaultTableModel
    )
    {

        //get programme code from combobox
        val programmeCode: String = programmeSelect.selectedItem as String

        //if code field or name field is empty, or by chance a programme code is not selected (very unlikely to happen)
        if (codeField.text == "" || nameField.text == "" || programmeSelect.selectedItem == null)
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

            //test to check if module code is unique
            var uniqueTest = false
            for (module in moduleHandler.modulesList)
            {
                if (module.code == codeField.text)
                {
                    uniqueTest = true
                }
            }

            //if input module code already exists
            if (uniqueTest)
            {
                JOptionPane.showMessageDialog(
                    mainPanel,
                    "Module codes must be unique.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                )
            } else {

                //create module instance
                val m: Modules = moduleHandler.createModule(
                    ManageProgrammeGUI.ph.getProgramme(programmeCode)!!,
                    nameField.text,
                    codeField.text,
                    programmeCode,
                    termSelect.selectedIndex + 1,
                    yearSelect.selectedItem as Int
                )

                //add module instance to list of modules
                moduleHandler.addModule(m)

                model.addRow(
                    arrayOf<Any>(
                        moduleHandler.modulesList[moduleHandler.modulesList.size - 1].toString()
                            .replace("Modules@", ""),
                        moduleHandler.modulesList[moduleHandler.modulesList.size - 1].name,
                        moduleHandler.modulesList[moduleHandler.modulesList.size - 1].code,
                        moduleHandler.modulesList[moduleHandler.modulesList.size - 1].programmeCode,
                        moduleHandler.modulesList[moduleHandler.modulesList.size - 1].term,
                        moduleHandler.modulesList[moduleHandler.modulesList.size - 1].year
                    )
                )

                //add module codes from instance to combobox in activity class
                ManageActivityGUI.selectModel.addElement(moduleHandler.modulesList[moduleHandler.modulesList.size - 1].code)

                //enable activity pane when a module is created
                if (moduleHandler.modulesList.size > 0)
                {
                    MainGUI.tabbedPane.setEnabledAt(2, true)
                }
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
        activityHandler: ActivityHandler,
        model: DefaultTableModel
    )
    {

        //slight error here; as with programme and modules, validation is meant to occur here as well.
        //however, I have not figured out a way to call scala classes in kotlin, which means
        //validation has to occur in java side because of second clash detection implementation in scala
        //definitely need to research more on this

        //module code from module select
        val moduleCode: String = moduleSelect.selectedItem as String

        //get hour and minutes and length from gui inputs
        val hour: Int = dateInput.substring(0, 2).toInt()
        val min: Int = dateInput.substring(3, 5).toInt()
        val length: Int = lengthSelect.selectedIndex + 1

        //create activity instance
        val a: Activity = activityHandler.createActivity(
            ManageModuleGUI.mh.getModule(moduleCode)!!,
            roomField.text,
            typeSelect.selectedItem as String,
            moduleCode,
            dateInput,
            valid.getEndTime(hour, min, length),
            daySelect.selectedItem as String
        )

        //add activity class instance to list
        activityHandler.addActivity(a)

        //add instance details to table
        model.addRow(
            arrayOf<Any>(
                activityHandler.activityList[activityHandler.activityList.size - 1].toString()
                    .replace("Activity@", ""),
                activityHandler.activityList[activityHandler.activityList.size - 1].room,
                activityHandler.activityList[activityHandler.activityList.size - 1].type,
                activityHandler.activityList[activityHandler.activityList.size - 1].moduleCode,
                activityHandler.activityList[activityHandler.activityList.size - 1].start,
                activityHandler.activityList[activityHandler.activityList.size - 1].end,
                activityHandler.activityList[activityHandler.activityList.size - 1].day
            )
        )

    }

    private val valid = DateValidation()
}