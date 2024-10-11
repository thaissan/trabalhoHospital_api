package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Paciente;
import br.com.serratec.repository.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente inserir(@Valid @RequestBody Paciente p) {
		return repository.save(p);
	}

	@PutMapping("{id}")
	public ResponseEntity<Paciente> alterarPaciente(@PathVariable Long id, @Valid @RequestBody Paciente p) {
		if (repository.existsById(id)) {
			p.setId(id);
			return ResponseEntity.ok(repository.save(p));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Paciente> inserirVarios(@Valid @RequestBody List<Paciente> ps) {
		return repository.saveAll(ps);
	}

	@GetMapping
	public List<Paciente> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}/consultas")
    public Paciente listarPacienteComConsultas(@Valid @PathVariable Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            throw new RuntimeException("Paciente não encontrado");
        }
    }

	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
