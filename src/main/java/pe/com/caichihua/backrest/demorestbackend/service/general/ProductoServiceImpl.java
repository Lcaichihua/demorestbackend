package pe.com.caichihua.backrest.demorestbackend.service.general;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.caichihua.backrest.demorestbackend.dto.general.ProductoDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ProductoEntity;
import pe.com.caichihua.backrest.demorestbackend.mappers.ProductoMapper;
import pe.com.caichihua.backrest.demorestbackend.repository.general.ProductoRepository;
import pe.com.caichihua.backrest.demorestbackend.service.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoMapper productoMapper;

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public ProductoDTO findById(Long id) throws ServiceException {
		try {
			ProductoEntity productoEntity = productoRepository.findById(id).orElse(null);
			return productoMapper.toDTO(productoEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProductoDTO> findByLikeObject(ProductoDTO productoDTO) throws ServiceException {
		try {
			List<ProductoEntity> lstProductoEntities = productoRepository
					.findByLikeNombre("%" + productoDTO.getNombre() + "%");
			return lstProductoEntities.stream().map(c -> productoMapper.toDTO(c)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProductoDTO save(ProductoDTO productoDTO) throws ServiceException {
		try {
			productoDTO.setEstado("1");
			return productoMapper.toDTO(productoRepository.save(productoMapper.toEntity(productoDTO)));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProductoDTO update(ProductoDTO productoDTO) throws ServiceException {
		try {
			Optional<ProductoEntity> optProducto = productoRepository.findById(productoDTO.getId());
			if (optProducto.isPresent()) {
				ProductoEntity retProducto = optProducto.get();
				BeanUtils.copyProperties(productoDTO, retProducto);
				return productoMapper.toDTO(productoRepository.save(retProducto));
			}
			return null;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			productoRepository.delete(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public Boolean actualizarStock(Long id, Double stock) throws ServiceException {
		return null;
	}
}
