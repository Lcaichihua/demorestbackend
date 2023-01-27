package pe.com.caichihua.backrest.demorestbackend.repository.procesos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ClienteEntity;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<ClienteEntity, Long>{

	@Query("select c from Cliente c where upper(c.glosa) like upper(:glosa) and c.estado='1'")
	List<ClienteEntity> findByLikeGlosa(@Param("glosa") String glosa);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE TBL_PEDIDO SET ESTADO='0' WHERE ID_CLIENTE=:id")
	void delete(@Param("id") Long id);
}
