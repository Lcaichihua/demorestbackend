package pe.com.caichihua.backrest.demorestbackend.controller.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.com.caichihua.backrest.demorestbackend.controller.base.GenericREST;
import pe.com.caichihua.backrest.demorestbackend.dto.general.ProductoDTO;
import pe.com.caichihua.backrest.demorestbackend.service.general.service.ProductoService;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/productos")
public class ProductoREST extends GenericREST {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			if (id <= 0) {
				return ResponseEntity.badRequest().body(String.format("El id %d no es válido", id));
			}
			log.info("id -> " + id);
			ProductoDTO productoDTO = productoService.findById(id);
			if (!isNull(productoDTO)) {
				return ResponseEntity.ok(productoDTO);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/nombre")
	public ResponseEntity<?> findByLikeNombre(
			@RequestParam(value = "nombre", defaultValue = "") String nombre) {
		try {
			log.info("nombre -> " + nombre);

			List<ProductoDTO> productos = productoService
					.findByLikeObject(ProductoDTO.builder().nombre(nombre).build());
			if (isNull(productos) || productos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(productos);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	//https://www.baeldung.com/spring-boot-bean-validation
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Validated ProductoDTO producto, BindingResult result) {
		try {
			
			log.info("result" + result);
			
			if (result.hasErrors()) {
				return super.getErrors(result);
				//return ResponseEntity.badRequest().body(result.getAllErrors());
			}
			
			log.info("id -> " + producto);

			ProductoDTO productoDTO = productoService.save(producto);
			if (!isNull(productoDTO)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(productoDTO);
			}
			return ResponseEntity.badRequest().build();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductoDTO productoDTO) {
		try {
			log.info("id -> " + productoDTO);
			productoDTO.setId(id);
			ProductoDTO resProductoDTO = productoService.update(productoDTO);
			if (!isNull(resProductoDTO)) {
				return ResponseEntity.ok(resProductoDTO);
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	@GetMapping("/nombre-pagin")
	public ResponseEntity<?> findByLikeNombrePagin( @RequestParam(value = "page", defaultValue = "1") Integer page,
													@RequestParam(value = "size", defaultValue = "10") Integer size,
													@RequestParam(value = "nombre", defaultValue = "") String nombre) {
		try {
			log.info("nombre -> " + nombre);
			log.info("page -> " + page);
			log.info("size -> " + size);

			Pageable pageable = PageRequest.of(page-1, size);

			List<ProductoDTO> productos = productoService.findByLikeNombrePagin(pageable,nombre);
			if (isNull(productos) || productos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(productos);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			log.info("id -> " + id);
			productoService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
}
