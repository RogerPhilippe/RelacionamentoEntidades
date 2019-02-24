package br.com.philippesis.reenti.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor

@Entity
public class Palestrante implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private String miniBio;
	
	@ManyToOne
	@JoinColumn(name = "palestra_id")
	private Palestra palestra;

}
