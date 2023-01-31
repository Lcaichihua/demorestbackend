package pe.com.caichihua.backrest.demorestbackend.service.procesos.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import pe.com.caichihua.backrest.demorestbackend.dto.procesos.PedidoDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ProductoEntity;
import pe.com.caichihua.backrest.demorestbackend.entity.procesos.PedidoDetalleEntity;
import pe.com.caichihua.backrest.demorestbackend.entity.procesos.PedidoEntity;
import pe.com.caichihua.backrest.demorestbackend.mappers.PedidoDetalleMapper;
import pe.com.caichihua.backrest.demorestbackend.mappers.PedidoMapper;
import pe.com.caichihua.backrest.demorestbackend.mappers.ProductoMapper;
import pe.com.caichihua.backrest.demorestbackend.repository.general.ProductoRepository;
import pe.com.caichihua.backrest.demorestbackend.repository.procesos.PedidoRepository;
import pe.com.caichihua.backrest.demorestbackend.service.exceptions.ServiceException;
import pe.com.caichihua.backrest.demorestbackend.service.procesos.service.PedidoService;

@Slf4j
@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoDetalleMapper pedidoDetalleMapper;

    @Autowired
    private ProductoMapper productoMapper;

    //@Autowired
    //private PedidoCustomRepository  pedidoCustomRepository;


    @Override
    public PedidoDTO findById(Long id) throws ServiceException {
        try {
            PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElse(null);
            return pedidoMapper.toDTO(pedidoEntity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PedidoDTO> findByLikeObject(PedidoDTO pedidoDTO) throws ServiceException {
        try {
            List<PedidoEntity> lstPedidoEntities = pedidoRepository.findByLikeGlosa("%" + pedidoDTO.getGlosa() + "%");
            return lstPedidoEntities.stream().map(c -> pedidoMapper.toDTO(c)).collect(Collectors.toList());
            // .toList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public PedidoDTO save(PedidoDTO pedidoDTO) throws ServiceException {
        try {

            PedidoEntity pedidoEntity = pedidoMapper.toEntity(pedidoDTO);

            List<PedidoDetalleEntity> listPedidoDetalleEntity = new ArrayList<>();

            // Detalle Pedido
            pedidoDTO.getDetalle().forEach(dp -> {

                // Buscando el producto

                PedidoDetalleEntity pedidoDetalleEntity = pedidoDetalleMapper.toEntity(dp);

                Optional<ProductoEntity> optProductoEntity = productoRepository.findById(dp.getProducto().getId());

                if (optProductoEntity.isPresent()) {

                    ProductoEntity producto = optProductoEntity.get();

                    pedidoDetalleEntity.setProducto(productoMapper.toEntity(dp.getProducto()));

                    pedidoDetalleEntity.setPrecio(producto.getPrecio());
                    pedidoDetalleEntity.calcularSubTotal();
                    pedidoDetalleEntity.setPedido(pedidoEntity);

                    pedidoDetalleEntity.setEstado("1");

                    listPedidoDetalleEntity.add(pedidoDetalleEntity);

                    // Actualizar la tabla producto


                }

            });

            pedidoEntity.setDetalle(listPedidoDetalleEntity);

            pedidoEntity.calcularSubTotal();

            pedidoEntity.setEstado("1");

            pedidoRepository.saveAndFlush(pedidoEntity);

           // pedidoCustomRepository.refresh(pedidoEntity);

            return pedidoMapper.toDTO(pedidoEntity);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public PedidoDTO update(PedidoDTO t) throws ServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) throws ServiceException {
        // TODO Auto-generated method stub

    }

}
