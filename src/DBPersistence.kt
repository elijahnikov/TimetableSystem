import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement


class DBPersistence: Persistence() {

    private val url: String = "jdbc:postgresql://localhost:5432/timetable"
    private val user: String = "postgres"
    private val pass: String = "postgres"

    private var conn: Connection? = null

    override fun save(
        programmeList: MutableList<Programme>,
        moduleList: MutableList<Modules>,
        activityList: MutableList<Activity>
    )
    {

        try {
            val conn: Connection = DriverManager.getConnection(url, user, pass)
            val st: Statement = conn.createStatement()
            st.executeUpdate(
                "INSERT INTO  programmes (name, code, type) " +
                        "VALUES ('test', 'TEST1', 'Undergraduate')"
            )
            conn.close()
        } catch (e: Exception) {
            System.err.println("Got an exception! ")
            System.err.println(e.message)
        }
    }

    override fun load() {

    }
}