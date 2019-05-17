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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

/**
 * The type Projection test.
 *
 * @author Christos Tsakostas
 */
public class ProjectionTest {

  /**
   * Should initialize private no args constructor.
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
    Constructor<SomeProjection> constructor = SomeProjection.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    SomeProjection SomeProjection = constructor.newInstance();
    assertThat(SomeProjection).isNotNull();
  }

  /** Should set and get version. */
  @Test
  public void shouldSetAndGetVersion() {
    SomeProjection someProjection =
        new SomeProjection(new SomeProjectionId(UuidGenerator.timeBasedUuid()));

    someProjection.setVersion(1);
    assertThat(someProjection.getVersion()).isEqualTo(1);
  }
}
