package com.fkedu.http;

/**
 * Created by fk on 2018/1/17.
 */
public interface FKFilter{
    public void init() throws Exception;
    public void doFilter(FKRequest paramServletRequest,FKResponse paramServletResponse) throws Exception;
    public void destroy();
}
