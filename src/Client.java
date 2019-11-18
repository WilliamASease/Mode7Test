//I'm going to rewrite this with sensible design
//And do something cool with the concept.
//William Sease 20/31/2019 Hello
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Client 
{
	
	final double MOVESPEED = .00002;
	double[] playerSubPos = {3000, 2500};
	int[] playerPos = {3000, 2500};
	int playerOrient = 0;
	double playerSubOrient = 0;
	long fpstime;
	int fpscnt;
	int fpscntprnt;
	boolean[] keysDown = new boolean[4];
	boolean changed = false;
	FrameWorker f;
	Utilities util = new Utilities();
	double[] sinLookupTable = util.getLookupTable("sin");
	double[] cosLookupTable = util.getLookupTable("cos");
	KeyListener keyStrokes = util.getKeyStrokes(this);
	BufferedImage[] imgs = util.getImgs();
	
	public Client()
	{
		System.out.println("START");
		f = new FrameWorker(this);
		f.addKeyListener(keyStrokes);
		
		while(true)
		{
			if(System.currentTimeMillis() - fpstime > 1000)
			{
				fpscntprnt = fpscnt;
				fpscnt = 0;
			}
			if(fpscnt == 0)
			{
				fpstime = System.currentTimeMillis();
				fpscnt = 0;
			}
			if(keysDown[0]) moveForward();
			if(keysDown[1]) turnRight();
			if(keysDown[2]) moveBackward();
			if(keysDown[3]) turnLeft();
			/*playerSubOrient= playerSubOrient + .000001;
			if (playerSubOrient < 0) playerSubOrient = 360;
			if (playerSubOrient >= 360) playerSubOrient = 0;
			playerOrient = (int)playerSubOrient;
			if (playerOrient > 360) playerOrient = 0;*/
		    f.invoke();
		    fpscnt++;
		    
			//System.out.println(Math.sin((double)playerOrient * 0.0174533));
			//System.out.println("VERTPOS= " + playerPos[0] + " HORZPOS= " + playerPos[1] + " ORIENT= " + playerOrient);
		}
	}
	
	private void moveForward() {
		playerSubPos[0] -= (MOVESPEED * Math.sin((double)playerOrient * 0.0174533));
		playerSubPos[1] -= (MOVESPEED * Math.cos((double)playerOrient * 0.0174533));
		playerPos[0] = (int) playerSubPos[0];
		playerPos[1] = (int) playerSubPos[1];
		//f.invoke();
	}

	private void moveBackward() {
		playerSubPos[0] += MOVESPEED * Math.sin((double)playerOrient * 0.0174533);
		playerSubPos[1] += MOVESPEED * Math.cos((double)playerOrient * 0.0174533);
		playerPos[0] = (int) playerSubPos[0];
		playerPos[1] = (int) playerSubPos[1];
		//f.invoke();
	}

	private void turnRight() {
		playerSubOrient -= .000008;
		if (playerSubOrient < 0) playerSubOrient = 359.999999;
		playerSubOrient %= 360;
		playerOrient = (int) Math.abs(playerSubOrient);
		//f.invoke();
	}

	private void turnLeft() {
		playerSubOrient += .000008;
		if (playerSubOrient < 0) playerSubOrient = 359.999999;
		playerSubOrient %= 360;
		playerOrient = (int) Math.abs(playerSubOrient);
		//f.invoke();
	}

	public static void main(String[] args)
	{
		Client c = new Client();
	}
}
