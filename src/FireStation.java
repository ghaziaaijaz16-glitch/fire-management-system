import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FireStation extends Frame {

    BufferedImage b2, b1, bi, b3;

    public FireStation() {

        setSize(1200, 800);
        setLayout(new BorderLayout());
        setTitle("Fire Station Management");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        try {
            b2 = ImageIO.read(new File("images/fire.jpg"));
            b1 = ImageIO.read(new File("images/bg.jpg"));
            b3 = ImageIO.read(new File("images/b3.jpg"));
            bi = ImageIO.read(new File("images/ff.jpg"));
        } catch (IOException e) {
            System.out.println("Image error");
        }

        Panel header = new Panel();
        
        header.setBackground(new Color(180, 0, 0));

        Label title = new Label("Fire Station ", Label.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        header.add(title);
        add(header, BorderLayout.NORTH);

        Panel center = new Panel() {
            public void paint(Graphics g) {
                super.paint(g);

                int w = getWidth();
                int h = getHeight();
                
                g.setFont(new Font("Serif",Font.ITALIC, 25));
                g.setColor(Color.WHITE);
                
                g.drawString("Professional firefighters & rapid-response system", 360, 30);

                int imgW = w / 2;
                int imgH = (h - 60) / 2;

                g.drawImage(bi, 0, 60, imgW, imgH, null);
                g.drawImage(b1, imgW, 60, imgW, imgH, null);
                g.drawImage(b2, 0, 60 + imgH, imgW, imgH, null);
                g.drawImage(b3, imgW, 60 + imgH, imgW, imgH, null);
            }
        };

        center.setBackground(new Color(180, 0, 0));
        add(center, BorderLayout.CENTER);


        Panel footer = new Panel();

        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        footer.setBackground(new Color(180,0,0));

        Button btnff = new Button("View Firefighters");
        Button btnft = new Button("View Fire Trucks");

        Font btnFont = new Font("Arial", Font.BOLD, 16);

        btnff.setFont(btnFont);
        btnff.setBackground(new Color(220, 20, 60));
        btnff.setForeground(Color.WHITE);
        btnff.setPreferredSize(new Dimension(220, 50)); 

        btnft.setFont(btnFont);
        btnft.setBackground(new Color(255, 140, 0));
        btnft.setForeground(Color.WHITE);
        btnft.setPreferredSize(new Dimension(220, 50)); 

        footer.add(btnff);
        footer.add(btnft);
        btnff.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnft.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(footer, BorderLayout.SOUTH);
        
        
        btnff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ftruck();   
            }
        });

        btnft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FireTruck();   
            }
        });

        Button btnReq = new Button("View Requests");
        btnReq.setBackground(Color.yellow);
        btnReq.setForeground(Color.black);
        btnReq.setFont(btnFont);
        btnReq.setPreferredSize(new Dimension(220, 50));
        footer.add(btnReq);

        btnReq.addActionListener(e -> new EmergencyReqTable());
        
        
     
        
        
        setVisible(true);
    }

    public static void main(String[] args) {
    	FireStation f = new FireStation();
    }
}