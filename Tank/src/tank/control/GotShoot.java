package tank.control;

import java.util.LinkedList;

import tank.model.Enemy;
import tank.model.MyTank;
import tank.model.TankShoot;

public class GotShoot implements Runnable {
	MyTank mt;
	Enemy en;
	TankShoot ts;
	LinkedList<TankShoot> myShoot;
	LinkedList<TankShoot> enemyShoot;
	LinkedList<Enemy> enemy;
	
	public GotShoot(TankControl tc,
			EnemyControl ec){
		//myShoot=new LinkedList<TankShoot>();
		//enemyShoot=new LinkedList<TankShoot>();
		//enemy=new LinkedList<Enemy>();
		this.mt=tc.mt;
		this.enemy=ec.enemyLink;
		this.enemyShoot=ec.enemyShoot;
		this.myShoot=tc.shootLink;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
				if(enemy!=null&&mt.isLive){
					for(int i=0;i<enemy.size();i++){
						en=enemy.get(i);
						if(myShoot.size()!=0){
							for(int j=0;j<myShoot.size();j++){
								ts=myShoot.get(j);
								if((ts.getX()-en.getX()>0)&&(ts.getX()-en.getX()<30)
										&&(ts.getY()-en.getY())<30&&(ts.getY()-en.getY())>0){
									en.isLive=false;
									System.out.println("Tank"+i+"is dead");
									ts.isLive=false;
									enemy.remove(i);
								}
								
							}
						}
							
					}
					for(int i=0;i<enemyShoot.size();i++){
						ts=enemyShoot.get(i);
						if((ts.getX()-mt.getX()>0)&&(ts.getX()-mt.getX()<30)
								&&(ts.getY()-mt.getY())<30&&(ts.getY()-mt.getY())>0){
							ts.isLive=false;
							mt.isLive=false;
						}
					}
				}
				for(int i=0;i<myShoot.size();i++){
					if(!myShoot.get(i).isLive)
						myShoot.remove(i);
				}
				for(int i=0;i<enemyShoot.size();i++){
					if(!enemyShoot.get(i).isLive)
						enemyShoot.remove(i);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
