package ������Ʈ_���ð���;
import javax.swing.*;
import java.awt.*;

public class Bullet extends JLabel		//�Ѿ� Ŭ����
{
	private int velocity = 10;		//move �޼ҵ尡 �� �� ȣ�� �� ������ �Ѿ��� �����̴� �Ÿ�
	private static ImageIcon img = new ImageIcon("image/bullet.jpg");	//�Ѿ� �̹���
	private boolean visible = false;		//���� �ش� �Ѿ��� ȭ�鿡 �������� �־�� �ϴ� �������� �˷���
	
	public Bullet(Point us)
	{
		super(img);
		super.setSize(4,4);
		visible = true;						//ȭ�鿡 �����Ǿ����Ƿ� visiable ������ true�� �Ѵ�
		super.setLocation(us.x+17, us.y);		//�÷��̾��� �߾ӿ��� �Ѿ��� �߻�ǵ��� �Ѵ�
	}
	
	public void move()
	{
		setLocation(this.getX(), this.getY() - velocity);	//�ʱ� ��ġ���� �������θ� �̵�
		
		if(this.getY() < 0)
		{
			visible = false;		//�Ѿ��� ȭ�� ������ ���ư��� ���̻� ȭ�鿡 ���� �ʿ䰡 ������ ����
		}
	}
	
	public boolean isvisible()
	{
		return visible;			//���� �Ѿ��� ���������ϴ� �������� �ƴ����� �˱�����
	}								//visiable�� ��ȯ�ϴ� �Լ�

}
