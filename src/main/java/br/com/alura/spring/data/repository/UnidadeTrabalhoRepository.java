package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.model.UnidadeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface UnidadeTrabalhoRepository extends JpaRepository<UnidadeTrabalho, Integer> {
}
