package keeper.project.et.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class HttpSecurityConfig : WebSecurityConfigurerAdapter(true){
    override fun configure(http: HttpSecurity?) {
        http?.httpBasic()?.disable()
            ?.cors()?.disable()
            ?.csrf()?.disable()
            ?.formLogin()?.disable()
    }
}
