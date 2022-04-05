/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio.ufjf.dcc025.sudoku;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author brysa
 */

class Posicao {

    static String[][] p = {
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",}, {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",}, {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",}, {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",}, {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",},
        {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",}, {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",}, {"vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio", "vazio",}

    };
    static int[][] jogo = {
        {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1}

    };
}
public class Sudoku {
     public static void desenhaJogo() {

        for (int i = 0; i < Posicao.jogo.length; i++) {

            for (int j = 0; j < Posicao.jogo.length; j++) {
                if (Posicao.jogo[i][j] == -1) {
                    System.out.print("X|");
                } else {
                    System.out.print(Posicao.jogo[i][j] + "|");
                }
            }
            System.out.println("  ");
        }

    }
public static void resetarJogo(){
    
       for (int i = 0; i < Posicao.jogo.length; i++) {

            for (int j = 0; j < Posicao.jogo.length; j++) {
                Posicao.jogo[i][j] = -1;
                Posicao.p[i][j] = "vazio";
            }
        }
}
    public static int jogoCompleto() {

        int cont = 0;
        for (int i = 0; i < Posicao.jogo.length; i++) {

            for (int j = 0; j < Posicao.jogo.length; j++) {
                if (Posicao.jogo[i][j] == -1) {
                    return 0;
                }
            }

        }

        if (!verificarJogo()) {
            System.out.println("Reveja suas jogadas");
            return 0;
        }

        return 1;

    }

    public static void adcPosicao(int l, int c, int define) {

        if (define == 0) {
            Posicao.p[l][c] = "fixo";
        } else {
            Posicao.p[l][c] = "modificavel";
        }
    }

