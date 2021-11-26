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

    //list to store activities in
    val activityList: MutableList<Activity> = mutableListOf()

    //adds activity to list
    fun addActivity(activity: Activity)
    {
        activityList.add(activity)
    }

//    //gets a specific activity by its module code, returns a list
//    fun getActivityByModule(mCode: String): MutableList<Activity>
//    {
//
//        val list: MutableList<Activity> = mutableListOf()
//
//        for (activity in activityList)
//        {
//            if (activity.moduleCode == mCode)
//            {
//                list.add(activity)
//            }
//        }
//        return list
//    }

    fun getActivityByModule(mCode: String): MutableList<Activity>
    {
        return activityList.filter { a -> a.moduleCode == mCode } as MutableList<Activity>
    }

}