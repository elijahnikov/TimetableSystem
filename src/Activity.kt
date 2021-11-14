class Activity (
    modules: Modules,
    var room: String,
    var type: String,
    var moduleCode: String,
    var start: String,
    var end: String,
    var day: String,
    ) {

    init {
        modules.activities.add(this)
    }

}