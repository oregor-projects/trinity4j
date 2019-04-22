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

import javax.persistence.Entity;

/**
 * The type Multi tenant aggregate root.
 *
 * @author Christos Tsakostas
 */
@Entity
public class MultiTenantAggregateRoot extends TenantAggregateRoot<MultiTenantAggregateRootId> {

  private static final long serialVersionUID = 1L;

  private MultiTenantAggregateRoot() {
    super();
  }

  /**
   * Instantiates a new Multi tenant aggregate root.
   *
   * @param id the id
   */
  public MultiTenantAggregateRoot(MultiTenantAggregateRootId id) {
    super(id);

    registerDomainMessage(new MultiTenantAggregateRootCreated());
  }
}
