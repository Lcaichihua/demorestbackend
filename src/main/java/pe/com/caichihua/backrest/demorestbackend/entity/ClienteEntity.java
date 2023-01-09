package pe.com.caichihua.backrest.demorestbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cliente")
@Table(name = "TBL_CLIENTE")
public class ClienteEntity {
	
	@Id
	@Column(name = "ID_CLIENTE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "La razón social es requerida")
	@Size(min = 10, max = 220, message = "La razón social debe tener como mínimo {min} y máximo {max} caracteres")
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	@NotBlank(message = "El ruc es requerido")
	@Size(min = 11, max = 11, message = "El ruc debe tener {min} números")
	@Column(name = "RUC")
	private String ruc;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "ESTADO")
	private String estado;
	
}
