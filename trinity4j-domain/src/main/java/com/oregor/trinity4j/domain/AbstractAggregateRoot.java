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

import com.oregor.trinity4j.commons.assertion.Assertion;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * Base class for Aggregate Roots.
 *
 * @param <I> the type parameter
 * @author Christos Tsakostas
 */
@Access(AccessType.FIELD)
@MappedSuperclass
public abstract class AbstractAggregateRoot<I extends AbstractAggregateRootId> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private final transient List<DomainMessage> domainMessages = new LinkedList<>();

  private I id;

  private LocalDateTime createdOn;

  private LocalDateTime updatedOn;

  @Version private Integer version;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate root.
   *
   * <p>No args constructor for Repository Frameworks.
   */
  @SuppressWarnings("CPD-START")
  protected AbstractAggregateRoot() {
    super();
    setCreatedOn(LocalDateTime.now());
  }

  /**
   * Instantiates a new Aggregate root.
   *
   * @param id the id
   */
  protected AbstractAggregateRoot(I id) {
    setId(id);
    setCreatedOn(LocalDateTime.now());
  }

  // ===============================================================================================
  // PUBLIC
  // ===============================================================================================

  /** Clear domain messages. */
  public void clearDomainMessages() {
    this.domainMessages.clear();
  }

  // ===============================================================================================
  // PROTECTED
  // ===============================================================================================

  /**
   * Register domain message.
   *
   * @param <D> the type parameter
   * @param domainMessage the domain message
   * @return the d
   */
  protected <D extends DomainMessage> void registerDomainMessage(D domainMessage) {
    if (domainMessage == null) {
      throw new IllegalArgumentException("Domain Message must not be null!");
    }

    fillDomainMessageProperties(domainMessage);

    domainMessages.add(domainMessage);
  }

  protected <D extends DomainMessage> void fillDomainMessageProperties(D domainMessage) {
    DomainMessageInfo domainMessageInfo =
        AnnotationUtils.findAnnotation(domainMessage.getClass(), DomainMessageInfo.class);

    if (domainMessageInfo != null) {
      domainMessage.setMessageVersion(domainMessageInfo.version());
      domainMessage.setMessageName(
          domainMessageInfo.name().equals("")
              ? domainMessage.getClass().getSimpleName()
              : domainMessageInfo.name());
      domainMessage.setMessageType(domainMessageInfo.type());
    } else {
      DomainEventInfo domainEventInfo =
          AnnotationUtils.findAnnotation(domainMessage.getClass(), DomainEventInfo.class);

      if (domainEventInfo != null) {
        domainMessage.setMessageVersion(domainEventInfo.version());
        domainMessage.setMessageName(
            domainEventInfo.name().equals("")
                ? domainMessage.getClass().getSimpleName()
                : domainEventInfo.name());
        domainMessage.setMessageType(DomainMessageType.EVENT);
      } else {
        throw new IllegalStateException(
            String.format(
                "Domain Message=%s is not annotated with with neither"
                    + " @DomainMessageInfo or @DomainEventInfo",
                domainMessage.getClass().getCanonicalName()));
      }
    }

    domainMessage.setRootId(getId().getTypeId());
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets domain messages.
   *
   * @return the domain messages
   */
  @Transient
  public List<DomainMessage> getDomainMessages() {
    return domainMessages;
  }

  /**
   * Gets created on.
   *
   * @return the created on
   */
  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  /**
   * Gets updated on.
   *
   * @return the updated on
   */
  public LocalDateTime getUpdatedOn() {
    return updatedOn;
  }

  /**
   * Gets version.
   *
   * @return the version
   */
  public Integer getVersion() {
    return version;
  }

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(Integer version) {
    Assertion.isNotNull(version, "version cannot be null");
    this.version = version;
  }

  /**
   * Sets updated on.
   *
   * @param updatedOn the updated on
   */
  public void setUpdatedOn(LocalDateTime updatedOn) {
    Assertion.isNotNull(updatedOn, "updatedOn cannot be null");
    this.updatedOn = updatedOn;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets id.
   *
   * @param id the id
   */
  private void setId(I id) {
    Assertion.isNotNull(id, "id cannot be null");
    this.id = id;
  }

  private void setCreatedOn(LocalDateTime createdOn) {
    Assertion.isNotNull(createdOn, "createdOn cannot be null");
    this.createdOn = createdOn;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Access(AccessType.PROPERTY)
  @EmbeddedId
  public I getId() {
    return id;
  }
}
