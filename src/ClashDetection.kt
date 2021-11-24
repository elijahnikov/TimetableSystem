import java.text.SimpleDateFormat
import java.util.*

class ClashDetection
{
    //list as null as we need to reset every time function is run
    private var clashes: MutableList<Activity>? = null

    //function returns a list of activities that given activity clashes with
    fun checkForClashes(
        timeStart: String,
        timeEnd: String,
        day: String,
        moduleCode: String
    ): MutableList<Activity>
    {

        println("KOTLIN")
        val moduleList: MutableList<Modules> = ManageModuleGUI.mh.modulesList
        val activityList: MutableList<Activity> = ManageActivityGUI.ah.activityList
        clashes = mutableListOf()

        val sdf = SimpleDateFormat("HH:mm")
        val start: Date = sdf.parse(timeStart)
        val end: Date = sdf.parse(timeEnd)

        //module that given activity is part of
        val moduleToCheck: Modules? = ManageModuleGUI.mh.getModule(moduleCode)

        //check each module for year and term
        for (module in moduleList)
        {
            //check if the activities' module has the same year and term as other modules
            if (moduleToCheck != null)
            {
                if ((moduleToCheck.year == module.year) && (moduleToCheck.term == module.term))
                {
                    for (activity in activityList)
                    {
                        //get activities start and end times as actual time format
                        val activityStart: Date = sdf.parse(activity.start)
                        val activityEnd: Date = sdf.parse(activity.end)
                        val mc: Modules? = ManageModuleGUI.mh.getModule(activity.moduleCode)
                        if (
                            activity.day == day &&
                            mc?.year == moduleToCheck.year &&
                            mc.term == moduleToCheck.term &&
                            ((start.after(activityStart) && start.before(activityEnd)) ||
                            (end.after(activityStart) && end.before(activityEnd)) ||
                            (start == activityStart && end == activityEnd) ||
                            ((start.before(activityStart) || start.after(activityStart)) && end == activityEnd) ||
                            (start == activityStart && (end.before(activityEnd) || end.after(activityEnd))))
                        )
                        {
                            if (activity !in clashes!!)
                            {
                                clashes?.add(activity)
                            }
                        }
                    }
                }
            }
        }
        return clashes as MutableList<Activity>
    }

    fun clashesToString(): String
    {
        //if clashes append to string builder and display in popup message
        val sb = StringBuilder()
        for (clash in clashes!!)
        {
            sb.append(clash.room)
            sb.append(", ")
            sb.append(clash.moduleCode)
            sb.append(", ")
            sb.append(clash.day)
            sb.append(", ")
            sb.append(clash.start)
            sb.append(", ")
            sb.append(clash.end)
        }

        return sb.toString()

    }

}