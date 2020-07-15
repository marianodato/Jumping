package Objetos.Plataformas;

import processing.core.PApplet;
import Objetos.Punto;

public class Verde extends Fija {

	public Verde(Punto pos, float ancho, float alto, float poso) {super(pos, ancho, alto, poso);}

	
	public void Mostrar(PApplet p)
	{
		p.fill(0,255,0);
		p.rect(this.getPos().getX(),this.getPos().getY(),this.getAncho(),this.getAlto());
	}
	
}
