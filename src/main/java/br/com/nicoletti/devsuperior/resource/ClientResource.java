package br.com.nicoletti.devsuperior.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nicoletti.devsuperior.entity.to.ClientTO;
import br.com.nicoletti.devsuperior.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<Page<ClientTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ClientTO> out = clientService.findAll(pageRequest);
		return ResponseEntity.ok().body(out);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientTO> findById(@PathVariable Long id) {
		ClientTO out = clientService.findById(id);
		return ResponseEntity.ok().body(out);
	}

	@PostMapping
	public ResponseEntity<ClientTO> insert(@RequestBody ClientTO to) {
		ClientTO out = clientService.insert(to);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(out.getId()).toUri();
		return ResponseEntity.created(uri).body(out);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientTO> update(@PathVariable Long id, @RequestBody ClientTO to) {
		ClientTO out = clientService.update(id, to);
		return ResponseEntity.ok().body(out);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ClientTO> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
