package Tesouro;

class Pontuacao {
	private int pontos;
	private int PONTOS_TESOURO = 10;
	private int PONTOS_ARMADILHA = -5;

	public Pontuacao() {
		this.pontos = 0;
	}

	public void adicionarTesouro() {
		pontos += PONTOS_TESOURO;
	}

	public void adicionarArmadilha() {
		pontos += PONTOS_ARMADILHA;
	}

	public int getPontos() {
		return pontos;
	}
	// Retorna a classificacao do jogador com base em seus pontos
	public String getClassificacao() {
		if (pontos >= 70) {
			return "Explorador Legendario!";
		} else if (pontos >= 50) {
			return "Cacador de Tesouros Experiente!";
		} else if (pontos >= 30) {
			return "Aventureiro Iniciante";
		} else {
			return "Precisa de mais pratica na exploracao";
		}
	}
}
