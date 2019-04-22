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

import java.util.UUID;

/** @author Christos Tsakostas */
public class AggregateEntityIdTest extends AbstractEqualityTest<AggregateEntityId> {

  private static UUID uuid1 = UuidGenerator.timeBasedUuid();
  private static UUID uuid2 = UuidGenerator.timeBasedUuid();

  @Override
  public AggregateEntityId createObject1() {
    return new SomeAggregateEntityId(uuid1);
  }

  @Override
  public AggregateEntityId createObject2() {
    return new SomeAggregateEntityId(uuid2);
  }
}
