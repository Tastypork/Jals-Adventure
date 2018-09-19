import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BackgroundMusic 
{
	private AudioInputStream ais;
	private Clip clip;
	private FloatControl gainControl;	
	
	public void play()
	{
		// plays background music
				try
				{
					ais = AudioSystem.getAudioInputStream(this.getClass().getResource("music/bgm.wav"));
					
					clip = AudioSystem.getClip();
					clip.open(ais);
					gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-5.0f);
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
	}
	
	public void stop()
	{
		clip.stop();
	}
	
	public void setValue(float f)
	{
		gainControl.setValue(f);
	}
}
