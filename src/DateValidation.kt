class DateValidation {

    //date validation
    fun validateDate(hour: Int, time: Int, length: Int): Boolean{

        //convert input time into military time
        //check if minutes is less than 10 to give a leading zero
        var timeFormat: String = if (time < 10){
            "0$time"
        } else {
            "$time"
        }
        val hourFormat: Int = "$hour$timeFormat".toInt()
        val lengthFormat: Int = length * 100

        //check if input hour is between 9 and 21 hours
        if (hour !in 22 downTo 9){
            return true
        }

        //check if input time is between 0 and 60 minutes
        if (time !in 60 downTo -1){
            return true
        }

        //check if start time + length of activity is greater than 21
        //time is formatted to military time e.g. 09:40 = 940 or 15:40 = 1540
        if (hourFormat + lengthFormat > 2100){
            return true
        }

        return false
    }

    fun getEndTime(hour: Int, time: Int, length: Int): String {

        var endHour = hour + length;
        var timeFormat: String = if (time < 10){
            "0$time"
        } else {
            "$time"
        }
        return "$endHour:$timeFormat"

    }

    fun compareDates(hour: Int, time: Int, programmeList: List<Programme>, modulesList: List<Modules>, activityList: List<Activity>){

    }

}