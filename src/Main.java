import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DBaluno alunoRepository = new AlunoDB();
        ControleEstudantil alunoService = new Notas(alunoRepository);

        Scanner sc = new Scanner(System.in);

        System.out.println(alunoService.notaAluno("Gandalf"));
        System.out.println(alunoService.notaAluno("Legolas"));
        System.out.println();
        System.out.println("-------------------------");

        List<String> alunosComNotaMaiorQue = alunoService.maiorNota(60);
        System.out.println("Alunos com nota maior que 6o: " + alunosComNotaMaiorQue);

        System.out.println();
        System.out.println("-------------------------");

        System.out.print("Digite a nota mínima para remover alunos: ");
        double notaMinima = sc.nextDouble();

        List<Aluno> alunosRemovidos = alunoService.removerAluno(notaMinima);
        String alunosRemovidosFormatados = alunosRemovidos.stream()
                .map(Aluno::toString)
                .collect(Collectors.joining(" | "));
        System.out.println("Alunos removidos: " + alunosRemovidosFormatados);

        System.out.println();
        System.out.println("-------------------------");

        Map<String, Aluno> alunosOrdenados = alunoService.ordenar();
        System.out.println("Alunos ordenados por nota (decrescente): ");
        int[] rank = {1};
        alunosOrdenados.values().stream()
                .forEach(aluno -> System.out.println(rank[0]++ + "º - " + aluno));

        System.out.println();
        System.out.println("-------------------------");

        Map<String, List<Aluno>> alunosPorFaixaDeNota = alunoService.rank();
        System.out.println("Alunos agrupados por faixa de nota: ");
        alunosPorFaixaDeNota.forEach((faixa, lista) -> {
            String alunosFormatados = lista.stream()
                    .map(Aluno::toString)
                    .collect(Collectors.joining(" | "));
            System.out.println("Faixa " + faixa + " = " + alunosFormatados);
        });

        sc.close();
    }
}
