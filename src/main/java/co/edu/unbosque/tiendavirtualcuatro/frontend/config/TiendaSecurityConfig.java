/**
 * 
 */
package co.edu.unbosque.tiendavirtualcuatro.frontend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.UsuarioDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.security.TiendaUserDetailsService;

/**
 * @author
 *
 */
@Configuration
@EnableWebSecurity
public class TiendaSecurityConfig extends WebSecurityConfigurerAdapter {

  // @Autowired
  // private UsuarioDAO usuarioDAO;

  // public TiendaSecurityConfig(UsuarioDAO usuarioDAO) {
  // this.usuarioDAO = usuarioDAO;
  // }

  @Autowired
  private UserDetailsService tiendaUserDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .userDetailsService(this.tiendaUserDetailsService)
      .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .csrf().disable()
      .authorizeRequests()
      //.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
      //.permitAll()
      .antMatchers("/usuarios/**", "/clientes/**", "/proveedores/**",
        "/productos/**").hasAnyAuthority("administrador")
      .antMatchers("/ventas", "/reportes/**").hasAnyAuthority("usuario")
      .anyRequest().authenticated()
      .and()
      .formLogin().permitAll()
      .and()
      .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/login")
      .and()
      .httpBasic()
      .and()
      .exceptionHandling().accessDeniedPage("/403");
  }

  // @Override
  // public void configure(AuthenticationManagerBuilder auth) throws Exception {
  // auth.userDetailsService(new TiendaUserDetailsService(this.usuarioDAO));
  // }
}
