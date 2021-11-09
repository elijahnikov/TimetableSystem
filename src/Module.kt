class Module (
    programme: Programme,
    var name: String,
    var code: String,
    var term: Int,
    var year: Int,
    val activities: MutableList<Activity> = mutableListOf()
    ) {

    init {
        programme.modules.add(this)
    }

}