package pe.com.caichihua.backrest.demorestbackend.service.general;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.caichihua.backrest.demorestbackend.dto.ClienteDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.ClienteEntity;
import pe.com.caichihua.backrest.demorestbackend.mappers.ClienteMapper;
import pe.com.caichihua.backrest.demorestbackend.repository.ClienteRepository;
import pe.com.caichihua.backrest.demorestbackend.service.exceptions.ServiceException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteMapper clienteMapper;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ClienteDTO findById(Long id) throws ServiceException {
		try {
			ClienteEntity clienteEntity = clienteRepository.findById(id).orElse(null);
			return clienteMapper.toDTO(clienteEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ClienteDTO> findByLikeObject(ClienteDTO clienteDTO) throws ServiceException {
		try {
			List<ClienteEntity> lstClienteEntities = clienteRepository
					.findByLikeRazonSocial("%" + clienteDTO.getRazonSocial() + "%");
			return lstClienteEntities.stream().map(c -> clienteMapper.toDTO(c)).toList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClienteDTO save(ClienteDTO clienteDTO) throws ServiceException {
		try {
			clienteDTO.setEstado("1");
			return clienteMapper.toDTO(clienteRepository.save(clienteMapper.toEntity(clienteDTO)));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClienteDTO update(ClienteDTO clienteDTO) throws ServiceException {
		try {
			Optional<ClienteEntity> optCliente = clienteRepository.findById(clienteDTO.getId());
			if (optCliente.isPresent()) {
				ClienteEntity retCliente = optCliente.get();
				BeanUtils.copyProperties(clienteDTO, retCliente);
				return clienteMapper.toDTO(clienteRepository.save(retCliente));
			}
			return null;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			clienteRepository.delete(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<ClienteDTO> findByRUC(String ruc) throws ServiceException {
		try {
			return Optional.empty();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
