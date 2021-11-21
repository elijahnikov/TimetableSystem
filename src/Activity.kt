class Activity (
    modules: Modules,
    var room: String,
    var type: String,
    var moduleCode: String,
    var start: String,
    var end: String,
    var day: String,
    )
{

    init
    {
        modules.activities.add(this)
//        println("Room = $room")
//        println("Type = $type")
//        println("MCode = $moduleCode")
//        println("Start = $start")
//        println("End = $end")
//        println("Day = $day")
    }

}