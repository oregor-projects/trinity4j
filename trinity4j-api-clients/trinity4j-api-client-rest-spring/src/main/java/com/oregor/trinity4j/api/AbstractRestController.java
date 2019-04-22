/*-
 * ==========================LICENSE_START=================================
 * TRINITY4J: A set of Domain-Driven Design Libraries for Java Applications
 * ========================================================================
 * Copyright (C) 2019 Christos Tsakostas, OREGOR LTD
 * ========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===========================LICENSE_END==================================
 */

package com.oregor.trinity4j.api;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

/**
 * The type Abstract rest controller.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractRestController {

  private static final String HEADER_ACCESS_TOKEN = "X-ACCESS-TOKEN";
  private static final String HEADER_TENANT_ID = "X-Tenant-Id";

  /**
   * Gets remote ip address.
   *
   * @param httpServletRequest the http request
   * @return the remote ip address
   */
  protected String getRemoteIpAddress(HttpServletRequest httpServletRequest) {
    String remoteIpAddress = httpServletRequest.getRemoteAddr();
    if (remoteIpAddress == null) {
      remoteIpAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
    }
    if (remoteIpAddress == null) {
      remoteIpAddress = "UNKNOWN";
    }
    return remoteIpAddress;
  }

  /**
   * Gets access token.
   *
   * @param httpServletRequest the a httpServletRequest
   * @return the access token
   */
  protected String getAccessToken(HttpServletRequest httpServletRequest) {
    return httpServletRequest.getHeader(HEADER_ACCESS_TOKEN);
  }

  /**
   * Gets tenant id.
   *
   * @param httpServletRequest the a request
   * @return the tenant id
   */
  protected String getTenantId(HttpServletRequest httpServletRequest) {
    return httpServletRequest.getHeader(HEADER_TENANT_ID);
  }

  /**
   * Gets no cache headers.
   *
   * @return the no cache headers
   */
  protected HttpHeaders getNoCacheHeaders() {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Cache-Control", "no-cache");
    return responseHeaders;
  }
}
