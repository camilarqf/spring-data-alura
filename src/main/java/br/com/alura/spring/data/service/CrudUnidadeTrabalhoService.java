package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Cargo;
import br.com.alura.spring.data.model.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean system = true;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {

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
        System.out.println("Descrição da unidade");
        String descricao = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        System.out.println("Endereço da unidade");
        String endereco = scanner.next();
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo!");
        listar();
    }

    public void editar(Scanner scanner){
        scanner = new Scanner(System.in);
        System.out.println("ID");
        int id = scanner.nextInt();
        System.out.println("Descrição da unidade");
        String descricao = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        System.out.println("Endereço da unidade");
        String endereco = scanner.next();
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Editado!");
        listar();
    }

    public void listar(){
        Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
        unidadeTrabalhos.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));
    }

    public void excluir(Scanner scanner){
        System.out.println("ID");
        int id = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Excluido!");
        listar();
    }


}
