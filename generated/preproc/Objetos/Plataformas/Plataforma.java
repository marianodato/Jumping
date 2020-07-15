 package Objetos.Plataformas;

import Objetos.Objeto;
import Objetos.Punto;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Plataforma extends Objeto 
{
	private float posoriginal;
	public Plataforma(Punto pos, float ancho, float alto, float poso) {super(pos, ancho, alto);posoriginal = poso;}
	
	public void Mostrar(PApplet p)
	{
	}

	public float getPosoriginal() {return posoriginal;}
	public void setPosoriginal(float posoriginal) {this.posoriginal = posoriginal;}

	public void Mover(PApplet p) 
	{	
		if((this.getPos().getY() + this.getAlto()) > 650) 
		{
			this.getPos().setY(0-this.getAlto());
			this.getPos().setX(p.random(0,500-this.getAncho()));
		}
	}
}
