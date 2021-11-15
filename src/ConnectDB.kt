import java.sql.Connection
import java.sql.DriverManager

class ConnectDB {

    private val url: String = "jdbc:postgresql://localhost:5432/timetable"
    private val user: String = "postgres"
    private val pass: String = "postgres"

    var con: Connection? = null
    fun getConnection(): Connection? {
        return if (con != null) con else getConnection(url, user, pass)
    }

    private fun getConnection(db_name: String, user_name: String, password: String): Connection? {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/$db_name?user=$user_name&password=$password")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return con
    }

}