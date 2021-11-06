package keeper.project.et.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
abstract class SuperDAO {

    @Autowired
    protected lateinit var db : JdbcTemplate

}
