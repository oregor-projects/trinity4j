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
 * The type Abstract uuid.
 *
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class AbstractUuid implements Serializable {

  private static final long serialVersionUID = 1L;

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private UUID uuid;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Abstract uuid. */
  protected AbstractUuid() {
    super();
  }

  /**
   * Instantiates a new Abstract uuid.
   *
   * @param uuid the uuid
   */
  protected AbstractUuid(UUID uuid) {
    setUuid(uuid);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets uuid.
   *
   * @return the uuid
   */
  public UUID getUuid() {
    return uuid;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets uuid.
   *
   * @param uuid the uuid
   */
  public void setUuid(UUID uuid) {
    Assertion.isNotNull(uuid, "the uuid is required");
    this.uuid = uuid;
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
    AbstractUuid that = (AbstractUuid) o;
    return Objects.equals(uuid, that.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}
