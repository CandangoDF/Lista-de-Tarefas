/*
 * verificar Github do Igor
 */
package cadastro_login_tarefas;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Principal {
    
    static Usuario usuarioLogado = null;
   
    public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	boolean rodando = true;
	
	
	while(true){
	    
	System.out.println("\n==========================================================");
	System.out.println("=========     PROGRAMINHA LISTA DE TAREFAS      ==========");
	System.out.println("==========================================================");
	
	System.out.println("[1] Cadastro de usuário");
	System.out.println("[2] Login de usuário");
	System.out.println("[3] Listar usuários");
	System.out.println("[4] Editar usuário");
	System.out.println("[5] Remover usuário");
	System.out.println("[6] Fechar Programa");
	System.out.print("\tEscolha uma opção: ");
	String opcao = sc.nextLine();
	
	switch(opcao){
	    
	    case"1":{
		
	    System.out.println("\n==========================================================");
	    System.out.println("============     CADASTRO DE USUÁRIOS       ==============");
	    System.out.println("==========================================================");
	    
            System.out.print("Digite seu nome: ");
            String nome = sc.nextLine();
	    System.out.print("Digite seu email: ");
            String email = sc.nextLine();
	    System.out.print("Digite sua senha: ");
            String senha = sc.nextLine();
	    
	    Usuario user = new Usuario();
	    user.setNome(nome);
	    user.setEmail(email);
	    user.setSenha(senha);
	    
	    if(senha.length() >= 6 && senha.length() <= 15){
		ConexaoDAO.inserirUsuario(user);
	        System.out.println("\t\nUsuario cadastrado com sucesso!");
		
	    }else{
		System.out.println("\t\nNão cadastrado! A senha deve conter entre 6 e 15 digítos!");
		
	    }
	    
		break;
	    }
	    
	    
	    case "2": {
		
	    System.out.println("\n==========================================================");
	    System.out.println("============               LOGIN                ==========");
	    System.out.println("==========================================================");
	    System.out.print("Digite seu email: ");
	    String email = sc.nextLine();
	    System.out.print("Digite sua senha: ");
	    String senha = sc.nextLine();
	    ArrayList<Usuario> userArray = ConexaoDAO.buscarUsuario();
	   
	    for (int i = 0; i < userArray.size(); i++) {
                        Usuario u = userArray.get(i);

                       if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                            usuarioLogado = u;
                            break;
                        }
                    }

                    if (usuarioLogado == null) {
                        System.out.println("Email/senha incorretos. Redirecionando para Index...");
                    } else {
                        System.out.println("Login feito com sucesso!");
                        Logado.menuLogin();
                    }

                    break;
                } 
	    
	    case "3": {
		
	    System.out.println("\n==========================================================");
	    System.out.println("============    LISTA DE USUÁRIOS CADASTRADOS    =========");
	    System.out.println("==========================================================");
		ArrayList<Usuario> userArray = ConexaoDAO.buscarUsuario();
		for(int i = 0; i < userArray.size(); i++){
		    Usuario uTemp = userArray.get(i);
		    
		    System.out.println("Usuario" +i);
		    System.out.println("\tNome: " + uTemp.getNome());
		    System.out.println("\tEmail: " + uTemp.getEmail());
		    System.out.println("\tSenha: " + uTemp.getSenha());
		    		    
		}break;
	    }
	    
	        
	    case "4":{
		
	    System.out.println("\n==========================================================");
	    System.out.println("============           EDITAR USUÁRIO            =========");
	    System.out.println("==========================================================");
		System.out.println("\nDigite o email do usuário que quer Editar: ");
	        String eemail = sc.nextLine();
	        
	        ArrayList<Usuario> userArray = ConexaoDAO.buscarUsuario();
		for(int i = 0; i < userArray.size(); i++){
		    Usuario uTemp = userArray.get(i);
		    
		    if(uTemp.getEmail().equals(eemail)){
		    
		    System.out.println("Usuario" +i);
		    System.out.println("\tNome: " + uTemp.getNome());
		    System.out.println("\tEmail: " + uTemp.getEmail());
		    System.out.println("\tSenha: " + uTemp.getSenha());	
		
		    System.out.println("\t\nColoque os novos valores para editar esta usuário");
		    System.out.print("\tDigite novo nome: ");
		    String nome = sc.nextLine();
		    System.out.print("\tDigite novo email: ");
		    String email = sc.nextLine();
		    System.out.print("\tDigite nova senha: ");
		    String senha = sc.nextLine();
		    int id = uTemp.getId();
		    
		    Usuario user = new Usuario();
		    user.setNome(nome);
		    user.setEmail(email);
		    user.setSenha(senha);
		    user.setId(id);
		    
		    ConexaoDAO.editarUsuario(user);
		    System.out.println("\nUsuário Editado com sucesso!");
		    break;
		    
		}else if(i == userArray.size() -1){ 
		    System.out.println("\nOops, algo deu errado, tente novamente!");	
		}
	    }break;
	}
	    
	    
	    
	    case "5":{
		
	    }
	    System.out.println("\n==========================================================");
	    System.out.println("============           DELETAR USUÁRIO           =========");
	    System.out.println("==========================================================");
		ArrayList<Usuario> userArray = ConexaoDAO.buscarUsuario();
		for(int i = 0; i < userArray.size(); i++){
		    Usuario uTemp = userArray.get(i);
		    
		    System.out.println("Usuario" +i);
		    System.out.println("\tNome: " + uTemp.getNome());
		    System.out.println("\tEmail: " + uTemp.getEmail());
		    System.out.println("\tSenha: " + uTemp.getSenha());	
		
		    System.out.println("\nDeseja realmente excluir esse Usuário?");
		    System.out.println("[1] SIM      [2]Não");
		    String escolha = sc.nextLine();
		    
		    switch(escolha){
			
			case"1":{
			    ConexaoDAO.removerUsuario(uTemp);
			    System.out.println("\nUsuário DELETADO");
			}break;
			
			case"2":{
			    System.out.println("Redirecionando para o MENU PRINCIPAL");
			}
		    }
		}break;	
		
	    
		
	    case "6":{
		System.out.println("\nPrograma Finalizado!");
		rodando = false;
		
		break;
        }
	    
	    default:{
	           System.out.println("\t\nOpção inválida... digite uma opção válida!");
	    }
	        if(true){
	        System.out.println("\nPressione Enter para continuar");
	        sc.nextLine();
	        }
            }
        }
    }
//=======================================    MENU LOGADO   ==============================================	






} //chave de fechamento da classe