import java.text.SimpleDateFormat
import scala.collection.mutable.ListBuffer

class ClashDetectionScala
{
    var clashes = new ListBuffer[Activity]()

    def checkForClashes(
                           timeStart: String,
                           timeEnd: String,
                           day: String,
                           moduleCode: String,
                       ): ListBuffer[Activity] = {

        clashes = new ListBuffer[Activity]

        val moduleList = ManageModuleGUI.mh.getModulesList
        val activityList = ManageActivityGUI.ah.getActivityList

        val sdf = new SimpleDateFormat("HH:mm")
        val start = sdf.parse(timeStart)
        val end = sdf.parse(timeEnd)

        val moduleToCheck: Modules = ManageModuleGUI.mh.getModule(moduleCode)

        moduleList.forEach(m => {
            if (m != null)
            {
                if ((moduleToCheck.getYear == m.getYear) && (moduleToCheck.getTerm == m.getTerm))
                {
                    activityList.forEach(a => {
                        val activityStart = sdf.parse(a.getStart)
                        val activityEnd = sdf.parse(a.getEnd)
                        val mc: Modules = ManageModuleGUI.mh.getModule(a.getModuleCode)

                        if (
                            a.getDay == day &&
                                    mc.getYear == moduleToCheck.getYear &&
                                    mc.getTerm == moduleToCheck.getTerm &&
                                    ((start.after(activityStart) && start.before(activityEnd)) ||
                                            (end.after(activityStart) && end.before(activityEnd)) ||
                                            (start == activityStart && end == activityEnd) ||
                                            ((start.before(activityStart) || start.after(activityStart)) && end == activityEnd) ||
                                            (start == activityStart && (end.before(activityEnd) || end.after(activityEnd))))
                        )
                        {
                            if (!clashes.contains(a))
                            {
                                clashes.addOne(a)
                            }
                        }
                    })
                }
            }
        })
        clashes
    }

    def clashesToString(): String =
    {
        val sb = new StringBuilder()

        for (clash <- clashes)
        {
            sb.append(clash.getRoom)
            sb.append(", ")
            sb.append(clash.getModuleCode)
            sb.append(", ")
            sb.append(clash.getDay)
            sb.append(", ")
            sb.append(clash.getStart)
            sb.append(", ")
            sb.append(clash.getEnd)
        }
        clashes.empty
        sb.toString()
    }


}
