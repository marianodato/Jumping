package Objetos.Plataformas;

import processing.core.PApplet;
import processing.core.PImage;
import Objetos.Punto;

abstract public class Fija extends Plataforma
{

	public void Mostrar(PApplet p)
	{
	}

	public Fija(Punto pos, float ancho, float alto, float poso) {super(pos, ancho, alto, poso);}
}
