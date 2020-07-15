package Objetos;

import java.util.Vector;

import processing.core.PApplet;
import processing.core.PImage;
import sun.org.mozilla.javascript.internal.ast.Jump;
import Interfaces.Tecleable;
import Objetos.Plataformas.Plataforma;

public class Hero extends Objeto implements Tecleable 
{

	private PImage der,izq,actual;
	private int bajarplat,score;
	private float vel,salto,piso,velsalto,velplat,posanterior;
	private boolean saltando,bajando,muere;
	private Vector<Plataforma> vpl;				//Vector de plataformas
	
	public Hero(Punto pos, float ancho, float alto, float _vel, float _salto, PApplet p) 
	{
		super(pos, ancho, alto);
		vel = _vel;
		velsalto = _vel;
		velplat = _vel;
		salto = _salto;
		saltando = false;
		bajando = false;
		muere = false;
		piso = 640;
		posanterior = 640;
		bajarplat = -1;
		score = 0;
		vpl = new Vector<Plataforma>();
		der = p.loadImage("F:\\Jumping\\data\\right.jpg");
		izq = p.loadImage("F:\\Jumping\\data\\left.jpg");
		actual = der;
	}

	public float getPosAnt() {return posanterior;}
	public float getVel() {return vel;}
	public void setVel(float vel) {this.vel = vel;}
	public float getSalto() {return salto;}
	public void setSalto(float salto) {this.salto = salto;}
	public void setvpl(Plataforma p) {this.vpl.add(p);}
	public int getScore() {return score;}
	public void setScore(int score) {	this.score = score;}
	public boolean getMuere() {return muere;}
	public void setMuerr(boolean muere) {	this.muere = muere;}
	public float getPosanterior() {return posanterior;}
	public PImage getDer() {return der;}
	public PImage getIzq() {return izq;}
	public void setActual(PImage actual) {this.actual = actual;}

	public void Procesar(char c)
	{
		if(c=='w'){	saltando = true;}
		//Mueve izquierda
		if(c=='a'){this.getPos().setX(this.getPos().getX() - vel);
		//Mueve derecha
		}else if (c=='d'){this.getPos().setX(this.getPos().getX() + vel);}
	}
	
	
	public void Mostrar(PApplet p)
	{
		p.fill(0,0,255);
		p.image(actual,this.getPos().getX(),this.getPos().getY(),this.getAncho(),this.getAlto());
		
	}

	public void Mover(PApplet p) 
	{
		if(saltando && !bajando)
		{
			this.getPos().setY(this.getPos().getY() - velsalto);
			velsalto -= 0.4;		//Friccion

			if(this.getPos().getY() <= posanterior - salto) bajando = true;

		}
		else if(bajando && saltando)
		{
			this.getPos().setY(this.getPos().getY() + velsalto);
			velsalto += 0.4;		//Gravedad		
			
			//Muevo Plataformas
			for(int i=0; i<vpl.size() && !muere;i++)
			{		
			//Si me poso en una
				if((this.getPos().getY() + getAlto() <= vpl.get(i).getPos().getY()				//
					&& this.getPos().getY() + getAlto()+ velsalto > vpl.get(i).getPos().getY())	//Y
					&& ((this.getPos().getX() + getAncho() >= vpl.get(i).getPos().getX()		//
					&& this.getPos().getX() + getAncho() <= vpl.get(i).getPos().getX() + 70) 	//
					||(this.getPos().getX() >= vpl.get(i).getPos().getX()						//
					&& this.getPos().getX() <= vpl.get(i).getPos().getX() + 70)) && bajando)	//X
				{
					posanterior = this.getPos().getY() + getAlto();
					velsalto = vel;
					this.getPos().setY(vpl.get(i).getPos().getY() - getAlto());
					score += 0.01*(650-this.getPos().getY());
					saltando = true;
					bajando = false;
					if(vpl.get(i).getPos().getY() < piso-10)bajarplat = i;				
				}
			}
			//Si toco el piso
			if(this.getPos().getY() >= piso - this.getAlto())
			{
				muere = true;
				bajando = false;
				saltando = false;
			}		
		}
		
		if(!muere) 
			if(this.getPos().getY() <= 0 )
			{
				bajando = true;
				bajarplat = -1;
				muere = true;
			}
		
		if(bajarplat != -1) BajarPlat(bajarplat);
		if(muere) Morir();
		
		if(this.getPos().getX() - vel <= 0)	this.getPos().setX(499-this.getAncho());		
		else if(this.getPos().getX() +this.getAncho() + vel >= 500)	this.getPos().setX(1);	

	}
	
	private void Morir()
	{
		this.getPos().setY(this.getPos().getY() - 4);
		
		for(int j=0; j<vpl.size();j++)
			vpl.get(j).getPos().setY(vpl.get(j).getPos().getY() - 10);
		
		if (this.getPos().getY() + this.getAlto() < 0) System.exit(0);
	}
	private void BajarPlat(int i)
	{
		for(int j=0; j<vpl.size();j++)
		{
			vpl.get(j).getPos().setY(vpl.get(j).getPos().getY() + velplat);
			if(vpl.get(i).getPos().getY() >= piso-10) bajarplat = -1; 
		}
	}
}
