package ������Ʈ_���ð���;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;
import javax.swing.*;
import java.util.concurrent.*;
import java.util.*;

public class PlayGameFrame extends JFrame
{
	private JPanel userPlay = new JPanel();			//�÷��̾ �����̴� panel
	private JPanel bulletPanel = new JPanel();		//�Ѿ��� �����̴� panel
	private JPanel itemMove = new JPanel();			//item�� �����̴� panel
	private JPanel enemyMove = new JPanel();		//enemy�� �����̴� panel
	private PlayPanel mainPlay = new PlayPanel();	//��� �̹����� �ִ� panel
	private JPanel state = new JPanel();			//����, �̸� , �ð��� ǥ�õǴ� ���� panel
	private JLabel scorefield;		//���� ǥ�� ����
	private JLabel namefield;		//�̸� ǥ�� ����
	private JLabel timefield;		//�ð� ǥ�� ����
	
	private Player user;		//�÷��̾�
	private int user_v = 4;		//�÷��̾� �ӵ�
	private boolean[] keys = {false, false, false, false}; //L, R, U, D �� ����Ű�� ������ ���������� ��Ÿ��
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//������ �Ѿ� ����Ʈ
	
	private MidEnemy me;		//mid enemy
	private FinalEnemy fe;		//final enemy
	
	private ArrayList<Items> items = new ArrayList<Items>();		//item ����Ʈ
	private ArrayList<Items> item_mes = new ArrayList<Items>();		//enemy�� �Ѹ��� item ����Ʈ
	private ArrayList<Items> item_fes = new ArrayList<Items>();
	
	private int time = 120;			//�÷��� Ÿ��
	
	private SecureRandom rand = new SecureRandom();
	
	private Object key_i = new Object();		//����ȭ�� ���� key
	private Object key_mes = new Object();
	private Object key_fes = new Object();
	private Object key_b = new Object();
	
