/**
 * 
 */
package co.edu.unbosque.tiendavirtualcuatro.frontend.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.UsuarioDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;

/**
 * @author 
 *
 */
@Service
public class TiendaUserDetailsService implements UserDetailsService {
  
  @Autowired
  private UsuarioDAO usuarioDAO = new UsuarioDAO();
  
  //public TiendaUserDetailsService (UsuarioDAO usuarioDAO) {
  ///  this.usuarioDAO = usuarioDAO;
  //}
  
  
  
  @Override
  public UserDetails loadUserByUsername(String userName) 
  throws UsernameNotFoundException {
    UsuarioVO usuario = this.usuarioDAO.getUsuarioPorAliasUsuario(userName);
    
    if (usuario == null) {
      throw new UsernameNotFoundException(
        "Autenticación fallida: No existe este usuario: " + userName);
    }
    /*
    return org.springframework.security.core.userdetails.User
        .withUsername(usuario.getUsuario())
        .password(usuario.getPassword())
        .roles(usuario.getRol())
        .build();
    */
    return new org.springframework.security.core.userdetails.User(
      usuario.getUsuario(), usuario.getPassword(), getAuthorities(usuario));
    //return new org.springframework.security.core.userdetails.User(
    //  "adm", "123", getAuthorities(usuario));
  }
  
  private static Collection<? extends GrantedAuthority> getAuthorities(UsuarioVO usuario) {
    String[] roles = new String[] { usuario.getRol() };
    //String[] roles = new String[] { "administrador" };
    Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
    return authorities;
  }
  
}
