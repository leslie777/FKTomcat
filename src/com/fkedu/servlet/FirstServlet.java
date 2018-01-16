package com.fkedu.servlet;

import com.fkedu.http.FKRequest;
import com.fkedu.http.FKResponse;
import com.fkedu.http.FKServlet;

/**
 * Created by fk on 2018/1/16.
 */
public class FirstServlet extends FKServlet{

    @Override
    public void doGet(FKRequest request, FKResponse response) throws Exception {
        this.doPost(request,response);
    }

    @Override
    public void doPost(FKRequest request, FKResponse response) throws Exception{
        response.write("This is First Servlet");
    }
}


