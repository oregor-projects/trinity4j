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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

/**
 * The type Aggregate root test.
 *
 * @author Christos Tsakostas
 */
public class AggregateRootTest {

  /**
   * Should initialize private no-args constructor.
   *
   * @throws NoSuchMethodException the no such method exception
   * @throws IllegalAccessException the illegal access exception
   * @throws InvocationTargetException the invocation target exception
   * @throws InstantiationException the instantiation exception
   */
  @Test
  public void shouldInitializePrivateNoArgsConstructor()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    Constructor<SomeAggregateRoot> constructor = SomeAggregateRoot.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    SomeAggregateRoot someAggregateRoot = constructor.newInstance();
    assertThat(someAggregateRoot).isNotNull();
  }

  /** Should register and clear domain messages. */
  @Test
  public void shouldRegisterAndClearDomainMessages() {
    SomeAggregateRoot someAggregateRoot =
        new SomeAggregateRoot(new SomeAggregateRootId(UuidGenerator.timeBasedUuid()));

    assertThat(someAggregateRoot).isNotNull();
    assertThat(someAggregateRoot.getId()).isNotNull();
    assertThat(someAggregateRoot.getDomainMessages()).isEmpty();

    someAggregateRoot.registerDomainMessage(new SomeEventOccurred());
    assertThat(someAggregateRoot.getDomainMessages().size()).isEqualTo(1);

    someAggregateRoot.clearDomainMessages();
    assertThat(someAggregateRoot.getDomainMessages()).isEmpty();
  }

  @Test
  public void shouldFailToRegisterNoAnnotatedDomainEvent() {
    SomeAggregateRoot someAggregateRoot =
        new SomeAggregateRoot(new SomeAggregateRootId(UuidGenerator.timeBasedUuid()));

    assertThatThrownBy(
            () -> someAggregateRoot.registerDomainMessage(new SomeNoAnnotatedDomainEvent()))
        .isInstanceOf(IllegalStateException.class);
  }

  /** Should set and get version. */
  @Test
  public void shouldSetAndGetVersion() {
    SomeAggregateRoot someAggregateRoot =
        new SomeAggregateRoot(new SomeAggregateRootId(UuidGenerator.timeBasedUuid()));

    someAggregateRoot.setVersion(1);
    assertThat(someAggregateRoot.getVersion()).isEqualTo(1);
  }

  /** Should fail to set null domain message. */
  @Test
  public void shouldFailToSetNullDomainMessage() {
    SomeAggregateRoot someAggregateRoot =
        new SomeAggregateRoot(new SomeAggregateRootId(UuidGenerator.timeBasedUuid()));

    assertThatThrownBy(() -> someAggregateRoot.registerDomainMessage(null))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
