import java.text.SimpleDateFormat
import java.util.*

class ClashDetection
{
    //list as null as we need to reset every time function is run
    //else clashes from previous attempts will remain and prevent activity creation
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

        //parse start and end times into date format to be able to do comparisons
        val sdf = SimpleDateFormat("HH:mm")
        val start: Date = sdf.parse(timeStart)
        val end: Date = sdf.parse(timeEnd)

        //module that given activity is part of
        val moduleToCheck: Modules? = ManageModuleGUI.mh.getModule(moduleCode)

        //check each module for year and term
        for (module in moduleList)
        {
            if (moduleToCheck != null)
            {
                //check if the activities' module has the same year and term as other modules
                if ((moduleToCheck.year == module.year) && (moduleToCheck.term == module.term))
                {
                    //for each activity in module
                    for (activity in activityList)
                    {
                        //get activities start and end times as actual time format
                        val activityStart: Date = sdf.parse(activity.start)
                        val activityEnd: Date = sdf.parse(activity.end)
                        //get module by activities module code
                        val mc: Modules? = ManageModuleGUI.mh.getModule(activity.moduleCode)
                        if (
                            activity.day == day && //if activity is on same day as others
                            mc?.year == moduleToCheck.year && //if said activities module is on same year and term as others
                            mc.term == moduleToCheck.term && //then we compare the times
                            ((start.after(activityStart) && start.before(activityEnd)) ||
                            (end.after(activityStart) && end.before(activityEnd)) ||
                            (start == activityStart && end == activityEnd) ||
                            ((start.before(activityStart) || start.after(activityStart)) && end == activityEnd) ||
                            (start == activityStart && (end.before(activityEnd) || end.after(activityEnd))))
                            //last two checks are necessary to allow b2b activities to occur
                        )
                        {
                            //if activity is not already in clashes list, add it
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

    //function to format the clashes list into a string to display to user
    fun clashesToString(): String
    {
        //if clashes append to string builder and display in popup message
        val sb = StringBuilder()
        sb.append("There is/are ${clashes?.size} clash(es):")
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
            sb.append("\n")
        }

        return sb.toString()

    }

}