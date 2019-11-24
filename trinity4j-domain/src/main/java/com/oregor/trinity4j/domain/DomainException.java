package com.oregor.trinity4j.domain;

/**
 * The type Domain exception.
 *
 * @author Christos Tsakostas
 */
public class DomainException extends RuntimeException {

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  private static final long serialVersionUID = 1L;

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private String messageKey;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Domain exception.
   *
   * @param messageKey the key
   */
  public DomainException(String messageKey) {
    super(messageKey, null, false, messageKey == null ? true : false);
    this.messageKey = messageKey;
  }

  /**
   * Instantiates a new Domain exception.
   *
   * @param message the message
   * @param cause the cause
   * @param enableSuppression the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  private DomainException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets key.
   *
   * @return the key
   */
  public String getMessageKey() {
    return messageKey;
  }
}
