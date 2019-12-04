package cadastro_login_tarefas;


public class Tarefas {
    
    private int id;
    private String titulo;
    private boolean finalizada;
    private int idUsuario;

    
    
    public int getId() {
	return id;
    }
    public void setId(int id) {
	this.id = id;
    }

    
    
    public int getIdUsuario() {
	return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
    }

    
    
    public String getTitulo() {
	return titulo;
    }
    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    
    
    public boolean isFinalizada() {
	return finalizada;
    }
    public void setFinalizada(boolean finalizada) {
	this.finalizada = finalizada;
    }
}
