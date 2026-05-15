import java.awt.*;
import java.awt.event.*;


public class FireTruck extends Frame {
	
	 String[] ids       = {"FT-001", "FT-002", "FT-003", "FT-004"};
	    String[] drivers   = {"SAM", "Ahmed", "Imran Khan", "Isha malik"};
	    String[] status    = {"Available", "On Mission", "Under Maintenance", "Available"};
	    String[] capacity  = {"5000 L", "4000 L", "3500 L", "6000 L"};
	    
int index = 0;	    	
//for reference variable==
Label id_adrss, driver_adrss, status_adrss, cap_adrss; 
//constructor	    
//frame
public FireTruck() {

	 setSize(780, 640);
     setTitle("Fire Station_Fire Truck Records SYSTEM");
     setLayout(new BorderLayout());
     
     addWindowListener(new WindowAdapter() {
    	    public void windowClosing(WindowEvent e) {
    	        dispose();
    	    }
    	});

//frame>>>header:
         Panel header = new Panel(new BorderLayout());
         header.setBackground(new Color(0, 70, 120)); // changed color
         header.setPreferredSize(new Dimension(780, 80));

//frame>>>header>>>button:
              Button selectBtn = new Button("SELECT FIRE TRUCK");
              selectBtn.setFont(new Font("Arial", Font.BOLD, 14));
              selectBtn.setForeground(Color.WHITE);
              selectBtn.setBackground(new Color(20, 20, 20));

//   ///////////info.. (->)lambda operator or used for switch.....	
//When the SELECT button is clicked>>>:\
//OK POPUP frame:
    selectBtn.addActionListener(e -> {
    	
    	if (!status[index].equalsIgnoreCase("Available")) {

            Dialog error = new Dialog(this, "Error", true);
            error.setSize(350, 150);
            error.setLayout(new FlowLayout());

            error.add(new Label("Truck is NOT available!", Label.CENTER));

            Button ok = new Button("OK");
            ok.setBackground(Color.RED);
            ok.setForeground(Color.WHITE);

            ok.addActionListener(ev -> error.dispose());
            error.add(ok);

            error.setLocationRelativeTo(this);
            error.setVisible(true);
            return;
    	}
    	
//    	SelectionManager.selectedTruck = ids[index];
    	
        Dialog d = new Dialog(this, "Truck Selected", true);
        d.setSize(350, 150);
        d.setLayout(new FlowLayout());
        
        d.add(new Label("Selected Truck: " + ids[index], Label.CENTER));

              Button ok = new Button("OK");
        
                ok.setBackground(new Color(255, 165, 0));
                ok.addActionListener(ev -> {
                    d.dispose();
//                    SelectionManager.checkDispatch();
//                    dispose();
                });
                
        d.add(ok);
        d.setLocationRelativeTo(this);   //  center popup
        d.setVisible(true);
        });

//database style >>>>
    Label title = new Label(" FIRE TRUCK CONTROL PANEL", Label.CENTER);
    title.setFont(new Font("Serif", Font.BOLD, 28));
    title.setForeground(Color.WHITE);

    Label sub = new Label("Fire Station Management System", Label.CENTER);
    sub.setForeground(Color.LIGHT_GRAY);

    Panel titlePanel = new Panel(new GridLayout(3, 1));
    titlePanel.setBackground(new Color(0, 70, 120));
    titlePanel.add(title);
    titlePanel.add(sub);

    header.add(selectBtn, BorderLayout.WEST);
    header.add(titlePanel, BorderLayout.CENTER);

    add(header, BorderLayout.NORTH);

    // DETAILS PANEL (Matches Firefighter Layout)
    Panel center = new Panel(new GridLayout(4, 2, 10, 10));
    center.setBackground(new Color(245, 245, 245));

    Label lbl1 = new Label("Truck ID:");
    lbl1.setFont(new Font("Arial", Font.BOLD, 16));
    id_adrss = new Label(ids[index]);

    Label lbl2 = new Label("Driver:");
    lbl2.setFont(new Font("Arial", Font.BOLD, 16));
    driver_adrss = new Label(drivers[index]);
    
    Label lbl3 = new Label(" Status:");
    lbl3.setFont(new Font("Arial", Font.BOLD, 16));
    status_adrss= new Label(status[index]);

    Label lbl4 = new Label("💧 Water Capacity:");
    lbl4.setFont(new Font("Arial", Font.BOLD, 16));
    cap_adrss = new Label(capacity[index]);

    center.add(lbl1); center.add(id_adrss);
    center.add(lbl2); center.add(driver_adrss);
    center.add(lbl3); center.add(status_adrss);
    center.add(lbl4); center.add(cap_adrss);

    add(center, BorderLayout.CENTER);

//frame>>>>>FOOTER:
    Panel footer = new Panel(new FlowLayout());
    footer.setBackground(new Color(0, 50, 90));

    Button prev = new Button("<< Previous");
    Button next = new Button("Next >>");
    Button close = new Button("Close");

    Button[] btns = {prev, next, close};
    for (Button b : btns) {
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        footer.add(b);
    }

//frame>>>FOOTER>>>button:
    prev.addActionListener(e -> move(-1));
    next.addActionListener(e -> move(1));
    close.addActionListener(e -> dispose());

    add(footer, BorderLayout.SOUTH);

    setVisible(true);
 }

 ////////TO NAVIGATE///////////
private void move(int n) {
    index += n;
    if (index < 0) index = 0;
    if (index >= ids.length) index = ids.length - 1;

    id_adrss.setText(ids[index]);
    driver_adrss.setText(drivers[index]);
    status_adrss.setText(status[index]);
    cap_adrss.setText(capacity[index]);
}

public static void showTable() {// TO DO Auto generated method stub
	
}

}