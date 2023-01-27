package pe.com.caichihua.backrest.demorestbackend.service.general;


import java.util.Optional;

import pe.com.caichihua.backrest.demorestbackend.dto.general.ClienteDTO;
import pe.com.caichihua.backrest.demorestbackend.service.base.GenericService;
import pe.com.caichihua.backrest.demorestbackend.service.exceptions.ServiceException;

public interface ClienteService extends GenericService<ClienteDTO> {

	Optional<ClienteDTO> findByRUC(String ruc) throws ServiceException;
	
}
