package com.dm.customer.httpclient;

import com.dm.customer.exception.RestApiResponse;
import io.micronaut.http.HttpResponse;

/**
 * Http response wrapper class is used to return the http response
 */
public class HttpResponseWrapper {

    private HttpResponseWrapper() {
        // private constructor to prevent instantiation from outside
    }

    /**
     * Returns the ok http response
     * @param restApiResponse
     * @return
     */
    public static HttpResponse<RestApiResponse> ok(RestApiResponse restApiResponse) {
        return HttpResponse.ok(restApiResponse);
    }

    /**
     * Returns the bad request http response
     * @param restApiResponse
     * @return
     */
    public static HttpResponse<RestApiResponse> badRequest(RestApiResponse restApiResponse) {
        return HttpResponse.badRequest(restApiResponse);
    }

    /**
     * Returns the no content found http response
     * @return
     */
    public static HttpResponse<RestApiResponse> noContentFound() {
        return HttpResponse.noContent();
    }
}
