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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The type Api paged collection request.
 *
 * @author Christos Tsakostas
 */
public abstract class ApiPagedCollectionRequest extends ApiCollectionRequest {

  @Schema(hidden = true)
  private String seekMethodLeftOffValue;

  private Integer pageNumber;
  private Integer pageSize;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Api paged collection request. */
  public ApiPagedCollectionRequest() {
    super();
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets left off.
   *
   * @return the left off
   */
  public String getSeekMethodLeftOffValue() {
    return seekMethodLeftOffValue;
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

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets left off.
   *
   * @param seekMethodLeftOffValue the left off
   */
  public void setSeekMethodLeftOffValue(String seekMethodLeftOffValue) {
    this.seekMethodLeftOffValue = seekMethodLeftOffValue;
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
}
