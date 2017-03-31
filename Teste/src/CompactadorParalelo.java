import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompactadorParalelo {
	public static void main(String[] args) {
		for(String s:args)
			System.out.println(s);
		long inicio = System.currentTimeMillis();
		String opcao = args[0];
		String saida = args[args.length-1];
		if(opcao.equals("serial")){
		for(int i=1;i<args.length-1;i++){
			try {
				Compactador.compactarParaZip(args[i], saida +"/saida"+ i+".zip");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("tempo total: "+(System.currentTimeMillis()-inicio));
		}
		else if(args[0].equals("paralelo")){
		ExecutorService executor = Executors.newFixedThreadPool(args.length-2);
		for(int i=1;i<args.length-1;i++){
			System.out.println(args.length);
			CompactadorThread ct = new CompactadorThread();
			ct.setArqEntrada(args[i]);
			ct.setArqSaida(saida +"/saida"+ i+".zip");
			executor.execute(ct);
			
		}
		executor.shutdown();
		while(!executor.isTerminated()){}
		System.out.println("tempo total: "+(System.currentTimeMillis()-inicio));
		}
		else{
			System.out.println("Opção inválida");
			System.out.println("Sintaxe:");
			System.out.println("compactador.jar [opção] [caminho]");
			System.out.println("[opção] -> serial ou paralelo");
		}
		
	}

}
