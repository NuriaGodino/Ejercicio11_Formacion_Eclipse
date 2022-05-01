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
public class TestMatriculacion {
	
	@Autowired
	ServiceFormacion service;
	
	@Test
	void testMatriculacion() {
		service.matriculaAlumno("test1", 1);
		assertEquals(1, service.cursosDelAlumno("test1").size());
		assertEquals(9, service.alumosPorCurso("java").size());
	}
}
