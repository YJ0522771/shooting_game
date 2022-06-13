package ������Ʈ_���ð���;
import java.util.*;
import java.io.*;
import java.nio.file.*;;

public class Record 	//����� ���Ͽ� ���
{
	private ArrayList<Player> list = new ArrayList<Player>();	//���Ͽ� ������ ��� ������ѵ� ����Ʈ
	private static ObjectInputStream input;
	private static ObjectOutputStream output;
	
	public Record(Player user)
	{
		try
		{
			input = new ObjectInputStream(Files.newInputStream(Paths.get("record.ser")));	//�б���� ������ ����
		}
		catch(IOException ioe)
		{
			try		//������ ���� �������� ������
			{
				output = new ObjectOutputStream(Files.newOutputStream(Paths.get("record.ser")));	//�����带 ���� ���� ����
				output.close();
			}
			catch(IOException ioe2)
			{
				System.err.println();
			}
			
			try
			{
				input = new ObjectInputStream(Files.newInputStream(Paths.get("record.ser")));		//�ٽ� �б� ���� ����
			}
			catch(IOException ioe2)		//�׷��� �� �Ǹ� ���� ���� ���
			{
				System.err.println("Error opening file.");
			}
			
		}
		
		
		try
		{
			while(true)		//eof���ܸ� ���� ���� ������ �о� ����Ʈ�� ����
			{
				Player temp = (Player) input.readObject();
				
				list.add(temp);
			}
		}
		catch(EOFException eof)
		{
			
		}
		catch(ClassNotFoundException cnf)
		{
			System.err.println("Invaild object type.");
		}
		catch(IOException ioe)
		{
			System.err.println("Error reading from file.");
		}
		
		try
		{
			if(input != null)		//�б� ������ �� ������ ����
				input.close();
		}
		catch(IOException ioe)
		{
			System.err.println("Error closing file.");
		}
		
		try
		{
			output = new ObjectOutputStream(Files.newOutputStream(Paths.get("record.ser")));	//���� ���� ������ ����
		}
		catch(IOException ioe)
		{
			System.err.println("Error opening file.");
		}
		
		int i;
		
		for(i=0; list.size()!=0 && i<list.size(); i++)	//���Ͽ��� �� �о���� ����Ʈ�� �̹� sorting�� �� �����̹Ƿ�
		{
			if(user.getTotal() > list.get(i).getTotal())	//���� �÷����� ������ ������ ��ġ�� ã��
			{
				break;
			}
		}
		
		list.add(i, user);		//�ش� ��ġ�� ����
		
		try
		{
			if(list.size() >= 10)	//����Ʈ �� �ִ� 10���� ���Ͽ� ����
			{
				for(i=0; i<10; i++)
				{
					output.writeObject(list.get(i));
				}
			}
			else
			{
				for(i=0; i<list.size(); i++)
				{
					output.writeObject(list.get(i));
				}
			}
		}
		catch(IOException ioe)
		{
			System.err.println();
		}
		
		try
		{
			if(output != null)		//������ ����
				output.close();
		}
		catch(IOException ioe)
		{
			System.err.println("Error closing file.");
		}
	}

}
