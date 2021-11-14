import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.PrintWriter
import java.lang.Exception


class FilePersistence: Persistence() {

    override fun save(
        programmeList: MutableList<Programme>,
        modulesList: MutableList<Modules>,
        activityList: MutableList<Activity>
    )
    {

        //save programmes
        try {
            PrintWriter("programmes.csv").use { writer ->
                val sb = StringBuilder()
                for (programme in programmeList){
                    sb.append(programme.name)
                    sb.append(',')
                    sb.append(programme.code)
                    sb.append(',')
                    sb.append(programme.type)
                    sb.append('\n')
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
                for (modules in modulesList){

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
                for (modules in modulesList){

                }
                writer.write(sb.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}