package com.netblizzard.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class BasicHttpResponse implements HttpResponseInterface {
	private HttpResponse response;
	public BasicHttpResponse(){}
	public BasicHttpResponse( HttpResponse res ){
		this.response = res;
	}
	
	public void setHttpResponse( HttpResponse res ){
		this.response = res;
	}
	
	/* (non-Javadoc)
	 * @see com.footprint.common.httpclient.HttpResponseInterface#getResponse(com.footprint.common.httpclient.HttpRequestInterface)
	 */
	public SearchResponse getResponse() throws IllegalStateException, IOException{
		SearchResponse result = new SearchResponse();
		result.setLogTime( new Date() );
		HttpEntity entity = response.getEntity();
		StringBuilder str = new StringBuilder();
		if (entity != null) {
		    InputStream instream = entity.getContent();
		    InputStreamReader isr = new InputStreamReader(instream, "utf-8");
		    BufferedReader br = new BufferedReader(isr);
		    String tmpLine;
		    int line = 0;
			while ((tmpLine = br.readLine()) != null && (++line < 3000)) {
				// System.out.println(begin);
				str.append(tmpLine + "\n");
			}
		    br.close();
		    isr.close();
		    instream.close();
		}
		result.setEntity( str.toString() );
		return result;
	}
}
