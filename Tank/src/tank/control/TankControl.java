package tank.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import tank.model.Enemy;
import tank.model.MyTank;
import tank.model.TankModel;
import tank.model.TankShoot;
import tank.view.TankTools;

public class TankControl implements KeyListener {
	MyTank mt;
	public LinkedList<TankShoot> shootLink;
	public TankControl (MyTank mt){
		this.mt=mt;
		shootLink=new LinkedList<TankShoot>();
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i=e.getKeyCode();
		if(mt.isLive){
			switch(i){
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				//换方向
				mt.switchDerect(TankTools.UP);
				if(mt.getY()>0)
					mt.move();
			//	System.out.println("x="+mt.getX()+"y="+mt.getY()+"derect="+mt.getDerect());
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				mt.switchDerect(TankTools.DOWN);
				if(mt.getY()<TankTools.PANELHEIGHT-TankTools.TANKSIZE)
					mt.move();
			//	System.out.println("x="+mt.getX()+"y="+mt.getY()+"derect="+mt.getDerect());
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				mt.switchDerect(TankTools.LEFT);
				if(mt.getX()>0)
					mt.move();
			//	System.out.println("x="+mt.getX()+"y="+mt.getY()+"derect="+mt.getDerect());
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				mt.switchDerect(TankTools.RIGHT);
				if(mt.getX()<TankTools.PANELWIDTH-TankTools.TANKSIZE)
				mt.move();
			//	System.out.println("x="+mt.getX()+"y="+mt.getY()+"derect="+mt.getDerect());
				break;
			case KeyEvent.VK_SPACE:
				//发射子弹
				shootLink.addLast(mt.shoot());
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
