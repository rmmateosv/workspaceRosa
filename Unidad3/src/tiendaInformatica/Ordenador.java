package tiendaInformatica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ordenador {
	private int codigo;
	private Date fecha;
	private ArrayList<Componente> piezas = new ArrayList<Componente>();
	public Ordenador(int codigo, Date fecha, ArrayList<Componente> piezas) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.piezas = piezas;
	}
	public Ordenador() {
		
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArrayList<Componente> getPiezas() {
		return piezas;
	}
	public void setPiezas(ArrayList<Componente> piezas) {
		this.piezas = piezas;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Codigo:"+codigo + 
				"\tFecha:"+formato.format(fecha)+
				"\tPiezas:");
		for(Componente c:piezas) {
			c.mostrar();
		}
	}
}
