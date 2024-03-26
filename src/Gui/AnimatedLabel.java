package Gui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class AnimatedLabel extends JLabel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private int count = 0;

    public AnimatedLabel() {
        setText("");
        //setFont(new Font("Arial", Font.BOLD, 18));
        //setForeground(Color.BLUE);
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        if (count > 11) {
            count = 1;
        }
        switch (count) {
            case 1:
                setText("POUR ");
                break;
            case 2:
                setText("POUR TROUVER");
                break;
            case 3:
                setText("POUR TROUVER SANS");
                break;
            case 4:
                setText("POUR TROUVER SANS CHERCHER, ");
                break;
            case 5:
                setText("POUR TROUVER SANS CHERCHER, IL");
                break;
            case 6:
                setText("POUR TROUVER SANS CHERCHER, IL FAUT");
                break;
            case 7:
                setText("POUR TROUVER SANS CHERCHER, IL FAUT SAVOIR");
                break;
            case 8:
                setText("POUR TROUVER SANS CHERCHER, IL FAUT SAVOIR AUSSI");
                break;
            case 9:
                setText("POUR TROUVER SANS CHERCHER, IL FAUT SAVOIR AUSSI CHERCHER");
                break;
            case 10:
                setText("POUR TROUVER SANS CHERCHER, IL FAUT SAVOIR AUSSI CHERCHER SANS");
                break;
            case 11:
                setText("POUR TROUVER SANS CHERCHER, IL FAUT SAVOIR AUSSI CHERCHER SANS TROUVER");
                break;
        }
    }

   
}

