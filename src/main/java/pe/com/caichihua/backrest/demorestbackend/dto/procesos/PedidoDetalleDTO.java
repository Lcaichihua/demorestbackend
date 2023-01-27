package pe.com.caichihua.backrest.demorestbackend.dto.procesos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.caichihua.backrest.demorestbackend.dto.general.ProductoDTO;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDetalleDTO {

    private static final long serialVersionUID = -5467458619551056586L;

    private Long id;

    private Integer cantidad;

    private Double precio;

    private Double subTotal;

    @JsonIgnore
    private PedidoDTO pedido;

    private ProductoDTO producto;

    private String estado;
}
