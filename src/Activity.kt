class Activity (
    module: Module,
    var room: String,
    var type: String,
    var start: String,
    var end: String,
    var day: String,
    ) {

    init {
        module.activities.add(this)
    }

}