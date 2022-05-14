package APS;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class CSVs {

    private String alunoPath;
    private String cursoPath;
    private String relacaoPath;

    private Cadastro cadastroInput;

    public CSVs(String aAlunoPath, String aCursoPath, String aRelacaoPath) {
        this.alunoPath = aAlunoPath;
        this.cursoPath = aCursoPath;
        this.relacaoPath = aRelacaoPath;
    }

    public Cadastro getCadastro(){

        this.cadastroInput = new Cadastro();

        loadAluno();
        loadCurso();

        return loadRelacoes();
    }

    private Cadastro loadRelacoes(){

        try (InputStream is = new FileInputStream(this.relacaoPath);
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr);
        ){
            String linha;
            int i=0;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String idAluno = palavras[0];
                String nomeCurso = palavras[1];
                String nivelPalavra = palavras[2];
                int ano = Integer.parseInt(palavras[3]);

                Curso.Nivel nivel = null;
                if (nivelPalavra.equals("GRADUACAO")){
                    nivel = Curso.Nivel.GRADUACAO;
                }else if(nivelPalavra.equals("POS_GRADUACAO")){
                    nivel = Curso.Nivel.POS_GRADUACAO;
                }

                Curso curso = new Curso(nomeCurso, nivel, ano);
                Aluno aluno = this.cadastroInput.getAlunoFromId(idAluno);

                cadastroInput.addRelacaoAlunoCurso(aluno, curso);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return this.cadastroInput;

    }

    private void loadAluno(){

        try (   InputStream is = new FileInputStream(this.alunoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ){
            String linha;
            int i=0;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String id = palavras[0];
                String nome = palavras[1];

                Aluno aluno = new Aluno(id, nome);
                this.cadastroInput.addAluno(aluno);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void loadCurso(){

        try (   InputStream is = new FileInputStream(this.cursoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ){
            String linha;
            int i=0;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String nome = palavras[0];
                String nivelPalavra = palavras[1];
                int ano = Integer.parseInt(palavras[2]);

                Curso.Nivel nivel = null;
                if (nivelPalavra.equalsIgnoreCase("GRADUACAO")){
                    nivel = Curso.Nivel.GRADUACAO;
                }else if(nivelPalavra.equalsIgnoreCase("POS_GRADUACAO")){
                    nivel = Curso.Nivel.POS_GRADUACAO;
                }

                Curso curso = new Curso(nome, nivel, ano);
                this.cadastroInput.addCurso(curso);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void saveCadastro(Cadastro cadastroOutput){

        saveAluno(cadastroOutput.getAlunos());
        saveCurso(cadastroOutput.getCursos());
        saveRelacoes(cadastroOutput);

    }

    private void saveRelacoes(Cadastro cadastroOutput){

        try(    OutputStream os = new FileOutputStream(this.relacaoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
        ){
            for(Aluno aluno : cadastroOutput.getAlunos()){
                for(Curso curso: cadastroOutput.getCursoFromAluno(aluno.getId())){
                    pw.println(aluno.getId()+","+curso.getNome()+","+curso.getNivel()+","+curso.getAno());
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void saveAluno(Set<Aluno> alunoOutput){

        try(    OutputStream os = new FileOutputStream(this.alunoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
        ){
            for(Aluno aluno : alunoOutput){
                pw.println(aluno.getId()+","+aluno.getNome());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }


    private void saveCurso(Set<Curso> cursoOutput){

        try(    OutputStream os = new FileOutputStream(this.cursoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
        ){
            for(Curso curso : cursoOutput){
                pw.println(curso.getNome()+","+curso.getNivel()+","+curso.getAno());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
