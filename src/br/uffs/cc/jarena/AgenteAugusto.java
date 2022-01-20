/**
 * Um exemplo de agente que anda aleatoriamente na arena. Esse agente pode ser usado como base
 * para a criação de um agente mais esperto. Para mais informações sobre métodos que podem
 * ser utilizados, veja a classe Agente.java.
 * 
 * Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 */

package br.uffs.cc.jarena;

public class AgenteAugusto extends Agente
{
	private double rand,cont;
    private Boolean centro = false;
    //starta as variaveis de controle de localizacao

	
	public AgenteAugusto(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		setDirecao(geraDirecaoAleatoria());
	}
	
	public void pensa() {
		//removida a estrategia de se mover aleatoriamente e startado a operacao de setar a movimentação
		if (this.cont < 0.08) {

            if(!this.centro) {

                rand = Math.random();
                if(getX() < 450){
                    if(rand > 0.5) setDirecao(DIREITA);
                    else{

                        if(getY() < 350) setDirecao(BAIXO);
                        else setDirecao(CIMA);

                    }
                } else {
                    if(rand > 0.5) setDirecao(ESQUERDA);
                    else{

                        if(getY() < 350) setDirecao(BAIXO);
                        else setDirecao(CIMA);

                    }
                }    
            }
            else {
                setDirecao(geraDirecaoAleatoria());
            }
        }


		}
		
	//if(podeDividir() && getEnergia() >= 800) {
	//	divide(); cancelamos a divisao por enquanto
		}
	}
	
	public void recebeuEnergia() {
		
	}
	
	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).
	}
	
	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
	}
	
	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.
	}
	
	public void para(){
		
	}
	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "Augusto".
		return "Augusto";
	}
}
