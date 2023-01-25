package pe.com.caichihua.backrest.demorestbackend.entity.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProductoEntity")
@Table(name = "TBL_PRODUCTO")
public class ProductoEntity {
    @Id
    @Column(name = "ID_PRODUCTO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 10, max = 120, message = "El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    @Column(name = "NOMBRE")
    private String nombre;

    @Positive(message = "El precio debe ser positivo")
    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "ESTADO")
    private String estado;

}
