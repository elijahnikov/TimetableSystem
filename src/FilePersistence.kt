import java.io.BufferedReader
import java.io.File
import java.io.PrintWriter
import java.util.*


class FilePersistence: Persistence() {

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

    override fun load(
        programmePath: String,
        modulePath: String,
        activityPath: String
    )
    {
        loadProgrammes()
        loadModules()
        loadActivities()
    }

    private fun loadProgrammes(){
        val programmePath: String = System.getProperty("user.dir") + "\\programmes.csv"

        try {
            val scanner = Scanner(readFile(programmePath))
            var i: Int = 0
            while (scanner.hasNextLine()) {
                val line: String = scanner.nextLine()
                i++
                println("$i, $line")
            }
            scanner.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun loadModules(){
        val modulePath: String = System.getProperty("user.dir") + "\\modules.csv"

        try {
            val scanner = Scanner(readFile(modulePath))
            var i: Int = 0
            while (scanner.hasNextLine()) {
                val line: String = scanner.nextLine()
                i++
                println("$i, $line")
            }
            scanner.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun loadActivities(){
        val activityPath: String = System.getProperty("user.dir") + "\\activities.csv"

        try {
            val scanner = Scanner(readFile(activityPath))
            var i: Int = 0
            while (scanner.hasNextLine()) {
                val line: String = scanner.nextLine()
                i++
                println("$i, $line")
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