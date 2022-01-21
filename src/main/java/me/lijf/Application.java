package me.lijf;

import me.lijf.service.FetchDataServiceFromSina;
import me.lijf.service.FetchDataServiceFromSnowBall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;

public class Application {
    public static void main(String[] args){
        String PORTFOLIO_STR="sz300750,sz300315,sz300676,sz000858";
        ArrayList<Dashboard> views=new ArrayList<>();

        String[] portfolio=PORTFOLIO_STR.split(",");

        JFrame frame =new JFrame("Desktop");
        JPanel desktop = new JPanel();
        desktop.setBackground(new Color(20,20,20));
        for(int i=0;i<portfolio.length;i++){
            Dashboard view=new Dashboard();
            desktop.add(view.getDesktop());
            views.add(view);
        }

        Timer timer=new Timer();
        FetchDataTask task=new FetchDataTask(new FetchDataServiceFromSnowBall(PORTFOLIO_STR),views);
        timer.schedule(task,0,30000);


        frame.setContentPane(desktop);
        //frame.setAlwaysOnTop(true);
        frame.setLocation(1050,550);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }
}
