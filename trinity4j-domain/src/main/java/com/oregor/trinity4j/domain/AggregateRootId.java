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
 * The type Aggregate root id.
 *
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class AggregateRootId implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID rootId;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate root id.
   *
   * <p>No args constructor for ORM frameworks.
   */
  protected AggregateRootId() {
    super();
  }

  /**
   * Instantiates a new Aggregate root id.
   *
   * @param rootId the root timeBasedUuid
   */
  protected AggregateRootId(UUID rootId) {
    setRootId(rootId);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets root timeBasedUuid.
   *
   * @return the root timeBasedUuid
   */
  public UUID getRootId() {
    return rootId;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets root timeBasedUuid.
   *
   * @param rootId the root timeBasedUuid
   */
  private void setRootId(UUID rootId) {
    Assertion.isNotNull(rootId, "rootId is required");
    this.rootId = rootId;
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
    AggregateRootId that = (AggregateRootId) o;
    return Objects.equals(rootId, that.rootId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rootId);
  }
}
