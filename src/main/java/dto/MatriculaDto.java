package dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MatriculaDto {
	private double nota;
	@JsonProperty("idCurso")
	private CursoDto cursoDto;
	@JsonProperty("alumno")
	private AlumnoDto alumnoDto;
}