    public static boolean posicaoValida(int l, int c) {

        String a = "vazio";

        if (Posicao.p[l][c].equals(a) || Posicao.p[l][c].equals("modificavel")) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean dica() {
        for (int linha = 0; linha < 9; linha++) {
            for (int col = 0; col < 9; col++) {
                if (Posicao.jogo[linha][col] == -1) {
                    for (int n = 1; n <= 9; n++) {
                        if (posicaoPermitida(linha, col, n)) {
                            System.out.println("Linha: " + (linha + 1));
                            System.out.println("Coluna: " + (col + 1));
                            System.out.println("Valor: " + n);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean verificaLinha(int linha, int n) {
        for (int i = 0; i < 9; i++) {
            if (Posicao.jogo[linha][i] == n) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificaColuna(int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (Posicao.jogo[i][col] == n) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificaBox(int linha, int col, int n) {
        int l = linha - linha % 3;
        int c = col - col % 3;
        for (int i = l; i < l + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (Posicao.jogo[i][j] == n) {
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean posicaoPermitida(int linha, int col, int n) {
        return !(verificaLinha(linha, n) || verificaColuna(col, n) || verificaBox(linha, col, n));
    }

    public static boolean resolveJogo() {
        for (int linha = 0; linha < 9; linha++) {
            for (int col = 0; col < 9; col++) {
                if (Posicao.jogo[linha][col] == -1) {
                    for (int n = 1; n <= 9; n++) {
                        if (posicaoPermitida(linha, col, n)) {
                            Posicao.jogo[linha][col] = n;
                            adcPosicao(linha, col, 0);
                            if (resolveJogo()) {
                                return true;
                            } else {
                                Posicao.jogo[linha][col] = -1;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static String proxDisponivel() {
        String prox = "";
        for (int linha = 0; linha < 9; linha++) {
            for (int col = 0; col < 9; col++) {
                if (Posicao.p[linha][col].equals("fixo")) {
                    prox = Integer.toString(linha).concat(Integer.toString(col));
                    return prox;

                }
            }
        }
        return prox;

    }

    public static void gerarJogoAleatorio() {

        Scanner teclado = new Scanner(System.in);
        Random gerador = new Random();
        int n = 0;
        int total = 0;
        int aleatorio;
        int linha = 0;
        int coluna = 0;
        int cont = 0;

        System.out.println("Quantos numeros aleatorios voce deseja sortear ");
        n = teclado.nextInt();

        System.out.println("Criando jogo... ");
        total = 81 - n;

        aleatorio = gerador.nextInt(10);
        if (aleatorio == 0) {
            aleatorio = 1;
        }

        Posicao.jogo[0][0] = aleatorio;
        adcPosicao(0, 0, 0);

        aleatorio = gerador.nextInt(10);
        if (aleatorio == 0) {
            aleatorio = 1;
        }

        Posicao.jogo[4][4] = aleatorio;
        adcPosicao(4, 4, 0);

        aleatorio = gerador.nextInt(10);
        if (aleatorio == 0) {
            aleatorio = 1;
        }

        Posicao.jogo[8][8] = aleatorio;
        adcPosicao(8,8, 0);

        resolveJogo();
        String prox = "";
        while (total != 0) {
            linha = gerador.nextInt(9);
            coluna = gerador.nextInt(9);
            if (Posicao.jogo[linha][coluna] != -1) {
                Posicao.jogo[linha][coluna] = -1;
                Posicao.p[linha][coluna] = "vazio";

            } else {
                prox = proxDisponivel();

                linha = Integer.parseInt(String.valueOf(prox.charAt(0)));
                coluna = Integer.parseInt(String.valueOf(prox.charAt(1)));
                Posicao.jogo[linha][coluna] = -1;
                Posicao.p[linha][coluna] = "vazio";

            }
            total--;
        }

    }

    public static void definirJogo() {

        String valores = "(0,0,0)";

        String nval = "(-1,-1,-1)";
        System.out.println("Defina os respectivos valores como : ");
        System.out.println("([linha],[coluna],[valor])");
        Scanner teclado = new Scanner(System.in);
        int j = 1;
        int linha;
        int coluna;
        int valor;
        int def = 0;


        while (j > 0) {
            valores = teclado.nextLine();
            String val_separado[] = valores.split("\s");

            for (int i = 0; i < val_separado.length; i++) {
                val_separado[i] = val_separado[i].trim();
                if ((val_separado[i].equals(nval))) {
                    j = -1;
                    break;
                }
                
                 if (val_separado[i].matches("\\(\\d{1}+,\\d{1}+,\\d{1}+\\)")) 

                {
                 linha = (Integer.parseInt(String.valueOf(val_separado[i].charAt(1)))) - 1;
                coluna = (Integer.parseInt(String.valueOf(val_separado[i].charAt(3)))) - 1;
                valor = (Integer.parseInt(String.valueOf(val_separado[i].charAt(5))));
                if (linha >= 0 && linha <= 8 && coluna >= 0 && coluna <= 8 && valor >= 1 && valor <= 9) {
                    if (!posicaoValida(linha, coluna)) {
                        System.out.println("A posicao: " + val_separado[i] + " nao existe ou ja esta ocupada");

                    } else {
                        adcPosicao(linha, coluna, def);
                        Posicao.jogo[linha][coluna] = valor;
                        System.out.println("A posicao: " + val_separado[i] + " foi adicionada");
                    }
                } else {
                    System.out.println("Os dados "  + val_separado[i] + " estao incorretos");
                }
               }else System.out.println("Digite no formato indicado e valores entre 1 e 9");
                

            }
            if (j == 0) {
                break;
            }

        }

    }

    public static void menu() {
        System.out.println("Bem Vindo(a) ao Jogo Sudoku ! ");
        System.out.println("Digite o numero em colchetes para opcao que deseja ");
        System.out.println(" - Gerar um jogo aleatorio [1] ");
        System.out.println(" - Definir proprio jogo [2] ");
        int opcao = 0;
        Scanner teclado = new Scanner(System.in);
        opcao = teclado.nextInt();
        switch (opcao) {
            case 1:
                gerarJogoAleatorio();
                break;
            case 2:
                definirJogo();
                break;
            default:
                System.out.println("Opcao Invalida");

        }

        desenhaJogo();
        menuJogo();

    }

    public static void adicionarJogada() {
        System.out.println("Defina os respectivos valores como : ");
        System.out.println("([linha],[coluna],[valor])");
        String valores = "(0,0,0)";

        int coluna;
        int linha;
        int valor;
        int def = 1;

        Scanner teclado = new Scanner(System.in);
        valores = teclado.nextLine();
        valores = valores.trim();
        if (valores.matches("\\(\\d{1}+,\\d{1}+,\\d{1}+\\)")) {
            linha = (Integer.parseInt(String.valueOf(valores.charAt(1)))) - 1;
            coluna = (Integer.parseInt(String.valueOf(valores.charAt(3)))) - 1;
            valor = (Integer.parseInt(String.valueOf(valores.charAt(5))));

            if (linha >= 0 && linha <= 8 && coluna >= 0 && coluna <= 8 && valor >= 1 && valor <= 9) {
                if (!posicaoValida(linha, coluna)) {
                    System.out.println("A posicao: " + valores + " ja esta ocupada");
                } else {
                    adcPosicao(linha, coluna, def);
                    Posicao.jogo[linha][coluna] = valor;
                }

            } else {
                System.out.println("Linha ou coluna inexistente, digite valores entre 1 e 9 e no formato indicado");
            }
        } else {
            System.out.println("Linha ou coluna inexistente, digite valores entre 1 e 9 e no formato indicado");
        }

    }

    public static void removerJogada() {
        System.out.println("Para remover um jogada digite: ");
        System.out.println("([linha],[coluna])");

        String valores = "(0,0)";
        String m = "modificavel";

        int coluna;
        int linha;

        Scanner teclado = new Scanner(System.in);
        valores = teclado.nextLine();
        valores = valores.trim();

        if (valores.matches("\\(\\d{1}+,\\d{1}+\\)")) 
        {
             linha = (Integer.parseInt(String.valueOf(valores.charAt(1)))) - 1;
             coluna = (Integer.parseInt(String.valueOf(valores.charAt(3)))) - 1;
              if (linha >= 0 && linha <= 8 && coluna >= 0 && coluna <= 8) {
            if (Posicao.p[linha][coluna].equals(m)) {
                System.out.println("Jogada removida com sucesso");
                Posicao.p[linha][coluna] = "vazio";
                Posicao.jogo[linha][coluna] = -1;
            } else {
                System.out.println("Linhas e colunas selecionadas previamentes nao podem ser removidas");
            }

        } else {
            System.out.println("Linha ou coluna selecionada nao existe");
        }
        }
        else{
            System.out.println("Digite no formato solicitado");
        }

    }

    public static boolean valorIgual(int[] valCol) {

        for (int i = 0; i < valCol.length; i++) {

            for (int j = 0; j < valCol.length; j++) {

                if (valCol[i] == valCol[j] && valCol[i] != -1 && valCol[i] != 0 && valCol[j] != 0 && j != i) {

                    return true;
                }

            }

        }

        return false;

    }

    public static int[] iniciarVet(int[] vet) {
        for (int i = 0; i < vet.length; i++) {

            vet[i] = 0;
        }
        return vet;
    }

    public static boolean verificarQuadranteJogo(int linha, int coluna, String def) {
        int cont = 0;
        int[] n = new int[9];
        for (int p = 0; p < 3; p++) {
            for (int k = 0; k < 3; k++) {
                if (Posicao.jogo[linha + p][coluna + k] >= 1) {
                    n[cont] = Posicao.jogo[linha + p][coluna + k];

                    cont++;
                }
                String aux = Integer.toString(linha + p).concat(Integer.toString(coluna + k));
                if (aux.equals(def)) {
                    if (valorIgual(n)) {
                        return false;
                    }

                }

            }
        }
        return true;
    }

    public static boolean verificarJogo() {

        int cont = 0;
        int[] n = new int[9];
        for (int linha = 0; linha < 9; linha++) {
            for (int col = 0; col < 9; col++) {

                n[cont] = Posicao.jogo[linha][col];

                cont++;

                if (col == 8) {
                    if (valorIgual(n)) {

                        return false;
                    }

                    cont = 0;
                }

            }
        }
        cont = 0;
        for (int coluna = 0; coluna < 9; coluna++) {
            for (int lin = 0; lin < 9; lin++) {

                n[cont] = Posicao.jogo[lin][coluna];

                cont++;

                if (lin == 8) {
                    if (valorIgual(n)) {

                        return false;
                    }

                    cont = 0;
                }

            }
        }

        if (!verificarQuadranteJogo(0, 0, "22")) {

            return false;
        }
        if (!verificarQuadranteJogo(3, 0, "52")) {

            return false;
        }
        if (!verificarQuadranteJogo(6, 0, "82")) {

            return false;
        }

        if (!verificarQuadranteJogo(0, 3, "25")) {

            return false;
        }
        if (!verificarQuadranteJogo(3, 3, "55")) {

            return false;
        }
        if (!verificarQuadranteJogo(6, 3, "85")) {

            return false;
        }

        if (!verificarQuadranteJogo(0, 6, "28")) {

            return false;
        }
        if (!verificarQuadranteJogo(3, 6, "58")) {

            return false;
        }
        if (!verificarQuadranteJogo(6, 6, "88")) {

            return false;
        }

        return true;
    }

    public static void menuJogo() {
        System.out.println(" Vamos jogar! ");
        String opcao = "e";
        System.out.println(" - Adicionar jogada [a]");
        System.out.println(" - Remover jogada [b]");
        System.out.println(" - Verificar jogo [c]");
        System.out.println(" - Sair [d]");
        System.out.println(" - Dica [e]");
        Scanner teclado = new Scanner(System.in);
        opcao = teclado.nextLine();
        int a = 1;
        int aux;
        int resp;
        while (a > 0) {
            switch (opcao) {
                case "a":
                    adicionarJogada();
                    break;
                case "b":
                    removerJogada();
                    break;
                case "c":
                    if (!verificarJogo()) {
                        System.out.println("Reveja suas jogadas");
                    } else {
                        System.out.println("Jogo valido continue");
                    }
                    break;
                case "d":
                    System.exit(0);
                    break;
                case "e":
                    if (!dica()) {
                        System.out.println("Nao ha dicas disponiveis");
                    }
                    break;
                default:
                    System.out.println("Opcao Invalida");
            }
            aux = jogoCompleto();
            if (aux == 1) {
                break;
            }
            System.out.println(" - Adicionar jogada [a]");
            System.out.println(" - Remover jogada [b]");
            System.out.println(" - Verificar jogo [c]");
            System.out.println(" - Sair [d]");
            System.out.println(" - Dica [e]");
            desenhaJogo();
            opcao = teclado.nextLine();
        }
        desenhaJogo();
        System.out.println("Parabens voce brilhou, jogo completo !");
        System.out.println("Deseja jogar novamente? ");
        System.out.println("[1] - Sim , [2] Nao");
        resp = teclado.nextInt();
        if (resp == 1) {
            resetarJogo();
            menu();
        }
        if (resp == 2) {
            System.exit(1);
        }

    }

    public static void main(String[] args) {

        menu();
    }

    
}





