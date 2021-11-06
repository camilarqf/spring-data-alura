package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.model.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
}
