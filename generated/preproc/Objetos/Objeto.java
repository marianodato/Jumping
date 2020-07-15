package Objetos;

import processing.core.PApplet;
import processing.core.PImage;
import Interfaces.Mostrable;
import Interfaces.Movible;

public abstract class Objeto implements Movible, Mostrable 
{
	private Punto pos;
	private float ancho;
	private float alto;

	public Punto getPos() {	return pos;}
	public void setPos(Punto pos) {this.pos = pos;}
	public float getAncho() {return ancho;}
	public void setAncho(float ancho) {this.ancho = ancho;}
	public float getAlto() {return alto;}
	public void setAlto(float alto) {this.alto = alto;}


	
	public Objeto(Punto pos, float ancho, float alto) 
	{
		super();
		this.pos = pos;
		this.ancho = ancho;
		this.alto = alto;
	}
	

	public void Mostrar(PApplet p){}
	public void Mover() {}

}
