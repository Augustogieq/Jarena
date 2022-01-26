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
		this.cont = Math.random(); // Contador aleatório de turnos, substitui o uso de static;

        if (!(Math.abs(this.getX() - 450) > 20 && Math.abs(this.getY() - 350) > 20)) {
            this.centro = true; // Agente está no centro do mapa;
        }

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
		if(!podeMoverPara(getDirecao())) {
            // Como não conseguimos nos mover, vamos escolher uma direção
            // nova.
           setDirecao(geraDirecaoAleatoria());
        }

		if(this.getEnergia() < 75){
            this.para();
        }
	}
	public void recebeuEnergia() {

		this.para();
        String x = String.format("%d", this.getX());
        String y = String.format("%d", this.getY());
        enviaMensagem("energyReceived/"+x+"/"+y+"/"+this.getId());

	}
	
	public void tomouDano(int energiaRestanteInimigo) {
		 if (this.getEnergia() > energiaRestanteInimigo) {
            this.para();
            String x = String.format("%d", this.getX());
            String y = String.format("%d", this.getY());
            enviaMensagem("help/"+x+"/"+y+"/"+this.getId());

        }
        else {
            
            if(!podeMoverPara(getDirecao())) {
                setDirecao(geraDirecaoAleatoria());
            // Corre pras colinas; Está perdendo o combate;
            
            }
        }
	}
	
	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
		enviaMensagem("combateGanho/");
	}
	
	public void recebeuMensagem(String mensagem) {
        
        String[] msg = mensagem.split("/");
        
        if(msg[0].equals("help") || msg[0].equals("energyReceived")) {
            
            int X = Integer.parseInt(msg[1]);
            int Y = Integer.parseInt(msg[2]);
            int ID = Integer.parseInt(msg[3]);
            if(this.getId() != ID){
            vaiAtePonto(X, Y);
            }
        }
        else if (msg[0].equals("combateGanho")) {
            setDirecao(geraDirecaoAleatoria());  // pois não precisa mais de ajuda;
        }
    }
          
           
	public void vaiAtePonto(int X,int Y) { // Vai até determinada coordenada;     
        if (Math.abs(this.getX()-X)>5)  { 
            if(this.getX()>X) { 
                setDirecao(ESQUERDA);
            } else setDirecao(DIREITA);
        }else {
            if(this.getY()>Y) {
                setDirecao(CIMA);
            } else setDirecao(BAIXO);
        }
    }

	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "Augusto".
		return "AgenteAugusto";
	}
}
