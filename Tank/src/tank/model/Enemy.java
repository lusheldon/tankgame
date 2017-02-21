package tank.model;

import tank.view.TankTools;

public class Enemy extends TankModel implements Runnable{
	
	public Enemy(int x, int y, int derect) {
		super(x, y, derect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int cont=0;
		while(this.isLive){
			try{
				Thread.sleep(50);
				this.move();	
				cont++;
				int flag=(int)(Math.random()*30)+20;
				if(cont>flag||getX()<=0||getY()<=0||getX()>TankTools.PANELWIDTH-30||getY()>TankTools.PANELHEIGHT-30){
					this.autoSwitchDerect();
					cont=0;
				}
			//	System.out.println("x="+this.getX()+"y="+this.getY()+"cont="+cont);
				//this.notifyObservers();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
