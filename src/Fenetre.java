import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Fenetre extends JFrame implements KeyListener{

	int x;
	int y;
	boolean tirer;
	int tailleCarre = 20;
	int hauteur;
	int largeur;
	int direction;
	int sol;
	Color background = Color.GRAY;
	Color foreground = Color.BLACK;
	boolean enCours;
	
	Fenetre(String titre, int hauteur, int largeur){
		super(titre);
		addKeyListener(this);
		this.setSize( largeur, hauteur );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		this.setLocationRelativeTo(null);
		this.hauteur = hauteur;
		this.largeur = largeur;
		sol = hauteur/2;
		x = 0;
		y = 0;
		enCours = true;
		this.setVisible( true );
		
	}
	
	public void paint (Graphics g){	
		g.setColor(background);
		g.fillRect(0, 0, largeur, hauteur);
		
		g.setColor(foreground);
		g.fillRect(0, sol, largeur, sol);
		
		g.setColor(Color.YELLOW);
		g.fillRect(largeur/2+x, sol-tailleCarre+y, tailleCarre , tailleCarre);
		
		if(tirer == true){
			System.out.println("pas tout seul");
			int xMi = largeur/2+x;
			int yMi = sol-tailleCarre+y;
			while(xMi < largeur && xMi > 0){
				g.setColor(Color.WHITE);
				g.fillOval(xMi, yMi, 6, 6);
				if(direction == -1){
					xMi -= 10;
				}else{
					xMi+= 10;
				}
				try{
					Thread.sleep(10);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			tirer = false;
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void animer (){		
		while(enCours){	
			if(getY()<0){
				setY(getY()+2);
			}
			this.repaint();			
			try { Thread.sleep(20);} 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT && largeur/2+x-5 > 0){
			setX(getX()-5);
			direction = -1;
		}
		if((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == 32) && sol-tailleCarre+y-60 > 0)
			setY(getY()-60);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && largeur/2+x+5+tailleCarre < largeur){
			setX(getX()+5);
			direction = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && getY() < -60)
			setY(getY()+60);
		if(e.getKeyChar() == 'q'){
			enCours = false;
		}
		if(e.getKeyChar() == 'x'){
			tirer = true;
		}
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String args[]){
		new Fenetre("PlateForme", 600, 1000).animer();
	}

}
