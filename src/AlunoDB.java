import java.util.HashMap;
import java.util.Map;

public class AlunoDB implements DBaluno {

    @Override
    public Map<String, Aluno> getAlunos() {
        Map<String, Aluno> alunos = new HashMap<>();
        alunos.put("Aluno1", new Aluno("Aragorn", 85));
        alunos.put("Aluno2", new Aluno("Gandalf", 100));
        alunos.put("Aluno3", new Aluno("Smeagle", 55));
        alunos.put("Aluno4", new Aluno("Arwen", 90));
        alunos.put("Aluno5", new Aluno("Frodo", 75));
        return alunos;
    }
}
