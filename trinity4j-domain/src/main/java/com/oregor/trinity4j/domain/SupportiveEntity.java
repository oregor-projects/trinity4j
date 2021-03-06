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
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * The type Supportive entity.
 *
 * @param <I> the type parameter
 */
@MappedSuperclass
public abstract class SupportiveEntity<I extends SupportiveEntityId<?>> implements Identifiable<I> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  @EmbeddedId private I id;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Helper entity.
   *
   * <p>No args constructor for Persistence Frameworks.
   */
  protected SupportiveEntity() {
    super();
  }

  /**
   * Instantiates a new Helper entity.
   *
   * @param id the id
   */
  public SupportiveEntity(I id) {
    setId(id);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets id.
   *
   * @param id the id
   */
  private void setId(I id) {
    Assertion.isNotNull(id, "id is required");
    this.id = id;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Access(AccessType.PROPERTY)
  @EmbeddedId
  @Override
  public I getId() {
    return id;
  }
}
