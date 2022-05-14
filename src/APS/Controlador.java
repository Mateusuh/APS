package APS;

import APS.CSVs;
import APS.View;

import java.util.Map;
import java.util.TreeMap;

public class Controlador {

    private final View view;
    private final CSVs model;
    private Cadastro cadastro;

    public static Map<Integer, Opcao> opcoesByCodigos = new TreeMap<>();{
        for(Opcao o: Opcao.values()) {
            opcoesByCodigos.put(o.codigo, o);
        }
    }

    public Controlador(View view, CSVs model) {
        this.view = view;
        this.model = model;
    }

    public void init() {
        cadastro = model.getCadastro();

        Opcao codigo = null;
        while(codigo != Opcao.SAIR) {
            codigo = view.menu();
            switch (codigo) {
                case LISTA_ALUNO: {listarAlunos(); break;}
                case LISTA_CURSO: {listarCursos(); break;}
                case ADICIONA_ALUNO: {adicionarAluno(); break;}
                case ADICIONA_CURSO: {adicionarCurso(); break;}
                case LISTA_ALUNO_FROM_CURSO: {listaAlunoFromCurso(); break;}
                case LISTA_CURSO_FROM_ALUNO: {listaCursoFromAluno(); break;}
                case ADICIONA_RELACAO: {addRelacao(); break;}
                case SAIR: {terminar(); break;}
                default:
                    throw new IllegalArgumentException("Unexpected value: " + codigo);
            }
        }
    }

    private void listarAlunos() {
        view.listarAluno(cadastro);
    }

    private void listarCursos() {
        view.listarCurso(cadastro);
    }

    private void adicionarAluno() {
        Aluno aluno = view.addAluno();
        this.cadastro.addAluno(aluno);
    }

    private void adicionarCurso() {
        Curso curso = view.addCurso();
        this.cadastro.addCurso(curso);
    }

    private void listaAlunoFromCurso() {
        Curso curso = view.getCursoFromLista(cadastro);
        if(curso==null) return;
        view.listarAlunoFromCurso(cadastro, curso);
    }


    private void listaCursoFromAluno() {
        Aluno aluno = view.getAlunoFromLista(cadastro);
        if(aluno==null) return;
        view.listarCursoFromAluno(cadastro, aluno);
    }

    private void addRelacao() {
        Aluno aluno = view.getAlunoFromLista(cadastro);
        if(aluno==null) return;
        Curso curso = view.getCursoFromLista(cadastro);
        if(curso==null) return;
        cadastro.addRelacaoAlunoCurso(aluno, curso);

    }

    private void terminar() {
        model.saveCadastro(cadastro);
    }

}
