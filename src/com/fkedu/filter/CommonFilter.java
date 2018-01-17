package com.fkedu.filter;

import com.fkedu.http.FKFilter;
import com.fkedu.http.FKRequest;
import com.fkedu.http.FKResponse;
import com.fkedu.http.FKServlet;

/**
 * Created by fk on 2018/1/16.
 */
public class CommonFilter extends FKServlet implements FKFilter {


    @Override
    public void init() throws Exception {

    }

    @Override
    public void doFilter(FKRequest paramServletRequest, FKResponse paramServletResponse) throws Exception {
        System.out.println("commonFilter");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doGet(FKRequest request, FKResponse response) throws Exception {

    }

    @Override
    public void doPost(FKRequest request, FKResponse response) throws Exception {

    }
}
