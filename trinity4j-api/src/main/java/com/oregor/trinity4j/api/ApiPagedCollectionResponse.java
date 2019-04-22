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

package com.oregor.trinity4j.api;

import java.util.List;

/**
 * The type Api paged collection response.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public abstract class ApiPagedCollectionResponse<T> extends ApiCollectionResponse<T> {

  private Integer totalPages;
  private Long totalElements;
  private Integer pageNumber;
  private Integer pageSize;
  // TODO: The following is for Spring compatibility in PolyGenesis - check how it can be avoided.
  private Integer number;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Api page response. */
  public ApiPagedCollectionResponse() {
    super();
  }

  /**
   * Instantiates a new Api paged collection response.
   *
   * @param items the items
   * @param totalPages the total pages
   * @param totalElements the total elements
   * @param pageNumber the page number
   * @param pageSize the page size
   */
  public ApiPagedCollectionResponse(
      List<T> items, Integer totalPages, Long totalElements, Integer pageNumber, Integer pageSize) {
    super(items);
    setTotalPages(totalPages);
    setTotalElements(totalElements);
    setPageNumber(pageNumber);
    setPageSize(pageSize);
    setNumber(pageNumber);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

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

  /**
   * Gets page number.
   *
   * @return the page number
   */
  public Integer getPageNumber() {
    return pageNumber;
  }

  /**
   * Gets page size.
   *
   * @return the page size
   */
  public Integer getPageSize() {
    return pageSize;
  }

  /**
   * Gets number.
   *
   * @return the number
   */
  public Integer getNumber() {
    return number;
  }

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets total pages.
   *
   * @param totalPages the total pages
   */
  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  /**
   * Sets total elements.
   *
   * @param totalElements the total elements
   */
  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  /**
   * Sets page number.
   *
   * @param pageNumber the page number
   */
  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  /**
   * Sets page size.
   *
   * @param pageSize the page size
   */
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  /**
   * Sets number.
   *
   * @param number the number
   */
  public void setNumber(Integer number) {
    this.number = number;
  }
}
