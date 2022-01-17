package me.lijf;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class FetchDataTask extends TimerTask {
    FetchDataService service;
    ArrayList<Dashboard> views;

    public FetchDataTask(FetchDataService service, ArrayList<Dashboard> views) {
        this.service = service;
        this.views = views;
    }

    @Override
    public void run() {
        List<PriceData> data=this.service.refresh();
        for(int i=0;i<data.size();i++){
            this.views.get(i).refresh(data.get(i));
        }
    }
}
