//package com.transportcompany.transport_app.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig(
//    private val jwtFilter: JwtAuthenticationFilter,
//    private val userDetailsService: UserDetailsServiceImpl
//) : WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity) {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/api/v4/employees/auth/**").permitAll() // разрешаем login без токена
//            .anyRequest().authenticated() // всё остальное — только с токеном
//            .and()
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
//    }
//
//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
//
//    @Bean
//    override fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }
//}
