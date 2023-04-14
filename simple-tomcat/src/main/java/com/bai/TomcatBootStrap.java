package com.bai;

import com.bai.servlet.BaseServlet;
import com.bai.servlet.Request;
import com.bai.servlet.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TomcatBootStrap {
    private int port = 8080;
    private ServerSocket server;
    private Map<String, BaseServlet> servletMapping = new HashMap<>();
    private Properties webxml = new Properties();
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

    private void init() {
        try {
            String WEB_INF = Objects.requireNonNull(this.getClass().getResource("/")).getPath();
            log.info("获取到WEB-INF路径[{}]", WEB_INF);
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            webxml.load(fis);
            log.info("读取到的web-xml信息为[{}]", webxml);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");

                    // 单实例，多线程
                    BaseServlet obj = (BaseServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }
            log.info("解析完url到Servlet的映射[{}]", servletMapping);
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();

        try {
            server = new ServerSocket(this.port);
            log.info("GP TomcatBootStrap 已启动，监听的端口是[{}]", this.port);

            while (true) {
                Socket client = server.accept();
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        process(client);
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) {
        try {
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            Request request = new Request(is);
            Response response = new Response(os);
            String url = request.getUrl();

            if (servletMapping.containsKey(url)) {
                servletMapping.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TomcatBootStrap().start();
    }
}
