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

import com.oregor.trinity4j.commons.assertion.Assertion;
import javax.persistence.MappedSuperclass;

/**
 * The type Tenant aggregate root.
 *
 * @param <I> the type parameter
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class TenantAggregateRoot<I extends AggregateRootId> extends AggregateRoot<I> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private TenantId tenantId;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Tenant aggregate root. */
  protected TenantAggregateRoot() {
    super();
  }

  /**
   * Instantiates a new Tenant aggregate root.
   *
   * @param id the id
   * @param tenantId the tenant id
   */
  protected TenantAggregateRoot(I id, TenantId tenantId) {
    super(id);
    setTenantId(tenantId);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets tenant id.
   *
   * @return the tenant id
   */
  public TenantId getTenantId() {
    return tenantId;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  protected <D extends DomainMessage> void fillDomainMessageProperties(D domainMessage) {
    super.fillDomainMessageProperties(domainMessage);
    domainMessage.setTenantId(getTenantId().getTypeId());
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets tenant id.
   *
   * @param tenantId the tenant id
   */
  private void setTenantId(TenantId tenantId) {
    Assertion.isNotNull(tenantId, "tenantId is required");
    this.tenantId = tenantId;
  }
}
