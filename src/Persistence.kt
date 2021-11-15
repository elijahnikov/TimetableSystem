abstract class Persistence {

    companion object {
        fun createDBPersistence() = DBPersistence()

        fun createFilePersistence() = FilePersistence()
    }

    abstract fun save(
        programmeList: MutableList<Programme>,
        moduleList: MutableList<Modules>,
        activityList: MutableList<Activity>
    )

    abstract fun load()

}