/*
 * Copyright (c) 2016 VMware, Inc. All Rights Reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met: Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.vmware.burp.extension.domain;

import burp.BurpExtender;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.net.URL;

@JsonIgnoreProperties(value = { "request", "response" })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_NULL)
public class HttpMessage {

   @XmlElement(required = true)
   private String host;

   @XmlElement(required = true)
   private int port;

   @XmlElement(required = true)
   private String protocol;

   @XmlElement(required = true)
   private URL url;

   @XmlElement(required = true)
   private short statusCode;

   @XmlTransient
   private byte[] request;

   @XmlTransient
   private byte[] response;

   @XmlElement(required = true)
   private String comment;

   @XmlElement(required = true)
   private String highlight;

   public HttpMessage() {
   }

   public HttpMessage(IHttpRequestResponse iHttpRequestResponse) {
      this.host = iHttpRequestResponse.getHttpService().getHost();
      this.port = iHttpRequestResponse.getHttpService().getPort();
      this.protocol = iHttpRequestResponse.getHttpService().getProtocol();
      IExtensionHelpers helpers = BurpExtender.getInstance().getHelpers();
      this.url = helpers.analyzeRequest(iHttpRequestResponse).getUrl();
      if ((iHttpRequestResponse.getResponse() != null)) {
         this.statusCode = helpers.analyzeResponse(iHttpRequestResponse.getResponse())
               .getStatusCode();
      }
      this.request = iHttpRequestResponse.getRequest();
      this.response = iHttpRequestResponse.getResponse();
      this.comment = iHttpRequestResponse.getComment();
      this.highlight = iHttpRequestResponse.getHighlight();
   }

   public String getHost() {
      return host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public int getPort() {
      return port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public String getProtocol() {
      return protocol;
   }

   public void setProtocol(String protocol) {
      this.protocol = protocol;
   }

   public URL getUrl() {
      return url;
   }

   public void setUrl(URL url) {
      this.url = url;
   }

   public short getStatusCode() {
      return statusCode;
   }

   public void setStatusCode(short statusCode) {
      this.statusCode = statusCode;
   }

   public byte[] getRequest() {
      return request;
   }

   public void setRequest(byte[] request) {
      this.request = request;
   }

   public byte[] getResponse() {
      return response;
   }

   public void setResponse(byte[] response) {
      this.response = response;
   }

   public String getComment() {
      return comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public String getHighlight() {
      return highlight;
   }

   public void setHighlight(String highlight) {
      this.highlight = highlight;
   }

}
