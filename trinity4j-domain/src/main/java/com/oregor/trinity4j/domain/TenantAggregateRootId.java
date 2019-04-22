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

import java.util.Objects;
import java.util.UUID;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TenantAggregateRootId extends AggregateRootId {

  private static final long serialVersionUID = 1L;

  private UUID tenantId;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate root id with tenant id.
   *
   * <p>No args constructor for ORM frameworks.
   */
  protected TenantAggregateRootId() {
    super();
  }

  /**
   * Instantiates a new Aggregate root id with tenant id.
   *
   * @param rootId the root id
   * @param tenantId the tenant id
   */
  public TenantAggregateRootId(UUID rootId, UUID tenantId) {
    super(rootId);
    setTenantId(tenantId);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets tenant timeBasedUuid.
   *
   * @return the tenant timeBasedUuid
   */
  public UUID getTenantId() {
    return tenantId;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets tenant timeBasedUuid.
   *
   * @param tenantId the tenant timeBasedUuid
   */
  private void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TenantAggregateRootId that = (TenantAggregateRootId) o;
    return Objects.equals(tenantId, that.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), tenantId);
  }
}
