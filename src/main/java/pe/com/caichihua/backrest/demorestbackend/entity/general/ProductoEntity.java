package pe.com.caichihua.backrest.demorestbackend.entity.general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProductoEntity")
@Table(name = "TBL_PRODUCTO")
public class ProductoEntity {

    @Id
    @Column(name = "ID_PRODUCTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProducto")
    @SequenceGenerator(sequenceName = "SEQ_PRODUCTO", allocationSize = 1, name = "seqProducto")
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