	private PlayGameFrame this_s = this;		//this ������

	
	
	
	public PlayGameFrame(String name)
	{
		setTitle("Shooting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		user = new Player(name);
		
		this.setLayout(null);			//��ǥ�� component�� ���̱� ���� ���̾ƿ��� null�� ����
		this.setSize(4*150, 5*150);
		
		scorefield = new JLabel("Score : " + user.getTotal());		//����, �̸�, �ð� ǥ�� label ����
		namefield = new JLabel("Name : " + user.getName());
		timefield = new JLabel("Time : " + time);
		
		Font font = new Font("", Font.ITALIC, 20);			//����, �̸�, �ð��� ��Ʈ ����
		scorefield.setFont(font);
		namefield.setFont(font);
		timefield.setFont(font);
		
		state.setBackground(Color.MAGENTA);			//����, �̸�, �ð��� ǥ���ϴ� panel�� ���� ����
		state.setLayout(new GridLayout(1,3));		//grid ���̾ƿ����� �� ĭ���� ���� ��
		state.add(scorefield);						//�� ĭ���� ����, �̸�, �ð��� �߰�
		state.add(namefield);
		state.add(timefield);
		state.setSize(this.getWidth(), this.getHeight()/15);
		state.setLocation(0, 0);
		
		add(state);
		
		mainPlay.setLayout(null);
		mainPlay.setLocation(0, state.getHeight());
		mainPlay.setSize(this.getWidth(), this.getHeight()-state.getHeight());
		
		userPlay.setLayout(null);
		userPlay.setLocation(0, 0);
		userPlay.setSize(this.getWidth(), this.getHeight()-state.getHeight());
		userPlay.setOpaque(false);			//panel�� ����� �����ϰ� ��
		user.setLocation((this.getWidth()-user.getWidth())/2, userPlay.getHeight() - 2*user.getHeight());	//�÷��̾��� �ʱ� ��ġ�� ȭ�� �� �Ʒ� ����� ����
		userPlay.add(user);		
		
		itemMove.setLayout(null);
		itemMove.setLocation(0, 0);
		itemMove.setSize(this.getWidth(), this.getHeight()-state.getHeight());
		itemMove.setOpaque(false);			//panel�� ����� �����ϰ� ��
		
		bulletPanel.setLayout(null);
		bulletPanel.setLocation(0, 0);
		bulletPanel.setSize(this.getWidth(), this.getHeight()-state.getHeight());
		bulletPanel.setOpaque(false);		//panel�� ����� �����ϰ� ��
		
		enemyMove.setLayout(null);
		enemyMove.setLocation(0, 0);
		enemyMove.setSize(this.getWidth(), this.getHeight()-state.getHeight());
		enemyMove.setOpaque(false);			//panel�� ����� �����ϰ� ��
		
		mainPlay.add(itemMove);			//������ panel���� ��� �̹��� �г� ���� ������
		itemMove.add(bulletPanel);
		bulletPanel.add(enemyMove);
		enemyMove.add(userPlay);
		
		add(mainPlay);
		

		
		Timer timer = new Timer();					//Runnable�� ����� ���� �������� �ν��Ͻ� ����
		MovingPlayer player = new MovingPlayer();
		MovingItems moving = new MovingItems();
		InitialItems initial = new InitialItems();
		MovingBullet moveBullet = new MovingBullet();
		MovingEnemy enemy = new MovingEnemy();
		
		ExecutorService ex = Executors.newCachedThreadPool();
		ex.execute(timer);			//������ ����
		ex.execute(player);
		ex.execute(moving);
		ex.execute(initial);
		ex.execute(moveBullet);
		ex.execute(enemy);
		
		setVisible(true);
	
		
		userPlay.requestFocus();							//Ű ������ ���� ��Ŀ���� ����
		userPlay.addKeyListener(new MovingListener());		//�÷��̾��� Ű���� listener �߰�
		
	}
	
	public class MovingListener extends KeyAdapter		//�÷��̾� �̵� listener
	{
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_LEFT)		//����Ű�� �������� �� Ű�� ������������ �˸��� ������ true�� �ٲ�
			{
				keys[0] = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				keys[1] = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				keys[2] = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				keys[3] = true;
			}
			
			if(e.getKeyChar() == ' ')		//�����̽� �ٰ� �������� �Ѿ� �߻�
			{
				int next;
				synchronized(key_b)
				{
					next = bullets.size();
					Bullet temp = new Bullet(user.getLocation());		//�Ѿ� �ν��Ͻ� ����
					bullets.add(temp);
					bulletPanel.add(bullets.get(next));					//panel�� �߰�
				}
			}
		}
		
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_LEFT)					//����Ű���� ���� ���� false�� �ٲ�
			{
				keys[0] = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				keys[1] = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				keys[2] = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				keys[3] = false;
			}
		}
		
	}
	
	public class Timer implements Runnable		//Ÿ�̸� ������
	{
		public void run()
		{
			while(true)
			{
				try 
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e) 
				{
					return;
				}
				
				time--;
				timefield.setText("Time : " + time);
				
				if(time == 0)			//�÷��� Ÿ���� ������ 
				{
					try 
					{
						Thread.sleep(2000);			//2�� ������
					}
					catch(InterruptedException e) 
					{
						return;
					}
					
					break;			//�ݺ��� Ż��� ������ ����
				}
					
			}
			
			new Record(user);		//����� ���Ͽ� ����
			user.setGrade();		//���� ���ھ ���� grade ����
			this_s.setVisible(false);			//�÷��� ȭ���� ����
			new GradeFrame(user.getGrade());	//grade�� ��Ÿ���� ȭ���� �ҷ���
			
		}

	}
	
	public class MovingPlayer implements Runnable	//�÷��̾��� �����ӿ� ���� ������
	{
		public void run()
		{
			while(true)
			{
				try 
				{
					Thread.sleep(15);
				}
				catch(InterruptedException e) 
				{
					return;
				}
				
				Point p = user.getLocation();
				
				if(keys[0])		//���� Ű�� ���������� �������� �̵�
				{
					if(p.x > 0)
					{
						user.setLocation(p.x - user_v, p.y);
					}
		
				}
				else if(keys[1])	//������ Ű�� ���������� ���������� �̵�
				{
					if(p.x < 545)
					{
						user.setLocation(p.x + user_v, p.y);
					}
				}
				if(keys[2])		//���� Ű�� ���������� ���� �̵�
				{
					if(p.y > 0)
					{
						user.setLocation(p.x, p.y - user_v);
					}
				}
				else if(keys[3])	//�Ʒ��� Ű�� ���������� �Ʒ��� �̵�
				{
					if(p.y < 620)
					{
						user.setLocation(p.x, p.y + user_v);
					}
				}
	
				if(time == 0)		//�÷��� Ÿ���� ������ �ݺ��� Ż��, ������ ����
					break;
			}
		}
	}
	
	public class MovingItems implements Runnable	//item�� �����̴� ������
	{
		public void run()
		{
			while(true)
			{
				try 
				{
					Thread.sleep(15);
				}
				catch(InterruptedException e) 
				{
					return;
				}
				
				for(int i=0; i<items.size(); i++)	//item ����Ʈ�� ����ִ� item�� ����ŭ
				{
					if(items.get(i).isvisible())	//item�� ���������ϴ� ��Ȳ�̸� ������
					{
						items.get(i).move();
					}
					if(!items.get(i).isvisible())	//������ ���ƾ��ϴ� ��Ȳ�̸�
					{
						itemMove.remove(items.get(i));	//ȭ��� ����Ʈ���� ����
						synchronized(key_i)
						{
							items.remove(i);
						}
					}
					
					int itx = items.get(i).getLocation().x + (items.get(i).getWidth()/2);
					int ity = items.get(i).getLocation().y + (items.get(i).getHeight()/2);
					Point us = user.getLocation();
					
					if((itx >= us.x && itx <= us.x+user.getWidth())			//item�� �÷��̾��� �����ȿ� ����
							&&(ity >= us.y && ity <= us.y+user.getHeight()))
					{
						user.scoreCalculate(items.get(i).getLabel());	//�ش� item�� ��ũ�� ���� ������ ����
						itemMove.remove(items.get(i));		//ȭ��� ����Ʈ���� ����
						synchronized(key_i)
						{
							items.remove(i);
						}
						
						scorefield.setText("Score : " + user.getTotal());	//���ھ� �ʵ� ����
					}
				}
				
				if(!MidEnemy.isFirst())
				{
					if(!me.death())
					{
						for(int i=0; i<item_mes.size(); i++)		//enemy�� ���� F item�� ������
						{
							if(item_mes.get(i).isvisible())		//���� �ڵ�� ����
							{
								item_mes.get(i).move();
							}
							if(!item_mes.get(i).isvisible())
							{
								itemMove.remove(item_mes.get(i));
								synchronized(key_mes)
								{
									item_mes.remove(i);
								}
							}
							
							int itx = item_mes.get(i).getLocation().x + (item_mes.get(i).getWidth()/2);
							int ity = item_mes.get(i).getLocation().y + (item_mes.get(i).getHeight()/2);
							Point us = user.getLocation();
							
							if((itx >= us.x && itx <= us.x+user.getWidth())
									&&(ity >= us.y && ity <= us.y+user.getHeight()))
							{
								user.scoreCalculate(item_mes.get(i).getLabel());
								itemMove.remove(item_mes.get(i));
								synchronized(key_mes)
								{
									item_mes.remove(i);
								}
								
								scorefield.setText("Score : " + user.getTotal());
							}
						}
					}
				}
				
				if(!FinalEnemy.isFirst())
				{
					if(!fe.death())
					{
						for(int i=0; i<item_fes.size(); i++)		//���� mid enemy�� ����
						{
							if(item_fes.get(i).isvisible())
							{
								item_fes.get(i).move();
							}
							if(!item_fes.get(i).isvisible())
							{
								itemMove.remove(item_fes.get(i));
								synchronized(key_fes)
								{
									item_fes.remove(i);
								}
							}
							
							int itx = item_fes.get(i).getLocation().x + (item_fes.get(i).getWidth()/2);
							int ity = item_fes.get(i).getLocation().y + (item_fes.get(i).getHeight()/2);
							Point us = user.getLocation();
							
							if((itx >= us.x && itx <= us.x+user.getWidth())
									&&(ity >= us.y && ity <= us.y+user.getHeight()))
							{
								user.scoreCalculate(item_fes.get(i).getLabel());
								itemMove.remove(item_fes.get(i));
								synchronized(key_fes)
								{
									item_fes.remove(i);
								}
								
								scorefield.setText("Score : " + user.getTotal());
							}
						}
					}
				}
				
				if(time == 0)			//�÷��� Ÿ���� ������ 
				{
					for(int i=0; i<items.size(); i++)	//ȭ����� ��� item�� ����� 
					{
						itemMove.remove(items.get(i));
					}
					
					items.clear();
					
					break;		//������ ����
				}
			}
		}
		
	}
	
	public class InitialItems implements Runnable		//item ���� ������
	{
		public void run()
		{
			int next = 0;
			Items temp;
			
			Items.initialize();
			
			while(true)
			{
				synchronized(key_i)
				{
					next = items.size();
					temp = new Items(rand.nextInt(2) + rand.nextInt(2) + rand.nextInt(2));		//ABCD�� ���� 1/8, 3/8, 3/8, 1/8�� Ȯ���� ����
					items.add(temp);	//������ �ν��Ͻ��� ����Ʈ�� �߰�
					itemMove.add(items.get(next));	//ȭ�鿡 �߰�
				}
				
				if(!MidEnemy.isFirst())		//mid enemy�� Ȱ��ȭ ���϶� F����
				{
					if(!me.death())
					{
						synchronized(key_mes)
						{
							next = item_mes.size();
							temp = new Items(4);
							temp.setLocation(me.getX() + 30, me.getY() + 45);
							item_mes.add(temp);
							itemMove.add(item_mes.get(next));
						}
					}
				}
				
				if(!FinalEnemy.isFirst())		//final enemy�� Ȱ��ȭ ���϶� F����
				{
					if(!fe.death())
					{
						synchronized(key_fes)
						{
							next = item_fes.size();
							temp = new Items(4);
							temp.setLocation(fe.getX() + 30, fe.getY() + 45);
							item_fes.add(temp);
							itemMove.add(item_fes.get(next));
						}
					}
				}
				
				try 		//0.4�ʿ� �� ���� item ����
				{
					Thread.sleep(400);
				}
				catch(InterruptedException e) 
				{
					return;
				}
				
				if(time == 0)		//�÷��� Ÿ���� ������ ������ ����
					break;
			}
		}
	}
	
	public class MovingBullet implements Runnable		//�Ѿ��� �����̴� ������
	{
		public void run()
		{
			while(true)
			{
				try 
				{
					Thread.sleep(15);
				}
				catch(InterruptedException e) 
				{
					return;
				}
				
				for(int i=0; i<bullets.size(); i++)		
				{
					if(bullets.get(i).isvisible())		//�Ѿ��� ȭ�鿡 �������� �� ���� ������
					{
						bullets.get(i).move();
					}
					if(!bullets.get(i).isvisible())	//ȭ�鿡�� �������� ���ƾ��ϸ� 
					{
						synchronized(key_b)
						{
							bulletPanel.remove(bullets.get(i));		//ȭ��� ����Ʈ���� ����
							bullets.remove(i);
						}
					}
				}
				
				for(int i=0; i<bullets.size(); i++)
				{
					int bx = bullets.get(i).getX();
					int by = bullets.get(i).getY();
					
					if(!MidEnemy.isFirst())
					{
						int mx = me.getX();
						int my = me.getY();
						
						if(!me.death())
						{
							if((bx>=mx && bx<=mx+me.getWidth()) 		//�Ѿ��� enemy�� �����ȿ� ������
									&& (by>=my && by<=my+me.getHeight()))
							{
								me.shotByBullet();			//�Ѿ˿� �¾��� ���� �޼ҵ� ����
								synchronized(key_b)
								{
									bulletPanel.remove(bullets.get(i));		//�Ѿ��� ȭ��� ����Ʈ���� ����
									bullets.remove(i);
								}
							}
							
							if(me.death())		//enemy�� ������
							{
								enemyMove.remove(me);		//ȭ�鿡�� enemy ����
								
								for(int j=0; j<item_mes.size(); j++)		//ȭ�鿡�� F�� ��� ����
								{
									itemMove.remove(item_mes.get(j));
								}
								
								user.scoreCalculate('M');		//�ش� ���ʹ��� ���� �ο�
								scorefield.setText("Score : " + user.getTotal());
							}
						}
						
					}
					
					if(!FinalEnemy.isFirst())		//���� mid�� ����
					{
						int fx = fe.getX();
						int fy = fe.getY();
						
						if(!fe.death())
						{
							if((bx>=fx && bx<=fx+fe.getWidth()) 
									&& (by>=fy && by<=fy+fe.getHeight()))
							{
								fe.shotByBullet();
								bulletPanel.remove(bullets.get(i));
								bullets.remove(i);
							}
							
							if(fe.death())
							{
								enemyMove.remove(fe);
								
								for(int j=0; j<item_fes.size(); j++)
								{
									itemMove.remove(item_fes.get(j));
								}
								
								user.scoreCalculate('T');
								scorefield.setText("Score : " + user.getTotal());
							}
						}
						
					}
				}
				
				if(time == 0)		//�÷��� Ÿ���� ������ ������ ����
				{
					if(!MidEnemy.isFirst())
					{
						if(!me.death())
						{
							for(int j=0; j<item_mes.size(); j++)		//ȭ�鿡�� F�� ��� ����
							{
								itemMove.remove(item_mes.get(j));
							}
						}	
					}
					
					if(!FinalEnemy.isFirst())
					{
						if(!fe.death())
						{
							for(int j=0; j<item_fes.size(); j++)
							{
								itemMove.remove(item_fes.get(j));
							}
						}
					}
				
					break;
				}
			}
			
			
		}
	}

	public class MovingEnemy implements Runnable		//enemy�� �����̴� ������
	{
		public void run()
		{
			while(true)
			{
				try 
				{
					Thread.sleep(15);
				}
				catch(InterruptedException e) 
				{
					return;
				}
					
				if(time == 80 && MidEnemy.isFirst())	//�ð��� 80�� ������ ��, mid�� ���� ������ ���� ������ ����
				{
					me = new MidEnemy();
					enemyMove.add(me);
				}
				
				if(!MidEnemy.isFirst())		//�̹� �ѹ� ���� �Ǿ���
				{
					if(!me.death())			//���� �ʾҴٸ�
					{
						me.move();			//������
					}
				}
				
				if(time == 40 && FinalEnemy.isFirst())		//�ð��� 40�� ���Ұ�, final�� ���� ������ ���� ������ ����
				{
					fe = new FinalEnemy();
					enemyMove.add(fe);
				}
				
				if(!FinalEnemy.isFirst())		//���� ����
				{
					if(!fe.death())
					{
						fe.move();
					}
				}
				
				if(time == 0)
				{
					if(!MidEnemy.isFirst())
					{
						if(!me.death())
						{
							enemyMove.remove(me);
						}
					}
					
					if(!FinalEnemy.isFirst())
					{
						if(!fe.death())
						{
							enemyMove.remove(fe);
						}
					}
					
					break;
				}
			}
		}
	}
}
