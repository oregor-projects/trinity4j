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

import java.util.UUID;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * The type Projection id.
 *
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class ProjectionId extends AbstractUuid {

  private static final long serialVersionUID = 1L;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Projection id. */
  protected ProjectionId() {
    super();
  }

  /**
   * Instantiates a new Projection id.
   *
   * @param uuid the uuid
   */
  protected ProjectionId(UUID uuid) {
    super(uuid);
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Access(AccessType.PROPERTY)
  @Column(name = "projection_id")
  @Override
  public UUID getUuid() {
    return super.getUuid();
  }
}
