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
import javax.persistence.Version;

/**
 * Base class for Projections.
 *
 * @param <I> the type parameter
 * @author Christos Tsakostas
 */
@Access(AccessType.FIELD)
@MappedSuperclass
public abstract class Projection<I extends ProjectionId> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private I id;

  @Version private Integer version;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Projection.
   *
   * <p>No args constructor for ORM Frameworks.
   */
  protected Projection() {
    super();
  }

  /**
   * Instantiates a new Projection.
   *
   * @param id the id
   */
  protected Projection(I id) {
    setId(id);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets id.
   *
   * @return the id
   */
  @Access(AccessType.PROPERTY)
  @EmbeddedId
  public I getId() {
    return id;
  }

  /**
   * Gets version.
   *
   * @return the version
   */
  public Integer getVersion() {
    return version;
  }

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(Integer version) {
    Assertion.isNotNull(version, "version cannot be null");
    this.version = version;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets id.
   *
   * @param id the id
   */
  private void setId(I id) {
    Assertion.isNotNull(id, "id cannot be null");
    this.id = id;
  }
}
