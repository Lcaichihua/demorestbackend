package pe.com.caichihua.backrest.demorestbackend.repository.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.caichihua.backrest.demorestbackend.entity.security.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

    UsuarioEntity findByUsuario(String usuario); // Spring Security

    //UsuarioEntity login(String usuario, String clave); // Clasico


}
