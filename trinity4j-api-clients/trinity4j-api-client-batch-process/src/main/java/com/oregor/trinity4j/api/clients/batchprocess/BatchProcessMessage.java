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

import com.oregor.trinity4j.commons.assertion.Assertion;

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
  private Boolean dryRun;

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  /**
   * For fetching page batch process message.
   *
   * @param messageType the message type
   * @param pageNumber the page number
   * @param pageSize the page size
   * @param dryRun the dry run
   * @return the batch process message
   */
  public static BatchProcessMessage forFetchingPage(
      String messageType, Integer pageNumber, Integer pageSize, Boolean dryRun) {
    Assertion.isNotNull(messageType, "messageType is required");
    Assertion.isNotNull(pageNumber, "pageNumber is required");
    Assertion.isNotNull(pageSize, "pageSize is required");
    Assertion.isNotNull(dryRun, "dryRun is required");

    return new BatchProcessMessage(messageType, pageNumber, pageSize, dryRun);
  }

  /**
   * For processing batch process message.
   *
   * @param messageType the message type
   * @param uniqueId the unique id
   * @param dryRun the dry run
   * @return the batch process message
   */
  public static BatchProcessMessage forProcessing(
      String messageType, String uniqueId, Boolean dryRun) {
    Assertion.isNotNull(messageType, "messageType is required");
    Assertion.isNotNull(uniqueId, "uniqueId is required");
    Assertion.isNotNull(dryRun, "dryRun is required");

    return new BatchProcessMessage(messageType, uniqueId, dryRun);
  }

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Batch process message. */
  private BatchProcessMessage() {
    super();
  }

  /**
   * Instantiates a new Batch process message.
   *
   * @param messageType the message type
   * @param pageNumber the page number
   * @param pageSize the page size
   * @param dryRun the dry run
   */
  private BatchProcessMessage(
      String messageType, Integer pageNumber, Integer pageSize, Boolean dryRun) {
    this.messageType = messageType;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.dryRun = dryRun;
  }

  /**
   * Instantiates a new Batch process message.
   *
   * @param messageType the message type
   * @param uniqueId the unique id
   * @param dryRun the dry run
   */
  private BatchProcessMessage(String messageType, String uniqueId, Boolean dryRun) {
    this.messageType = messageType;
    this.uniqueId = uniqueId;
    this.dryRun = dryRun;
  }

  // ===============================================================================================
  // QUERIES
  // ===============================================================================================

  /**
   * Is for fetching page boolean.
   *
   * @return the boolean
   */
  public Boolean isForFetchingPage() {
    return pageSize != null && pageNumber != null && uniqueId == null;
  }

  /**
   * Is for processing boolean.
   *
   * @return the boolean
   */
  public Boolean isForProcessing() {
    return pageSize == null && pageNumber == null && uniqueId != null;
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

  /**
   * Gets dry run.
   *
   * @return the dry run
   */
  public Boolean getDryRun() {
    return dryRun;
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

  /**
   * Sets dry run.
   *
   * @param dryRun the dry run
   */
  public void setDryRun(Boolean dryRun) {
    this.dryRun = dryRun;
  }
}
