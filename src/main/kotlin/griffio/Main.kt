package griffio

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.queries.Sample
import org.postgresql.ds.PGSimpleDataSource

private fun getSqlDriver() = PGSimpleDataSource().apply {
    setURL("jdbc:postgresql://localhost:5432/unnestdb")
    applicationName = "App Main"
}.asJdbcDriver()

fun main() {
    val driver = getSqlDriver()
    val sample = Sample(driver)
    sample.salesQueries.insert("McDoggies", arrayOf("57214-A", "TU-1225", "14754-Z"), arrayOf(8, 12, 7))
    sample.salesQueries.insert("Donut Hut", arrayOf("13214-Q", "H2251", "13FF-H"), arrayOf(4, 24, 8))
    sample.salesQueries.select().executeAsList().map(::println)
    sample.salesQueries.selectMaxHeadcount().executeAsList().map(::println)
    sample.salesQueries.select().executeAsList().map(::println)
    println("---------")
    sample.salesQueries.insertUsers(arrayOf("a", "b", "c"), arrayOf(1, 2, 3))
    sample.salesQueries.selectUsers().executeAsList().map(::println)
    println("---------")
    sample.salesQueries.updateUsers(arrayOf("a", "b"), arrayOf(31, 47))
    sample.salesQueries.selectUsers().executeAsList().map(::println)
    println("---------")
    sample.salesQueries.deleteUsers(arrayOf("a", "b"), arrayOf(31, 47))
    sample.salesQueries.selectUsers().executeAsList().map(::println)
    println("---------")
    sample.salesQueries.selectLocation("13ff").executeAsList().map(::println)
}
