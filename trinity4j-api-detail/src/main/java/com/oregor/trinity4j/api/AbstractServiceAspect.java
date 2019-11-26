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

import com.oregor.trinity4j.domain.DomainException;
import com.oregor.trinity4j.properties.PropertyFileAuxService;
import java.util.Locale;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Abstract service aspect.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractServiceAspect {

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  private static final Logger LOG = LoggerFactory.getLogger(AbstractServiceAspect.class);
  private static final String UNEXPECTED_EXCEPTION = "UNEXPECTED_EXCEPTION";

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  private PropertyFileAuxService propertyFileAuxService;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract service aspect.
   *
   * @param propertyFileAuxService the property file aux service
   */
  protected AbstractServiceAspect(PropertyFileAuxService propertyFileAuxService) {
    this.propertyFileAuxService = propertyFileAuxService;
  }

  // ===============================================================================================
  // FUNCTIONALITY
  // ===============================================================================================

  /**
   * Around object.
   *
   * @param proceedingJoinPoint the proceeding join point
   * @return the object
   * @throws Throwable the throwable
   */
  protected Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    Object[] paramValues = proceedingJoinPoint.getArgs();
    ApiRequest apiRequest = ApiRequest.class.cast(paramValues[0]);

    try {
      return proceedingJoinPoint.proceed();
    } catch (DomainException e) {
      throw new ApiException(getApiError(apiRequest, e.getErrorCode(), e.getArguments()));
    } catch (Exception e) {
      // If we reach here, something bad happened such as NPE
      LOG.error(String.format("Unexpected API Exception=%s", e.getMessage()), e);
      throw new UnexpectedApiException(getApiError(apiRequest, UNEXPECTED_EXCEPTION));
    }
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  private ApiError getApiError(ApiRequest apiRequest, String errorCode) {
    return getApiError(apiRequest, errorCode, null);
  }

  private ApiError getApiError(ApiRequest apiRequest, String errorCode, Object[] arguments) {
    return ApiError.of(
        errorCode,
        propertyFileAuxService.getMessage(
            errorCode,
            arguments,
            Locale.forLanguageTag(
                apiRequest != null && apiRequest.getUserLanguage() != null
                    ? apiRequest.getUserLanguage()
                    : Locale.ENGLISH.toLanguageTag())));
  }
}
