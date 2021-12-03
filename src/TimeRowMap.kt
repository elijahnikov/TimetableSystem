class TimeRowMap
{

    //class to map all activities in specific programme to rows and columns in timetable table

    //array of times
    private val times = arrayOf(
        "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00",
        "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
        "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30"
    )

    //map days to column indexes
    private val days: Map<String, Int> = mapOf(
        "Monday" to 1,
        "Tuesday" to 2,
        "Wednesday" to 3,
        "Thursday" to 4,
        "Friday" to 5
    )

    //map times to row indexes
    //can instead instantiate hashmap with times and index but this is more concise
    private fun timeHashMap(): HashMap<String, Int>
    {
        //hashmap with 24 key value pairs
        val hashMap: HashMap<String, Int> = HashMap(24)
        for ((index, value) in times.withIndex())
        {
            hashMap[value] = index + 1 //plus one is necessary to avoid first row in timetable table
        }
        return hashMap
    }

    fun mapToTimetableRows(code: String, term: Int, year: Int)
    {

        //list of modules by programme code that user opens to view timetable
        val moduleList: MutableList<Modules> = ManageModuleGUI.mh.getModuleByProgramme(code)

        //all activities in said programme
        var activities: MutableList<Activity>

        //array of index's
        var indexArr: MutableList<Int>?

        //for each module, get all activities and append to activity list
        for (module in moduleList)
        {
            if (module.term == term && module.year == year){
                //all activities with given module code that is in given term and year
                activities = ManageActivityGUI.ah.getActivityByModule(module.code)
                for (ac in activities)
                {
                    //array of index's
                    indexArr = mutableListOf()

                    //get index of start time and add to indexArr from timeHashMap
                    timeHashMap()[ac.start]?.let { indexArr.add(it) }
                    //get index of end time and add to indexArr from timeHashMap
                    val num: Int? = timeHashMap()[ac.end]
                    if (num != null)//null in case of bad entry
                    {
                        indexArr.add(num - 1)
                    }
                    //this is necessary as we need to get all the row indexes between start and end rows

                    //loop between the two numbers in indexArr, first number = first row, second number = last row
                    //e.g. if indexArr[0] == 10 and indexArr[1] == 15, loop and set values at row indexes between 10 and 15
                    for (i in indexArr[0]..indexArr[1])
                    {
                        //add to timetable table
                        TimetableGUI.model.setValueAt("${module.name} (${module.code}), ${ac.room} - ${ac.type}", i - 1, days[ac.day]!!)
                    }
                }
            }
        }
    }
}