/*-
 * ==========================LICENSE_START=================================
 * TRINITY4J: A set of Domain-Driven Design Libraries for Java Applications
 * ========================================================================
 * Copyright (C) 2019 - 2020 Christos Tsakostas, OREGOR LTD
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

import java.util.UUID;
import org.junit.jupiter.api.Test;

/** @author Christos Tsakostas */
class AbstractApiServiceTest {

  class SomeAbstractApiService extends AbstractApiService {

    public SomeAbstractApiService() {
      super();
    }
  }

  @Test
  void ensureUuid() {
    SomeAbstractApiService someAbstractApiService = new SomeAbstractApiService();
    assertThat(someAbstractApiService.ensureUuid("08EC2798FB2411EAA061CD12E69408B8"))
        .isEqualTo(UUID.fromString("08EC2798-FB24-11EA-A061-CD12E69408B8"));
  }
}
