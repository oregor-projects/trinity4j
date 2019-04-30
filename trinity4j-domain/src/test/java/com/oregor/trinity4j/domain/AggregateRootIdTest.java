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

import java.util.UUID;
import org.junit.Test;

/**
 * The type Aggregate root id test.
 *
 * @author Christos Tsakostas
 */
public class AggregateRootIdTest extends AbstractEqualityTest<AggregateRootId> {

  private static UUID uuid1 = UuidGenerator.timeBasedUuid();
  private static UUID uuid2 = UuidGenerator.timeBasedUuid();

  @Test
  public void shouldCreateAggregateRootId() {
    AggregateRootId aggregateRootId = createObject1();

    assertThat(aggregateRootId).isNotNull();
    assertThat(aggregateRootId.getRootId()).isNotNull();
    assertThat(aggregateRootId.getRootId()).isEqualTo(uuid1);
  }

  @Test
  public void shouldCreateAggregateRootIdWithEmptyConstructor() {
    AggregateRootId aggregateRootId = new SomeAggregateRootId();

    assertThat(aggregateRootId).isNotNull();
    assertThat(aggregateRootId.getRootId()).isNull();
  }

  @Override
  public AggregateRootId createObject1() {
    return new SomeAggregateRootId(uuid1);
  }

  @Override
  public AggregateRootId createObject2() {
    return new SomeAggregateRootId(uuid2);
  }
}