package ������Ʈ_���ð���;

import javax.swing.ImageIcon;

public class MidEnemy extends Enemy		//���� ���� 80�� ���� ��Ÿ���� enemy
{
	private static ImageIcon img = new ImageIcon("image/MID.jpg");
	private static final int life = 25;		//mid enemy�� �� �� �Ѿ��� �¾ƾ� �Ҹ�Ǵ����� ��Ÿ��
	private static boolean First = true;	//�ش� enemy �ν��Ͻ��� �̹� ������� ���� �ִ����� ��Ÿ�� true�� ���� ������� ���� ���ٴ� �ǹ�
	
	public MidEnemy()
	{
		super(img);
		First = false;		//�ش� enemy�� �����Ǿ����Ƿ� ó���� �ƴ��� ����
	}
	
	public boolean death()		//�ش� enemy�� �Ҹ��ؾ��ϴ��� ���θ� ��Ÿ��
	{
		if(super.getCount() >= this.life)		//���� life�̻����� ������ �Ҹ��ؾ����� �˸�
			return true;
		else
			return false;
	}
	
	public static boolean isFirst()		//�ش� enemy�� �ν��Ͻ��� ������ ó������ �ƴ����� ��ȯ
	{
		return First;
	}

}
