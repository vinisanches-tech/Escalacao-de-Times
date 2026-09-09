package br.com.duxusdesafio.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {

	
	private final ApiService apiService;
	
	public ApiController(ApiService apiService) {
		
		this.apiService = apiService;
	}

@PostMapping("/integrantes")

	public Integrante cadastrarIntegrante(@RequestBody Integrante integrante) { // Envia cadastro dos integrantes
		return apiService.cadastrarIntegrante(integrante);
	} 

@PostMapping("/times")
	public Time cadastrarTime(@RequestBody Time time) { // Envia cadastro do time
		return apiService.cadastrarTime(time);
	}

// Organizado desta forma para separar envio de informações de recebimento das informações

@GetMapping("/times")

	public List<Time> listaTimes(){ // Puxa todos times
		return apiService.buscarTodosOsTimes();
	}

@GetMapping("/integrantes")
	public List<Integrante> listaIntegrantes(){ // Puxa todos integrantes
		return apiService.buscarTodosOsIntegrantes();
	}

@GetMapping("/timesData")
	public Time buscarTime(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) { // Puxa o time da data
	
    List<Time> todosOsTimes = apiService.buscarTodosOsTimes();
    	return apiService.timeDaData(data, todosOsTimes);
	}

@GetMapping("/integranteFav")
	public Integrante buscarIntegrante(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) { // Puxa integrante que mais foi escalado.
	
	List<Time> todosOsTimes = apiService.buscarTodosOsTimes();
		return apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);
	}

@GetMapping("/jogadoresTimeFav")
	public List<String> jogadoresTime(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) { // Puxa jogadores do time que mais jogou.
	
	List<Time> todosOsTimes = apiService.buscarTodosOsTimes();
		return apiService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
	}

@GetMapping("/funcaoFav")
	public String funcaoIntegrante(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) { // Puxa funcao mais usada nos times.
	
	List<Time> todosOsTimes = apiService.buscarTodosOsTimes();
		return apiService.funcaoMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
	}

@GetMapping("/timeMais")
	public String timeRecorrente(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) { // Puxa time com mais jogos.
	
	List<Time> todosOsTimes = apiService.buscarTodosOsTimes();
		return apiService.clubeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
	}

@GetMapping("/clubesPeriodo")
	public Map<String, Long> clubesPeriodo(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) { // Puxa quantas vezes o time jogou.
	
	List<Time> todosOsTimes = apiService.buscarTodosOsTimes();
		return apiService.contagemDeClubesNoPeriodo(dataInicial, dataFinal, todosOsTimes);
	}

@GetMapping("/funcaoMais")
	public Map<String, Long> funcaoPeriodo(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) { // Puxa quais funcoes foram usadas.
	
	List<Time> todosOsTimes = apiService.buscarTodosOsTimes();
		return apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
	}
}