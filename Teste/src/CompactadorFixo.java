import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompactadorFixo {
	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		int numeroThreads = Integer.parseInt(args[0]);
		int count = 0;
		String saida = args[args.length - 1];
		ExecutorService executor = Executors.newFixedThreadPool(numeroThreads);
		ArrayList<String> argumentos = new ArrayList<>();
		for (int i = 1; i < args.length - 1; i++) {
			argumentos.add(args[i]);
		}
		while (!argumentos.isEmpty()) {
			executor.execute(new CompactadorThread(argumentos.remove(0), saida + "/saida" + (count++) + ".zip"));
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("tempo total: " + (System.currentTimeMillis() - inicio));
	}
}
