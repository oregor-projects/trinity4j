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
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.MappedSuperclass;

/**
 * The type Aggregate entity id.
 *
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class AggregateEntityId implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID entityId;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate root id.
   *
   * <p>No args constructor for ORM frameworks.
   */
  protected AggregateEntityId() {
    super();
  }

  /**
   * Instantiates a new Aggregate entity id.
   *
   * @param entityId the entity id
   */
  protected AggregateEntityId(UUID entityId) {
    setEntityId(entityId);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets entity id.
   *
   * @return the entity id
   */
  public UUID getEntityId() {
    return entityId;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets entity id.
   *
   * @param entityId the entity id
   */
  private void setEntityId(UUID entityId) {
    Assertion.isNotNull(entityId, "entityId is required");
    this.entityId = entityId;
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
    AggregateEntityId that = (AggregateEntityId) o;
    return Objects.equals(entityId, that.entityId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityId);
  }
}
