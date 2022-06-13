package ������Ʈ_���ð���;
import javax.swing.*;

import java.awt.*;

public class GradeFrame extends JFrame		//���� ���ھ ���� ����� �����ִ� ȭ��
{
	private MainPanel bg;
	private JLabel title = new JLabel("Your grade is");		//grade�� �Բ� ������ ����
	private JLabel grade;				//grade�� ǥ���� label
	private JLabel comment;
	
	public GradeFrame(char res)		//PlayGameFrame���� �÷��̾��� ���� ����� �Ѱܹ޴´�
	{
		setTitle("Shooting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(4*150, 5*150);
		
		bg = new MainPanel();		//���ȭ�� �̹����� ������ panel
		bg.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 150));	//panel�� ���̾ƿ��� flow�� ����, ���� ������ gap�� ���� 30 150���� ����
		
		title.setFont(new Font("", Font.PLAIN, 60));		//title�� ��Ʈ ����
		
		grade = new JLabel("" + res);
		grade.setFont(new Font("", Font.BOLD, 80));			//grade�� ��Ʈ ����
		
		bg.add(title);		//title�� grade�� panel�� ����
		bg.add(grade);
		
		comment = new JLabel();
		
		switch(res)			//grade�� ���� ���� �ٸ� ��Ʈ�� �Բ� �ִ´�
		{
			case 'A' :
				comment.setText("Great!!");
				break;
			case 'B' :
				comment.setText("Good!");
				break;
			case 'C' :
				comment.setText("So-So");
				break;
			case 'D' :
				comment.setText("Poor!");
				break;
			case 'F' :
				comment.setText("Terrible!!");
				break;
		}
		
		comment.setFont(new Font("", Font.PLAIN, 60));		//��Ʈ�� panel�� ����
		bg.add(comment);
		
		add(bg);			//panel�� frame�� ����
		
		setVisible(true);	//ȭ���� ������
	}
}
