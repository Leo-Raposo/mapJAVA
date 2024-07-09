import java.util.List;
import java.util.Map;

public interface ControleEstudantil {
    String notaAluno(String nome);
    List<String> maiorNota(double nota);
    List<Aluno> removerAluno(double nota);
    Map<String, Aluno> ordenar();
    Map<String, List<Aluno>> rank();
}
