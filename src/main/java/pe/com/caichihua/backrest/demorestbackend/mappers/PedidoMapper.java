package pe.com.caichihua.backrest.demorestbackend.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.caichihua.backrest.demorestbackend.dto.procesos.PedidoDTO;
import pe.com.caichihua.backrest.demorestbackend.dto.procesos.PedidoDetalleDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.procesos.PedidoEntity;

import java.util.ArrayList;

import static java.util.Objects.isNull;

@Component
public class PedidoMapper {
    @Autowired
    private PedidoDetalleMapper pedidoDetalleMapper;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    public PedidoDTO toDTO(PedidoEntity pedidoEntity) {

        if (pedidoEntity == null) {
            return null;
        }
        if (pedidoEntity.getDetalle() == null) {
            //	System.out.println("pedidoEntity.getDetalle() is null");
            return null;
        }
        PedidoDTO pedidoDTO = new PedidoDTO();

        BeanUtils.copyProperties(pedidoEntity, pedidoDTO);

        pedidoDTO.setDetalle(new ArrayList<>());

        pedidoEntity.getDetalle().forEach(pd -> {
            PedidoDetalleDTO pedidoDetalleDTO = pedidoDetalleMapper.toDTO(pd);
            pedidoDetalleDTO.setProducto(productoMapper.toDTO(pd.getProducto()));
            pedidoDTO.getDetalle().add(pedidoDetalleDTO);
        });

        return pedidoDTO;
    }

    public PedidoEntity toEntity(PedidoDTO pedidoDTO) {
        if (isNull(pedidoDTO)) {
            return null;
        }
        PedidoEntity pedidoEntity = new PedidoEntity();
        BeanUtils.copyProperties(pedidoDTO, pedidoEntity);
        pedidoEntity.setCliente(clienteMapper.toEntity(pedidoDTO.getCliente()));

        return pedidoEntity;
    }
}
