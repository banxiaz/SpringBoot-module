package com.bai.servlet.impl;

import com.bai.servlet.BaseServlet;
import com.bai.servlet.Request;
import com.bai.servlet.Response;

import java.io.IOException;

public class FirstServlet extends BaseServlet {
    @Override
    protected void doGet(Request request, Response response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(Request request, Response response) throws IOException {
        response.write("first servlet");
    }
}
