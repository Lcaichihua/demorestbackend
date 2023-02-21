package pe.com.caichihua.backrest.demorestbackend.service.seguridad.impl;

import static java.util.Objects.*;
import static java.util.Collections.emptyList;
import org.springframework.beans.factory.annotation.Autowired;
/* Spring Security	*/
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.caichihua.backrest.demorestbackend.entity.security.UsuarioEntity;
import pe.com.caichihua.backrest.demorestbackend.repository.seguridad.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioEntity usuarioEntity= this.usuarioRepository.findByUsuario(username);

        if (isNull(usuarioEntity)) {
            throw new UsernameNotFoundException("Usuario no existe");
        }

        return new UserDetailsImpl(usuarioEntity);

        //return new User(usuarioEntity.getUsuario(),usuarioEntity.getClave(),emptyList());
    }

}
