package com.fkedu.http;

import java.io.OutputStream;

/**
 * Created by fk on 2018/1/16.
 */
public class FKResponse {
    private  OutputStream os;
    public FKResponse(OutputStream os){
        this.os = os;
    }

    public void write(String outString) throws Exception{
        os.write(outString.getBytes());
    }
}
