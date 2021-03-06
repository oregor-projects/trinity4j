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
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Constructor;
import java.util.UUID;
import org.junit.Test;

/** @author Christos Tsakostas */
public class TenantIdTest extends AbstractEqualityTest<TenantId> {

  private static UUID uuid1 = UuidGenerator.timeBasedUuid();
  private static UUID uuid2 = UuidGenerator.timeBasedUuid();

  @Test
  public void shouldInstantiate() {
    TenantId tenantId = createObject1();

    assertThat(tenantId).isNotNull();
    assertThat(tenantId.getTypeId()).isNotNull();
    assertThat(tenantId.getTypeId()).isEqualTo(uuid1);
  }

  @Test
  public void shouldInstantiateWithEmptyConstructor() throws NoSuchMethodException {
    Constructor<TenantId> constructor = TenantId.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThatCode(constructor::newInstance).doesNotThrowAnyException();
  }

  @Test
  public void shouldNotInstantiateWithEmptyConstructor() throws NoSuchMethodException {
    Constructor<TenantId> constructor = TenantId.class.getDeclaredConstructor();
    assertThatThrownBy(constructor::newInstance).isInstanceOf(IllegalAccessException.class);
  }

  @Override
  public TenantId createObject1() {
    return new TenantId(uuid1);
  }

  @Override
  public TenantId createObject2() {
    return new TenantId(uuid2);
  }
}
