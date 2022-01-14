package me.lijf;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Dashboard {
    private Font font=new Font("宋体",Font.PLAIN,18);
    private JPanel desktop=new JPanel();

    private JLabel sid=new JLabel("Loading...");
    private JLabel price=new JLabel("0.00");
    private JLabel stamp=new JLabel("0:00");
    private JLabel local=new JLabel("0:00");

    public Dashboard() {
        this.desktop=new JPanel();
        this.desktop.setSize(100,200);
        this.desktop.setLayout(new GridLayout(4,1));
        this.desktop.add(this.sid);
        this.desktop.add(this.price);
        this.desktop.add(this.stamp);
        this.desktop.add(this.local);
        this.sid.setFont(font);
        this.price.setFont(font);
    }

    public void refresh(PriceData data){
        this.sid.setText(data.getName());
        this.price.setText(data.getLatest());
        this.stamp.setText(data.getStamp());

        Date date=new Date();
        String local=date.getHours()+":"+date.getMinutes();
        this.local.setText(local);
    }

    public JPanel getDesktop() {
        return desktop;
    }

    public void setDesktop(JPanel desktop) {
        this.desktop = desktop;
    }

}
