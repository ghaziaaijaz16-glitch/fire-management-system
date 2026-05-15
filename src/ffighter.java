
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ffighter extends Frame {

    BufferedImage[] profileImgs = new BufferedImage[5];

    String[] names      = {"Hassan",   "Hussain",     "Abbas",       "Qasim",        "Laila"};
    String[] ids        = {"FF-001",   "FF-002",      "FF-003",      "FF-004",        "FF-005"};
    String[] ranks      = {"Captain",  "Lieutenant",  "Sergeant",    "Firefighter",   "Firefighter"};
    String[] status     = {"On Duty",  "On Duty",     "Off Duty",    "On Leave",      "On Duty"};
    String[] phones     = {"0300-1234567", "0311-2345678", "0322-3456789", "0333-4567890", "0344-5678901"};
    String[] experience = {"10 years", "7 years",     "5 years",     "3 years",       "2 years"};

    String[] imagePaths = {
            "images/f5.jpeg",
            "images/f6.jpeg",
            "images/f7.jpeg",
            "images/f8.jpeg",
        "images/f9.jpeg"
    };

    int currentIndex = 0;

    Label nameVal, idVal, rankVal, statusVal, phoneVal, expVal, recordLabel;
    Panel profilePanel;
    Button btnToggleDuty;

    public ffighter() {
        setSize(750, 620);
        setTitle("Fire Station - Firefighter Records");
        setLayout(new BorderLayout());
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { dispose(); }
        });

        // ── LOAD IMAGES ──────────────────────────────────────────────
        for (int i = 0; i < imagePaths.length; i++) {
            try {
                profileImgs[i] = ImageIO.read(new File(imagePaths[i]));
            } catch (IOException e) {
                profileImgs[i] = null;
            }
        }

        // ── LABELS ───────────────────────────────────────────────────
        nameVal   = new Label(names[0]);
        idVal     = new Label(ids[0]);
        rankVal   = new Label(ranks[0]);
        statusVal = new Label(status[0]);
        phoneVal  = new Label(phones[0]);
        expVal    = new Label(experience[0]);

        recordLabel = new Label("Record 1 of " + names.length, Label.CENTER);
        recordLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        recordLabel.setForeground(Color.BLACK);

        // ── TOGGLE BUTTON ─────────────────────────────────────────────
        btnToggleDuty = new Button("Set to On Duty");
        btnToggleDuty.setFont(new Font("Arial", Font.BOLD, 12));
        btnToggleDuty.setBackground(new Color(255, 255, 0));
        btnToggleDuty.setForeground(Color.BLACK);
        btnToggleDuty.setPreferredSize(new Dimension(150, 32));
        btnToggleDuty.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToggleDuty.setVisible(status[0].equals("Off Duty"));

        btnToggleDuty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (status[currentIndex].equals("Off Duty")) {
                    status[currentIndex] = "On Duty";
                    updateDisplay();
                }
            }
        });

        // ── HEADER ───────────────────────────────────────────────────
        // Use a custom Panel with overridden paint so the button sits
        // INSIDE the painted header without being clipped by layout
        Panel header = new Panel(null) { // null layout = absolute positioning
            public void paint(Graphics g) {
                super.paint(g);
            }
        };
        header.setBackground(Color.BLACK);
        header.setPreferredSize(new Dimension(750, 75));

        Label title = new Label("Firefighter Records", Label.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 5, 580, 36);

        Label sub = new Label("Fire Station Management System", Label.CENTER);
        sub.setFont(new Font("Arial", Font.ITALIC, 18));
        sub.setForeground(new Color(255, 200, 200));
        sub.setBounds(0, 40, 580, 28);

        // Button positioned absolutely on the right side of header
        btnToggleDuty.setBounds(590, 22, 148, 32);

        header.add(title);
        header.add(sub);
        header.add(btnToggleDuty);
        add(header, BorderLayout.NORTH);

        // ── PROFILE IMAGE PANEL ───────────────────────────────────────
        profilePanel = new Panel() {
            public void paint(Graphics g) {
                super.paint(g);
                BufferedImage img = profileImgs[currentIndex];
                if (img != null) {
                    g.drawImage(img, 15, 20, 170, 170, null);
                } else {
                    g.setColor(new Color(80, 80, 80));
                    g.fillRoundRect(15, 20, 170, 170, 20, 20);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 14));
                    g.drawString("No Image", 65, 110);
                }
                g.setColor(new Color(255, 255, 0));
                g.fillRoundRect(30, 205, 140, 30, 10, 10);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 13));
                g.drawString("ID: " + ids[currentIndex], 65, 225);
            }
        };
        profilePanel.setPreferredSize(new Dimension(210, 260));
        profilePanel.setBackground(Color.WHITE);

        // ── DETAILS PANEL ─────────────────────────────────────────────
        Panel details = new Panel(new GridLayout(6, 2, 12, 16));
        details.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial", Font.BOLD, 15);
        Font valueFont = new Font("Arial", Font.PLAIN, 15);

        String[] labelTexts = {"Name:", "ID:", "Rank:", "Status:", "Phone:", "Experience:"};
        Label[]  vals       = {nameVal, idVal, rankVal, statusVal, phoneVal, expVal};

        for (int i = 0; i < labelTexts.length; i++) {
            Label lbl = new Label(labelTexts[i]);
            lbl.setFont(labelFont);
            lbl.setForeground(Color.BLACK);
            vals[i].setFont(valueFont);
            vals[i].setForeground(Color.BLACK);
            details.add(lbl);
            details.add(vals[i]);
        }

        updateStatusColor();

        Panel southPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Color.WHITE);
        southPanel.add(recordLabel);

        Panel rightPanel = new Panel(new BorderLayout(0, 10));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(details, BorderLayout.CENTER);
        rightPanel.add(southPanel, BorderLayout.SOUTH);

        Panel center = new Panel(new BorderLayout(30, 15));
        center.setBackground(Color.WHITE);
        center.add(profilePanel, BorderLayout.WEST);
        center.add(rightPanel, BorderLayout.CENTER);

        // ── STATUS LEGEND ─────────────────────────────────────────────
        Panel legend = new Panel(new FlowLayout(FlowLayout.LEFT, 25, 10));
        legend.setBackground(Color.BLACK);

        Label onDutyLbl = new Label("● On Duty");
        onDutyLbl.setForeground(new Color(0, 210, 0));
        onDutyLbl.setFont(new Font("Arial", Font.BOLD, 13));

        Label offDutyLbl = new Label("● Off Duty");
        offDutyLbl.setForeground(new Color(220, 220, 0));
        offDutyLbl.setFont(new Font("Arial", Font.BOLD, 13));

        Label onLeaveLbl = new Label("● On Leave");
        onLeaveLbl.setForeground(new Color(255, 120, 0));
        onLeaveLbl.setFont(new Font("Arial", Font.BOLD, 13));

        legend.add(onDutyLbl);
        legend.add(offDutyLbl);
        legend.add(onLeaveLbl);

        Panel centerWrapper = new Panel(new BorderLayout());
        centerWrapper.setBackground(Color.WHITE);
        centerWrapper.add(center, BorderLayout.CENTER);
        centerWrapper.add(legend, BorderLayout.SOUTH);
        add(centerWrapper, BorderLayout.CENTER);

        // ── FOOTER ────────────────────────────────────────────────────
        Panel footer = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        footer.setBackground(new Color(180, 0, 0));

        Button btnPrev   = new Button("<< Previous");
        Button btnNext   = new Button("Next  >>");
        Button btnSelect = new Button("Select  ");
        Button btnFirst  = new Button("|< First");
        Button btnLast   = new Button("Last >|");

        Font btnFont = new Font("Arial", Font.BOLD, 13);
        styleButton(btnFirst,  new Color(255, 255, 0), Color.BLACK, btnFont);
        styleButton(btnPrev,   new Color(255, 255, 0), Color.BLACK, btnFont);
        styleButton(btnSelect, new Color(255, 255, 0), Color.BLACK, btnFont);
        styleButton(btnNext,   new Color(255, 255, 0), Color.BLACK, btnFont);
        styleButton(btnLast,   new Color(255, 255, 0), Color.BLACK, btnFont);

        footer.add(btnFirst);
        footer.add(btnPrev);
        footer.add(btnNext);
        footer.add(btnSelect);
        add(footer, BorderLayout.SOUTH);

        // ── NAV LISTENERS ─────────────────────────────────────────────
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < names.length - 1) { currentIndex++; updateDisplay(); }
            }
        });
        btnPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex > 0) { currentIndex--; updateDisplay(); }
            }
        });
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { currentIndex = 0; updateDisplay(); }
        });
        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { currentIndex = names.length - 1; updateDisplay(); }
        });

