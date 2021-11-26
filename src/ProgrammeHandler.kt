class ProgrammeHandler
{

    fun createProgramme(
        name: String,
        code: String,
        type: String,
    ): Programme
    {

        return Programme(
            name, code, type
        )
    }

    val programmeList: MutableList<Programme> = mutableListOf()

    fun addProgramme(programme: Programme)
    {
        programmeList.add(programme)
    }

    //first method used of getting programme
//    fun getProgramme(pCode: String): Programme?
//    {
//        for (programme in programmeList)
//        {
//            if (programme.code == pCode)
//            {
//                return programme
//            }
//        }
//        return null
//    }

    fun getProgramme(pCode: String): Programme?
    {
        return programmeList.find { p -> p.code == pCode }
    }

}