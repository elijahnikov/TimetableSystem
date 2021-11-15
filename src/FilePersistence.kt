import java.io.BufferedReader
import java.io.File
import java.io.PrintWriter
import java.util.*


class FilePersistence: Persistence() {

    var p: Programme? = null

    var m: Modules? = null

    var a: Activity? = null

    //save data to file
    override fun save(
        programmeList: MutableList<Programme>,
        moduleList: MutableList<Modules>,
        activityList: MutableList<Activity>
    )
    {

        //save programmes
        try {
            PrintWriter("programmes.csv").use { writer ->
                val sb = StringBuilder()
                for (programme in programmeList){
                    sb.append(programme.name)
                    sb.append(",")
                    sb.append(programme.code)
                    sb.append(",")
                    sb.append(programme.type)
                    sb.append("\n")
                }
                writer.write(sb.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //saving modules
        try {
            PrintWriter("modules.csv").use { writer ->
                val sb = StringBuilder()
                for (modules in moduleList){
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
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //saving activities
        try {
            PrintWriter("activities.csv").use { writer ->
                val sb = StringBuilder()
                for (activity in activityList){
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //load files
    override fun load() {

        loadProgrammes()
        println(ManageProgrammeGUI.ph.programmeList)
        loadModules()
        println(ManageModuleGUI.mh.modulesList)
        loadActivities()
        println(ManageActivityGUI.ah.activityList)

        //add loaded instances into table model(s)

        //add instance details to table
        ManageProgrammeGUI.model.addRow(
            arrayOf<Any>(

            )
        )

        ManageModuleGUI.model.addRow(
            arrayOf<Any>(

            )
        )

        ManageActivityGUI.model.addRow(
            arrayOf<Any>(

            )
        )
    }

    //load programmes from programmes.csv
    private fun loadProgrammes() {
        val programmePath: String = System.getProperty("user.dir") + "\\programmes.csv"

        try {
            var programmeName: String
            var programmeCode: String
            var programmeType: String

            val scanner = Scanner(readFile(programmePath))

            while (scanner.hasNextLine()) {

                //get each line from programme csv (each line is one saved instance)
                val line: String = scanner.nextLine()

                //get each value from line
                val entries = line.split(",").toTypedArray()
                println(line)
                programmeName = entries[0]
                programmeCode = entries[1]
                programmeType = entries[2]

                //create new programme instances from given data in csv
                p = ManageProgrammeGUI.ph.createProgramme(programmeName, programmeCode, programmeType)
                //add programme instance to list of instances
                ManageProgrammeGUI.ph.addProgramme(p!!)
            }
            scanner.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun loadModules(){
        val modulePath: String = System.getProperty("user.dir") + "\\modules.csv"

        try {
            var moduleName: String
            var moduleCode: String
            var programmeCode: String
            var term: String
            var year: String

            val scanner = Scanner(readFile(modulePath))

            while (scanner.hasNextLine()) {

                //get each line from module csv (each line is one saved instance)
                val line: String = scanner.nextLine()

                //get each value from line
                val entries = line.split(",").toTypedArray()
                println(line)
                moduleName = entries[0]
                moduleCode = entries[1]
                programmeCode = entries[2]
                term = entries[3]
                year = entries[4]

                //create new module instances from given data in csv
                //get programme from programme code and attach to module
                var p: Programme? = ManageProgrammeGUI.ph.getProgramme(programmeCode)
                println("P: ${p?.name}")
                m = p?.let {
                    ManageModuleGUI.mh.createModule(it, moduleName, moduleCode, programmeCode, term.toInt(), year.toInt())
                }
                ManageModuleGUI.mh.addModule(m!!)

            }
            scanner.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun loadActivities(){
        val activityPath: String = System.getProperty("user.dir") + "\\activities.csv"

        try {
            var room: String
            var type: String
            var moduleCode: String
            var timeStart: String
            var timeEnd: String
            var day: String

            val scanner = Scanner(readFile(activityPath))

            while (scanner.hasNextLine()) {

                //get each line from activities csv (each line is one saved instance)
                val line: String = scanner.nextLine()

                //get each value from line
                val entries = line.split(",").toTypedArray()
                println(line)
                room = entries[0]
                type = entries[1]
                moduleCode = entries[2]
                timeStart = entries[3]
                timeEnd = entries[4]
                day = entries[5]

                //create new activity instances from given data in csv
                //get module from module code and attach to module
                var m: Modules? = ManageModuleGUI.mh.getModule(moduleCode)
                println("M: ${m?.name}")
                a = m?.let {
                    ManageActivityGUI.ah.createActivity(it, room, type, moduleCode, timeStart, timeEnd, day)
                }
                ManageActivityGUI.ah.addActivity(a!!)

            }
            scanner.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun readFile(path: String): String {
        val bufferedReader: BufferedReader = File(path).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

}