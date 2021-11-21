import java.sql.Connection
import java.sql.DriverManager


object ConnectDB
{

    private const val url: String = "timetable"
    private const val user: String = "postgres"
    private const val pass: String = "postgres"

    private var con: Connection? = null

    // get db, user, pass from settings file
    val connection: Connection?
        get() = if (con != null) con else getConnection(url, user, pass)
    // get db, user, pass from settings file

    private fun getConnection(db_name: String, user_name: String, password: String): Connection?
    {
        try
        {
            con = DriverManager.getConnection("jdbc:postgresql://localhost/$db_name?user=$user_name&password=$password")
        } catch (e: Exception)
        {
            e.printStackTrace()
        }
        return con
    }
}