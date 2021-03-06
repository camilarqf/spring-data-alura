package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
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
        System.out.println("Descrição do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo!");
        listar();
    }

    public void editar(Scanner scanner){
        scanner = new Scanner(System.in);
        System.out.println("ID");
        int id = scanner.nextInt();
        System.out.println("Descrição do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Editado!");
        listar();
    }

    public void listar(){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    public void excluir(Scanner scanner){
        System.out.println("ID");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Excluido!");
        listar();
    }


}
