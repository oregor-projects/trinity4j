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
  // STATIC CONSTANTS
  // ===============================================================================================

  private static final String MESSAGE_TYPE_IS_REQUIRED = "messageType is required";
  private static final String SEEK_METHOD_LEFT_OFF_VALUE_IS_REQUIRED =
      "seekMethodLeftOffValue is required";
  private static final String PAGE_NUMBER_IS_REQUIRED = "pageNumber is required";
  private static final String PAGE_SIZE_IS_REQUIRED = "pageSize is required";
  private static final String DRY_RUN_IS_REQUIRED = "dryRun is required";
  private static final String UNIQUE_ID_IS_REQUIRED = "uniqueId is required";

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private String messageType;
  private String seekMethodLeftOffValue;
  private Integer pageNumber;
  private Integer pageSize;
  private String uniqueId;
  private Boolean dryRun;

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  /**
   * For seeking page batch process message.
   *
   * @param messageType the message type
   * @param seekMethodLeftOffValue the seek method left off value
   * @param pageNumber the page number
   * @param pageSize the page size
   * @param dryRun the dry run
   * @return the batch process message
   */
  public static BatchProcessMessage forSeekingPage(
      String messageType,
      String seekMethodLeftOffValue,
      Integer pageNumber,
      Integer pageSize,
      Boolean dryRun) {
    Assertion.isNotNull(messageType, MESSAGE_TYPE_IS_REQUIRED);
    Assertion.isNotNull(seekMethodLeftOffValue, SEEK_METHOD_LEFT_OFF_VALUE_IS_REQUIRED);
    Assertion.isNotNull(pageNumber, PAGE_NUMBER_IS_REQUIRED);
    Assertion.isNotNull(pageSize, PAGE_SIZE_IS_REQUIRED);
    Assertion.isNotNull(dryRun, DRY_RUN_IS_REQUIRED);

    return new BatchProcessMessage(
        messageType, seekMethodLeftOffValue, pageNumber, pageSize, null, dryRun);
  }

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
    Assertion.isNotNull(messageType, MESSAGE_TYPE_IS_REQUIRED);
    Assertion.isNotNull(pageNumber, PAGE_NUMBER_IS_REQUIRED);
    Assertion.isNotNull(pageSize, PAGE_SIZE_IS_REQUIRED);
    Assertion.isNotNull(dryRun, DRY_RUN_IS_REQUIRED);

    return new BatchProcessMessage(messageType, null, pageNumber, pageSize, null, dryRun);
  }

  /**
   * For processing batch process message.
   *
   * @param messageType the message type
   * @param seekMethodLeftOffValue the seek method left off value
   * @param uniqueId the unique id
   * @param dryRun the dry run
   * @return the batch process message
   */
  public static BatchProcessMessage forProcessing(
      String messageType, String seekMethodLeftOffValue, String uniqueId, Boolean dryRun) {
    Assertion.isNotNull(messageType, MESSAGE_TYPE_IS_REQUIRED);
    Assertion.isNotNull(uniqueId, UNIQUE_ID_IS_REQUIRED);
    Assertion.isNotNull(dryRun, DRY_RUN_IS_REQUIRED);

    return new BatchProcessMessage(
        messageType, seekMethodLeftOffValue, null, null, uniqueId, dryRun);
  }

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Batch process message. */
  private BatchProcessMessage() {
    super();
  }

  private BatchProcessMessage(
      String messageType,
      String seekMethodLeftOffValue,
      Integer pageNumber,
      Integer pageSize,
      String uniqueId,
      Boolean dryRun) {
    this.messageType = messageType;
    this.seekMethodLeftOffValue = seekMethodLeftOffValue;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.uniqueId = uniqueId;
    this.dryRun = dryRun;
  }

  // ===============================================================================================
  // QUERIES
  // ===============================================================================================

  /**
   * Is for seeking page boolean.
   *
   * @return the boolean
   */
  public Boolean isForSeekingPage() {
    return pageSize != null && seekMethodLeftOffValue != null && uniqueId == null;
  }

  /**
   * Is for fetching page boolean.
   *
   * @return the boolean
   */
  public Boolean isForFetchingPage() {
    return pageSize != null
        && pageNumber != null
        && uniqueId == null
        && seekMethodLeftOffValue == null;
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
