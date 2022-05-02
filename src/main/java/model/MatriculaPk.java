package model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode //para meter equals y hashcode
@Embeddable
public class MatriculaPk implements Serializable{
	private int idCurso;
	private String usuario;
	
	//esta clase la maquina virtual de java te obliga a que sea serializable(un obj se pueda convertir en un muchos 1 y 0 para que se convierta en un obj)
	//para sol el problema le digo que implements la interfaz serializable
}
