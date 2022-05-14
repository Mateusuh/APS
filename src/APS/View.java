package APS;

public interface View {

    Opcao menu();

    void listarAluno(Cadastro cadastro);
    void listarCurso(Cadastro cadastro);

    void listarAlunoFromCurso(Cadastro cadastro, Curso curso);
    void listarCursoFromAluno(Cadastro cadastro, Aluno aluno);

    Curso addCurso();
    Aluno addAluno();

    Curso getCursoFromLista(Cadastro cadastro);
    Aluno getAlunoFromLista(Cadastro cadastro);

}
