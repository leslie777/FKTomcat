package com.fkedu.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by fk on 2018/1/16.
 */
public class FKRequest {
    private String method;
    private String url;

    public FKRequest(InputStream is){
//http协议就是一串字符串,里面就有url
        try {
            String content = "";
            byte [] buff = new byte[1024];
            int len = 0;
            if((len = is.read(buff)) > 0){
                content = new String(buff,0,len);
            }
            String line = content.split("\\n")[0];
            String [] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getMethod(){
        return this.method;
    }

    public String getUrl(){
        return this.url;
    }
    public Map<String,String> getParmenter(){
        return null;
    }
}
