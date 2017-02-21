package tank.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import tank.control.EnemyControl;
import tank.control.GotShoot;
import tank.control.TankControl;
import tank.model.MyTank;

public class MainFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar jmb;
	private JMenu jm1,jm2,jm3;
	private JMenuItem jmi1,jmi2,jmi3;
	TankView tankView;
	MyTank mt;
	TankControl tc;
	GotShoot gs;
	public MainFrame(TankView tankView,MyTank mt,TankControl tc,GotShoot gs){	
		this.setTitle("Tank Game");
		this.tankView=tankView;
		this.mt=mt;
		this.tc=tc;
		this.gs=gs;
		this.setSize(TankTools.PANELWIDTH+5, TankTools.PANELHEIGHT+53);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-TankTools.PANELWIDTH)/2, (screenSize.height-TankTools.PANELHEIGHT)/2);
		this.setVisible(true);
		this.setResizable(false);
		this.addKeyListener(this.tc);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//添加组件
		Container contentPane=this.getContentPane();
		
		//初始化菜单
		jmi1=new JMenuItem("开始游戏");
		jmi1.addActionListener(this);
		jmi1.setFont(TankTools.FONT);
		jmi2=new JMenuItem("暂停游戏");
		jmi2.addActionListener(this);
		jmi2.setFont(TankTools.FONT);
		jmi3=new JMenuItem("退出游戏");
		jmi3.addActionListener(this);
		jmi3.setFont(TankTools.FONT);
		
		jm1=new JMenu("游戏");
		jm1.setFont(TankTools.FONT);
		jm2=new JMenu("查看");
		
		jm2.setFont(TankTools.FONT);
		jm3=new JMenu("帮助");
		jm3.setFont(TankTools.FONT);
		
		jmb=new JMenuBar();
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		
		//初始化游戏面板
		
		
		
		//添加组件
		contentPane.add(jmb,BorderLayout.NORTH);
		contentPane.add(tankView,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		MyTank mt=new MyTank(20,20,1);
		TankControl tc=new TankControl(mt);
		EnemyControl ec=new EnemyControl();
		GotShoot gs=new GotShoot(tc,ec);
		TankView tankView=new TankView(mt,tc,ec);
		MainFrame mf=new MainFrame(tankView,mt,tc,gs);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String event=e.getActionCommand();
		Thread t=new Thread(this.tankView);
		Thread t2=new Thread(this.tankView.ec);
		Thread t3=new Thread(this.gs);
		switch(event){
		case "开始游戏":	
			t.start();
			t2.start();
			t3.start();
			break;
		case "暂停游戏":
			TankTools.TANKSPEED=0;
			break;
		case "退出游戏":
			System.exit(0);
		}
	}
}
