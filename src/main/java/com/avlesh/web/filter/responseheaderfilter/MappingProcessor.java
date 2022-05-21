/**
 * Copyright 2009 Avlesh Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.avlesh.web.filter.responseheaderfilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>MappingProcessor interface.</p>
 *
 * @author dmclau
 * @version $Id: $Id
 */
public interface MappingProcessor {
  /**
   * <p>preProcess.</p>
   *
   * @param request a {@link jakarta.servlet.http.HttpServletRequest} object
   * @param response a {@link jakarta.servlet.http.HttpServletResponse} object
   * @param applicableMapping a {@link com.avlesh.web.filter.responseheaderfilter.Mapping} object
   */
  void preProcess(HttpServletRequest request, HttpServletResponse response, Mapping applicableMapping);

  /**
   * <p>applyHeaders.</p>
   *
   * @param request a {@link jakarta.servlet.http.HttpServletRequest} object
   * @param response a {@link jakarta.servlet.http.HttpServletResponse} object
   * @param responseHeaders a {@link java.util.List} object
   * @param applicableMapping a {@link com.avlesh.web.filter.responseheaderfilter.Mapping} object
   */
  void applyHeaders(HttpServletRequest request,
                    HttpServletResponse response,
                    List<Mapping.ResponseHeader> responseHeaders,
                    Mapping applicableMapping);

  /**
   * <p>postProcess.</p>
   *
   * @param request a {@link jakarta.servlet.http.HttpServletRequest} object
   * @param response a {@link jakarta.servlet.http.HttpServletResponse} object
   * @param applicableMapping a {@link com.avlesh.web.filter.responseheaderfilter.Mapping} object
   */
  void postProcess(HttpServletRequest request, HttpServletResponse response, Mapping applicableMapping);
}
