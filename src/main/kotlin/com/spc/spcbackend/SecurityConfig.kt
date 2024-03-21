import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration().apply {
            allowCredentials = false
            allowedOrigins = listOf("*") // Specify allowed origins
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
            allowedHeaders = listOf("*") // Allow all headers
        }
        source.registerCorsConfiguration("/**", config) // Apply configuration to all paths
        return CorsFilter(source)
    }

    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            securityMatcher("/api/**")
            authorizeHttpRequests { }
            cors { }
            httpBasic { disable() }
        }
        return http.build()
    }
}