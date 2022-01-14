package me.lijf;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;

public class FetchDataService {
    String favourites;

    public FetchDataService(String favourites) {
        this.favourites = favourites;
    }

    public PriceData refresh(){
        CloseableHttpClient client= HttpClients.createDefault();
        try{
            HttpGet httpGet=new HttpGet("http://hq.sinajs.cn/list="+favourites);
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

    private PriceData result(String text){
        text=text.substring(text.indexOf(favourites)+favourites.length()+2).replace("\";","");
        String[] data=text.split(",");
        return new PriceData(data[0],data[2],data[3],data[4],data[5],data[31]);
    }

    private PriceData error(){
        PriceData ret=new PriceData("error","error","error","error","error",new Date().toLocaleString());
        return ret;
    }
}
