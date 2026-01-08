package com.danielalonsomendez.ad_springboot;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
public class Controlador {
	
	ArrayList<Tarea> tareas = new ArrayList<>(
			Arrays.asList(
					new Tarea(1,"Estudiar Spring Boot",false),
					new Tarea(2,"Hacer la compra",true),
					new Tarea(3,"Terminar Spring Boot",false)
					));
	
	@GetMapping
	public ArrayList<Tarea> obtenerTareas() {
		return tareas;
	}
	@PostMapping
	public String agregarTarea(@RequestBody Tarea tarea) {
		tarea.setId(tareas.size()+1);
		tareas.add(tarea);
		return "Tarea agregado: " + tarea.getNombre();
	}
	
	@PutMapping("/{id}")
	public String actualizarTarea(@PathVariable int id) {
		if(id-1 >= 0 && id-1 < tareas.size()) {
			tareas.get(id-1).setCompletada(true);
			return "Tarea actualizado: " + tareas.get(id-1).getNombre();
		} else {
			return "ID inválido: " + id;
		}
	}
	@DeleteMapping("/{id}")
	public String eliminarTarea(@PathVariable int id) {
		if(id-1 >= 0 && id-1 < tareas.size()) {
			tareas.remove(id-1);
			return "Tarea eliminada";
		} else {
			return "Tarea no encontrada";
		}
	}

}
