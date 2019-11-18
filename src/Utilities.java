//WillSease
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utilities 
{
	public Utilities() {}
	public KeyListener getKeyStrokes(Client c)
	{
		KeyListener out = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_UP) c.keysDown[0] = true;
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) c.keysDown[1] = true;
				if(e.getKeyCode() == KeyEvent.VK_DOWN) c.keysDown[2] = true;
				if(e.getKeyCode() == KeyEvent.VK_LEFT) c.keysDown[3] = true;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_UP) c.keysDown[0] = false;
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) c.keysDown[1] = false;
				if(e.getKeyCode() == KeyEvent.VK_DOWN) c.keysDown[2] = false;
				if(e.getKeyCode() == KeyEvent.VK_LEFT) c.keysDown[3] = false;
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		return out;
	}
	
	public BufferedImage[] getImgs()
	{
		BufferedImage[] out = new BufferedImage[2];
		File folder = new File("images");
		File[] folderCont = folder.listFiles();
		for (int i = 0; i < folderCont.length; i++) {
			try {
				out[i] = (ImageIO.read(folderCont[i]));
				//System.out.println("got" + folderCont[i].getName());
			} catch (IOException e) {
				System.err.println("ImageIO Failure!");
			}
		}
		return out;
	}
	
	public static int[][] boardToInts(BufferedImage image, ImageObserver obs) 
	{
		int[][] out = new int[7500][5000];
		for (int i = 0; i < out.length; i++)
			for (int j = 0; j < out[i].length; j++)
			{
				out[i][j] = image.getRGB(i, j);
			}
		return out;
	}
	
	public double[] getLookupTable(String in)
	{
		double[] out = new double[360 * 100];
		for(int i = 0; i < out.length; i++)
		{
			//System.out.println(i + (i + 360 * 4));
			if(in.equals("sin")) out[i] = Math.sin((((double)i) / 100) * 0.0174533);
			if(in.equals("cos")) out[i] = Math.cos((((double)i) / 100) * 0.0174533);
		}
		return out;
	}
}
