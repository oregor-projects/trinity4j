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

package com.oregor.trinity4j.api.clients.batchprocess;

/**
 * The type Batch process message.
 *
 * @author Christos Tsakostas
 */
public class BatchProcessMessage {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private String messageType;
  private Integer pageNumber;
  private Integer pageSize;
  private String uniqueId;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Batch process message. */
  public BatchProcessMessage() {
    super();
  }

  /**
   * Instantiates a new Batch process message.
   *
   * @param messageType the message type
   * @param pageNumber the page number
   * @param pageSize the page size
   */
  public BatchProcessMessage(String messageType, Integer pageNumber, Integer pageSize) {
    this.messageType = messageType;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
  }

  /**
   * Instantiates a new Batch process message.
   *
   * @param messageType the message type
   * @param uniqueId the unique id
   */
  public BatchProcessMessage(String messageType, String uniqueId) {
    this.messageType = messageType;
    this.uniqueId = uniqueId;
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets message type.
   *
   * @return the message type
   */
  public String getMessageType() {
    return messageType;
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
   * Gets unique id.
   *
   * @return the unique id
   */
  public String getUniqueId() {
    return uniqueId;
  }

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets message type.
   *
   * @param messageType the message type
   */
  public void setMessageType(String messageType) {
    this.messageType = messageType;
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
   * Sets unique id.
   *
   * @param uniqueId the unique id
   */
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }
}
