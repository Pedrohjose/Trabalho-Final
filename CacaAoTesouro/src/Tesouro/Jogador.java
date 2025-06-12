package Tesouro;

class Jogador {
	private int tesourosEncontrados;
	private int tentativasUsadas;
	private Pontuacao pontuacao;

	public Jogador() {
		this.tesourosEncontrados = 0;
		this.tentativasUsadas = 0;
		this.pontuacao = new Pontuacao();
	}

	public void encontrarTesouro() {
		tesourosEncontrados++;
		pontuacao.adicionarTesouro();
	}

	public void encontrarArmadilha() {
		pontuacao.adicionarArmadilha();
	}

	public void usarTentativa() {
		tentativasUsadas++;
	}

	public int getTesoursEncontrados() {
		return tesourosEncontrados;
	}

	public int getTentativasUsadas() {
		return tentativasUsadas;
	}

	public int getPontuacao() {
		return pontuacao.getPontos();
	}

	public String getClassificacao() {
		return pontuacao.getClassificacao();
	}
}
