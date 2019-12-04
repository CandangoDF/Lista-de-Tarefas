/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro_login_tarefas;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConexaoDAO {
    
    public static void inserirUsuario(Usuario u){
        
        try{
	    
        Driver driver = new Driver();
	DriverManager.registerDriver(driver);
	
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");	
	
	PreparedStatement stmt = conn.prepareStatement("INSERT INTO lista_tarefas.usuario (nome, email, senha) VALUE (?,?,?)");
	
	stmt.setString(1, u.getNome());
	stmt.setString(2, u.getEmail());
	stmt.setString(3, u.getSenha());
	
	int linhasAfetadas = stmt.executeUpdate();
	
	if(linhasAfetadas > 0){
	    //usuario cadastrado
	}else{
	    // algo deu errado
	}
	
	
	}catch(SQLException e){
	e.printStackTrace();
	}
    }
    
    public static ArrayList<Usuario> buscarUsuario(){
	ArrayList<Usuario> userArray = new ArrayList();
	
	try{
	Driver driver = new Driver();
	DriverManager.registerDriver(driver);
	
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lista_tarefas.usuario");
	
	ResultSet rs = stmt.executeQuery();
	
	while(rs.next()){
	    
	    int id = rs.getInt("id");
	    String nome  = rs.getString("nome");
	    String email = rs.getString("email");
	    String senha = rs.getString("senha");
	    
	    Usuario user = new Usuario();
	    user.setId(id);
	    user.setNome(nome);
	    user.setEmail(email);
	    user.setSenha(senha);
	    
	    userArray.add(user);
	}
	
       }catch(SQLException e){
	e.printStackTrace();
       }
	return userArray;
    }
    
    
    public static void editarUsuario(Usuario u){
	//quando não tenho retorno preciso parametrizar para acessar o método
	
	try{
	    Driver driver = new Driver();
	    DriverManager.registerDriver(driver);
	    
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
	    PreparedStatement stmt = conn.prepareStatement("UPDATE lista_tarefas.usuario SET nome = ?, email = ?, senha = ? WHERE id = ?");
	    stmt.setString(1, u.getNome());
	    stmt.setString(2, u.getEmail());
	    stmt.setString(3, u.getSenha());
	    stmt.setInt(4, u.getId());
	    
	    int linhasAfetadas = stmt.executeUpdate();
	    
	    if(linhasAfetadas > 0){
		//usuario cadastrao com sucesso
	    }else{
		//não cadastrado 
	    }
	}catch(SQLException e){
	    e.printStackTrace();
	}
    }
    
    
    public static void removerUsuario(Usuario u){
    
        try{
	    Driver driver = new Driver();
	    DriverManager.registerDriver(driver);
	    
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
	    PreparedStatement stmt = conn.prepareStatement("DELETE FROM lista_tarefas.usuario WHERE id = ?");
            
	    stmt.setInt(1, u.getId());
	    int linhasAfetadas = stmt.executeUpdate();
	    
	    if(linhasAfetadas > 0){
		//usuario alterado
	    }else{
		//usuario não alterado
	    }
    
       }catch(SQLException e){
	   e.printStackTrace();
	   
       }
    }
  
    //===================================== métodos das tarefas =================================
    
    public static ArrayList<Tarefas>buscarTarefasDoUsuario(int idUsuario){
	ArrayList<Tarefas> tarefas = new ArrayList();
	
	try{
	    Driver driver = new Driver();
	    DriverManager.registerDriver(driver);
	    
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
	    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lista_tarefas.tarefas WHERE id_usuario = ?");
	    stmt.setInt(1, idUsuario);
	    
	    ResultSet rs = stmt.executeQuery();
	    
	    while(rs.next()){
	         int id = rs.getInt("id");
		 String titulo = rs.getString("titulo");
		 boolean finalizada = rs.getBoolean("finalizada");
		 
		 Tarefas t = new Tarefas();
		 t.setId(id);
		 t.setTitulo(titulo);
		 t.setFinalizada(finalizada);
		 t.setIdUsuario(idUsuario);
	        	
		tarefas.add(t);
	    }
	   	    
	} catch(SQLException e){
	    e.printStackTrace();
	}
	
	return tarefas;
	
    }
    
    
    public static boolean inserirTarefa(Tarefas t){
	boolean sucesso = false;
	
	try{
	    Driver driver = new Driver();
	    DriverManager.registerDriver(driver);
	    
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
	    PreparedStatement stmt = connection.prepareStatement("INSERT INTO lista_tarefas.tarefas (titulo, finalizada, id_usuario) VALUE (?,?,?)");
	   
	    stmt.setString(1, t.getTitulo());
	    stmt.setBoolean(2, t.isFinalizada());
	    stmt.setInt(3, t.getIdUsuario());
	    
	    int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                sucesso = true;  //não tem em inserir usuarios
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso; // não tem em inserir usuario
    }

    public static boolean finalizarTarefa(Tarefas t) {
        boolean sucesso = false;

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

            PreparedStatement stmt = connection.prepareStatement("UPDATE lista_tarefas.tarefas SET finalizada = ? WHERE id = ? and id_usuario = ?");

            stmt.setBoolean(1, true);
            stmt.setInt(2, t.getId());
            stmt.setInt(3, t.getIdUsuario());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas > 0) {
                sucesso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public static boolean removerTarefa(Tarefas t) {
        boolean sucesso = false;

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM lista_tarefas.tarefas WHERE id = ? and id_usuario = ?");

            stmt.setInt(1, t.getId());
            stmt.setInt(2, t.getIdUsuario());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                sucesso = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}

	    
    
    
    
    
    

    
    
    
    

