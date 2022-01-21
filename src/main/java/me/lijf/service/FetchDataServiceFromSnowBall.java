package me.lijf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.lijf.PriceData;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FetchDataServiceFromSnowBall implements FetchDataService{
    String favourites;

    public FetchDataServiceFromSnowBall(String favourites) {
        this.favourites = favourites;
    }

    public List<PriceData> refresh(){
        ArrayList<PriceData> data=new ArrayList<>();

        CloseableHttpClient client= HttpClients.createDefault();
        try{
            HttpGet httpGet=new HttpGet("https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol="+favourites.toUpperCase());
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
        JSONObject msg= JSON.parseObject(text);
        if(msg.getInteger("error_code")!=0) return error();
        JSONArray data=msg.getJSONArray("data");
        List<PriceData> ret=new ArrayList<>();
        for(int i=0;i<data.size();i++){
            JSONObject item=data.getJSONObject(i);
            Date timestamp=new Date(item.getLong("timestamp"));
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
            ret.add(new PriceData(
                    directory.get(item.getString("symbol")),item.getString("last_close"),item.getString("current"),
                    item.getString("high"),item.getString("low"),sdf.format(timestamp)));
        }
        return ret;
    }

    private List<PriceData> error(){
        PriceData error=new PriceData("error","error","error","error","error",new Date().toLocaleString());
        List<PriceData> ret=new ArrayList<>();
        ret.add(error);
        return ret;
    }

    private static Map<String,String> directory=new LinkedHashMap(){{
        put("SZ300750","宁德时代");
        put("SZ300315","掌趣科技");
        put("SZ300676","华大基因");
        put("SZ000858","五粮液");
    }};
}
