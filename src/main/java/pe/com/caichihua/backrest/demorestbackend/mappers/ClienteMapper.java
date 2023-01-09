package pe.com.caichihua.backrest.demorestbackend.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pe.com.caichihua.backrest.demorestbackend.dto.ClienteDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.ClienteEntity;

import static java.util.Objects.isNull;

@Component
public class ClienteMapper {

	// Mappers
	public ClienteDTO toDTO(ClienteEntity clienteEntity) {
		//if(isNull(clienteEntity)) {
		if(clienteEntity==null) {
			return null;
		}
		ClienteDTO clienteDTO = new ClienteDTO();
		BeanUtils.copyProperties(clienteEntity,clienteDTO );
		return clienteDTO;
	}
	
	public ClienteEntity toEntity(ClienteDTO clienteDTO) {
		if(isNull(clienteDTO)) {
			return null;
		}
		ClienteEntity clienteEntity= new ClienteEntity();
		BeanUtils.copyProperties(clienteDTO,clienteEntity);
		return clienteEntity;
	}
}
