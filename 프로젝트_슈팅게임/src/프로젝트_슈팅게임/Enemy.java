package ������Ʈ_���ð���;
import javax.swing.*;

public class Enemy extends JLabel		//enemy Ŭ���� //mid enemy�� final enemy�� super class
{
	private int count = 0;				//�Ѿ��� �� �� �¾Ҵ��� ���� ����
	private int direct = -1; 			//�ش� enemy�� �̵� ����
	private int velocity = 8;			//move �޼ҵ尡 ȣ��� ������ �����̴� �Ÿ�
	
	
	public Enemy(ImageIcon img)
	{
		super(img);
		super.setLocation(600, 0);
		super.setSize(60, 90);
	}
	
	public void move()
	{
		if(direct == 1)		//���������� �̵�
			setLocation(this.getLocation().x + velocity, this.getLocation().y);
		else if(direct == -1)		//�������� �̵�
			setLocation(this.getLocation().x - velocity, this.getLocation().y);
		
		if(this.getLocation().x >= 490)		//enemy�� ȭ���� ����� �̵� ������ �ٲ۴�
			direct = -1;
		else if(this.getLocation().x <= 0)
			direct = 1;
	}
	
	public void shotByBullet()		//�Ѿ��� �¾��� �� count�� ���������ش�
	{
		count++;
	}
	
	public int getCount()			//�Ѿ��� �� �� �¾Ҵ����� ��ȯ
	{
		return this.count;
	}
	

}
