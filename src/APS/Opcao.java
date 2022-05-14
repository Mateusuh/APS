package APS;

public enum Opcao {

    LISTA_ALUNO(1, "Listar todos os alunos cadastrados"),
    LISTA_CURSO(2, "Listar todos os cursos cadastrados"),
    LISTA_ALUNO_FROM_CURSO(3, "Listar todos os alunos de um curso escolhido"),
    LISTA_CURSO_FROM_ALUNO(4, "Listar todos os cursos que um aluno está participando"),
    ADICIONA_ALUNO(5, "Cadastrar um aluno"),
    ADICIONA_CURSO(6, "Cadastrar um curso"),
    ADICIONA_RELACAO(7, "Adicionar uma relacao entre um aluno e um curso"),
    SAIR(0, "Sair do programa e salvar os dados");

    public String descricao;
    public int codigo;

    private Opcao(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
