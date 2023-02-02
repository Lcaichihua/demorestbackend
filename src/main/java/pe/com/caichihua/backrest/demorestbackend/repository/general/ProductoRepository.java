package pe.com.caichihua.backrest.demorestbackend.repository.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ProductoEntity;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{



	@Query("select c from ProductoEntity c where upper(c.nombre) like upper(:nombre) and c.estado='1'")
	List<ProductoEntity> findByLikeNombre(@Param("nombre") String nombre);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE TBL_PRODUCTO SET ESTADO='0' WHERE ID_PRODUCTO=:id")
	void delete(@Param("id") Long id);

	@Modifying
	@Query("update ProductoEntity set stock=stock - :cantidad where id=:id") // JPQL
	void updataStock(@Param("id") Long id, @Param("cantidad") Integer stock);
}
