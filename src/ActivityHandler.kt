class ActivityHandler {

    fun createActivity(
        modules: Modules,
        room: String,
        type: String,
        moduleCode: String,
        start: String,
        end: String,
        day: String,
    ): Activity {

        return Activity(
            modules, room, type, moduleCode, start, end, day
        )
    }

    val activityList: MutableList<Activity> = mutableListOf()

    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

}