package APS;

import java.util.Objects;

public class Curso implements Comparable<Curso>{

    public enum Nivel{
        GRADUACAO, POS_GRADUACAO, NONE;
    }

    private String nome;
    private Nivel nivel;
    private int ano;

    public Curso(String nome, Nivel nivel, int ano) {
        super();
        this.nome = nome;
        this.nivel = nivel;
        this.ano = ano;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Nivel getNivel() {
        return this.nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        return Objects.equals(this.nome, other.nome)
                && Objects.equals(this.nivel, other.nivel)
                && Objects.equals(this.ano, other.ano);
    }

    @Override
    public int compareTo(Curso o) {
        if(!this.nome.equals(o.nome)) {
            return this.nome.compareTo(o.nome);
        }
        if(!this.nivel.equals(o.nivel)) {
            return this.nivel.compareTo(o.nivel);
        }
        return Integer.compare(this.ano, o.ano);
    }


    @Override
    public String toString() {
        return "Curso: [nome=" + this.nome + ", nivel=" + this.nivel + ", ano=" + this.ano + "]";
    }

}
