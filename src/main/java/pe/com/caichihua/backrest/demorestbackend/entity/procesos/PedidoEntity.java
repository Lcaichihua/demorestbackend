package pe.com.caichihua.backrest.demorestbackend.entity.procesos;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ClienteEntity;

import static java.util.Objects.isNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "PedidoEntity")
@Table(name = "TBL_PEDIDO")
@EqualsAndHashCode
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PEDIDO")
    private Long id;

    @Size(min = 10, max = 240, message = "La glosa es requerido y debe tener como mínimo {min} y máximo {max} caracteres")
    @Column(name = "GLOSA")
    private String glosa;

    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;

    @Positive(message = "El precio debe ser mayor que cero")
    @Column(name = "TOTAL")
    private Double total;

    @Column(name = "ESTADO")
    @Builder.Default
    private String estado = "1";

    @NotNull(message = "El cliente es requerido")
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @Where(clause = "estado='1'")
    private List<PedidoDetalleEntity> detalle;

    public void calcularSubTotal() {

        Double tmpTotal = 0.0;

        for (PedidoDetalleEntity pedidoDetalle : detalle) {
            if (isNull(detalle) || detalle.isEmpty() || isNull(pedidoDetalle)) {
                setTotal(0.0);
            }

            tmpTotal += pedidoDetalle.getSubTotal();
        }
        setTotal(tmpTotal);
    }


    @PrePersist
    private void persistFechaRegistro() {
        fechaRegistro = new Date();
    }

    @PreUpdate
    private void updateFechaRegistro() {
        fechaRegistro = new Date();
    }



}
