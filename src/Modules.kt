class Modules (
    programme: Programme,
    var name: String,
    var code: String,
    var programmeCode: String,
    var term: Int,
    var year: Int,
    val activities: MutableList<Activity> = mutableListOf()
    ) {

    init {
        programme.modules.add(this)
        println("Name = $name")
        println("Code = $code")
        println("PCode = $programmeCode")
        println("Term = $term")
        println("Year = $year")
    }

}