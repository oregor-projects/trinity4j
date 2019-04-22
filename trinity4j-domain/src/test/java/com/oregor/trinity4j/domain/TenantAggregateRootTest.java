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

/** @author Christos Tsakostas */
public class TenantAggregateRootTest {

  private static UUID rootUuid = UuidGenerator.timeBasedUuid();
  private static UUID tenantUuid = UuidGenerator.timeBasedUuid();

  @Test
  public void shouldFillDomainMessageProperties() {
    SomeTenantAggregateRoot someTenantAggregateRoot =
        new SomeTenantAggregateRoot(new SomeTenantAggregateRootId(rootUuid, tenantUuid));

    someTenantAggregateRoot.registerDomainMessage(new SomeDomainEvent());

    assertThat(someTenantAggregateRoot.getDomainMessages().size()).isEqualTo(1);

    DomainMessage domainMessage = someTenantAggregateRoot.getDomainMessages().get(0);
    assertThat(domainMessage.getRootId()).isEqualTo(rootUuid);
    assertThat(domainMessage.getTenantId()).isEqualTo(tenantUuid);
  }
}
