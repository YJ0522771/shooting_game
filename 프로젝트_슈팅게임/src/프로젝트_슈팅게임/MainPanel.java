package ������Ʈ_���ð���;
import javax.swing.*;

import java.awt.*;

public class MainPanel extends JPanel	//�̹����� ���� mainȭ�� panel
{
	ImageIcon main_bg = new ImageIcon("image/main.jpg");
	Image img = main_bg.getImage();
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
