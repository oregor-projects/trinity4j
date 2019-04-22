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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

/** @author Christos Tsakostas */
public class AbstractRestControllerTest {

  private HttpServletRequest httpServletRequest;
  private SomeAbstractRestController someAbstractRestController;

  @Before
  public void setUp() {
    httpServletRequest = mock(HttpServletRequest.class);
    someAbstractRestController = new SomeAbstractRestController();
  }

  @Test
  public void shouldGetRemoteIpAddress() {
    String remoteAddress = "192.168.1.1";
    given(httpServletRequest.getRemoteAddr()).willReturn(remoteAddress);

    assertThat(someAbstractRestController.getRemoteIpAddress(httpServletRequest))
        .isEqualTo(remoteAddress);
  }

  @Test
  public void shouldGetRemoteIpAddressFromHeader() {
    String remoteAddress = "192.168.1.1";
    given(httpServletRequest.getHeader("X-FORWARDED-FOR")).willReturn(remoteAddress);

    assertThat(someAbstractRestController.getRemoteIpAddress(httpServletRequest))
        .isEqualTo(remoteAddress);
  }

  @Test
  public void shouldFailToGetRemoteIpAddress() {
    assertThat(someAbstractRestController.getRemoteIpAddress(httpServletRequest))
        .isEqualTo("UNKNOWN");
  }

  @Test
  public void getAccessToken() {
    someAbstractRestController.getAccessToken(httpServletRequest);

    verify(httpServletRequest).getHeader(eq("X-ACCESS-TOKEN"));
  }

  @Test
  public void getHeaderTenantId() {
    someAbstractRestController.getTenantId(httpServletRequest);

    verify(httpServletRequest).getHeader(eq("X-Tenant-Id"));
  }

  @Test
  public void getNoCacheHeaders() {
    HttpHeaders httpHeaders = someAbstractRestController.getNoCacheHeaders();

    assertThat(httpHeaders).isNotNull();
    assertThat(httpHeaders.get("Cache-Control")).isEqualTo(Arrays.asList("no-cache"));
  }
}
