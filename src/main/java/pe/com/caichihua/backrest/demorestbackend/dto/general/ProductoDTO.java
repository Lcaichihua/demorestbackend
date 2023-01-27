package pe.com.caichihua.backrest.demorestbackend.dto.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;

    private String nombre;

    private Double precio;

    private Integer stock;

    private String estado;

}
