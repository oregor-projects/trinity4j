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
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class for Aggregate Entities.
 *
 * @param <I> the type parameter
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class AbstractAggregateEntity<I extends AbstractAggregateEntityId<?>>
    implements Identifiable<I> {

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  @EmbeddedId private I id;

  private LocalDateTime createdOn;

  private LocalDateTime updatedOn;

  @Version private Integer version;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate entity.
   *
   * <p>No args constructor for ORM frameworks.
   */
  @SuppressWarnings("CPD-START")
  protected AbstractAggregateEntity() {
    super();
    setCreatedOn(LocalDateTime.now());
  }

  /**
   * Instantiates a new Aggregate entity.
   *
   * @param id the id
   */
  protected AbstractAggregateEntity(I id) {
    setId(id);
    setCreatedOn(LocalDateTime.now());
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets created on.
   *
   * @return the created on
   */
  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  /**
   * Gets updated on.
   *
   * @return the updated on
   */
  public LocalDateTime getUpdatedOn() {
    return updatedOn;
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

  /**
   * Sets updated on.
   *
   * @param updatedOn the updated on
   */
  public void setUpdatedOn(LocalDateTime updatedOn) {
    Assertion.isNotNull(updatedOn, "updatedOn cannot be null");
    this.updatedOn = updatedOn;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  private void setId(I id) {
    Assertion.isNotNull(id, "id cannot be null");
    this.id = id;
  }

  private void setCreatedOn(LocalDateTime createdOn) {
    Assertion.isNotNull(createdOn, "createdOn cannot be null");
    this.createdOn = createdOn;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public I getId() {
    return id;
  }
}
