package me.lijf.service;

import me.lijf.PriceData;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FetchDataServiceFromSina implements FetchDataService{
    String favourites;

    public FetchDataServiceFromSina(String favourites) {
        this.favourites = favourites;
    }

    public List<PriceData> refresh(){
        ArrayList<PriceData> data=new ArrayList<>();

        CloseableHttpClient client= HttpClients.createDefault();
        try{
            HttpGet httpGet=new HttpGet("http://qt.gtimg.cn/q="+favourites);
            CloseableHttpResponse response=client.execute(httpGet);
            String text= EntityUtils.toString(response.getEntity());
            return result(text);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return error();
    }

    private List<PriceData> result(String text){
        String[] lines=text.split(";\\n");
        List<PriceData> ret = new ArrayList<>();

        for(String line:lines) {
            line = line.substring(text.indexOf("=\"") + 2).replace("\";", "");
            String[] data = line.split(",");
            PriceData priceData = new PriceData(data[0], data[2], data[3], data[4], data[5], data[31]);
            ret.add(priceData);
        }
        return ret;
    }

    private List<PriceData> error(){
        PriceData error=new PriceData("error","error","error","error","error",new Date().toLocaleString());
        List<PriceData> ret=new ArrayList<>();
        ret.add(error);
        return ret;
    }
}
