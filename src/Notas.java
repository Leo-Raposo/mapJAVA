import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Notas implements ControleEstudantil {

    private final DBaluno alunoRepository;

    public Notas(DBaluno alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public String notaAluno(String nome) {
        Map<String, Aluno> alunos = alunoRepository.getAlunos();
        Optional<Aluno> aluno = alunos.values().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .findFirst();
        return aluno.map(a -> "Nota do aluno " + nome + ": " + a.getNota())
                .orElse("Aluno " + nome + " não encontrado.");
    }

    @Override
    public List<String> maiorNota(double nota) {
        Map<String, Aluno> alunos = alunoRepository.getAlunos();
        return alunos.values().stream()
                .filter(aluno -> aluno.getNota() > nota)
                .map(Aluno::getNome)
                .collect(Collectors.toList());
    }

    @Override
    public List<Aluno> removerAluno(double nota) {
        Map<String, Aluno> alunos = alunoRepository.getAlunos();
        List<Aluno> alunosRemovidos = alunos.values().stream()
                .filter(aluno -> aluno.getNota() < nota)
                .collect(Collectors.toList());

        alunosRemovidos.forEach(aluno -> alunos.values().removeIf(a -> a.equals(aluno)));
        return alunosRemovidos;
    }

    @Override
    public Map<String, Aluno> ordenar() {
        Map<String, Aluno> alunos = alunoRepository.getAlunos();
        return alunos.entrySet().stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue().getNota(), entry1.getValue().getNota()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<String, List<Aluno>> rank() {
        Map<String, Aluno> alunos = alunoRepository.getAlunos();
        return alunos.values().stream()
                .collect(Collectors.groupingBy(aluno -> {
                    double nota = aluno.getNota();
                    if (nota >= 90) {
                        return "A";
                    } else if (nota >= 80) {
                        return "B";
                    } else if (nota >= 70) {
                        return "C";
                    } else if (nota >= 60) {
                        return "D";
                    } else {
                        return "F";
                    }
                }));
    }
}
