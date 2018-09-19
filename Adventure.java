import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;

/* @author Joshua O'Callaghan */
/* Main class for Jal's Adventure*/

public class Adventure extends JFrame
{
	
	// Variable Declaration
	private ImageIcon iconImage;
	private static Adventure Main_Adventure;
	private Backdrop[] backdrops;
	private JButton play, next;
	private JLayeredPane home;
	private int progression, total;
	private	int tracker = 1;
	
	// main method that builds the frame
	public static void main(String[] args) throws IOException
	{
		Main_Adventure = new Adventure();
		Main_Adventure.setVisible(true);
		Main_Adventure.setResizable(false);	
		Main_Adventure.setMinimumSize(new Dimension(1280,770));
		Main_Adventure.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}	
	
	// constructor
	private Adventure()
	{		
		// creates icon for application
		iconImage = new ImageIcon(this.getClass().getResource("images/icon.png"));
		this.setIconImage(iconImage.getImage());
		
		// initialization
		backdrops = new Backdrop[100];
		total = 2;
		progression = 0;
		
		// creates title page
		backdrops[progression] = new Backdrop("title");
		progression++;
		home = backdrops[0].getPane();
		add(home);
		
		// create backdrops
		for (int i = 1; i <= total; i++)
		{
			backdrops[i] = new Backdrop("backdrop" + i);		
		}
		
		// gets buttons from title screen		
		play = backdrops[0].getButton("play");
		play.addActionListener(new PlayListener());
		next = backdrops[1].getButton("next");
		next.addActionListener(new TraverseListener());
	}

	
	private class PlayListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == play)
			{
				// play 
				ButtonClick button = new ButtonClick();
				button.play();
				
				remove(home);
				home = backdrops[1].getPane();
				add(home);
				revalidate();
			}
					
		}
	}
	
	private class TraverseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int totalLines = backdrops[progression].getDialogue().getTotalLines();
			
			if (e.getSource() == next)
			{				
				if (tracker >= totalLines)
				{
					if (progression < total)
					{
						progression++;
						next = backdrops[progression].getButton("next");
						next.addActionListener(new TraverseListener());
						tracker = 0;
						
						remove(home);
						home = backdrops[progression].getPane();
						add(home);
						revalidate();
					}
					
				}
				else
				{
					tracker++;
				}
			}
		}
	}
}
