package com.mrd.request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
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

/**
 * Requests - HTTP Client Library for Java.
 *
 * <p>
 * The Requests class provides a simple and efficient way to make HTTP requests with various methods (GET, POST, PUT, PATCH, DELETE).
 * It also supports sending custom headers along with the requests. The library uses Apache HttpClient under the hood to handle the HTTP communications.
 * </p>
 *
 * @author Miguel Robledo Diego
 */

public class Requests {

    /**
     * Variable of httpClient
     */
    private CloseableHttpClient httpClient;

    /**
     * 
     * Do Get with headers, Headers can be null but url must be mandatory
     * 
     * @param url
     * @param headers
     * @return String response on Json format
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doGet(String url,Map<String,String> headers) throws IOException, URISyntaxException {
        httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(new URI(url));
        addHeaders(httpGet,headers);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        return handleResponse(httpResponse);
    }

    /**
     * 
     * Do Post with headers, Headers can be null, Object and url are mandatory
     * 
     * @param url
     * @param object
     * @param headers
     * @return String response on Json format
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doPost(String url, Object object,Map<String,String> headers) throws IOException, URISyntaxException {
        return doRequest(new HttpPost(new URI(url)), object, headers);
    }

    /**
     * 
     * Do Put with headers, Headers can be null, Object and url are mandatory
     * 
     * @param url
     * @param object
     * @param headers
     * @return String response on Json format
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doPut(String url, Object object,Map<String,String> headers) throws IOException, URISyntaxException {
        return doRequest(new HttpPut(new URI(url)), object,headers);
    }

    /**
     * 
     * Do Patch with headers, Headers can be null, Object and url are mandatory
     * 
     * @param url
     * @param object
     * @param headers
     * @return String response on Json format
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doPatch(String url, Object object,Map<String,String> headers) throws IOException, URISyntaxException {
        return doRequest(new HttpPatch(new URI(url)), object,headers);
    }

    /**
     * 
     * Do delete with headers, Headers can be null
     * 
     * @param url (Mandatory)
     * @param headers (Nulleable)
     * @return String response on Json format
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doDelete(String url,Map<String,String> headers) throws IOException, URISyntaxException {
        httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(new URI(url));
        addHeaders(httpDelete,headers);
        HttpResponse httpResponse = httpClient.execute(httpDelete);
        return handleResponse(httpResponse);
    }

    /**
     * 
     * execute the request returning the response on Json Format
     * 
     * @param httpRequest
     * @param object
     * @param headers
     * @return String response on Json format
     * @throws IOException
     */
    private String doRequest(HttpEntityEnclosingRequestBase httpRequest, Object object,Map<String,String> headers) throws IOException {
        httpClient = HttpClients.createDefault();
        Gson gson = new Gson();
        httpRequest.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        addHeaders(httpRequest,headers);
        httpRequest.setEntity(new StringEntity(gson.toJson(object)));
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        return handleResponse(httpResponse);
    }

    /**
     * 
     * convert entity response to Json string
     * 
     * @param httpResponse
     * @return String response on Json format
     * @throws IOException
     */
    private String handleResponse(HttpResponse httpResponse) throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String response = EntityUtils.toString(httpEntity);
        EntityUtils.consume(httpEntity); // Consume entity to free memory
        httpClient.close(); // close httpClient
        return response;
    }


    /**
     * 
     * Add all headers to request
     * 
     * @param request 
     * @param headers
     */
    private void addHeaders (HttpRequest request,Map<String, String> headers){
        if(headers!=null){
            for(Map.Entry<String, String> header : headers.entrySet()){
                request.setHeader(header.getKey(),header.getValue());
            }
        
        }
    }

}