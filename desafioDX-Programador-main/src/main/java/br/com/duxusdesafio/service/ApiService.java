package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService {

	// parte de passagem dos métodos para os repositorios de armazenamento em banco de dados.
	private final TimeRepository timeRepository;
	private final IntegranteRepository integranteRepository;
	private final ComposicaoTimeRepository composicaoTimeRepository;
	
	public ApiService(TimeRepository timeRepository, 
			IntegranteRepository integranteRepository,
			ComposicaoTimeRepository composicaoTimeRepository) {
		
		this.timeRepository = timeRepository;
		this.integranteRepository = integranteRepository;
		this.composicaoTimeRepository = composicaoTimeRepository;
		
	}


    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        // para cada time da lista de time, verificar qual pertence a data. Se for igual a data, retornar o time
    	
    	for (Time time: todosOsTimes) {
    		if (data.isEqual(time.getData())) {
    			return time;
    		}
    	}
        return null;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        /* para cada time da lista, verificar se está entre dataInicial e dataFinal, verificar se o integrante foi escalado. Se o integrante foi escalado
    		* adicionar contador +1. Retornar integrante com maior contagem.
    		*/
    	
    	// armazena os integrantes dos times
    	Map<Integrante, Integer> integrantes = new HashMap<>();
    	
    	
    	for (Time time : todosOsTimes) {
    		if (
    			(time.getData().isAfter(dataInicial) || time.getData().isEqual(dataInicial)) 
    			&& (time.getData().isBefore(dataFinal) || time.getData().isEqual(dataFinal))) { //utilizado parenteses ( || ) && (||) devido prioridade de leitura do java
    			
    			for (ComposicaoTime composicao : time.getComposicaoTime()) { //busca os integrantes pela escalação
    				
    				Integrante mais = composicao.getIntegrante(); //chama o integrante da classe ComposicaoTime
    				integrantes.put(mais, integrantes.getOrDefault(mais, 0) + 1); //aumenta sempre que aparecer o mesmo integrante
        			
    				}
    			}
    		}
    	
    	int MaiorContagem = 0; 
		Integrante integranteMaisUsado = null;
		
		
    	for (Map.Entry<Integrante, Integer> entry : integrantes.entrySet()) { // busca os valores para o resultado
			
			Integrante integrante = entry.getKey();
			int contador = entry.getValue();
		
			if (contador > MaiorContagem) {
				MaiorContagem = contador; //passa valor de dentro do for para fora
				integranteMaisUsado = integrante; 
			}
    	 }
        return integranteMaisUsado;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais recorrente dentro do período.
     * OBS: Time é o clube + composição em determinada data
     */
    public List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // para cada time da lista em determinada data, verificar se está nos times mais recorrentes. Se estiver, verificar os jogadores daquele time.
    	
    	Map<String, Integer> times = new HashMap<>();
    	
    	for (Time time : todosOsTimes) {
    		if ((time.getData().isAfter(dataInicial) || time.getData().isEqual(dataInicial)) 
    			&& (time.getData().isBefore(dataFinal) || time.getData().isEqual(dataFinal))) {
    				
    				String timesE = time.getNomeDoClube();
    				times.put(timesE, times.getOrDefault(timesE, 0) + 1);		
    		}
    	}
    	
    	int MaiorContagem = 0;
    	String timeMaisUsado = null;
				
    	for (Map.Entry<String, Integer> entry : times.entrySet()) {
    					
    		String time1 = entry.getKey();
    		int contador = entry.getValue();
   					
   			if (contador > MaiorContagem) {
   				MaiorContagem = contador;
   				timeMaisUsado = time1;
   			}
    	}
    				
    		List<String> integrantes = new ArrayList<>(); // cria lista vazia, para armazenar os integrantes do time mais usado
   			for (Time time : todosOsTimes) {
   				if (time.getNomeDoClube().equals(timeMaisUsado)) { //para todos os times da lista de times, verificar qual o nome do clube é igual ao time mais usado
    							
   					for(ComposicaoTime composicao : time.getComposicaoTime()) { // entrar na composicao dos times para puxar o jogador mais usado
    							
   						Integrante integrante = composicao.getIntegrante();	    							integrantes.add(integrante.getNome());	//armazena o jogador no arraylist
    				}
    			}	
    		}
   		return integrantes;
    }

    /**
     * Vai retornar a função mais recorrente nos times dentro do período
     */
    public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // para cada time da lista de times, verificar as funções dos jogadores e retornar a função mais recorrente deles.
    	
    	Map<String, Integer> funcoes = new HashMap<>();
    	
    	for (Time time : todosOsTimes) {
    		if ((time.getData().isAfter(dataInicial) || time.getData().isEqual(dataInicial)) 
        			&& (time.getData().isBefore(dataFinal) || time.getData().isEqual(dataFinal))) {
    			
    			for (ComposicaoTime composicao : time.getComposicaoTime()) {
    				
    				Integrante integrante = composicao.getIntegrante();
    				String funcoesE = integrante.getFuncao();
        			funcoes.put(funcoesE, funcoes.getOrDefault(funcoesE, 0) + 1);
    			}
    		
    		}
    	}

    	int MaiorContagem = 0;
    	String FuncaoMaisUsada = null;
    	
		for (Map.Entry<String, Integer> entry : funcoes.entrySet()) {
			
			String funcao1 = entry.getKey();
			int contador = entry.getValue();
			
			if (contador > MaiorContagem) {
   				MaiorContagem = contador;
   				FuncaoMaisUsada = funcao1;
			}	
		} 

		return FuncaoMaisUsada;
		
    }
    
    /**
     * Vai retornar o nome do Clube mais comum dentro do período
     */
    public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO para cada time da lista, em determinada data. Retornar o com mais jogos. ( código feito no método anterior, reutilizado)
    	
    	Map<String, Integer> times = new HashMap<>();
    	
    	for (Time time : todosOsTimes) {
    		if ((time.getData().isAfter(dataInicial) || time.getData().isEqual(dataInicial)) 
    			&& (time.getData().isBefore(dataFinal) || time.getData().isEqual(dataFinal))) {
    				
    				String timesE = time.getNomeDoClube();
    				times.put(timesE, times.getOrDefault(timesE, 0) + 1);		
    		}
    	}
    	
    	int MaiorContagem = 0;
    	String timeMaisUsado = null;
				
    	for (Map.Entry<String, Integer> entry : times.entrySet()) {
    					
    		String time1 = entry.getKey();
    		int contador = entry.getValue();
   					
   			if (contador > MaiorContagem) {
   				MaiorContagem = contador;
   				timeMaisUsado = time1;
   			}
    	}
        return timeMaisUsado;
    }


    /**
     * Vai retornar o número (quantidade) de aparições de cada Clube participante no período
     */
    public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // para cada time da lista, em determinado periodo, contar quantas vezes apareceram nesse período. (código reutilizado)
    	
    	
    	Map<String, Long> times = new HashMap<>(); //armazena os times
    	
    	for (Time time : todosOsTimes) {
    		if ((time.getData().isAfter(dataInicial) || time.getData().isEqual(dataInicial)) 
    			&& (time.getData().isBefore(dataFinal) || time.getData().isEqual(dataFinal))) {
    				
    				String timesE = time.getNomeDoClube();
    				times.put(timesE, times.getOrDefault(timesE, 0L) + 1L); //aumenta na lista as vezes que o time apareceu
    		}
    	}
    	
        return times;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período.
     * Dica - pense sobre repetições!
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // para cada funcao do integrante, em determinado período, contar quantas fezes apareceram nesse período. (código reutilizado)
    	
    	Map<String, Long> funcoes = new HashMap<>(); 
    	
    	for (Time time : todosOsTimes) {
    		if ((time.getData().isAfter(dataInicial) || time.getData().isEqual(dataInicial)) 
    			&& (time.getData().isBefore(dataFinal) || time.getData().isEqual(dataFinal))) {
    				
    			for (ComposicaoTime composicao : time.getComposicaoTime()) {
    				
    				Integrante integrante = composicao.getIntegrante();
    				String funcoesE = integrante.getFuncao();	
        			funcoes.put(funcoesE, funcoes.getOrDefault(funcoesE, 0L) + 1L); //aumenta na lista as vezes que o time apareceu
    			}
    		
    		}
    	}
        return funcoes;
    }
    
    public List<Time> buscarTodosOsTimes() {
        return timeRepository.findAll(); //findAll aproveitado do JpaRepository, para passar todos registros de time do banco de dados.
    }
    
    public List<Integrante> buscarTodosOsIntegrantes() {
    	return integranteRepository.findAll(); //puxa todos registros de integrantes do banco de dados.
    }
    
    
    public Integrante cadastrarIntegrante(Integrante integrante) {
    	return integranteRepository.save(integrante); //armazena integrante cadastrado via @RequestBody da ApiController
    }
    
    // parte para registrar time com jogadores e composição
    public Time cadastrarTime(Time time) {
    	
    	
    	List<ComposicaoTime> escalados = time.getComposicaoTime(); //necessario para puxar a composicao junto aos jogadores
    		
    	for (ComposicaoTime composicao : escalados) { 
    			composicao.setTime(time);
    	}
    	
    	Time timeSalvo = timeRepository.save(time);
        return timeSalvo; //armazena time cadastrado, junto dos jogadores e composicao
    }
    
    
    

}
