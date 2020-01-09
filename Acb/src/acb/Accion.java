package acb;

public class Accion {
	private int codigo;
	private Partido partido;
	private TipoAccion tipo;
	private Jugador jugador;
	private boolean anulada;
	public Accion() {
		super();
	}
	public Accion(int codigo, Partido partido, TipoAccion tipo, Jugador jugador, boolean anulada) {
		super();
		this.codigo = codigo;
		this.partido = partido;
		this.tipo = tipo;
		this.jugador = jugador;
		this.anulada = anulada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Partido getPartido() {
		return partido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	public TipoAccion getTipo() {
		return tipo;
	}
	public void setTipo(TipoAccion tipo) {
		this.tipo = tipo;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public boolean isAnulada() {
		return anulada;
	}
	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}
	
	public void mostrar() {
		System.out.println("Código:" + codigo + 
				"\tPartido:" + partido.getCodigo() +
				"\tTipo Acción:" +  tipo.getTipo()+"-"+tipo.getDescripcion()+
				"\tJugador:" + jugador.getNombre() + 
				"\tAnulada:" + anulada);
	}
}
