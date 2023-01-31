package pe.com.caichihua.backrest.demorestbackend.service.general.service;

import pe.com.caichihua.backrest.demorestbackend.dto.general.ProductoDTO;
import pe.com.caichihua.backrest.demorestbackend.service.base.GenericService;
import pe.com.caichihua.backrest.demorestbackend.service.exceptions.ServiceException;

public interface ProductoService extends GenericService<ProductoDTO> {

	Boolean actualizarStock(Long id ,Double stock) throws ServiceException;
	
}
