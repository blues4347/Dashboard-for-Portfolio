package me.lijf;

import javax.swing.*;
import java.util.Timer;

public class Application {
    public static void main(String[] args){
        Dashboard view=new Dashboard();
        FetchDataService service=new FetchDataService();

        FetchDataTask task=new FetchDataTask(service,view);
        Timer timer=new Timer();
        timer.schedule(task,0,60000);

        JFrame frame =new JFrame("Desktop");
        frame.setContentPane(view.getDesktop());
        frame.setAlwaysOnTop(true);
        frame.setLocation(1300,650);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }
}
