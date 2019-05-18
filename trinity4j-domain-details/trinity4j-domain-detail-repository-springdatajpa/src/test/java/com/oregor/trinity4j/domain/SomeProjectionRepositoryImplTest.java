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

import java.util.Optional;
import java.util.UUID;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** @author Christos Tsakostas */
public class SomeProjectionRepositoryImplTest extends JpaTest {

  @Autowired private SomeProjectionRepository someProjectionRepository;

  @Test
  public void shouldStoreAndRestoreSomeProjection() {
    SomeProjectionId someProjectionId = new SomeProjectionId(UuidGenerator.timeBasedUuid());
    SomeProjection someProjection = new SomeProjection(someProjectionId);

    SomeProjection storedSomeProjection = someProjectionRepository.store(someProjection);

    assertThat(storedSomeProjection).isNotNull();

    Optional<SomeProjection> optionalOfRestoredSomeProjection =
        someProjectionRepository.restore(someProjectionId);

    assertThat(optionalOfRestoredSomeProjection).isNotNull();
    assertThat(optionalOfRestoredSomeProjection).isPresent();
    assertThat(optionalOfRestoredSomeProjection.get().getId()).isEqualTo(someProjectionId);
  }

  @Test
  public void shouldGenerateNextId() {
    SomeProjectionId someProjectionId = someProjectionRepository.nextId();

    assertThat(someProjectionId).isNotNull();
    assertThat(someProjectionId.getTypeId()).isNotNull();
    assertThat(UUID.fromString(someProjectionId.getTypeId().toString())).isNotNull();
  }
}
