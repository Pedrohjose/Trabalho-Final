package Tesouro;

import java.util.Scanner;

public class CacaAoTesouro {
	
	private int TOTAL_TESOUROS = 8;
	private int TOTAL_ARMADILHAS = 5;
	private int TENTATIVAS_MAXIMAS = 25;

	private Mapa mapa;
	private Jogador jogador;
	private Scanner scanner;
	private boolean jogoAtivo;

	// Construtor - inicializa os objetos necessários para o jogo
	public CacaAoTesouro() {
		this.mapa = new Mapa();
		this.jogador = new Jogador();
		this.scanner = new Scanner(System.in);
		this.jogoAtivo = true;
	}

	// Inicia o jogo
	public void iniciarJogo() {
		exibirMensagemInicial(); 
		inicializarMapa(); 
		executarLoopPrincipal(); 
		finalizarJogo(); 
		scanner.close(); 
	}

	// Mensagem inicial
	private void exibirMensagemInicial() {
		System.out.println("===============================================");
		System.out.println("               CAÇA AO TESOURO                 ");
		System.out.println("===============================================");
		System.out.println("Bem-vindo à Ilha Misteriosa!");
		System.out.println("Encontre os 8 tesouros escondidos!");
		System.out.println("Cuidado com as armadilhas!");
		System.out.println("Você tem 25 tentativas.");
		System.out.println("===============================================");
	}

	// Posiciona os tesouros e armadilhas no mapa
	private void inicializarMapa() {
		mapa.posicionarTesouros(TOTAL_TESOUROS);
		mapa.posicionarArmadilhas(TOTAL_ARMADILHAS);
		System.out.println("\nIlha gerada! Boa sorte na sua aventura!\n");
	}

	// Executa o loop principal do jogo
	private void executarLoopPrincipal() {
		while (jogoAtivo) {
			exibirEstadoJogo();
			Posicao posicao = obterPosicaoJogador();
			if (!validarJogada(posicao)) {
				continue;
			}
			realizarEscavacao(posicao);
			verificarCondicoesFim();
		}
	}

	// Exibe o estado atual do jogo
	private void exibirEstadoJogo() {
		mapa.exibirMapaVisivel();
		System.out.println("\n--- STATUS DO JOGO ---");
		System.out.println("Tesouros encontrados: " + jogador.getTesoursEncontrados() + "/" + TOTAL_TESOUROS);
		System.out.println("Tentativas restantes: " + (TENTATIVAS_MAXIMAS - jogador.getTentativasUsadas()));
		System.out.println("Pontuacao atual: " + jogador.getPontuacao());
		System.out.println();
	}

	// Solicita do jogador uma linha e coluna para escavar
	private Posicao obterPosicaoJogador() {
		System.out.print("Digite a linha (0-7): ");
		int linha = scanner.nextInt();

		System.out.print("Digite a coluna (0-7): ");
		int coluna = scanner.nextInt();

		return new Posicao(linha, coluna);
	}

	// Verifica se a jogada é válida
	private boolean validarJogada(Posicao posicao) {
		if (!mapa.posicaoValida(posicao)) {
			System.out.println("ERRO: Coordenadas invalidas! Digite valores entre 0 e 7.\n");
			return false;
		}

		if (mapa.jaFoiEscavada(posicao)) {
			System.out.println("AVISO: Voce ja escavou esta posicao! Tente outra.\n");
			return false;
		}

		return true;
	}

	// Realiza a escavação e atualiza a pontuação com base no que foi encontrado
	private void realizarEscavacao(Posicao posicao) {
		jogador.usarTentativa();
		System.out.println("\nEscavando posicao (" + posicao.getLinha() + ", " + posicao.getColuna() + ")...\n");

		char resultado = mapa.escavar(posicao);

		if (resultado == 't') {
			jogador.encontrarTesouro();
			System.out.println("*** PARABENS! Voce encontrou um tesouro! ***");
			System.out.println("+10 pontos!");
		} else if (resultado == 'a') {
			jogador.encontrarArmadilha();
			System.out.println("*** CUIDADO! Voce caiu em uma armadilha! ***");
			System.out.println("-5 pontos!");
		} else {
			System.out.println("Apenas areia... Continue procurando!");
		}

		System.out.println();
	}

	// Verifica se o jogo chegou ao fim
	private void verificarCondicoesFim() {
		if (jogador.getTesoursEncontrados() == TOTAL_TESOUROS) {
			jogoAtivo = false;
			System.out.println("===============================================");
			System.out.println("           PARABENS! VOCE VENCEU!             ");
			System.out.println("===============================================");
			System.out.println("Voce encontrou todos os tesouros!");
		} else if (jogador.getTentativasUsadas() >= TENTATIVAS_MAXIMAS) {
			jogoAtivo = false;
			System.out.println("===============================================");
			System.out.println("             GAME OVER!                       ");
			System.out.println("===============================================");
			System.out.println("Voce nao conseguiu encontrar todos os tesouros.");
			System.out.println("Tesouros encontrados: " + jogador.getTesoursEncontrados() + "/" + TOTAL_TESOUROS);
		}
	}

	// Exibe a pontuação final e o mapa completo revelando tudo
	private void finalizarJogo() {
		System.out.println("\nPONTUACAO FINAL: " + jogador.getPontuacao());
		System.out.println("CLASSIFICACAO: " + jogador.getClassificacao());
		mapa.exibirMapaCompleto();
		System.out.println("\nObrigado por jogar!");
	}

	// Método principal que inicia o programa
	public static void main(String[] args) {
		CacaAoTesouro jogo = new CacaAoTesouro();
		jogo.iniciarJogo();
	}
}
