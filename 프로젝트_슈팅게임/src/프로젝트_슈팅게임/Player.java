package ������Ʈ_���ð���;
import javax.swing.*;

public class Player extends JLabel
{
	private int totalscore;		//�÷��̾��� �������ھ�
	private String name;		//�̸�
	private char grade;			//�������ھ ���� grade
	private static ImageIcon user = new ImageIcon("image/player.png");
	private final int SCORE_A = 2;		//�� item�� ���� ȹ�� ����
	private final int SCORE_B = 1;
	private final int SCORE_C = -1;
	private final int SCORE_D = -2;
	private final int SCORE_MID = 20;	//enemy�� ����� ��� ��� ����
	private final int SCORE_FINAL = 40;
	
	public Player(String name)
	{
		super(user);
		super.setSize(40, 40);
		this.totalscore = 0;		//���ھ� �ʱ�ȭ
		this.name = name;			//�Ѱܹ��� �̸��� �÷��̾� �̸����� ����
	}
	
	public void scoreCalculate(char event)		//�� item�� ���� ��� �޼ҵ�
	{
		if(event == 'A')
			this.totalscore += SCORE_A;
		else if(event == 'B')
			this.totalscore += SCORE_B;
		else if(event == 'C')
			this.totalscore += SCORE_C;
		else if(event == 'D')
			this.totalscore += SCORE_D;
		else if(event == 'F')
			this.totalscore = 0;
		else if(event == 'M')
			this.totalscore += SCORE_MID;
		else if(event == 'T')
			this.totalscore += SCORE_FINAL;
	}
	
	public int getTotal()		//���� ���ھ� ��ȯ
	{
		return this.totalscore;
	}
	
	public String getName()		//�̸� ��ȯ
	{
		return this.name;
	}
	
	public void setGrade()		//���� ���ھ ���� grade ��ȯ
	{
		if(this.totalscore >= 160)
			this.grade = 'A';
		else if(this.totalscore >= 120)
			this.grade = 'B';
		else if(this.totalscore >= 80)
			this.grade = 'C';
		else if(this.totalscore >= 40)
			this.grade = 'D';
		else
			this.grade = 'F';
	}
	
	public char getGrade()		//grade ��ȯ
	{
		return this.grade;
	}
}
