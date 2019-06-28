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
public class ApiCollectionResponseTest {

  private class SomeCollectionDto implements CollectionItemIdentifiable {

    @Override
    public String getId() {
      return null;
    }
  }

  private class SomeApiCollectionResponse extends ApiCollectionResponse<SomeCollectionDto> {

    public SomeApiCollectionResponse() {}

    public SomeApiCollectionResponse(List<SomeCollectionDto> items) {
      super(items);
    }
  }

  @Test
  public void shouldInitialize() {
    SomeApiCollectionResponse someApiCollectionResponse =
        new SomeApiCollectionResponse(new ArrayList<>());

    assertThat(someApiCollectionResponse.getOccurredOn()).isNotNull();
    assertThat(someApiCollectionResponse.getItems()).isNotNull();
  }

  @Test
  public void shouldInitializeWithEmptyConstructor() {
    SomeApiCollectionResponse someApiCollectionResponse = new SomeApiCollectionResponse();

    assertThat(someApiCollectionResponse.getOccurredOn()).isNotNull();
    assertThat(someApiCollectionResponse.getItems()).isNull();
  }
}
