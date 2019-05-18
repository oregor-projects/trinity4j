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

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/** @author Christos Tsakostas */
public class ApiPagedCollectionResponseTest {

  private class SomeCollectionDto {}

  private class SomeApiPagedCollectionResponse
      extends ApiPagedCollectionResponse<SomeCollectionDto> {

    public SomeApiPagedCollectionResponse() {
      super();
    }

    public SomeApiPagedCollectionResponse(
        List<SomeCollectionDto> items,
        Integer totalPages,
        Long totalElements,
        Integer pageNumber,
        Integer pageSize) {
      super(items, totalPages, totalElements, pageNumber, pageSize);
    }
  }

  @Test
  public void shouldInitialize() {
    SomeApiPagedCollectionResponse someApiPagedCollectionResponse =
        new SomeApiPagedCollectionResponse(new ArrayList<>(), 10, 100L, 1, 5);

    assertThat(someApiPagedCollectionResponse.getOccurredOn()).isNotNull();
    assertThat(someApiPagedCollectionResponse.getItems()).isNotNull();
    assertThat(someApiPagedCollectionResponse.getTotalElements()).isEqualTo(100L);
    assertThat(someApiPagedCollectionResponse.getTotalPages()).isEqualTo(10);
    assertThat(someApiPagedCollectionResponse.getPageNumber()).isEqualTo(1);
    assertThat(someApiPagedCollectionResponse.getPageSize()).isEqualTo(5);
  }

  @Test
  public void shouldInitializeWithEmptyConstructor() {
    SomeApiPagedCollectionResponse someApiPagedCollectionResponse =
        new SomeApiPagedCollectionResponse();

    assertThat(someApiPagedCollectionResponse.getOccurredOn()).isNotNull();
    assertThat(someApiPagedCollectionResponse.getItems()).isNull();
  }
}
