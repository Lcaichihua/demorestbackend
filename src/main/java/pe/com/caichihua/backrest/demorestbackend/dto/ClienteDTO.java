package pe.com.caichihua.backrest.demorestbackend.dto;

import javax.validation.constraints.Email;
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
public class ClienteDTO { // Bean

	private Long id;
	
	@NotBlank(message = "La razón social es requerida")
	@Size(min = 10, max = 220, message = "La razón social debe tener como mínimo {min} y máximo {max} caracteres")
	private String razonSocial;
	
	
	@NotBlank(message = "El ruc es requerido")
	@Size(min = 11, max = 11, message = "El ruc debe tener {min} números")
	private String ruc;
	
	private String telefono;
	
	//@Email(message = "El formato del correo no es el adecuado (hola@dominio.com)")
	//private String mail;
	
	private String estado;
	
}
