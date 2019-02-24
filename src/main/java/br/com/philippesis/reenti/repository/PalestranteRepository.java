package br.com.philippesis.reenti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.philippesis.reenti.entity.Palestrante;

public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {

}
