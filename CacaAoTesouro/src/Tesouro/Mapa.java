package Tesouro;

import java.util.Random;

class Mapa {
	private int TAMANHO = 8;
	private char[][] mapaOculto;
	private char[][] mapaVisivel;
	private boolean[][] posicaoEscavada;

	private char AREIA = '~';
	private char TESOURO_OCULTO = 't';
	private char ARMADILHA_OCULTA = 'a';
	private char TESOURO_ENCONTRADO = 'T';
	private char ARMADILHA_ENCONTRADA = 'A';
	private char POSICAO_VAZIA = 'O';

	public Mapa() {
		mapaOculto = new char[TAMANHO][TAMANHO];
		mapaVisivel = new char[TAMANHO][TAMANHO];
		posicaoEscavada = new boolean[TAMANHO][TAMANHO];
		inicializar();
	}

	// Inicializa todos os espaços com areia
	private void inicializar() {

		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				mapaOculto[i][j] = AREIA;
				mapaVisivel[i][j] = AREIA;
				posicaoEscavada[i][j] = false;
			}
		}
	}

	// Coloca uma quantidade de tesouros aleatoriamente no mapa
	public void posicionarTesouros(int quantidade) {

		Random gerador = new Random();
		int tesorosColocados = 0;

		while (tesorosColocados < quantidade) {
			int linha = gerador.nextInt(TAMANHO);
			int coluna = gerador.nextInt(TAMANHO);

			if (mapaOculto[linha][coluna] == AREIA) {
				mapaOculto[linha][coluna] = TESOURO_OCULTO;
				tesorosColocados++;
			}
		}
	}

	// Coloca uma quantidade de armadilhas aleatoriamente no mapa
	public void posicionarArmadilhas(int quantidade) {

		Random gerador = new Random();
		int armadilhasColocadas = 0;

		while (armadilhasColocadas < quantidade) {
			int linha = gerador.nextInt(TAMANHO);
			int coluna = gerador.nextInt(TAMANHO);

			if (mapaOculto[linha][coluna] == AREIA) {
				mapaOculto[linha][coluna] = ARMADILHA_OCULTA;
				armadilhasColocadas++;
			}
		}
	}

	// Verifica se a posição está dentro dos limites do mapa
	public boolean posicaoValida(Posicao pos) {

		return pos.getLinha() >= 0 && pos.getLinha() < TAMANHO && pos.getColuna() >= 0 && pos.getColuna() < TAMANHO;
	}

	// Verifica se a posição já foi escavada anteriormente
	public boolean jaFoiEscavada(Posicao pos) {
		return posicaoEscavada[pos.getLinha()][pos.getColuna()];
	}

	// Executa a escavação na posição e retorna o conteúdo encontrado
	public char escavar(Posicao pos) {

		int linha = pos.getLinha();
		int coluna = pos.getColuna();

		posicaoEscavada[linha][coluna] = true;
		char conteudo = mapaOculto[linha][coluna];

		// Atualiza o mapa visível com base no que foi encontrado
		if (conteudo == TESOURO_OCULTO) {
			mapaVisivel[linha][coluna] = TESOURO_ENCONTRADO;
		} else if (conteudo == ARMADILHA_OCULTA) {
			mapaVisivel[linha][coluna] = ARMADILHA_ENCONTRADA;
		} else {
			mapaVisivel[linha][coluna] = POSICAO_VAZIA;
		}

		return conteudo;
	}

	// Exibe o mapa visível
	public void exibirMapaVisivel() {

		System.out.println("    0 1 2 3 4 5 6 7");
		System.out.println("  +-----------------+");

		for (int i = 0; i < TAMANHO; i++) {
			System.out.print(i + " | ");
			for (int j = 0; j < TAMANHO; j++) {
				System.out.print(mapaVisivel[i][j] + " ");
			}
			System.out.println("|");
		}

		System.out.println("  +-----------------+");
	}

	// Exibe o mapa completo
	public void exibirMapaCompleto() {

		System.out.println("\n--- MAPA COMPLETO DA ILHA ---");
		System.out.println("    0 1 2 3 4 5 6 7");
		System.out.println("  +-----------------+");

		for (int i = 0; i < TAMANHO; i++) {
			System.out.print(i + " | ");
			for (int j = 0; j < TAMANHO; j++) {
				if (posicaoEscavada[i][j]) {
					System.out.print(mapaVisivel[i][j] + " ");
				} else {
					System.out.print(mapaOculto[i][j] + " ");
				}
			}
			System.out.println("|");
		}
		System.out.println("  +-----------------+");
	}
}
