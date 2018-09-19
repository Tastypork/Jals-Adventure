import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Dialogue 
{
		// variables
		private String currentLine;
		private int progression, total;
		private String[] characterLines;
		
		// takes the parameter given and finds the text file
		public Dialogue(String textFile)
		{	
			String append = "speech/" + textFile + ".txt";
			InputStream file = this.getClass().getResourceAsStream(append);
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));
			
			characterLines = new String[100];
			total = 0;
			progression = 0;
			
			String st;
		    try 
		    {
		    	while ((st = reader.readLine()) != null)
			    {
		    		characterLines[total] = st;
					total++;
			    }
		    } 
		  
		    catch (IOException e) 
		    {
		    	e.printStackTrace();
		    }
		    
		    currentLine = characterLines[0];
		    
			/*try (Scanner scanner = new Scanner(file))
			{
				// stores each line in an array
				while(scanner.hasNextLine())
				{
					characterLines[total] = scanner.nextLine();
					total++;
				}
				scanner.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}*/
			
			//currentLine = characterLines[0];
			
		}
		
		//  gets the next line in the array;
		public String getNextLine()
		{
			if(progression < total)
			{
				progression++;
				currentLine = characterLines[progression];
				return currentLine;
			}
			else
			{
				return "invalid";
			}
		}
		
		public String getPreviousLine()
		{
			if(progression > 0)
			{
				progression--;
				currentLine = characterLines[progression];
				return currentLine;
			}
			else
			{
				return "invalid";
			}
		}
		
		public String getCurrentLine()
		{
			return currentLine;
		}
		
		public int getTotalLines()
		{
			return total;
		}

}
