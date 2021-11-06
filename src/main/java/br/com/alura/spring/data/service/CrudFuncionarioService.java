package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Cargo;
import br.com.alura.spring.data.model.Funcionario;
import br.com.alura.spring.data.model.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private CrudCargoService crudCargoService;
    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner){
        while(system){
            System.out.println("Qual ação vc quer fazer?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Editar");
            System.out.println("3 - Listar");
            System.out.println("4 - Excluir");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    editar(scanner);
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    excluir(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }

    }

    public void salvar(Scanner scanner){
        scanner = new Scanner(System.in);
        System.out.println("Nome Funcionário");
        String nome = scanner.next();
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        System.out.println("CPF Funcionário");
        String cpf = scanner.next();
        funcionario.setCpf(cpf);
        System.out.println("Salário Funcionário");
        Double salario = scanner.nextDouble();
        System.out.println(salario);
        funcionario.setSalario(salario);
        funcionario.setCargo(cargo(scanner).get());
        funcionario.setUnidadeTrabalhoList(lista(scanner));
        funcionario.setDataContratacao(new Date());
        funcionarioRepository.save(funcionario);
        System.out.println("Salvo!");
        listar();
    }

    public Optional<Cargo> cargo (Scanner scanner){
        Boolean isTrue = true;
        Optional<Cargo> cargo = Optional.of(new Cargo());

        scanner = new Scanner(System.in);
        if(isTrue){
            System.out.println("Cargo Funcionário ID");
            int id = scanner.nextInt();
            cargo = cargoRepository.findById(id);
        }else{
            isTrue = false;
        }

        return cargo;
    }

    public List<UnidadeTrabalho> lista(Scanner scanner){
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue){
            System.out.println("Digite unidade ID (para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0){
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            }
            else{
                isTrue = false;
            }
        }
        return unidades;
    }

    public void editar(Scanner scanner){
        scanner = new Scanner(System.in);
        System.out.println("Nome Funcionário");
        String nome = scanner.next();
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        System.out.println("CPF Funcionário");
        String cpf = scanner.next();
        funcionario.setCpf(cpf);
        System.out.println("Salário Funcionário");
        Double salario = scanner.nextDouble();
        System.out.println(salario);
        funcionario.setSalario(salario);
        funcionario.setCargo(cargo(scanner).get());
        funcionario.setUnidadeTrabalhoList(lista(scanner));
        funcionario.setDataContratacao(new Date());
        funcionarioRepository.save(funcionario);
        System.out.println("Editado!");
        listar();
    }

    public void listar(){
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    public void excluir(Scanner scanner){
        System.out.println("ID");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Excluido!");
        listar();
    }


}
