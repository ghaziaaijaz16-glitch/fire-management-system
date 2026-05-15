import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Main extends Frame implements ActionListener{
	
	
	BufferedImage bi ;

	
	
	public Main(){
		
		this.setTitle("Emergency System");
		this.setSize(1200, 800);
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		try {
		   
			bi = ImageIO.read(new File("D:/2nd sem/CEPProject/images/bg1.jpg"));
			
		} catch(IOException e) {
			
		}
//			setResizable(false);
			setVisible(true);
			
			
		
		
	
		
		
	
		
		GridLayout gl = new GridLayout(2,1);
		
		
		
		Panel left = new Panel() {
			public void paint(Graphics g) {
				super.paint(g);
				Font f = new Font("Arial", 3, 25);
			    g.setFont(f);
			    g.setColor(Color.white);
				g.drawString("Welcome to Emergency Management System", 290, 80 );
				Font fn = new Font("Arial", 3, 18);
				g.setColor(Color.lightGray);
				g.setFont(fn);
				
				g.drawString("Saving Lives, Every Second Counts", 400, 120 );
				
				

				
			}
		};
		left.setBackground(new Color(135, 0, 0));
		left.setLayout(null);
		
		Button req = new Button("Emergency Request");
		Font fb = new Font("Arial", Font.ITALIC, 19);
		req.setFont(fb);
		req.setSize(30, 30);
		req.setBounds(440, 150, 220, 80);
		req.setBackground(Color.white);
		req.setForeground(Color.RED);
		left.add(req);
		
		req.addActionListener(this);
		
		
	
		
		
		Panel right = new Panel() {
		    public void paint(Graphics g) {
		        super.paint(g);
		        g.drawImage(bi, 0, 0, getWidth(), getHeight(), null);
		    }
		};
		this.setLayout(gl);
		this.add(right);
		this.add(left);
		
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		new EmergencyReq();
	}
	
	
	
	public static void main(String[] args)   {
		
	Main My = 	new Main();
	}
}
