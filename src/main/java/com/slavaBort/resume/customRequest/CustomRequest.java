package com.slavaBort.resume.customRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Vyacheslav Alekseevich
 * 21/02/2020
 */

public class CustomRequest {

    public JSONObject customGetRequest(String url, Map<String, String> paramMap) {
        if (!paramMap.isEmpty()) {
            try {
                URIBuilder uriBuilder = new URIBuilder(url);
                paramMap.forEach(uriBuilder::addParameter);

                HttpGet getRequest = new HttpGet(uriBuilder.toString());
                getRequest.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "ru-RU");
                getRequest.addHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8");

                try (CloseableHttpClient httpClient = HttpClients.createDefault();
                     CloseableHttpResponse response = httpClient.execute(getRequest)) {
                    int statusCode = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    if (statusCode == 200) {
                        if (entity != null) {
                            return new JSONObject(EntityUtils.toString(entity));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
