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
import java.util.UUID;
import org.junit.Test;

/** @author Christos Tsakostas */
public class UuidGeneratorTest {

  @Test
  public void shouldFailToInstantiate() throws NoSuchMethodException {
    Constructor<UuidGenerator> constructor = UuidGenerator.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThatThrownBy(constructor::newInstance).isInstanceOf(InvocationTargetException.class);
  }

  @Test
  public void uuid() {
    UUID uuid = UuidGenerator.timeBasedUuid();

    assertThat(uuid).isNotNull();

    String strUuid = uuid.toString();
    assertThat(strUuid).isNotNull();

    assertThat(UUID.fromString(strUuid)).isEqualTo(uuid);
  }

  @Test
  public void fromString() {
    String uuidString = "1D5612BF59A811EB935BCB5F149B4C79";
    UUID uuid = UuidGenerator.fromString(uuidString);

    assertThat(uuid).isNotNull();

    String strUuid = uuid.toString();
    assertThat(strUuid).isNotNull();

    assertThat(UUID.fromString(strUuid)).isEqualTo(uuid);
  }
}
