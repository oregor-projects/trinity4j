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
 * The type Api collection response.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public abstract class ApiCollectionResponse<T extends CollectionItemIdentifiable>
    extends ApiResponse {

  private List<T> items;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Api page response. */
  protected ApiCollectionResponse() {
    super();
  }

  /**
   * Instantiates a new Api collection response.
   *
   * @param error the error
   */
  protected ApiCollectionResponse(ApiError error) {
    super(error);
  }

  /**
   * Instantiates a new Api collection response.
   *
   * @param items the items
   */
  protected ApiCollectionResponse(List<T> items) {
    setItems(items);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets items.
   *
   * @return the items
   */
  public List<T> getItems() {
    return items;
  }

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets items.
   *
   * @param items the items
   */
  public void setItems(List<T> items) {
    this.items = items;
  }
}
