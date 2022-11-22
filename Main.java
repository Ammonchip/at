public class Main{
    public static void main(String[] args) {
        Turma turma = new Turma();
        turma.addHorario(1);
        turma.addHorario(2);
        turma.addHorario(3);
        
        System.out.println(turma.getDescricao());
    }
}
