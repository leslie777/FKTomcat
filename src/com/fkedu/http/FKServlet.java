package com.fkedu.http;

/**
 * Created by fk on 2018/1/16.
 */
public abstract class FKServlet {

    public void service(FKRequest request,FKResponse response) throws Exception{
        //有service方法来决定，是调用doGet或者doPost
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        } else {
            doPost(request,response);
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void doGet(FKRequest request,FKResponse response) throws Exception;

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void doPost(FKRequest request,FKResponse response) throws Exception;
}
