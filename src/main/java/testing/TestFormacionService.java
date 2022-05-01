package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.ServiceFormacion;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestFormacionService {
	
	@Autowired
	ServiceFormacion service;
	
	@Test
	void validarUsuario() {
		assertEquals("tomates", service.validarUsuario("admin", "a").getNombre());
	}
	
	@Test
	void cursosDelAlumno() {
		assertEquals("java 10", service.cursosDelAlumno("aaa").get(0).getNombre());
	}
	
	@Test
	void listaDeCursos() {
		assertEquals(18, service.listaDeCursos().size());
	}

}
