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
import javax.persistence.MappedSuperclass;

/**
 * The type Abstract id.
 *
 * @param <U> the type parameter
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class AbstractId<U extends Serializable> implements Serializable {

  private static final long serialVersionUID = 1L;

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  /** The type Id will usually be a UUID, Long or String. */
  private U typeId;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Abstract id. */
  protected AbstractId() {
    super();
  }

  /**
   * Instantiates a new Abstract id.
   *
   * @param typeId the type id
   */
  public AbstractId(U typeId) {
    setTypeId(typeId);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets type id.
   *
   * @return the type id
   */
  public U getTypeId() {
    return typeId;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets type id.
   *
   * @param typeId the type id
   */
  private void setTypeId(U typeId) {
    Assertion.isNotNull(typeId, "typeId is required");
    this.typeId = typeId;
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
    AbstractId<?> that = (AbstractId<?>) o;
    return Objects.equals(typeId, that.typeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeId);
  }
}
