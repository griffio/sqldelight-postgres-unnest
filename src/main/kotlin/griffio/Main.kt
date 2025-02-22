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
}
