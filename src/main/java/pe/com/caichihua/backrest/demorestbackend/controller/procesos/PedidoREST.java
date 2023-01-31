package pe.com.caichihua.backrest.demorestbackend.controller.procesos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.com.caichihua.backrest.demorestbackend.controller.base.GenericREST;
import pe.com.caichihua.backrest.demorestbackend.dto.procesos.PedidoDTO;
import pe.com.caichihua.backrest.demorestbackend.service.procesos.service.PedidoService;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/pedidos")
public class PedidoREST extends GenericREST {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id)  throws RuntimeException{
		try {
			if (id <= 0) {
				return ResponseEntity.badRequest().body(String.format("El id %d no es válido", id)); // Corregir
			}
			log.info("id -> " + id);
			PedidoDTO pedidoDTO = pedidoService.findById(id);
			if (!isNull(pedidoDTO)) {
				return ResponseEntity.ok(pedidoDTO);
			}
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/glosa")
	public ResponseEntity<?> findByLikeNombre(@RequestParam(value = "glosa", defaultValue = "") String glosa) {
		try {
			log.info("glosa -> " + glosa);
			List<PedidoDTO> pedidos = pedidoService
					.findByLikeObject(PedidoDTO.builder().glosa(glosa).build());
			if (isNull(pedidos) || pedidos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(pedidos);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}


	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Validated PedidoDTO pedido, BindingResult result) {
		try {
			log.info("result" + result);
			if (result.hasErrors()) {
				return super.getErrors(result);
			}
			log.info("id -> " + pedido);

			PedidoDTO pedidoDTO = pedidoService.save(pedido);

			if (!isNull(pedidoDTO)) {
				//PedidoDTO resPedido=pedidoService.findById(24L);
				//System.out.println(resPedido);
				return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
			}
			return ResponseEntity.badRequest().build();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	/*
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PedidoDTO pedidoDTO) {
		try {
			log.info("id -> " + pedidoDTO);
			pedidoDTO.setId(id);
			PedidoDTO resPedidoDTO = pedidoService.update(pedidoDTO);
			if (!isNull(resPedidoDTO)) {
				return ResponseEntity.ok(resPedidoDTO);
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
			pedidoService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}*/
}

