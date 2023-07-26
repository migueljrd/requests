package com.mrd.request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class Requests {
    private CloseableHttpClient httpClient;

    public Requests() {
        httpClient = HttpClients.createDefault();
    }

    public String doGet(String url) throws IOException, URISyntaxException {
        HttpGet httpGet = new HttpGet(new URI(url));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        return handleResponse(httpResponse);
    }

    public String doPost(String url, Object object) throws IOException, URISyntaxException {
        return doRequest(new HttpPost(new URI(url)), object);
    }

    public String doPut(String url, Object object) throws IOException, URISyntaxException {
        return doRequest(new HttpPut(new URI(url)), object);
    }

    public String doPatch(String url, Object object) throws IOException, URISyntaxException {
        return doRequest(new HttpPatch(new URI(url)), object);
    }

    public String doDelete(String url) throws IOException, URISyntaxException {
        HttpDelete httpDelete = new HttpDelete(new URI(url));
        HttpResponse httpResponse = httpClient.execute(httpDelete);
        return handleResponse(httpResponse);
    }

    private String doRequest(HttpEntityEnclosingRequestBase httpRequest, Object object) throws IOException {
        Gson gson = new Gson();
        httpRequest.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        httpRequest.setEntity(new StringEntity(gson.toJson(object)));
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        return handleResponse(httpResponse);
    }

    private String handleResponse(HttpResponse httpResponse) throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String response = EntityUtils.toString(httpEntity);
        EntityUtils.consume(httpEntity); // Consumir la entidad para liberar recursos
        return response;
    }

    public void close() throws IOException {
        httpClient.close();
    }
}