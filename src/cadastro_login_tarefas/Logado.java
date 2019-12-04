
package cadastro_login_tarefas;

import java.util.ArrayList;
import java.util.Scanner;


public class Logado {
    
    ArrayList<Usuario> userArray  = new ArrayList();
    static Scanner sc = new Scanner(System.in);
    
   public static void menuLogin(){
       
        while(Principal.usuarioLogado != null){ //usuário logado não nulo
        
	System.out.println("\n==========================================================");
	System.out.println("===========      HOME PAGE, VOCÊ ESTÁ LOGADO!     ========");
	System.out.println("==========================================================");
	
	      System.out.println("[1] Lista de tarefas");
	      System.out.println("[2] Lista de tarefas finalizadas");
	      System.out.println("[3] Lista de tarefas não finalizadas");
	      System.out.println("[4] Adicionar nova tarefa");
	      System.out.println("[5] Finalizar tarefa");
	      System.out.println("[6] Remover tarefa");
	      System.out.println("[7] Logout");
	      System.out.print("\tDigite a opção desejada: ");
	      String opcao = sc.nextLine();
	 
	      switch(opcao){
		
		case"1":{
		
	System.out.println("\n=============================================");
	System.out.println("==========      LISTA DE TAREFAS     ========");
	System.out.println("=============================================");    
		    
	        ArrayList<Tarefas> tarefas = ConexaoDAO.buscarTarefasDoUsuario(Principal.usuarioLogado.getId());
		Principal.usuarioLogado.setTarefas(tarefas);
		int nTarefas = Principal.usuarioLogado.getTarefas().size();
		
		if(nTarefas == 0){
		    System.out.println("Lista de tarefas vazia. Redirecionando para HOME PAGE...");
		    break;
		}
		      
		for(int i = 0; i < nTarefas; i++){
		    Tarefas t = Principal.usuarioLogado.getTarefas().get(i);
		    
		    System.out.println("Tarefa" + (i + 1) + ":");
		    System.out.println("\tTitulo:\t\t" + t.getTitulo());
		    System.out.println("\tFinalizada:\t" + t.isFinalizada());
		}
		break;
            }
		  
		case"2":{
		      
	System.out.println("\n=============================================");
	System.out.println("========      TAREFAS FINALIZADAS     =======");
	System.out.println("=============================================");   	    
		
	        ArrayList<Tarefas> tarefas = ConexaoDAO.buscarTarefasDoUsuario(Principal.usuarioLogado.getId());
		Principal.usuarioLogado.setTarefas(tarefas);
		    
		    
		    
		      break;
		  }
		  
		case"3":{
		      
		      
		      break;
		  }
		
		case"4":{
		      
	System.out.println("\n=============================================");
	System.out.println("=============     NOVA TAREFA     ===========");
	System.out.println("============================================="); 	      
		    
	        System.out.print("Digite a uma tarefa: ");
	        String titulo = sc.nextLine();
		
		Tarefas t = new Tarefas();
		t.setTitulo(titulo);
		t.setFinalizada(false);
		t.setIdUsuario(Principal.usuarioLogado.getId());
		
		boolean inserida = ConexaoDAO.inserirTarefa(t);
		if(inserida){
		    System.out.println("Tarefa adicionada com Sucesso!");
		}else{
		    System.out.println("Aconteceu algum erro. Tente novamente!");
		}
		    
		      break;
		  }
		
		  
		case"5":{
		      
		      
		      break;
		  }  
		  
		case"6":{
		      
		      
		      break;
		  }  
		  
		case"7":{
		    
		    Principal.usuarioLogado = null;
		    System.out.println("Fazendo Logout...");
		    break;
		    } 
		}
		
	        if(Principal.usuarioLogado != null){
		    System.out.println("Pressione enter para voltar para Home Page");
		    sc.nextLine();
		
		}
		
		   System.out.println("Logout feito com Sucesso... redirecionando para Index");
		    
				  
	      
	}
    }
}