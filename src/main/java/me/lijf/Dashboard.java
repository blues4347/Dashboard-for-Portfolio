package me.lijf;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Dashboard {
    private Font font=new Font("微软雅黑",Font.PLAIN,16);
    private JPanel desktop=new JPanel();

    private JLabel sid=new JLabel("Loading..");
    private JLabel price=new JLabel("0.00");
    private JLabel pcnt =new JLabel("0.00");
    private JLabel hi=new JLabel("000.000");
    private JLabel low=new JLabel("000.000");
    private JLabel stamp=new JLabel("0:00");
    private JLabel local=new JLabel("0:00");

    public Dashboard() {
        this.desktop=new JPanel();
        this.desktop.setSize(150,200);
        this.desktop.setLayout(new GridLayout(7,1));
        this.desktop.add(this.sid);
        this.desktop.add(this.stamp);
        this.desktop.add(this.price);
        this.desktop.add(this.pcnt);
        this.desktop.add(this.hi);
        this.desktop.add(this.low);
        this.desktop.add(this.local);
        this.sid.setFont(font);
        this.sid.setHorizontalAlignment(SwingConstants.RIGHT);
        this.local.setHorizontalAlignment(SwingConstants.RIGHT);
        this.price.setFont(font);
        this.price.setHorizontalAlignment(SwingConstants.RIGHT);
        this.pcnt.setFont(font);
        this.pcnt.setHorizontalAlignment(SwingConstants.RIGHT);
        this.hi.setHorizontalAlignment(SwingConstants.RIGHT);
        this.low.setHorizontalAlignment(SwingConstants.RIGHT);
        this.stamp.setHorizontalAlignment(SwingConstants.RIGHT);

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
