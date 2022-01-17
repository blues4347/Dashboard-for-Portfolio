package me.lijf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;

public class Application {
    public static void main(String[] args){
//        String[] portfolio={"sz300750","sz300315"};
        String PORTFOLIO_STR="sz300750,sz300315";
        ArrayList<Dashboard> views=new ArrayList<>();

        String[] portfolio=PORTFOLIO_STR.split(",");

        JFrame frame =new JFrame("Desktop");
        JPanel desktop = new JPanel();
        desktop.setBackground(Color.BLACK);
        for(int i=0;i<portfolio.length;i++){
            Dashboard view=new Dashboard();
            desktop.add(view.getDesktop());
            views.add(view);
        }

        Timer timer=new Timer();
        FetchDataTask task=new FetchDataTask(new FetchDataService(PORTFOLIO_STR),views);
        timer.schedule(task,0,30000);


        frame.setContentPane(desktop);
        frame.setAlwaysOnTop(true);
        frame.setLocation(3050,820);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }
}
