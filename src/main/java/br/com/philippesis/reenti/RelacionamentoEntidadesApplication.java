package br.com.philippesis.reenti;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.philippesis.reenti.entity.*;
import br.com.philippesis.reenti.repository.PalestranteTemporarioRepository;
import br.com.philippesis.reenti.repository.PalestrasCurtasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.philippesis.reenti.repository.PalestraRepository;
import br.com.philippesis.reenti.repository.PalestranteRepository;

@SpringBootApplication
public class RelacionamentoEntidadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelacionamentoEntidadesApplication.class, args);
		
	}
	
	@Bean
	CommandLineRunner init(PalestraRepository palestraRepository,
						   PalestranteRepository palestranteRepository,
						   PalestrasCurtasRepository palestrasCurtasRepository,
						   PalestranteTemporarioRepository palestranteTemporarioRepository) {
		return args -> addPalestra(palestraRepository,
				palestranteRepository,
				palestrasCurtasRepository,
				palestranteTemporarioRepository);
	}
	
	private void addPalestra(PalestraRepository palestraRepository,
							 PalestranteRepository palestranteRepository,
									PalestrasCurtasRepository palestrasCurtasRepository,
							 PalestranteTemporarioRepository palestranteTemporarioRepository) {

		if(palestraRepository.findAll().isEmpty()) {

			List<Palestrante> palestrantes = new ArrayList<>();

			// Criando local
			Local local = makeLocal("Top A", 500, "502");

			// Criando palestrantes
			Palestrante palestrante1 = makePalestrante("Antonio Carlos", "Doutor em Data Base");
			Palestrante palestrante2 = makePalestrante("Letícia Campos", "Doutora em Sistemas da Informação");

			// Adicionando palestrantes na lista
			palestrantes.add(palestrante1);
			palestrantes.add(palestrante2);

			// Criando palestra
			Palestra p1 = Palestra.builder()
					.titulo("Palestra Relacionamento Entidades JPA")
					.dataHora(new Date())
					.duracao(90)
					.local(local)
					.palestrantes(palestrantes)
					.build();

			// Necessário adicionar a palestra também ao palestrante
			palestrante1.setPalestra(p1);
			palestrante2.setPalestra(p1);

			// Adicionando na tabela palestra
			palestraRepository.save(p1);


		} else if(palestrasCurtasRepository.findAll().isEmpty()) {

			List<PalestranteTemporario> palestrantes = new ArrayList<>();

			// Criando local
			Local local = makeLocal("Top A", 120, "101");

			// Criando palestrantes
			PalestranteTemporario palestrante1 = makePalestranteTemporario("Miriam Leite", "Doutora em BigData");
			PalestranteTemporario palestrante2 = makePalestranteTemporario("Cleber Machado", "Mestre em Ciência da Computação");

			// Adicionando palestrantes na lista
			palestrantes.add(palestrante1);
			palestrantes.add(palestrante2);

			// Tabela Palestras curtas

			PalestrasCurtas pc1 = PalestrasCurtas.builder()
					.titulo("Tema livre 15 minutos")
					.duracao(15)
					.dataHora(new Date())
					.local(local)
					.palestranteTemporario(palestrantes)
					.build();

			// Adicionando na tabela palestras curtas
			palestrasCurtasRepository.save(pc1);

		} else {

			// Listar palestras
			List<Palestra> palestras = palestraRepository.findAll();

			for (Palestra palestra : palestras) {
				System.out.println("Palestra titulo: "+palestra.getTitulo()+"\n"+
						"Data e Hora: "+palestra.getDataHora() +"\nLocal: Predio " +palestra.getLocal().getPredio());
			}

			// Listar palestrantes
			List<Palestrante> palestrantes = palestranteRepository.findAll();

			for (Palestrante palestrante : palestrantes) {
				System.out.println("Palestrante: "+palestrante.getNome()+"\nPalestra: "+palestrante.getPalestra().getTitulo());
			}

			// Listar Palestras Curtas
			List<PalestrasCurtas> palestrasCurtasList = palestrasCurtasRepository.findAll();

			for (PalestrasCurtas palestrasCurtas:palestrasCurtasList) {
				System.out.println("Palestra Curta: "+palestrasCurtas.getTitulo()+"\nLocal: Predio "+
						palestrasCurtas.getLocal().getPredio());
			}

			// Lista Palestrantes Temporários
			List<PalestranteTemporario> palestranteTemporarioList = palestranteTemporarioRepository.findAll();

			for (PalestranteTemporario palestranteTemporario:palestranteTemporarioList) {
				System.out.println("Palestrante Temporário: "+palestranteTemporario.getNome());
			}

		}
		
	}

	private Local makeLocal(String predio, int capacidade, String sala) {
		// Adicionando local
		Local local = Local.builder()
				.predio(predio)
				.capacidade(capacidade)
				.sala(sala)
				.build();

		return local;
	}

	private Palestrante makePalestrante(String nome, String miniBio) {
		Palestrante palestrante;
		palestrante = Palestrante.builder()
				.nome(nome)
				.miniBio(miniBio)
				.build();
		return palestrante;
	}

	private PalestranteTemporario makePalestranteTemporario(String nome, String miniBio) {
		PalestranteTemporario palestranteTemporario = PalestranteTemporario.builder()
				.nome(nome)
				.miniBio(miniBio)
				.build();
		return palestranteTemporario;
	}

}
