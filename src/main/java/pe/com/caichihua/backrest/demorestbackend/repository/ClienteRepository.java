package pe.com.caichihua.backrest.demorestbackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.caichihua.backrest.demorestbackend.entity.ClienteEntity;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

	@Query("select c from Cliente c where upper(c.razonSocial) like upper(:razonSocial) and c.estado='1'")
	List<ClienteEntity> findByLikeRazonSocial(@Param("razonSocial") String razonSocial);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE TBL_CLIENTE SET ESTADO='0' WHERE ID_CLIENTE=:id")
	void delete(@Param("id") Long id);
}
