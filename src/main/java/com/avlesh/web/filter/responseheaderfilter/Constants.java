/**
 * Copyright 2009 Avlesh Singh
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.avlesh.web.filter.responseheaderfilter;

/**
 * Constants, mostly DOM node names in the <code>configFile</code> for the filter.
 *
 * @see ConfigProcessor
 * @see ResponseHeaderFilter
 * @author dmclau
 * @version $Id: $Id
 */
public class Constants {
  /** Constant <code>RESPONSE_HEADER_MAPPER="response-header-mapper"</code> */
  public static final String RESPONSE_HEADER_MAPPER = "response-header-mapper";
  /** Constant <code>MAPPING="mapping"</code> */
  public static final String MAPPING = "mapping";
  /** Constant <code>DEFAULT="default"</code> */
  public static final String DEFAULT = "default";
  /** Constant <code>CONDITIONAL="conditional"</code> */
  public static final String CONDITIONAL = "conditional";
  /** Constant <code>CONDITIONAL_QUERY_PARAM_NAME="queryParamName"</code> */
  public static final String CONDITIONAL_QUERY_PARAM_NAME = "queryParamName";
  /** Constant <code>CONDITIONAL_QUERY_PARAM_VALUE="queryParamValue"</code> */
  public static final String CONDITIONAL_QUERY_PARAM_VALUE = "queryParamValue";
  /** Constant <code>RESPONSE_HEADERS="response-headers"</code> */
  public static final String RESPONSE_HEADERS = "response-headers";
  /** Constant <code>HEADER="header"</code> */
  public static final String HEADER = "header";
  /** Constant <code>HEADER_KEY="key"</code> */
  public static final String HEADER_KEY = "key";
  /** Constant <code>HEADER_VALUE="value"</code> */
  public static final String HEADER_VALUE = "value";
}
