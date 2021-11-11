package br.com.alura.spring.data;

import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatorioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final CargoRepository cargoRepository;
	private final CrudCargoService crudCargoService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final RelatorioService relatorioService;
	private Boolean system = true;

	public Application(CargoRepository cargoRepository, CrudCargoService crudCargoService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, CrudFuncionarioService crudFuncionarioService, RelatorioService relatorioService) {
		this.cargoRepository = cargoRepository;
		this.crudCargoService = crudCargoService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.relatorioService = relatorioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system){
			System.out.println("Qual ação vc quer fazer?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade Trabalho");
			System.out.println("3 - Funcionário");
			System.out.println("4 - Relatórios");
			int action = scanner.nextInt();

			if(action == 1) {
				crudCargoService.inicial(scanner);
			}
			if(action == 2){
				crudUnidadeTrabalhoService.inicial(scanner);
			}
			if(action == 3){
				crudFuncionarioService.inicial(scanner);
			}
			if(action == 4){
				relatorioService.inicial(scanner);
			}

			system = false;


		}

	}
}
