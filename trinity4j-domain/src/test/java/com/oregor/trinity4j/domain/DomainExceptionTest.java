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

package com.oregor.trinity4j.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

/** @author Christos Tsakostas */
class DomainExceptionTest {

  @Test
  void getKey() {
    assertThatThrownBy(() -> throwDomainException("someMessageKey")).hasMessage("someMessageKey");
  }

  @Test
  void getKeyAndArguments() {
    Object[] arguments = {0};
    assertThatThrownBy(() -> throwDomainException("someMessageKey", arguments))
        .hasMessage("someMessageKey");
  }

  @Test
  void nullMessageKey() {
    assertThatThrownBy(() -> throwDomainException(null)).hasMessage(null);
  }

  private void throwDomainException(String messageKey) {
    throw new DomainException(messageKey);
  }

  private void throwDomainException(String messageKey, Object[] arguments) {
    throw new DomainException(messageKey, arguments);
  }
}
