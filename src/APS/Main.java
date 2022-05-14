package APS;

class Main {

    public static void main(String[] args) {
        View view = new ViewTerminal();
        String alunoPath = "C:/Users/Mateus/Desktop/APS/aluno.csv";
        String cursoPath = "C:/Users/Mateus/Desktop/APS/curso.csv";
        String relacaoPath = "C:/Users/Mateus/Desktop/APS/aluno_curso.csv";
        CSVs model = new CSVs(alunoPath, cursoPath, relacaoPath);

        new Controlador(view, model).init();
    }

}