btnSelect.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        // Dialog ka owner Frame hoga (ffighter.this)
        Dialog d = new Dialog(ffighter.this, "Firefighter Selected", true);
        d.setSize(380, 170);
        d.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

        // Firefighter info show
        Label msg = new Label(
            "Selected Firefighter: " + names[currentIndex] +
            " (" + ids[currentIndex] + ")",
            Label.CENTER
        );
        msg.setFont(new Font("Arial", Font.BOLD, 14));

        d.add(msg);

        Button ok = new Button("OK");
        ok.setBackground(new Color(255, 165, 0));
        ok.setFont(new Font("Arial", Font.BOLD, 13));

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                d.dispose();
            }
        });

        d.add(ok);

        // Dialog ko center me show kare
        d.setLocationRelativeTo(ffighter.this);
        d.setVisible(true);
    }
});



        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ── UPDATE DISPLAY ────────────────────────────────────────────────
    private void updateDisplay() {
        nameVal.setText(names[currentIndex]);
        idVal.setText(ids[currentIndex]);
        rankVal.setText(ranks[currentIndex]);
        phoneVal.setText(phones[currentIndex]);
        expVal.setText(experience[currentIndex]);
        updateStatusColor();
        recordLabel.setText("Record " + (currentIndex + 1) + " of " + names.length);
        btnToggleDuty.setVisible(status[currentIndex].equals("Off Duty"));
        profilePanel.repaint();
    }

    // ── STATUS COLOR ──────────────────────────────────────────────────
    private void updateStatusColor() {
        String s = status[currentIndex];
        statusVal.setText(s);
        if (s.equals("On Duty"))
            statusVal.setForeground(new Color(0, 210, 0));
        else if (s.equals("Off Duty"))
            statusVal.setForeground(new Color(220, 220, 0));
        else
            statusVal.setForeground(new Color(255, 120, 0));
    }

    // ── STYLE BUTTON ──────────────────────────────────────────────────
    private void styleButton(Button b, Color bg, Color fg, Font f) {
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFont(f);
        b.setPreferredSize(new Dimension(130, 42));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) { new ffighter(); }
}