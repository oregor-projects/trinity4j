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

import java.util.Optional;

public interface Storable<R, I> {

  /**
   * Stores the provided aggregate root to the repository.
   *
   * @param aggregateRoot The Aggregate Root
   * @return the r
   */
  R store(R aggregateRoot);

  /**
   * Restores an aggregate root from the repository, as optional, by its id.
   *
   * @param aggregateRootId must not be {@literal null}.
   * @return the optional aggregate root
   * @throws IllegalArgumentException if {@code anAbstractId} is {@literal null}
   */
  Optional<R> restore(I aggregateRootId);

  /**
   * Removes an aggregate root from the repository by its id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
  void remove(I id);
}
