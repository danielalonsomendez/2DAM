package com.danielalonso.citas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/citas")
public class Controlador {

	ArrayList<Cita> citas = new ArrayList<>(
			Arrays.asList(new Cita(1, "Hola", "Pendiente", new Date()), new Cita(2, "Hola2", "Aceptada", new Date())

			));

	@GetMapping
	public ArrayList<Cita> obtenerCitas() {
		return citas;
	}

	@PostMapping
	public String agregarCita(@RequestBody Cita cita) {
		cita.setId(citas.size() + 1);
		if (!cita.getEstado().equals("Aceptada") && !cita.getEstado().equals("Pendiente")
				&& !cita.getEstado().equals("Cancelada")) {
			return "Estado de cita no valido";
		}
		citas.add(cita);
		return "Cita agregada: " + cita.getNombre();
	}

	@PutMapping("/{id}")
	public String actualizarCita(@PathVariable int id, @RequestBody Cita cita) {
		cita.setId(id);
		if (!cita.getEstado().equals("Aceptada") && !cita.getEstado().equals("Pendiente")
				&& !cita.getEstado().equals("Cancelada")) {
			return "Estado de cita no valido";
		}
		for (int i = 0; i < citas.size(); i++) {
			if (citas.get(i).getId() == id) {
				citas.set(i, cita);
				return "Cita actualizada: " + cita.getNombre();
			}
		}
		return "Cita no encontrada";
	}

	@DeleteMapping("/{id}")
	public String eliminarCita(@PathVariable int id) {
		for (int i = 0; i < citas.size(); i++) {
			if (citas.get(i).getId() == id) {
				citas.remove(i);
				return "Cita eliminada";
			}
		}

		return "Cita no encontrada";

	}

}
