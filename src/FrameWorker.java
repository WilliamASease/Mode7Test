//WillSease
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameWorker extends JFrame
{
	RenderWindow painter;
	
	@SuppressWarnings("static-access")
	public FrameWorker(Client c)
	{
		super();
		this.setSize(900, 900);
		this.setLocation(100, 100);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		painter = new RenderWindow(c);
		this.add(painter);
		painter.setVisible(true);
		painter.setSize(this.getWidth(), this.getHeight());
		this.setVisible(true);
	}
	
	public void invoke()
	{
		painter.repaint();
	}
}
