package br.com.philippesis.reenti;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.philippesis.reenti.entity.Local;
import br.com.philippesis.reenti.entity.Palestra;
import br.com.philippesis.reenti.entity.Palestrante;
import br.com.philippesis.reenti.repository.PalestraRepository;
import br.com.philippesis.reenti.repository.PalestranteRepository;

@SpringBootApplication
public class RelacionamentoEntidadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelacionamentoEntidadesApplication.class, args);
		
	}
	
	@Bean
	CommandLineRunner init(PalestraRepository palestraRepository, PalestranteRepository palestranteRepository) {
		return args -> addPalestra(palestraRepository, palestranteRepository);
	}
	
	private static void addPalestra(PalestraRepository palestraRepository, PalestranteRepository palestranteRepository) {
		
		if(palestraRepository.findAll().isEmpty()) {
		
			// Adicionando local
			Local local = Local.builder()
					.predio("Top A")
					.capacidade(100)
					.sala("502")
					.build();
			
			List<Palestrante> palestrantes = new ArrayList<Palestrante>();
			
			// Antonio
			Palestrante palestranteAntonio;
			palestranteAntonio = Palestrante.builder()
					.nome("Antonio Carlos")
					.build();
			palestrantes.add(palestranteAntonio);
			// Carla
			Palestrante palestranteCarla;
			palestranteCarla = Palestrante.builder()
					.nome("Carla Lúcia")
					.build();
			palestrantes.add(palestranteCarla);
			
			// Criando palestra
			Palestra p1 = Palestra.builder()
					.titulo("Palestra Relacionamento Entidades JPA")
					.dataHora(new Date())
					.duracao(90)
					.local(local)
					.palestrantes(palestrantes)
					.build();
			
			// Necessário adicionar a palestra também ao palestrante
			palestranteAntonio.setPalestra(p1);
			palestranteCarla.setPalestra(p1);
			
			palestraRepository.save(p1);
		
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
			
		}
		
	}

}
