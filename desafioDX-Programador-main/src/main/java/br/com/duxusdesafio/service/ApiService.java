package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    		* adicionar contador +1. Retornar integrante com maior contagem
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
				MaiorContagem = contador;
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
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar a função mais recorrente nos times dentro do período
     */
    public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome do Clube mais comum dentro do período
     */
    public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o número (quantidade) de aparições de cada Clube participante no período
     */
    public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período.
     * Dica - pense sobre repetições!
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

}
