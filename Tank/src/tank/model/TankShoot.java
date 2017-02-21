package tank.model;

import java.util.Observable;

import tank.view.TankTools;

public class TankShoot extends Observable implements Runnable{
	private int x;
	private int y;
	private int derect;
	public boolean isLive;
	
//	public static void main(String[] arg){
//		TankShoot ts=new TankShoot(300,200,3);
//		Thread t=new Thread(ts);
//		t.start();
//	}
	
	TankShoot(int x,int y,int derect){
		this.setDerect(derect);
		this.setX(x+TankTools.TANKSIZE/2);
		this.setY(y+TankTools.TANKSIZE/2);
		this.isLive=true;
	}
	public void move(){
		switch(this.getDerect()){
		case TankTools.UP:
			this.setY(this.getY() - 4);
			break;
		case TankTools.DOWN:
			this.setY(this.getY() + 4);
			break;
		case TankTools.LEFT:
			this.setX(this.getX() - 4);
			break;
		case TankTools.RIGHT:
			this.setX(this.getX() + 4);
			break;
		}
	}
	//À¿Õˆ≈–∂œ
	public boolean death(){
		if(getX()<0||getY()<0||getX()>TankTools.PANELWIDTH||getY()>TankTools.PANELHEIGHT)
			this.isLive=false;

		return this.isLive;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.isLive){
			try{
				Thread.sleep(100);
				if(TankTools.TANKSPEED!=0)
					this.move();
				if(getX()<0||getY()<0||getX()>TankTools.PANELWIDTH||getY()>TankTools.PANELHEIGHT)
					this.isLive=false;
				//System.out.println("x="+this.getX()+"y="+this.getY());
				//this.notifyObservers();
			}catch(Exception e){
				e.printStackTrace();
			}
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

	public int getDerect() {
		return derect;
	}

	public void setDerect(int derect) {
		this.derect = derect;
	}
}
