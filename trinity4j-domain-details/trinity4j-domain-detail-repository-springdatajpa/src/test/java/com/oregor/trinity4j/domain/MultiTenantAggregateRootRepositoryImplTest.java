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
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** @author Christos Tsakostas */
public class MultiTenantAggregateRootRepositoryImplTest extends JpaTest {

  @Autowired private MultiTenantAggregateRootRepository multiTenantAggregateRootRepository;

  private TenantId tenantId;

  @Before
  public void setUp() {
    tenantId = new TenantId(UuidGenerator.timeBasedUuid());
  }

  @Test
  public void shouldStoreAndRestoreSomeAggregateRoot() {
    MultiTenantAggregateRootId multiTenantAggregateRootId =
        new MultiTenantAggregateRootId(UuidGenerator.timeBasedUuid());

    MultiTenantAggregateRoot multiTenantAggregateRoot =
        new MultiTenantAggregateRoot(multiTenantAggregateRootId, tenantId);

    assertThat(multiTenantAggregateRoot.getDomainMessages().size()).isEqualTo(1);

    MultiTenantAggregateRoot storedMultiTenantAggregateRoot =
        multiTenantAggregateRootRepository.store(multiTenantAggregateRoot);

    assertThat(storedMultiTenantAggregateRoot).isNotNull();
    assertThat(storedMultiTenantAggregateRoot.getDomainMessages()).isNotNull();
    assertThat(storedMultiTenantAggregateRoot.getDomainMessages().size()).isEqualTo(1);

    Optional<MultiTenantAggregateRoot> optionalOfRestoredMultiTenantAggregateRoot =
        multiTenantAggregateRootRepository.restore(multiTenantAggregateRootId);

    assertThat(optionalOfRestoredMultiTenantAggregateRoot).isNotNull();
    assertThat(optionalOfRestoredMultiTenantAggregateRoot).isPresent();
    assertThat(optionalOfRestoredMultiTenantAggregateRoot.get().getId())
        .isEqualTo(multiTenantAggregateRootId);
  }

  @Test
  public void shouldFindPaginated() {
    Paginated<MultiTenantAggregateRoot> multiTenantAggregateRootPaginated =
        multiTenantAggregateRootRepository.findPaginated(tenantId, 0, 10);

    assertThat(multiTenantAggregateRootPaginated).isNotNull();
    assertThat(multiTenantAggregateRootPaginated.getItems()).isNotNull();
    assertThat(multiTenantAggregateRootPaginated.getTotalElements()).isEqualTo(0);
    assertThat(multiTenantAggregateRootPaginated.getTotalPages()).isEqualTo(0);
  }

  @Test
  public void shouldGenerateNextId() {
    MultiTenantAggregateRootId multiTenantAggregateRootId =
        multiTenantAggregateRootRepository.nextId();

    assertThat(multiTenantAggregateRootId).isNotNull();
    assertThat(multiTenantAggregateRootId.getUuid()).isNotNull();
  }
}
