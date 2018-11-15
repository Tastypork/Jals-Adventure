import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Backdrop 
{
	private JLayeredPane main_layer;
	private ImageIcon soundImage, sound1Image, sound2Image,sound3Image;
	private JButton play, settings, quit, sound, next;
	private JLabel imageLabel;
	private JLabel charTalk;
	private String backdropName;
	private BackgroundMusic bgm;
	private Dialogue d;
	
	public Backdrop(String name)
	{
		if(name.equals("title"))
		{
			// variable initialization
			backdropName = name;
			bgm = new BackgroundMusic();
			main_layer = new JLayeredPane();			
			imageLabel = new JLabel(getImage("images/title.png"));
			imageLabel.setBounds(0, 0, 1280, 720);
			
			soundImage = getImage("images/sound.png");
			sound1Image = getImage("images/sound1.png");
			sound2Image = getImage("images/sound2.png");
			sound3Image = getImage("images/sound3.png");
			
			// button creation
			play = new JButton(getImage("images/play.png"));
			play.setBounds(532,300,215,75);
			play.addActionListener(new PlayListener());
			
			/*settings = new JButton(getImage("images/settings.png"));
			settings.setBounds(532, 362, 215, 75);*/
			
			quit = new JButton(getImage("images/quit.png"));
			quit.setBounds(532, 400, 215, 75);
			quit.addActionListener(new CloseListener());
			
			sound = new JButton(soundImage);
			sound.setBounds(1220, 10, 45, 45);
			sound.addActionListener(new SoundListener());
			
			// add modules to frame
			main_layer.add(imageLabel, new Integer(0));
			main_layer.add(play, new Integer(1));
			//main_layer.add(settings, new Integer(1));
			main_layer.add(quit, new Integer(1));
			main_layer.add(sound, new Integer(1));
			
			bgm.play();
		}
		/*else if(name.equals("settings"))
		{
			imageLabel = new JLabel(getImage("images/settings.png"));
			imageLabel.setBounds(0, 0, 1280, 720);
		}*/
		else
		{
			// variables
			Font font = new Font("SansSerif", Font.BOLD, 32);
			
			// find the image and set bounds
			String append = "backdrops/" + name + ".png";
			imageLabel = new JLabel(getImage(append));
			imageLabel.setBounds(0, 0, 1280, 720);
			
			// retrieve first text line and set bounds
			d = new Dialogue(name);
			charTalk = new JLabel(d.getCurrentLine(), SwingConstants.CENTER);
			charTalk.setBounds(100, 575, 1100, 100);
			charTalk.setFont(font);
			charTalk.setForeground(Color.WHITE);
			
			// add buttons
			next = new JButton(getImage("images/next.png"));
			next.setBounds(550, 650, 200, 50);
			next.addActionListener(new TraverseListener());

			main_layer = new JLayeredPane();
			main_layer.add(imageLabel, new Integer(0));
			main_layer.add(charTalk, new Integer(1));
			main_layer.add(next, new Integer (2));
			
			
			
		}
	}
	
	public Dialogue getDialogue()
	{
		return d;
	}
	
	public JLayeredPane getPane()
	{
		return main_layer;
	}
	
	public JButton getButton(String button)
	{
		if (button.equals("play"))
		{
			return play;
		}
		else if (button.equals("next"))
		{
			return next;
		}
		/*else if (button.equals("settings"))
		{
			return settings;
		}*/
		else
		{
			return quit;
		}
	}

	private class PlayListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			bgm.stop();	
		}
	}
	
	private class CloseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ButtonClick button = new ButtonClick();
			button.play();
			System.exit(0);
		}
	}
	
	private class SoundListener implements ActionListener
	{
		int tracker = 0;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ButtonClick button = new ButtonClick();
			button.play();
			
			if (tracker == 0)
			{
				bgm.setValue(-15.0f);
				sound.setIcon(sound1Image);
				tracker++;
			}
			else if (tracker == 1)
			{
				bgm.setValue(-25.0f);
				sound.setIcon(sound2Image);
				tracker++;
			}
			else if (tracker == 2)
			{
				bgm.setValue(-60.0f);
				sound.setIcon(sound3Image);
				tracker++;
			}
			else if (tracker == 3)
			{
				bgm.setValue(-0.0f);
				sound.setIcon(soundImage);
				tracker = 0;
			}
		}
	}
	
	private class TraverseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ButtonClick button = new ButtonClick();
			button.play();
			
			if (e.getSource() == next)
			{
				charTalk.setText(d.getNextLine());
			}
		}
	}
	
	private ImageIcon getImage(String path)
	{
		ImageIcon temp_img = new ImageIcon(this.getClass().getResource(path));
		
		return temp_img;
	}
}
