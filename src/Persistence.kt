abstract class Persistence {

    companion object {
        fun createDBPersistence() = DBPersistence()

        fun createFilePersistence() = FilePersistence()
    }

    abstract infix fun save(data: String)

}