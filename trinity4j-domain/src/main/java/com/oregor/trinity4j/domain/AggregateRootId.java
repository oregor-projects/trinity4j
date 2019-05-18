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
 * The type Aggregate root id.
 *
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class AggregateRootId extends AbstractId<UUID> {

  private static final long serialVersionUID = 1L;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Aggregate root id. */
  protected AggregateRootId() {
    super();
  }

  protected AggregateRootId(UUID typeId) {
    super(typeId);
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Access(AccessType.PROPERTY)
  @Column(name = "root_id")
  @Override
  public UUID getTypeId() {
    return super.getTypeId();
  }
}
