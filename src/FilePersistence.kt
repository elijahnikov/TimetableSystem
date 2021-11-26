import java.io.*
import java.util.*

class FilePersistence: Persistence()
{

    private var p: Programme? = null
    private var m: Modules? = null
    private var a: Activity? = null

    //save data to file
    override fun save(
        programmeList: MutableList<Programme>,
        moduleList: MutableList<Modules>,
        activityList: MutableList<Activity>
    )
    {

        //save programmes
        try
        {
            PrintWriter("programmes.csv").use { writer ->
                val sb = StringBuilder()
                for (programme in programmeList)
                {
                    //save each programme and its details to a line, separated by commas
                    sb.append(programme.name)
                    sb.append(",")
                    sb.append(programme.code)
                    sb.append(",")
                    sb.append(programme.type)
                    sb.append("\n")
                }
                writer.write(sb.toString())
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }

        //saving modules
        try
        {
            PrintWriter("modules.csv").use { writer ->
                val sb = StringBuilder()
                for (modules in moduleList)
                {
                    //save each module and its details to a line, separated by commas
                    sb.append(modules.name)
                    sb.append(",")
                    sb.append(modules.code)
                    sb.append(",")
                    sb.append(modules.programmeCode)
                    sb.append(",")
                    sb.append(modules.term)
                    sb.append(",")
                    sb.append(modules.year)
                    sb.append("\n")
                }
                writer.write(sb.toString())
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }

        //saving activities
        try
        {
            PrintWriter("activities.csv").use { writer ->
                val sb = StringBuilder()
                for (activity in activityList)
                {
                    //save each activity and its details to a line, separated by commas
                    sb.append(activity.room)
                    sb.append(",")
                    sb.append(activity.type)
                    sb.append(",")
                    sb.append(activity.moduleCode)
                    sb.append(",")
                    sb.append(activity.start)
                    sb.append(",")
                    sb.append(activity.end)
                    sb.append(",")
                    sb.append(activity.day)
                    sb.append("\n")
                }
                writer.write(sb.toString())
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
    }

    //load files
    override fun load()
    {

        //call to load functions, csv location is in project directory
        loadProgrammes(System.getProperty("user.dir") + "\\programmes.csv")
        loadModules(System.getProperty("user.dir") + "\\modules.csv")
        loadActivities(System.getProperty("user.dir") + "\\activities.csv")

        //enable module and activity panes when data has been loaded in
        if (ManageProgrammeGUI.ph.programmeList.size > 0)
        {
            MainGUI.tabbedPane.setEnabledAt(1, true)
        }

        if (ManageModuleGUI.mh.modulesList.size > 0)
        {
            MainGUI.tabbedPane.setEnabledAt(2, true)
        }

        //add instance details to table
        for (programme in ManageProgrammeGUI.ph.programmeList)
        {
            ManageProgrammeGUI.model.addRow(
                arrayOf<Any>(
                    programme.toString().replace("Programme@", ""),
                    programme.name,
                    programme.code,
                    programme.type,
                    "Open"//necessary for giving button in column "Open" text
                )
            )
            //add programme code to programme select in module gui
            ManageModuleGUI.selectModel.addElement(programme.code)
        }

        for (module in ManageModuleGUI.mh.modulesList)
        {
            ManageModuleGUI.model.addRow(
                arrayOf<Any>(
                    module.toString().replace("Modules@", ""),
                    module.name,
                    module.code,
                    module.programmeCode,
                    module.term,
                    module.year
                )
            )
            //add module code to module select in activity gui
            ManageActivityGUI.selectModel.addElement(module.code)
        }

        for (activity in ManageActivityGUI.ah.activityList)
        {
            ManageActivityGUI.model.addRow(
                arrayOf<Any>(
                    activity.toString().replace("Activity@", ""),
                    activity.room,
                    activity.type,
                    activity.moduleCode,
                    activity.start,
                    activity.end,
                    activity.day
                )
            )
        }
    }

    //load programmes from programmes.csv
    val loadProgrammes = { path: String ->

        try
        {
            var programmeName: String
            var programmeCode: String
            var programmeType: String

            val scanner = Scanner(readFile(path))

            while (scanner.hasNextLine())
            {

                //get each line from programme csv (each line is one saved instance)
                val line: String = scanner.nextLine()

                //get each value from line
                val entries = line.split(",").toTypedArray()
                programmeName = entries[0]
                programmeCode = entries[1]
                programmeType = entries[2]

                //create new programme instances from given data in csv
                p = ManageProgrammeGUI.ph.createProgramme(programmeName, programmeCode, programmeType)
                //add programme instance to list of instances
                ManageProgrammeGUI.ph.addProgramme(p!!)
            }
            scanner.close()
        } catch (e: FileNotFoundException)
        {
            println("File not found. Please ensure you save some data before loading.")
        }
    }

    val loadModules = { path: String ->

        try
        {
            var moduleName: String
            var moduleCode: String
            var programmeCode: String
            var term: String
            var year: String

            val scanner = Scanner(readFile(path))

            while (scanner.hasNextLine())
            {

                //get each line from module csv (each line is one saved instance)
                val line: String = scanner.nextLine()

                //get each value from line
                val entries = line.split(",").toTypedArray()
                moduleName = entries[0]
                moduleCode = entries[1]
                programmeCode = entries[2]
                term = entries[3]
                year = entries[4]

                //create new module instances from given data in csv
                //get programme from programme code and attach to module
                val p: Programme? = ManageProgrammeGUI.ph.getProgramme(programmeCode)
                println("P: ${p?.name}")
                m = p?.let {
                    ManageModuleGUI.mh.createModule(it, moduleName, moduleCode, programmeCode, term.toInt(), year.toInt())
                }
                ManageModuleGUI.mh.addModule(m!!)

            }
            scanner.close()
        } catch (e: FileNotFoundException)
        {
            println("File not found. Please ensure you save some data before loading.")
        }
    }

    val loadActivities = { path: String ->

        try
        {
            var room: String
            var type: String
            var moduleCode: String
            var timeStart: String
            var timeEnd: String
            var day: String

            val scanner = Scanner(readFile(path))

            while (scanner.hasNextLine())
            {

                //get each line from activities csv (each line is one saved instance)
                val line: String = scanner.nextLine()

                //get each value from line
                val entries = line.split(",").toTypedArray()
                room = entries[0]
                type = entries[1]
                moduleCode = entries[2]
                timeStart = entries[3]
                timeEnd = entries[4]
                day = entries[5]

                //create new activity instances from given data in csv
                //get module from module code and attach to activity
                val m: Modules? = ManageModuleGUI.mh.getModule(moduleCode)
                println("M: ${m?.name}")
                a = m?.let {
                    ManageActivityGUI.ah.createActivity(it, room, type, moduleCode, timeStart, timeEnd, day)
                }
                ManageActivityGUI.ah.addActivity(a!!)

            }
            scanner.close()
        } catch (e: FileNotFoundException)
        {
            println("File not found. Please ensure you save some data before loading.")
        }
    }

    //function to read file and return a buffered reader
    private fun readFile(path: String): String
    {
        val bufferedReader: BufferedReader = File(path).bufferedReader()
        return bufferedReader.use {
            it.readText()
        }
    }

}