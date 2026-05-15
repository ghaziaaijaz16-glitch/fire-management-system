import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EmergencyReqTable extends Frame {

    Panel table;
    java.util.List<String> allLines = new ArrayList<>();

    EmergencyReqTable() {

        setLayout(new BorderLayout());

        table = new Panel();
        table.setLayout(new GridLayout(0, 6, 10, 10));

        Font header = new Font("Serif", Font.BOLD, 23);
        Font data = new Font("Serif", Font.PLAIN, 20);

        // ===== Headers =====
        addHeader("Name", header);
        addHeader("Emergency", header);
        addHeader("Location", header);
        addHeader("Phone", header);
        addHeader("Accept", header);
        addHeader("Delete", header);

        loadData(data);

        add(table, BorderLayout.NORTH);

        setTitle("Emergency Requests");
        setSize(900, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    // ===== Header helper =====
    void addHeader(String text, Font f) {
        Label l = new Label(text, Label.CENTER);
        l.setFont(f);
        table.add(l);
    }

    // ===== Load file data =====
    void loadData(Font dataFont) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("req.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                allLines.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < allLines.size(); i++) {
            final int index = i;
            String[] d = allLines.get(i).split(",");

            if (d.length == 4) {

                addCell(d[0], dataFont);
                addCell(d[1], dataFont);
                addCell(d[2], dataFont);
                addCell(d[3], dataFont);

                Button accept = new Button("Accept");
                accept.setBackground(Color.green);
                accept.setForeground(Color.black);
                accept.addActionListener(e ->
                    System.out.println("Accepted request from: " + d[0])
                );
                table.add(accept);

                Button delete = new Button("Delete");
                delete.setBackground(Color.red);
                delete.setForeground(Color.white);
                delete.addActionListener(e -> {
                    deleteRecord(index);
                    refresh();
                });
                table.add(delete);
            }
        }
    }

    void addCell(String text, Font f) {
        Label l = new Label(text);
        l.setFont(f);
        table.add(l);
    }

    void deleteRecord(int index) {
        try {
            allLines.remove(index);
            BufferedWriter bw = new BufferedWriter(new FileWriter("req.txt"));
            for (String s : allLines) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void refresh() {
        dispose();
        new EmergencyReqTable();
    }

    public static void main(String[] args) {
        new EmergencyReqTable();
    }
}
