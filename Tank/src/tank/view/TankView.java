/**
 * 
 */
package tank.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tank.control.EnemyControl;
import tank.control.TankControl;
import tank.model.Enemy;
import tank.model.MyTank;
import tank.model.TankModel;
import tank.model.TankShoot;

/**
 * @author Sheldon
 *
 */
public class TankView extends JPanel implements Runnable,Observer{
	
	/**
	 * 
	 */
	//HashMap<?, ?> hm=null;
	MyTank mt=null;
	Enemy en=null;
	TankControl tc=null;
	TankShoot ts=null;
	EnemyControl ec=null;
	private static final long serialVersionUID = 1L;
	TankView(MyTank mt,TankControl tc,EnemyControl ec){
		this.setSize(TankTools.PANELWIDTH,TankTools.PANELHEIGHT);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		this.mt=mt;
		this.tc=tc;
		this.ec=ec;
		//this.addKeyListener(mt);
		//repaint();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(mt.isLive)
			drawMyTank(mt.getX(),mt.getY(),mt.getDerect(),g );
		//遍历shootLink取出子弹
		if(tc.shootLink!=null){
			for(int i=0;i<tc.shootLink.size();i++){
				ts=tc.shootLink.get(i);
				drawShoot(ts.getX(),ts.getY(),ts.getDerect(),g);
			}
		}
		if(ec.enemyLink!=null){
			for(int i=0;i<ec.enemyLink.size();i++){
				en=ec.enemyLink.get(i);
				drawEnemy(en.getX(),en.getY(),en.getDerect(),g);
			}
		}
		if(ec.enemyShoot!=null){
			for(int i=0;i<ec.enemyShoot.size();i++){
				ts=ec.enemyShoot.get(i);
				g.setColor(Color.YELLOW);
				drawShoot(ts.getX(),ts.getY(),ts.getDerect(),g);
			}
			g.setColor(Color.white);
		}
		//mt.hm.get(key)
	}
	
	void drawMyTank(int x,int y,int derect, Graphics g){
		g.setColor(Color.cyan);
		drawTank(x,y,derect,g);
	}
	
	void drawEnemy(int x,int y,int derect,Graphics g){
		g.setColor(Color.white);
		drawTank(x,y,derect,g);
	}
	void drawTank(int x, int y, int derect,Graphics g){
		switch(derect){
			case TankTools.UP: 
			case TankTools.DOWN: 
				//绘制左边1/3
				g.draw3DRect(x, y, TankTools.TANKSIZE/3, TankTools.TANKSIZE, true);
				//绘制中间1/3
				g.draw3DRect(x+TankTools.TANKSIZE/3, y+TankTools.TANKSIZE/4, 
						TankTools.TANKSIZE/3, TankTools.TANKSIZE/2, false);
				//绘制右边1/3
				g.draw3DRect(x+2*TankTools.TANKSIZE/3, y,
						TankTools.TANKSIZE/3, TankTools.TANKSIZE, true);
				if(derect==TankTools.UP){
					g.drawLine(x+TankTools.TANKSIZE/2, y, x+TankTools.TANKSIZE/2, 
							y+TankTools.TANKSIZE/2);
				}else if(derect==TankTools.DOWN){
					g.drawLine(x+TankTools.TANKSIZE/2, y+TankTools.TANKSIZE/2, 
							x+TankTools.TANKSIZE/2, y+TankTools.TANKSIZE);
				}
				break;
			case TankTools.LEFT: 
			case TankTools.RIGHT: {
				//绘制上边1/3
				g.draw3DRect(x, y, TankTools.TANKSIZE, TankTools.TANKSIZE/3, true);
				//绘制中间1/3
				g.draw3DRect(x+TankTools.TANKSIZE/4, y+TankTools.TANKSIZE/3, 
						TankTools.TANKSIZE/2, TankTools.TANKSIZE/3, false);
				//绘制下边1/3
				g.draw3DRect(x, y+2*TankTools.TANKSIZE/3,
						TankTools.TANKSIZE, TankTools.TANKSIZE/3, true);
				if(derect==TankTools.LEFT){
					g.drawLine(x, y+TankTools.TANKSIZE/2, x+TankTools.TANKSIZE/2, 
							y+TankTools.TANKSIZE/2);
				}else if(derect==TankTools.RIGHT){
					g.drawLine(x+TankTools.TANKSIZE/2, y+TankTools.TANKSIZE/2, 
							x+TankTools.TANKSIZE, y+TankTools.TANKSIZE/2);
				}
				break;
			}
		}		
	}
	public void drawShoot(int x,int y,int derect,Graphics g){
		g.setColor(Color.red);
		g.drawRect(x, y, 1, 1);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
			try{
				Thread.sleep(200);	
				repaint();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
	}

}
