import java.util.ArrayList;
import java.util.Hashtable;

public class Turma {
    int numAlunos;
    String professor;
    String nome;
    boolean acessivel;
    ArrayList<Integer> horarios = new ArrayList<Integer>();
    Turma(){};
    Turma(String nome, String professor, int numAlunos, boolean acessivel) 
    {
        this.professor = professor;
        this.nome = nome;
        this.numAlunos = numAlunos;
        this.acessivel = acessivel;
    }

    public void addHorario(int horario){
        this.horarios.add(horario);
    }
    public String getHorariosString(){
        String[] hs = {"8hs", "10hs", "12hs", "14hs", "16hs", "18hs", "20hs"};
        String[] dia = {"segunda", "terça", "quarta", "quinta", "sexta"}; 

        Hashtable dictHora = new Hashtable<Integer, String>();
        Hashtable dictSem = new Hashtable<Integer, String>();
        
        for(int i = 0, j = 0, k = 0; i < 35; i++, j++) {
            k = j == 7 ? k + 1 : k;
            j = j == 7 ? 0 : j;

            dictHora.put(i + 1, hs[j]);
            dictSem.put(i + 1, dia[k]);
        }
        
        ArrayList<String> ans = new ArrayList<String>();

        for(int i = 0; i < horarios.size(); i++) {
            ans.add(dictSem.get(horarios.get(i))+" "+ dictHora.get(horarios.get(i)));
        }

        return String.join(", ", ans);
    }
    public String getDescricao(){
        return "Turma: "+this.nome+"\n"+"Professor: "+this.professor+"\n"+"Número de alunos: "+this.numAlunos+"\n"+"Horário: "+getHorariosString()+"\n"+"Acessível: "+(acessivel ? "sim" : "não");
    }
}
