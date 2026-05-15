import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class EmergencyReq extends Frame implements ActionListener {

	Label l, l1 , l2 , l3 , l4;
	TextField t1 , t2, t3 , t4;
	Button b;
		public EmergencyReq() {
		
			l = new Label("Emergency Request");
			l.setFont(new Font("Serif", Font.BOLD, 32 ));
			l.setBounds(120, 75 ,600, 50 );
			
			
			l1 = new Label("Name :- ");
			l1.setFont(new Font("SERIF", Font.PLAIN, 30 ));
			l1.setBounds(100, 160 , 100, 50 );

			
			t1 = new TextField();
			t1.setBounds(100, 215 , 300, 50 );
			t1.setFont(new Font("SERIF", Font.PLAIN, 27 ));
			

			l2 = new Label("Emergency Type :-");
			l2.setFont(new Font("SERIF", Font.PLAIN, 30 ));
			l2.setBounds(100, 280 , 250, 50 );

			
			t2 = new TextField();
			t2.setBounds(100, 330 , 300, 50 );
			t2.setFont(new Font("SERIF", Font.PLAIN, 27 ));

			l3 = new Label("Location :-");
			l3.setFont(new Font("SERIF", Font.PLAIN, 30 ));
			l3.setBounds(100, 390 , 200, 50 );

			
			t3 = new TextField();
			t3.setBounds(100, 440 , 300, 50 );
			t3.setFont(new Font("SERIF", Font.PLAIN, 27 ));

			l4 = new Label("Phone No :-");
			l4.setFont(new Font("SERIF", Font.PLAIN, 30 ));
			l4.setBounds(100, 510 , 200, 50 );

			
			t4 = new TextField();
			t4.setBounds(100, 560 , 300, 50 );
			t4.setFont(new Font("SERIF", Font.PLAIN, 27 ));

			b = new Button("Send Request");
			b.setBounds(140, 645 , 200, 70 );
			b.setFont(new Font("SERIF", Font.PLAIN, 27 ));
			b.setBackground(Color.white);
			b.setForeground(Color.red);
			b.addActionListener(this);
			b.addActionListener(e-> new FireStation());
			this.add(l);
			this.add(l1);
			this.add(t1);
			this.add(l2);
			this.add(t2);
			this.add(l3);
			this.add(t3);
			this.add(l4);
			this.add(t4);
			this.add(b);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			this.setBackground(new Color(135, 0, 0));
			this.setForeground(Color.black);
			this.setLayout(null);
			this.setVisible(true);
			this.setSize(550,800);
		}
		
		

public void actionPerformed(ActionEvent e) {

    String name = t1.getText();
    String type = t2.getText();
    String location = t3.getText();
    String phone = t4.getText();

    try {
        FileWriter fw = new FileWriter("req.txt", true); // ✅ append
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(name + "," + type + "," + location + "," + phone);
        bw.newLine();

        bw.close();
        System.out.println("Data written successfully");

    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EmergencyReq();
	}

}
