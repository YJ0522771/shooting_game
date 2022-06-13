package ������Ʈ_���ð���;
import javax.swing.*;
import java.security.SecureRandom;

public class Items extends JLabel		//item(ź��) Ŭ����
{
	private static final char[] labels = {'A', 'B', 'C', 'D', 'F'};		//��� item �ν��Ͻ��� ���Ǵ� index�� ��ũ
	private static ImageIcon[] imgs = new ImageIcon[5];					//�������� �����Ǵ� index�� ���� �̹����� �����ǹǷ� ������ �̹��� �ν��Ͻ��� �迭�� ����
	private int index;				//�ν��Ͻ� ������ �Բ� �������� �����Ǵ� ��
	private int velocity = 0;		//�ν��Ͻ� ������ �Բ� �������� ����
	
	private boolean visible = false;		//���� �ش� item�� ���������ϴ� ��Ȳ���� ��Ÿ��
	
	private SecureRandom rand = new SecureRandom();
	
	
	public Items(int random)
	{
		super(imgs[random]);	//�ν��Ͻ� ���� �� �������� ���� ���� index�� �����ϰ� �ش� �̹����� �־� �ν��Ͻ� ����
		this.index = random;	
		this.velocity = rand.nextInt(3)+1;	//�ӵ��� 1~3 �� �������� ����
		setSize(20,20);
		setLocation(rand.nextInt(570), 0);	//�������� �ʱ� x��ǥ�� �������� ����
		visible = true;					//���� �ش� item�� ȭ�鿡 �������� ���� ��Ÿ��
	}
	
	public static void initialize()			//�̹��� �迭�� �ʱ�ȭ�ϴ� static�޼ҵ�
	{
		imgs[0] = new ImageIcon("image/A.png");
		imgs[1] = new ImageIcon("image/B.png");
		imgs[2] = new ImageIcon("image/C.png");
		imgs[3] = new ImageIcon("image/D.png");
		imgs[4] = new ImageIcon("image/F.png");
	}
	
	public void move()
	{
		setLocation(this.getX(), this.getY()+velocity);
		
		if(this.getY() > 750)			//item�� ȭ�� �� �ر��� �������� ȭ�鿡 �������� ���ƾ����� �˸�
		{
			visible = false;
		}
	}
	
	public boolean isvisible()			//���� ȭ�鿡 �������ϴ� ���������� ��ȯ
	{
		return visible;
	}
	
	public char getLabel()				//�ش� �ν��Ͻ��� � ��ũ���� index�� �ش��ϴ� ��ũ�� ��ȯ
	{
		return labels[index];
	}

}
