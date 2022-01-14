package me.lijf;

import java.util.Date;
import java.util.TimerTask;

public class FetchDataTask extends TimerTask {
    FetchDataService service;
    Dashboard view;

    public FetchDataTask(FetchDataService service, Dashboard view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void run() {
        PriceData data=this.service.refresh();
        this.view.refresh(data);
        System.out.println(new Date());
    }
}
