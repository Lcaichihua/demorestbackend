package pe.com.caichihua.backrest.demorestbackend.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pe.com.caichihua.backrest.demorestbackend.dto.general.ProductoDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ProductoEntity;

import static java.util.Objects.isNull;

@Component
public class ProductoMapper {

	// Mappers
	public ProductoDTO toDTO(ProductoEntity ProductoEntity) {
		//if(isNull(ProductoEntity)) {
		if(ProductoEntity==null) {
			return null;
		}
		ProductoDTO productoDTO = new ProductoDTO();
		BeanUtils.copyProperties(ProductoEntity,productoDTO );
		return productoDTO;
	}
	
	public ProductoEntity toEntity(ProductoDTO ProductoDTO) {
		if(isNull(ProductoDTO)) {
			return null;
		}
		ProductoEntity productoEntity= new ProductoEntity();
		BeanUtils.copyProperties(ProductoDTO,productoEntity);
		return productoEntity;
	}
}
