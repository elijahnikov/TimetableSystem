class ProgrammeHandler {

    fun createProgramme(
        name: String,
        code: String,
        type: String,
    ): Programme {

        return Programme(
            name, code, type
        )
    }

    val programmeList: MutableList<Programme> = mutableListOf()

    fun addProgramme(programme: Programme) {
        programmeList.add(programme);
    }

    fun printProgramme(){
        println(programmeList)
    }

}