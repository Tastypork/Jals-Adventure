
import javax.sound.sampled.*;

public class ButtonClick 
{
	public void play()
	{
		try
		{
			AudioInputStream ais1 = AudioSystem.getAudioInputStream(this.getClass().getResource("music/button.wav"));
			
			Clip buttonClick = AudioSystem.getClip();
			buttonClick.open(ais1);
			buttonClick.start();
		}
		catch(Exception f)
		{
			f.printStackTrace();
		}
	}
}
