package tank.model;

import java.util.Observable;

import tank.view.TankTools;

public class TankModel extends Observable{
	private int x;
	private int y;
	private int derect;
	public boolean isLive;
	TankShoot ts;
	
//	public static void  main (String[] arg){
//		//new TankModel(20,20,1);
//		//Thread t=new Thread(new TankModel(1,2,1));
//		//t.start();
//	}
	TankModel(int x,int y,int derect){
		this.setX(x);
		this.setY(y);
		this.setDerect(derect);
		this.isLive=true;
		
	}
	//我方坦克按键改变方向
	public void switchDerect(int derect){
		this.setDerect(derect);
	}
	//敌人坦克自动改变方向
	public void autoSwitchDerect(){
		if(TankTools.TANKSPEED!=0){
			int derect = (int) (Math.random() * 4);
			if (derect != this.getDerect())
				this.setDerect(derect);
			else
				autoSwitchDerect();
		}

	}
	//发射子弹
	public TankShoot shoot(){
		if(TankTools.TANKSPEED!=0){
			ts=new TankShoot(this.getX(),this.getY(),this.getDerect());
			Thread t=new Thread(ts);
			t.start();
			return ts;
		}
		return null;
		
	}
	public void move(){
		switch (this.getDerect()) {
		case TankTools.UP:
			setY(getY() - TankTools.TANKSPEED);
			break;
		case TankTools.DOWN:
			setY(getY() + TankTools.TANKSPEED);
			break;
		case TankTools.LEFT:
			setX(getX() - TankTools.TANKSPEED);
			break;
		case TankTools.RIGHT:
			setX(getX() + TankTools.TANKSPEED);
			break;
		}
	}
	//死亡判断
	public void death(){
		
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
