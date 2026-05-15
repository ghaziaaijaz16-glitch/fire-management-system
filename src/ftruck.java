
import java.awt.*;
import java.awt.event.*;

public class ftruck extends Frame implements ActionListener {

    Button btn;
    Panel centerPanel;

    public ftruck() {

        setTitle("WELCOME TO FIRESTATION");
        setSize(750, 620);
        setLayout(new BorderLayout());

        // 🔹 HEADER
        Panel header = new Panel();
        header.setBackground(Color.BLACK);
        header.setPreferredSize(new Dimension(750, 70));
        header.setLayout(new BorderLayout());

        Panel titlePanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.BLACK);

        Label title = new Label("Fire Station System");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        titlePanel.add(title);

        header.add(titlePanel, BorderLayout.CENTER);
    
        add(header, BorderLayout.NORTH);

        // 🔹 CENTER PANEL (IMAGES)
        centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        centerPanel.setBackground(Color.DARK_GRAY);

        Image img1 = Toolkit.getDefaultToolkit().getImage("images/f1.jpeg");
        Image img2 = Toolkit.getDefaultToolkit().getImage("images/f2.jpeg");
        Image img3 = Toolkit.getDefaultToolkit().getImage("images/f3.jpeg");
        Image img4 = Toolkit.getDefaultToolkit().getImage("images/f4.jpeg");

        centerPanel.add(new ImageCanvas(img1, "FireFighters "));
        centerPanel.add(new ImageCanvas(img2, "FireFighters"));
        centerPanel.add(new ImageCanvas(img3, "FireFighters"));
        centerPanel.add(new ImageCanvas(img4, "FireFighters"));

        add(centerPanel, BorderLayout.CENTER);

    
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { dispose(); }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            new ffighter();
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new ftruck();
    }
}

// 🔥 IMAGE CARD CLASS (UPGRADED)
class ImageCanvas extends Canvas implements MouseListener {

    Image img;
    String text;
    boolean hover = false;

    ImageCanvas(Image img, String text) {
        this.img = img;
        this.text = text;
        addMouseListener(this);
    }

    public void paint(Graphics g) {

        int w = getWidth();
        int h = getHeight();

        // 🔹 Draw image
        if (img != null) {
            g.drawImage(img, 0, 0, w, h - 30, this);
        }

        // 🔹 Hover effect
        if (hover) {
            g.setColor(new Color(255, 255, 0, 80));
            g.fillRect(0, 0, w, h - 30);
        }

        // 🔹 Text background
        g.setColor(Color.BLACK);
        g.fillRect(0, h - 30, w, 30);

        // 🔹 Text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(text, 10, h - 10);
    }

    // 🔥 Mouse Events
    public void mouseEntered(MouseEvent e) {
        hover = true;
        repaint();
    }

    public void mouseExited(MouseEvent e) {
        hover = false;
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        new ffighter(); // open next page
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}