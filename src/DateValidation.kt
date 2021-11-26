class DateValidation {

    //date time validation, if returns true then there is an error
    fun validateDate(hour: Int, time: Int, length: Int): Boolean
    {

        //convert input time into military time
        //check if minutes is less than 10 to give a leading zero
        val timeFormat: String = if (time < 10)
        {
            "0$time"
        } else {
            "$time"
        }
        //hour format is final military time
        val hourFormat: Int = "$hour$timeFormat".toInt()
        val lengthFormat: Int = length * 100

        //check if input hour is between 9 and 21 hours
        if (hour !in 21 downTo 9)
        {
            return true
        }

        //make sure time is either half past the hour or on the hour e.g. 12:30 or 12:00
        if (time.toString() != "30" && time.toString() != "0"){
            return true
        }

        //check if input time is between 0 and 60 minutes
        if (time !in 60 downTo -1)
        {
            return true
        }

        //check if start time + length of activity is greater than 21
        //time is formatted to military time e.g. 09:40 = 940 or 15:40 = 1540
        if (hourFormat + lengthFormat > 2100)
        {
            return true
        }

        return false
    }

    //function to get end time from start time and length
    fun getEndTime(hour: Int, time: Int, length: Int): String
    {
        //as the earliest time possible is 09:00, no work on leading zeros needs to be done
        //e.g. a 09:00 activity will always end on or after 10:00
        val endHour = hour + length
        val timeFormat: String = if (time < 10)
        {
            "0$time"
        } else
        {
            "$time"
        }

        return "$endHour:$timeFormat"
    }

}