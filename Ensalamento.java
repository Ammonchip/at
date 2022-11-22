import java.util.ArrayList;

public class Ensalamento {
    
    ArrayList<Sala> salas = new ArrayList<Sala>();
    ArrayList<Turma> turmas = new ArrayList<Turma>();
    ArrayList<TurmaEmSala> ensalamento = new ArrayList<TurmaEmSala>();
    
    Ensalamento(){};

    void addSala(Sala sala){
        this.salas.add(sala);
    }

    void addTurma(Turma turma){
        this.turmas.add(turma);
    }

    Sala getSala(Turma turma) {
        for (TurmaEmSala i : this.ensalamento) {
            if (i.turma == turma) {
                return i.sala;
            }
        }
        return null;
    }

    boolean salaDisponivel(Sala sala, int horario) {
        
        for (TurmaEmSala i : this.ensalamento) {
            if (i.sala == sala) {
                if (i.turma.horarios.contains(horario)) return false;
            }
        }

        return true;
    }

    boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios) {
        for (TurmaEmSala i : this.ensalamento) {
            if (i.sala == sala) {
                for (Integer j : horarios) {
                    if (i.turma.horarios.contains(j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    boolean alocar(Turma turma, Sala sala) {
        if (!(turma.acessivel && sala.acessivel)) return false;
        if (!(turma.numAlunos <= sala.capacidade)) return false;
        if (!salaDisponivel(sala, turma.horarios)) return false;

        this.ensalamento.add(new TurmaEmSala(turma, sala));
        
        return true;
    }

    void alocarTodas() {
        for (Turma turma : this.turmas) {
            boolean foo = false;
            for (int i = 0; !foo || i < this.salas.size(); i++) {
                foo = alocar(turma, this.salas.get(i));
            }
            if(!foo) this.ensalamento.add(new TurmaEmSala(turma, null));
        }
    }

    int getTotalTurmasAlocadas() {
        int foo = 0;
        for (TurmaEmSala boo : this.ensalamento) {
            foo = boo.sala != null ? foo + 1 : foo;
        }
        return foo;
    }

    int getTotalEspacoLivre() {
        int total = 0;
        for (TurmaEmSala bar : this.ensalamento) {
            total += bar.sala.capacidade - bar.turma.numAlunos;
        }
        return total;
    }

    String relatorioResumoEnsalamento() {
        return String.format("Total de Salas: %d\nTotal de Turmas: %d\nTurmas Alocadas: %d\nEspaços Livres: %d\n", 
        this.salas.size(),
        this.turmas.size(),
        getTotalTurmasAlocadas(),
        getTotalEspacoLivre());
    }

    String relatorioTurmasPorSala() {

        ArrayList<String> ans = new ArrayList<String>();
        ans.add(relatorioResumoEnsalamento() + "\n");

        for (Sala sala : this.salas) {
            ans.add("--- " + sala.getDescricao() + "---\n");

            for (TurmaEmSala foo : this.ensalamento) {
                if (sala == foo.sala) {
                    ans.add(foo.turma.getDescricao() + "\n");
                }
            }
        }

        return String.join("", ans);
    }
    
    String relatorioSalasPorTurma() {
        ArrayList<String> ans = new ArrayList<String>();
        ans.add(relatorioResumoEnsalamento() + "\n");
        for (Turma turma : this.turmas) {
            boolean boo = false;
            ans.add(turma.getDescricao() + "\n");

            for (TurmaEmSala foo : this.ensalamento) {
                if (turma == foo.turma) {
                    if (foo.sala != null) {
                        ans.add(foo.turma.getDescricao() + "\n");
                        boo = true;
                    }
                }
            }
            if (!boo) ans.add("Sala: SEM SALA" + "\n");
        }
        return String.join("", ans);
    }

}
