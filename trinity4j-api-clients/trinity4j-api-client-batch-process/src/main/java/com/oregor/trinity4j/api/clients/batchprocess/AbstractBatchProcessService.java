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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oregor.trinity4j.api.ApiPagedCollectionResponse;
import com.oregor.trinity4j.api.CollectionItemIdentifiable;
import com.oregor.trinity4j.commons.assertion.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Abstract batch process service.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractBatchProcessService<T extends CollectionItemIdentifiable>
    implements BatchProcessService {

  // ===============================================================================================
  // LOGGER
  // ===============================================================================================

  private static final Logger LOG = LoggerFactory.getLogger(AbstractBatchProcessService.class);

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  private final ObjectMapper objectMapper;
  private final BatchProcessMessagePublisher batchProcessMessagePublisher;
  private final BatchProcessCommandService batchProcessCommandService;
  private final BatchProcessQueryService<T> batchProcessQueryService;
  private final Integer defaultPageSize;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract batch process service.
   *
   * @param objectMapper the object mapper
   * @param batchProcessMessagePublisher the batch process message publisher
   * @param batchProcessCommandService the batch process command service
   * @param batchProcessQueryService the batch process query service
   * @param defaultPageSize the default page size
   */
  public AbstractBatchProcessService(
      ObjectMapper objectMapper,
      BatchProcessMessagePublisher batchProcessMessagePublisher,
      BatchProcessCommandService batchProcessCommandService,
      BatchProcessQueryService<T> batchProcessQueryService,
      Integer defaultPageSize) {
    this.objectMapper = objectMapper;
    this.batchProcessMessagePublisher = batchProcessMessagePublisher;
    this.batchProcessCommandService = batchProcessCommandService;
    this.batchProcessQueryService = batchProcessQueryService;
    this.defaultPageSize = defaultPageSize;
  }

  // ===============================================================================================
  // ABSTRACT
  // ===============================================================================================

  protected abstract String getBatchProcessServiceName();

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public void fetchPage(BatchProcessMessage batchProcessMessage) {
    try {
      batchProcessMessage.setPageNumber(ensurePageNumber(batchProcessMessage.getPageNumber()));
      batchProcessMessage.setPageSize(ensurePageSize(batchProcessMessage.getPageSize()));

      ApiPagedCollectionResponse<T> response =
          batchProcessQueryService.fetchPagedCollection(batchProcessMessage);

      response
          .getItems()
          .forEach(
              collectionItem -> {
                try {
                  Assertion.isNotNull(collectionItem.getId(), "collectionItem.getId() is required");

                  batchProcessMessagePublisher.send(
                      objectMapper.writeValueAsString(
                          BatchProcessMessage.forProcessing(
                              batchProcessMessage.getMessageType(),
                              response.getSeekMethodLeftOffValue(),
                              collectionItem.getId(),
                              batchProcessMessage.getDryRun())));
                } catch (JsonProcessingException e) {
                  throw new IllegalArgumentException(e.getMessage(), e);
                }
              });

      if (response.getItems().size() == batchProcessMessage.getPageSize()) {
        if (batchProcessMessage.getSeekMethodLeftOffValue() != null) {
          batchProcessMessagePublisher.send(
              objectMapper.writeValueAsString(
                  BatchProcessMessage.forSeekingPage(
                      batchProcessMessage.getMessageType(),
                      response.getSeekMethodLeftOffValue(),
                      incrementPageNumber(batchProcessMessage.getPageNumber()),
                      batchProcessMessage.getPageSize(),
                      batchProcessMessage.getDryRun())));

        } else {
          batchProcessMessagePublisher.send(
              objectMapper.writeValueAsString(
                  BatchProcessMessage.forFetchingPage(
                      batchProcessMessage.getMessageType(),
                      incrementPageNumber(batchProcessMessage.getPageNumber()),
                      batchProcessMessage.getPageSize(),
                      batchProcessMessage.getDryRun())));
        }
      } else {
        LOG.debug(
            "Processed {} pages with pageSize={} and dryRun={} in {}",
            batchProcessMessage.getPageNumber() + 1,
            batchProcessMessage.getPageSize(),
            batchProcessMessage.getDryRun(),
            getBatchProcessServiceName());
      }
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    }
  }

  @Override
  public void processForId(BatchProcessMessage batchProcessMessage) {
    batchProcessCommandService.processForId(batchProcessMessage);
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  /**
   * Ensures the page number.
   *
   * @param pageNumber the page number
   * @return the integer
   */
  private Integer ensurePageNumber(Integer pageNumber) {
    if (pageNumber == null) {
      pageNumber = 0;
    }

    return pageNumber;
  }

  /**
   * Ensures the page size.
   *
   * @param pageSize the page size
   * @return the integer
   */
  private Integer ensurePageSize(Integer pageSize) {
    if (pageSize == null) {
      pageSize = defaultPageSize;
    }

    return pageSize;
  }

  /**
   * Increments the page number.
   *
   * @param pageNumber the page number
   * @return the integer
   */
  private Integer incrementPageNumber(Integer pageNumber) {
    return pageNumber + 1;
  }
}
