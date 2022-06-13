package ������Ʈ_���ð���;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame		//���α׷��� �����ϸ� ������ ùȭ��
{
	private String name;		//����ڷκ��� �̸��� �Է� ���� ����
	private MainPanel bg;		//frame�� panel
	private JButton start;		//game start button
	private JButton record;		//���ݱ����� top10����� ���� ȭ������ ���� button
	private JTextField input;	//�̸��� �Է��� textfield
	private JFrame main;		//�� �������� this �����͸� ������ ��
	private JFrame insert;		//�̸��� �Է� ���� â
	
	public MainFrame()
	{
		setTitle("Shooting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(4*150, 5*150);
		
		bg = new MainPanel();		//��� �̹����� ���� panel
		
		start = new JButton("Start Game");	//�� button�� �ν��Ͻ� ����
		record = new JButton("Show Record");
		
		bg.setLayout(null);		//�� component�� ��ǥ������ ���̱� ���� ���̾ƿ��� null�� ����
		
		start.setSize(this.getWidth()/3, this.getHeight()/15);		//�� button�� ũ��� ��ġ ����
		start.setLocation((this.getWidth()-start.getWidth())/2-10, this.getHeight()/2);
		
		record.setSize(this.getWidth()/3, this.getHeight()/15);
		record.setLocation((this.getWidth()-start.getWidth())/2-10, start.getLocation().y + start.getHeight() + 10);
		
		Font font = new Font("", Font.BOLD, 20);
		start.setFont(font);		//�� button�� ��Ʈ ����
		record.setFont(font);
		
		bg.add(start);		//button���� panel�� �߰�
		bg.add(record);

		add(bg);			//panel�� frame�� �߰�
		
		main = this;		//listener���� this�����͸� ����� �� �ְ� ������ ����
		
		start.addActionListener(new NameListener());		//�� button ���� listener�� �߰�
		record.addActionListener(new RecordListener());
	
		
	}
	
	public class NameListener implements ActionListener		//start button�� ������ ���� listener
	{
		public void actionPerformed(ActionEvent e) 
		{
			insert = new JFrame();
			insert.setTitle("Player Name");		//�̸� �Է��� �޴� frame�� �ν��Ͻ� ����
			
			JLabel text = new JLabel("\nPlease insert your name\n");	//�̸� �Է��� ���ϴ� ����
			input = new JTextField(20);
			JButton ok = new JButton("Enter");		//�̸� �Է��� ������ ������ �����ϴ� button
			
			insert.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));		//frame�� ���̾ƿ��� flow�� ����
			insert.add(text);		//����, textfield, button�� frame�� �߰�
			insert.add(input);
			insert.add(ok);
			
			insert.setSize(300, 200);
			insert.setVisible(true);	//ȭ�鿡 ��Ÿ��
			
			ok.addActionListener(new StartGameListener());	//button�� ������ ���� listener �߰�
			
		}
	}
	
	public class StartGameListener implements ActionListener	//������ �����ϴ� listener
	{
		public void actionPerformed(ActionEvent e)
		{
			main.setVisible(false);			//mainframe�� �̸� �Է�â�� ȭ�鿡�� ����
			insert.setVisible(false);
			new PlayGameFrame(input.getText());		//���� �̸��� ���ڷ� �Ѱ��ָ� ���� ȭ���� �ҷ���
		}
		
	}
	
	public class RecordListener implements ActionListener	//record button�� ������ ���� listener
	{
		public void actionPerformed(ActionEvent e)
		{
			main.setVisible(false);		//mainframe�� ȭ�鿡�� �����
			new RecordFrame();			//recordframe�� �ҷ���
		}
	}

}
