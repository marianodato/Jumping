import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

import Interfaces.Mostrable;
import Interfaces.Movible;
import Objetos.Hero;
import Objetos.Objeto;
import Objetos.Punto;
import Objetos.Plataformas.Fija;
import Objetos.Plataformas.Roja;
import Objetos.Plataformas.Verde;

public class Jumping extends PApplet
{
	Vector<Mostrable> vmostrables = new Vector<Mostrable>();
	Vector<Movible> vmovibles = new Vector<Movible>();
	Vector<Fija> vf  = new Vector<Fija>();
	Hero h = new Hero(new Punto(225,560), 50, 80, 12, 240,this);
	
	private int plat_ancho, plat_alto;

	public void setup()
	{ 		
		size(500,650);
		plat_ancho = 70;
		plat_alto = 10;
		
		for(int i=150,j=0;j<5;j++,i+=random(130,150))CreateFija(i,plat_ancho,plat_alto,random(0,100));
		
		vmostrables.add(h);
		for(int i=0; i<vf.size();i++)vmostrables.add(vf.get(i));
		vmovibles.add(h);
		//font=load
	
	} 
 
	public void draw()
	{ 
		background(255); 
	
		if(h.getPosanterior() == 640 && h.getMuere() == false)			//Piso
		{	
			fill(0,255,0);
			rect(0,640,500,9);
		}
		if(h.getPos().getY() >= 0 && h.getMuere() == false)				//Techo
		{			
			fill(0,0,0);
			rect(0,0,500,plat_alto);
			fill(255,255,255);
			textSize(plat_alto);
			text("DANGER",230,10);
		}
		
		for(int i=0; i<vmostrables.size();i++) vmostrables.get(i).Mostrar(this);
		for(int i=0; i<vmovibles.size();i++) vmovibles.get(i).Mover(this);
		
		if(keyPressed) keyPressed();
		
		fill(125,125,125);
		textSize(25);
		text(h.getScore(),15,40);
		
	} 

	public void keyPressed()
	{		 
		if (key == CODED) {
		    if (keyCode == UP && h.getPosAnt() == 640) {
		    	h.Procesar('w');
		    } 
		    if (keyCode == RIGHT) {
		    	h.Procesar('d');
		    	h.setActual(h.getDer());
		    } 
		    if (keyCode == LEFT) {
		    	h.Procesar('a');
		    	h.setActual(h.getIzq());
		    } 
		}
	}
	
	public void CreateFija(float y, float ancho, float alto, float r)
	{
		Punto p = new Punto(random(0,500-plat_ancho),650-y);
		
		if(r>50)
		{
			Roja f = new Roja(p,ancho,alto,y);
			vf.add(f);
			vmostrables.add(f);
			vmovibles.add(f);
			h.setvpl(f);
		}else{
			Verde f = new Verde(p,ancho,alto,y);
			vf.add(f);
			vmostrables.add(f);
			vmovibles.add(f);
			h.setvpl(f);
		}		
	}	
}
