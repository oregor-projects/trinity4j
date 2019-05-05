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
 * The interface Tenant repository.
 *
 * @param <R> the type parameter
 * @param <I> the type parameter
 */
public interface TenantRepository<R extends TenantAggregateRoot<I>, I extends AggregateRootId>
    extends IdentityProvider<I>, Storable<R, I> {

  /**
   * Returns all the aggregate root entities stored in the repository, provided a Tenant UUID.
   *
   * @param tenantId the tenant id
   * @return the iterable
   */
  Iterable<R> findAll(TenantId tenantId);

  /**
   * Returns pages of all the aggregate root entities stored in the repository, provided a Tenant
   * UUID.
   *
   * @param tenantId the tenant id
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the paginated
   */
  Paginated<R> findPaginated(TenantId tenantId, Integer pageNumber, Integer pageSize);
}
