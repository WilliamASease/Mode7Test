import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderWindow extends JPanel
{
	//Consts for your tinkering pleasure.
	int MINI_MAP_SCALAR = 40;
	
	Client c;
	BufferedImage[] imgs = null;
	int[][] board = null;
	int[][] viewport = new int[900][700];
	
	public RenderWindow(Client c)
	{
		this.c = c;
	}
	
	public void paint(Graphics g)
	{
		if (imgs == null) imgs = c.imgs;
		if (board == null) board = Utilities.boardToInts(imgs[0], (ImageObserver)this);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 900, 900);
		g.drawImage(imgs[1], 0, 0, this);
		g.setColor(Color.BLACK);
		g.drawString(c.playerPos[0] + " " + c.playerPos[1] + " " + c.playerOrient + " FPS: " + c.fpscntprnt, 50, 50);
		
		//int[][] newBoard = Utilities.newBoard(board, c.playerOrient);
		//Render Viewport
				int GREEN = imgs[0].getRGB(0, 0);
				for (int y = 0; y < 450; y++)
					for (int x = 0; x < 900; x++)
					{
						double pos[] = {(c.playerPos[0] + ((double)x - (double)450))
								,(c.playerPos[1] - (((double)450 - (double)y) * 5))};
						
						pos[0] -= c.playerPos[0];
						pos[1] -= c.playerPos[1];
						double oldX = pos[0];
						
						pos[0] = pos[0]  * c.cosLookupTable[(int)(360 * 100- c.playerSubOrient * 100) - 1] - pos[1] * c.sinLookupTable[(int)(360 * 100 -c.playerSubOrient * 100-1)];
						pos[1] = pos[1]  * c.cosLookupTable[(int)(360 * 100- c.playerSubOrient * 100) - 1] + oldX * c.sinLookupTable[(int)(360 * 100 -c.playerSubOrient * 100-1)];
						
						//pos[0] = pos[0]  * c.cosLookupTable[(int)(c.playerOrient * 16)] - pos[1] * c.sinLookupTable[(int)(c.playerOrient * 16)];
						//pos[1] = pos[1]  * c.cosLookupTable[(int)(c.playerOrient * 16)] + oldX * c.sinLookupTable[(int)(c.playerOrient)];
						
						pos[0] += c.playerPos[0];
						pos[1] += c.playerPos[1];
						
						if (pos[0] < 0 || pos[0] >= 7500 || pos[1] < 0 || pos[1] >= 5000)
							viewport[x][y] = 2;
						else if(board[(int)pos[0]][(int)pos[1]] == GREEN)
							viewport[x][y] = 0;
						else viewport[x][y] = 1;
					}
				//Draw Viewport.
				for (int i = 0; i < 900; i++)
					for (int j = 0; j < 450; j++)
					{
						if(viewport[i][j] == 0) g.setColor(Color.GREEN);
						else if (viewport[i][j] == 1) g.setColor(Color.RED);
						else g.setColor(Color.BLUE);
						g.drawRect(i, j + 450, 1, 1);
					}
		
				
				//Draw Mini-map
		for (int i = 0; i < board.length/MINI_MAP_SCALAR; i++)
			for (int j = 0; j < board[j].length/MINI_MAP_SCALAR; j++)
			{
				if(board[i*MINI_MAP_SCALAR][j*MINI_MAP_SCALAR] == imgs[0].getRGB(0, 0)) g.setColor(Color.GREEN);
				else g.setColor(Color.RED);
				g.fillRect(500 + i, 50 + j, 1, 1);
			}
		g.setColor(Color.blue);
		g.fillRect(500 + c.playerPos[0] / MINI_MAP_SCALAR, 50 + c.playerPos[1] / MINI_MAP_SCALAR, 4, 4);
	}
}
