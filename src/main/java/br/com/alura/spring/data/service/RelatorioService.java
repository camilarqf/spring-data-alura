package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {
    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        while(system){
            System.out.println("Qual ação vc quer fazer?");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar Funcionário nome");
            System.out.println("2 - Buscar Funcionário nome, data contratação e salário Maior");
            System.out.println("3 - Buscar Funcionário data contratação maior");
            System.out.println("4 - Excluir");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscarFuncionarioNome(scanner);
                    break;
                case 2:
                    buscarFuncionarioSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscarFuncionarioDataMaior(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }

    }

    public void buscarFuncionarioNome(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();
        List<Funcionario> funcionario = funcionarioRepository.findByNome(nome);
        funcionario.forEach(System.out::println);
    }

    public void buscarFuncionarioSalarioMaiorData(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();
        System.out.println("Qual salario deseja pesquisar?");
        Double salario = scanner.nextDouble();
        System.out.println("Qual data deseja pesquisar?");
        String data = scanner.next();
        LocalDate dataFormatada = LocalDate.parse(data, formato);
        List<Funcionario> lista = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, dataFormatada);
        lista.forEach(System.out::println);
    }

    public void buscarFuncionarioDataMaior(Scanner scanner){
        System.out.println("Qual data deseja pesquisar?");
        String data = scanner.next();
        LocalDate dataFormatada = LocalDate.parse(data, formato);
        List<Funcionario> lista = funcionarioRepository.findDataContratacaoMaior(dataFormatada);
        lista.forEach(System.out::println);
    }
}
