class ActivityHandler {

    fun createActivity(
        module: Module,
        room: String,
        type: String,
        start: String,
        end: String,
        day: String,
    ): Activity {

        return Activity(
            module, room, type, start, end, day
        )
    }

    val activityList: MutableList<Activity> = mutableListOf()

    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

}