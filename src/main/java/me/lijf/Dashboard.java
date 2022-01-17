package me.lijf;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Dashboard {
    private Font font=new Font("微软雅黑",Font.PLAIN,16);
    private JPanel desktop;

    private JLabel sid=new JLabel("Loading..");
    private JLabel price=new JLabel("0.00");
    private JLabel pcnt =new JLabel("0.00");
    private JLabel hi=new JLabel("000.000");
    private JLabel low=new JLabel("000.000");
    private JLabel stamp=new JLabel("0:00");
    private JLabel local=new JLabel("0:00");

    public Dashboard() {
        this.desktop=new JPanel();
        this.desktop.setBackground(Color.BLACK);
        this.desktop.setLayout(new GridLayout(7,1));
        this.desktop.add(this.sid);style(this.sid);this.sid.setFont(font);
        this.desktop.add(this.stamp);style(this.stamp);
        this.desktop.add(this.price);style(this.price);this.price.setFont(font);
        this.desktop.add(this.pcnt);style(this.pcnt);this.pcnt.setFont(font);
        this.desktop.add(this.hi);style(this.hi);
        this.desktop.add(this.low);style(this.low);
        this.desktop.add(this.local);style(this.local);
    }

    private void style(JLabel label){
        label.setForeground(new Color(150,150,150));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void refresh(PriceData data){
        this.sid.setText(data.getName());
        this.price.setText(data.getLatest());
        this.pcnt.setText(data.getPcnt());
        this.hi.setText(data.getHi());
        this.low.setText(data.getLow());
        this.stamp.setText(data.getStamp());

        Date date=new Date();
        String local=date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        this.local.setText(local);
    }

    public JPanel getDesktop() {
        return desktop;
    }

    public void setDesktop(JPanel desktop) {
        this.desktop = desktop;
    }

}
