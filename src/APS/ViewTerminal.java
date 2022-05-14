package APS;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ViewTerminal implements View {

    @Override
    public Opcao menu() {
        try {

            System.out.println("Entre com uma das opcoes:");
            for(Opcao o: Opcao.values()) {
                System.out.println("" + o.codigo + " - " + o.descricao);
            }

            Scanner in = new Scanner(System.in);
            try {
                String entrada = in.nextLine();
                int opcaoCodigo = Integer.parseInt(entrada);

                if(!Controlador.opcoesByCodigos.keySet().contains(opcaoCodigo)) {
                    throw new InputMismatchException("Está opção não existe.");
                }
                return Controlador.opcoesByCodigos.get(opcaoCodigo);

            }catch(NumberFormatException e) {
                throw new InputMismatchException("Está opção não é um número.");
            }
        }catch(InputMismatchException e) {
            System.out.println(e.getMessage());
            menu();
        }
        return null;

    }

    @Override
    public void listarAluno(Cadastro cadastro) {
        System.out.println("Todos os Alunos cadastrados:");
        for(Aluno aluno : cadastro.getAlunos()) {
            System.out.println(aluno);
        }
    }

    @Override
    public void listarCurso(Cadastro cadastro) {
        System.out.println("Todos os Cursos cadastrados:");
        for(Curso curso : cadastro.getCursos()) {
            System.out.println(curso);
        }
    }

    @Override
    public void listarAlunoFromCurso(Cadastro cadastro, Curso curso) {
        System.out.println("Todos os Alunos do Curso " + curso + ":");
        for(Aluno aluno : cadastro.getAlunoFromCurso(curso)) {
            System.out.println(aluno);
        }
    }

    @Override
    public void listarCursoFromAluno(Cadastro cadastro, Aluno aluno) {
        System.out.println("Todos os Cursos do Aluno " + aluno + ":");
        for(Curso curso : cadastro.getCursoFromAluno(aluno.getId())) {
            System.out.println(curso);
        }
    }

    @Override
    public Curso addCurso() {
        return getNewCurso();
    }

    @Override
    public Aluno addAluno() {
        return getNewAluno();
    }

    @Override
    public Curso getCursoFromLista(Cadastro cadastro) {
        System.out.println("Escolha um Curso");
        Curso Curso = escolherCurso(cadastro);
        if(!cadastro.getCursos().contains(Curso)) {
            System.out.println("Nao existe um Curso com essas informacoes entradas");
            return null;
        }

        return Curso;
    }

    @Override
    public Aluno getAlunoFromLista(Cadastro cadastro) {
        System.out.println("Entre com o id do aluno");
        listarAluno(cadastro);
        System.out.println("Entre com o id do aluno");
        String id = getString();

        Aluno aluno = cadastro.getAlunoFromId(id);
        if(aluno==null) {
            System.out.println("Nao existe o id deste aluno");
        }

        return aluno;
    }

    public Aluno getNewAluno() {
        System.out.println("Entre com os dados do aluno");
        String id = getIdAluno();
        String nome = getNomeAluno();
        return new Aluno(id, nome);
    }

    public String getIdAluno() {
        System.out.println("Entre com o id do aluno");
        return getString();
    }

    public String getNomeAluno() {
        System.out.println("Entre com o nome do aluno");
        return getString();
    }

    public Curso getNewCurso() {
        System.out.println("Entre com os dados do curso");
        String nome = getNomeCurso();
        Curso.Nivel nivel = getNivelCurso();
        int ano = getAnoCurso();

        return new Curso(nome, nivel, ano);
    }

    public int getAnoCurso() {
        while(true) {
            System.out.println("Entre com o ano do curso");
            try {
                int ano = Integer.parseInt(getString());
                if (ano > 2022 || ano < 1900){throw new InputMismatchException("");}
                return ano;
            } catch (NumberFormatException e) {
                System.out.println("Utilize somente números.");
            } catch (InputMismatchException e){
                System.out.println("Somente de 1900 à 2022!");
            }
        }
    }

    public String getNomeCurso() {
        System.out.println("Entre com o nome do curso");
        return getString();
    }

    public Curso.Nivel getNivelCurso() {
        while(true) {
            System.out.println("Entre com o nivel do curso - GRADUACAO / POS_GRADUACAO");
            String nivel = getString();
            if (nivel.equalsIgnoreCase("GRADUACAO")) {
                return Curso.Nivel.GRADUACAO;
            }else if (nivel.equalsIgnoreCase("POS_GRADUACAO")) {
                return Curso.Nivel.POS_GRADUACAO;
            }else{
                System.out.println("Somente GRADUACAO ou POS_GRADUACAO");
            }
        }
    }

    private Curso escolherCurso(Cadastro cadastro) {
        String nome = escolherNomesCursos(cadastro);
        Curso.Nivel nivel = escolherNivelCursos(cadastro);
        int ano = escolherAnoCursos(cadastro);
        return new Curso(nome, nivel, ano);
    }

    private String escolherNomesCursos(Cadastro cadastro) {
        Set<String> nomes = new TreeSet<>();
        for(Curso curso : cadastro.getCursos()) {
            nomes.add(curso.getNome());
        }
        System.out.println("Escolha um dos nomes");
        for(String nome: nomes) {
            System.out.println(nome);
        }
        System.out.println("Entre com o nome escolhido:");
        return getString();
    }

    private Curso.Nivel escolherNivelCursos(Cadastro cadastro) {
        Set<Curso.Nivel> niveis = new TreeSet<>();
        for(Curso curso : cadastro.getCursos()) {
            niveis.add(curso.getNivel());
        }
        System.out.println("Escolha um dos niveis");
        for(Curso.Nivel nivel : niveis) {
            System.out.println(nivel);
        }
        Curso.Nivel nivel;
        while(true) {
            System.out.println("Entre com o nivel escolhido:");
            String nivelPalavra = getString();
            if (nivelPalavra.equalsIgnoreCase("GRADUACAO")) {
                nivel = Curso.Nivel.GRADUACAO;
                return nivel;
            } else if (nivelPalavra.equalsIgnoreCase("POS_GRADUACAO")) {
                nivel = Curso.Nivel.POS_GRADUACAO;
                return nivel;
            }else{
                System.out.println("Somente GRADUACAO ou POS_GRADUACAO");
            }
        }
    }

    private int escolherAnoCursos(Cadastro cadastro) {
        Set<Integer> anos = new TreeSet<>();
        for(Curso curso : cadastro.getCursos()) {
            anos.add(curso.getAno());
        }
        System.out.println("Escolha um dos anos");
        for(int ano : anos) {
            System.out.println(ano);
        }
        System.out.println("Entre com o ano escolhido:");
        while(true) {
            try {
                return Integer.parseInt(getString());
            } catch (Exception e) {
                System.out.println("Apenas números!");
            }
        }
    }

    public String getString() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        return str.trim();
    }
}