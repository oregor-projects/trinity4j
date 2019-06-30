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
 * The type Abstract scheduled domain message publisher.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractScheduledDomainMessagePublisher<
        T extends DomainMessagePublishedData, D extends DomainMessagePublishDto>
    implements ScheduledDomainMessagePublisher {

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  private final DomainMessageDataRepository<?> domainMessageDataRepository;
  private final DomainMessagePublishedDataRepository<T> domainMessagePublishedDataRepository;
  private final DomainMessagePublishedDataConverter<T> domainMessagePublishedDataConverter;
  private final DomainMessagePublishDtoConverter<D> domainMessagePublishDtoConverter;
  private final DomainMessagePublisher<D> domainMessagePublisher;
  private final Integer defaultPageSize;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract scheduled domain message publisher.
   *
   * @param domainMessageDataRepository the domain message data repository
   * @param domainMessagePublishedDataRepository the domain message published data repository
   * @param domainMessagePublishedDataConverter the domain message published data converter
   * @param domainMessagePublishDtoConverter the domain message publish dto converter
   * @param domainMessagePublisher the domain message publisher
   * @param defaultPageSize the default page size
   */
  protected AbstractScheduledDomainMessagePublisher(
      DomainMessageDataRepository<?> domainMessageDataRepository,
      DomainMessagePublishedDataRepository<T> domainMessagePublishedDataRepository,
      DomainMessagePublishedDataConverter<T> domainMessagePublishedDataConverter,
      DomainMessagePublishDtoConverter<D> domainMessagePublishDtoConverter,
      DomainMessagePublisher<D> domainMessagePublisher,
      Integer defaultPageSize) {
    this.domainMessageDataRepository = domainMessageDataRepository;
    this.domainMessagePublishedDataRepository = domainMessagePublishedDataRepository;
    this.domainMessagePublishedDataConverter = domainMessagePublishedDataConverter;
    this.domainMessagePublishDtoConverter = domainMessagePublishDtoConverter;
    this.domainMessagePublisher = domainMessagePublisher;
    this.defaultPageSize = defaultPageSize;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public void publish() {
    Paginated<? extends DomainMessageData> domainMessageDataPaginated =
        domainMessageDataRepository.findPaginated(0, defaultPageSize);

    domainMessageDataPaginated
        .getItems()
        .forEach(
            domainMessageData -> {
              T domainMessagePublishedData =
                  domainMessagePublishedDataConverter.convert(domainMessageData);

              D domainMessagePublishDto =
                  domainMessagePublishDtoConverter.convert(domainMessagePublishedData);

              domainMessagePublishedDataRepository.store(domainMessagePublishedData);
              domainMessageDataRepository.remove(domainMessageData.getMessageId());
              domainMessagePublisher.send(domainMessagePublishDto);
            });
  }
}
