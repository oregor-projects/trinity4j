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

import org.junit.Test;

/** @author Christos Tsakostas */
public class ApiPagedCollectionRequestTest {

  private class SomeApiPagedCollectionRequest extends ApiPagedCollectionRequest {}

  @Test
  public void shouldInitialize() {
    SomeApiPagedCollectionRequest someApiPagedCollectionRequest =
        new SomeApiPagedCollectionRequest();
    someApiPagedCollectionRequest.setPageNumber(0);
    someApiPagedCollectionRequest.setPageSize(10);

    assertThat(someApiPagedCollectionRequest.getOccurredOn()).isNotNull();
    assertThat(someApiPagedCollectionRequest.getPageNumber()).isEqualTo(0);
    assertThat(someApiPagedCollectionRequest.getPageSize()).isEqualTo(10);
  }
}
