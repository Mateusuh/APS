package APS;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Cadastro {

    Map<Aluno, Set<Curso>> alunos = new TreeMap<>();
    Map<Curso, Set<Aluno>> cursos = new TreeMap<>();

    Map<String, Aluno> alunoById = new TreeMap<>();

    public Aluno getAlunoFromId(String id) {
        return alunoById.get(id);
    }

    public Set<Aluno> getAlunos() {
        return alunos.keySet();
    }
    public void addAluno(Aluno aluno) {
        this.alunos.put(aluno, new TreeSet<>());
        this.alunoById.put(aluno.getId(), aluno);
    }

    public Set<Curso> getCursos() {
        return cursos.keySet();
    }
    public void addCurso(Curso curso) {
        this.cursos.put(curso, new TreeSet<>());
    }

    public Set<Curso> getCursoFromAluno(String id) {
        return alunos.get(new Aluno(id, ""));
    }
    public Set<Aluno> getAlunoFromCurso(Curso curso) {
        return cursos.get(curso);
    }

    public void addRelacaoAlunoCurso(Aluno aluno, Curso curso) {
        this.cursos.get(curso).add(aluno);
        this.alunos.get(aluno).add(curso);
    }

}
