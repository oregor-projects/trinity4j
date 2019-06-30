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

/**
 * Contract for projection repositories.
 *
 * @param <O> the type parameter
 * @param <I> the type parameter of the projection Id
 * @author Christos Tsakostas
 */
public interface ProjectionRepository<O extends Projection<I>, I extends ProjectionId>
    extends IdentityProvider<I>, Storable<O, I> {

  /**
   * Returns all the aggregate root entities stored in the repository.
   *
   * @return the iterable
   */
  Iterable<O> findAll();

  /**
   * Returns pages of all the aggregate root entities stored in the repository.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the paginated
   */
  Paginated<O> findPaginated(Integer pageNumber, Integer pageSize);
}
