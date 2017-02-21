package tank.control;

import java.util.LinkedList;

import tank.model.Enemy;
import tank.model.TankShoot;
import tank.view.TankTools;

public class EnemyControl implements Runnable {
	Enemy en;
	public LinkedList<TankShoot> enemyShoot;
	public LinkedList<Enemy> enemyLink;
	public EnemyControl(){
		enemyLink=new LinkedList<Enemy>();
		enemyShoot=new LinkedList<TankShoot>();
		for(int i=0;i<6;i++){
			en=new Enemy((int)(Math.random()*500),
					(int)(Math.random()*400),(int)(Math.random()*4));
			enemyLink.addLast(en);
			Thread t=new Thread(en);
			t.start();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(500);
				if(TankTools.TANKSPEED!=0)
					if(enemyLink.size()!=0){
						int i=(int)(Math.random()*enemyLink.size());
				
						en=enemyLink.get(i);
						enemyShoot.addLast(en.shoot());
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
