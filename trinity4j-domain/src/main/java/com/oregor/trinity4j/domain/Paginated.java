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
 * Container for fetching data in pages.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public class Paginated<T> {

  private Iterable<T> items;
  private Integer totalPages;
  private Long totalElements;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Paginated.
   *
   * @param items the items
   * @param totalPages the total pages
   * @param totalElements the total elements
   */
  public Paginated(Iterable<T> items, Integer totalPages, Long totalElements) {
    setItems(items);
    setTotalPages(totalPages);
    setTotalElements(totalElements);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets items.
   *
   * @return the items
   */
  public Iterable<T> getItems() {
    return items;
  }

  /**
   * Gets total pages.
   *
   * @return the total pages
   */
  public Integer getTotalPages() {
    return totalPages;
  }

  /**
   * Gets total elements.
   *
   * @return the total elements
   */
  public Long getTotalElements() {
    return totalElements;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets items.
   *
   * @param items the items
   */
  private void setItems(Iterable<T> items) {
    this.items = items;
  }

  /**
   * Sets total pages.
   *
   * @param totalPages the total pages
   */
  private void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  /**
   * Sets total elements.
   *
   * @param totalElements the total elements
   */
  private void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }
}
