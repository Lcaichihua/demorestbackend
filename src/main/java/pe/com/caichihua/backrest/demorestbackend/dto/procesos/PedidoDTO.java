package pe.com.caichihua.backrest.demorestbackend.dto.procesos;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.caichihua.backrest.demorestbackend.dto.general.ClienteDTO;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ClienteEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

    private Long id;

    private String glosa;

    private Date fechaRegistro;

    private Double total;

    private String estado;

    private ClienteDTO cliente;

    private List<PedidoDetalleDTO> detalle;

}
