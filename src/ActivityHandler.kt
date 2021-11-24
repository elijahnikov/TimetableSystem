class ActivityHandler
{
    fun createActivity(
        modules: Modules,
        room: String,
        type: String,
        moduleCode: String,
        start: String,
        end: String,
        day: String,
    ): Activity
    {
        return Activity(
            modules, room, type, moduleCode, start, end, day
        )
    }

    val activityList: MutableList<Activity> = mutableListOf()

    fun addActivity(activity: Activity)
    {
        activityList.add(activity)
    }

    fun getActivityByModule(mCode: String): MutableList<Activity>
    {

        val list: MutableList<Activity> = mutableListOf()

        for (activity in activityList)
        {
            if (activity.moduleCode == mCode)
            {
                list.add(activity)
            }
        }
        return list
    }

}