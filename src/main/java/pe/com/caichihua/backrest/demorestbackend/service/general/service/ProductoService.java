package pe.com.caichihua.backrest.demorestbackend.service.general.service;

import org.springframework.data.domain.Pageable;
import pe.com.caichihua.backrest.demorestbackend.dto.general.ProductoDTO;
import pe.com.caichihua.backrest.demorestbackend.service.base.GenericService;
import pe.com.caichihua.backrest.demorestbackend.service.exceptions.ServiceException;

import java.util.List;

public interface ProductoService extends GenericService<ProductoDTO> {

	Boolean actualizarStock(Long id ,Double stock) throws ServiceException;

	List<ProductoDTO> findByLikeNombrePagin(Pageable pageable, String nombre) throws ServiceException;


}
