package pe.com.caichihua.backrest.demorestbackend.entity.procesos;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.com.caichihua.backrest.demorestbackend.entity.general.ProductoEntity;

import static java.util.Objects.isNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Data
@Entity(name = "PedidoDetalle")
@Table(name = "TBL_PEDIDO_DETALLE")
@EqualsAndHashCode
public class PedidoDetalleEntity implements Serializable {

    private static final long serialVersionUID = -5467458619551056586L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_DETALLE")
    private Long id;

    //@Positive(message = "La cantidad debe ser mayo que cero")
    @Column(name = "CANTIDAD")
    private Integer cantidad;

    //@Positive(message = "El precio debe ser mayo que cero")
    @Column(name = "PRECIO")
    private Double precio;

    //@Positive(message = "El sub total debe ser mayo que cero")
    @Column(name = "SUB_TOTAL")
    private Double subTotal;

    @Column(name = "ESTADO")
    @Builder.Default
    private String estado = "1";

    @JsonIgnore // Para evitar bucles
    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false)
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private ProductoEntity producto;


    @Override
    public String toString() {
        return "PedidoDetalleEntity [id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", subTotal="
                + subTotal + ", estado=" + estado + ", producto=" + producto + "]";
    }


    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }




    public Integer getCantidad() {
        return cantidad;
    }




    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }




    public Double getPrecio() {
        return precio;
    }




    public void setPrecio(Double precio) {
        this.precio = precio;
    }




    public Double getSubTotal() {
        return subTotal;
    }




    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }




    public String getEstado() {
        return estado;
    }




    public void setEstado(String estado) {
        this.estado = estado;
    }




    public PedidoEntity getPedido() {
        return pedido;
    }




    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }




    public ProductoEntity getProducto() {
        return producto;
    }




    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }




    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public void calcularSubTotal() {

        if (isNull(cantidad)) {
            cantidad = 0;
        }
        if (isNull(precio)) {
            precio = 0.0;
        }
        setSubTotal(cantidad * precio);
    }



}