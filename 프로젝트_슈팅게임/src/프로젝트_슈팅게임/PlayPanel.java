package ������Ʈ_���ð���;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlayPanel extends JPanel		//��� �̹����� ���̱� ���� �÷��� panel
{
	
	ImageIcon main_bg = new ImageIcon("image/play.jpg");
	Image img = main_bg.getImage();
		
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
