package com.fkedu.tomcat;

import com.fkedu.http.FKRequest;
import com.fkedu.http.FKResponse;
import com.fkedu.http.FKFilter;
import com.fkedu.http.FKServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 *
 * Created by fk on 2018/1/16.
 */
public class FKTomcat {
    private  int port = 8080;

    private ServerSocket server;

    private Map<Pattern, Class<?>> filterMaping = new HashMap<Pattern, Class<?>>();

    private Map<String,FKServlet> servletMaping = new HashMap<>();

    private Properties webxml = new Properties();

    private String WEB_INF = this.getClass().getResource("/").getPath();

    public FKTomcat() {
    }
    public FKTomcat(int port) {
        this.port = port;
    }

    private void  init(){
        FileInputStream fis = null;
        //加载web.xml文件，t同时初始化filtermapping对象
        try{

            fis = new FileInputStream(WEB_INF+"web.properties");

            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if(key.endsWith(".url")&&key.startsWith("filter")){
                    String filterName = key.replaceAll("\\.url$","");
                    String url = webxml.getProperty(key);

                    Pattern pattern = Pattern.compile(url);

                    String className = webxml.getProperty(filterName+".className");

                    FKFilter obj = (FKFilter) Class.forName(className).newInstance();

                    Class<?> filterClass = Class.forName(className);

                    filterMaping.put(pattern,filterClass);
                } else if(key.endsWith(".url")&&key.startsWith("servlet")){
                    String servletName = key.replaceAll("\\.url$","");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName+".className");
                    FKServlet obj = (FKServlet) Class.forName(className).newInstance();
                    servletMaping.put(url,obj);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void process(Socket client) throws Exception {
        //3.获取到socker对象，将socker.getinstream()封装成requset
        //                    将socker.getoutstream()封装成response

        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        //使用client请求的socket输入流信息初始化Request
        FKRequest request = new FKRequest(is);
        FKResponse response = new FKResponse(os);

        //4. 实现动态调用doget/dopost方法，并且能够自定义返回结果
        //想办法拿到用户请求的url
        try{
            String url = request.getUrl();

            for (Map.Entry<Pattern, Class<?>> entry : filterMaping.entrySet()) {
                //映射中只要能找到
                if(entry.getKey().matcher(url).matches()) {
                    FKFilter filter = (FKFilter)entry.getValue().newInstance();
                    filter.doFilter(request,response);
                    break;
                }
            }
            if(servletMaping.containsKey(url)){
                servletMaping.get(url).service(request,response);
            }else {
                response.write("404 - NotFound");
            }
        }catch (Exception e){
            response.write("500 ," + e.getMessage());
        } finally {
            is.close();
            os.close();
            //为什么关闭client ,应为http请求时采用短链接，用完不关闭就会一直转
            client.close();
        }



    }


    public  void start(){
        //1。加载配置文件，初始化filtermappingar
        init();
        try {
            server = new ServerSocket(this.port);
            System.out.println("FK tomcat 已启动，建通端口为："+this.port);

        }catch (Exception e){
            System.out.println("FK tomcat 启动失败，" + e.getMessage());
            return;
        }

        //2. 等待用户请求 用一个死循环来等待用户请求
        while (true) {
            try{
                Socket client = server.accept();
                process(client);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new FKTomcat(8888).start();
    }
}
