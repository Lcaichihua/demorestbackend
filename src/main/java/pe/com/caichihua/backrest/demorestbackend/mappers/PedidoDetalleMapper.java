package pe.com.caichihua.backrest.demorestbackend.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pe.com.caichihua.backrest.demorestbackend.dto.procesos.PedidoDetalleDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.procesos.PedidoDetalleEntity;

import static java.util.Objects.isNull;

@Component
public class PedidoDetalleMapper {

    public PedidoDetalleDTO toDTO(PedidoDetalleEntity pedidoDetalleEntity) {
        if(pedidoDetalleEntity==null) {
            return null;
        }
        PedidoDetalleDTO pedidoDetalleDTO = new PedidoDetalleDTO();
        BeanUtils.copyProperties(pedidoDetalleEntity,pedidoDetalleDTO );
        return pedidoDetalleDTO;
    }

    public PedidoDetalleEntity toEntity(PedidoDetalleDTO pedidoDetalleDTO) {
        if(isNull(pedidoDetalleDTO)) {
            return null;
        }
        PedidoDetalleEntity pedidoDetalleEntity= new PedidoDetalleEntity();
        BeanUtils.copyProperties(pedidoDetalleDTO,pedidoDetalleEntity);
        return pedidoDetalleEntity;
    }
}
