package pe.com.caichihua.backrest.demorestbackend.controller.general;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import pe.com.caichihua.backrest.demorestbackend.controller.base.GenericREST;
import pe.com.caichihua.backrest.demorestbackend.dto.general.ClienteDTO;
import pe.com.caichihua.backrest.demorestbackend.service.general.service.ClienteService;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class ClienteREST extends GenericREST {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			if (id <= 0) {
				return ResponseEntity.badRequest().body(String.format("El id %d no es válido", id));
			}
			log.info("id -> " + id);
			ClienteDTO clienteDTO = clienteService.findById(id);
			if (!isNull(clienteDTO)) {
				return ResponseEntity.ok(clienteDTO);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/razon-social")
	public ResponseEntity<?> findByLikeRazonSocial(
			@RequestParam(value = "razonSocial", defaultValue = "") String razonSocial) {
		try {
			log.info("razonSocial -> " + razonSocial);

			List<ClienteDTO> clientes = clienteService
					.findByLikeObject(ClienteDTO.builder().razonSocial(razonSocial).build());
			if (isNull(clientes) || clientes.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(clientes);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	//https://www.baeldung.com/spring-boot-bean-validation
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Validated ClienteDTO cliente, BindingResult result) {
		try {
			
			log.info("result" + result);
			
			if (result.hasErrors()) {
				return super.getErrors(result);
				//return ResponseEntity.badRequest().body(result.getAllErrors());
			}
			
			log.info("id -> " + cliente);

			ClienteDTO clienteDTO = clienteService.save(cliente);
			if (!isNull(clienteDTO)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
			}
			return ResponseEntity.badRequest().build();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ClienteDTO clienteDTO) {
		try {
			log.info("id -> " + clienteDTO);
			clienteDTO.setId(id);
			ClienteDTO resClienteDTO = clienteService.update(clienteDTO);
			if (!isNull(resClienteDTO)) {
				return ResponseEntity.ok(resClienteDTO);
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			log.info("id -> " + id);
			clienteService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
}
