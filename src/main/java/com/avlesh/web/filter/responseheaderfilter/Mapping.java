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

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Each &lt;mapping&gt; definition in the <code>configFile</code> is converted into a {@link
 * Mapping} <br>
 * For a sample rule, click here - {@link com.avlesh.web.filter.responseheaderfilter.ConfigProcessor} <br>
 * For parsing rules, click here - {@link com.avlesh.web.filter.responseheaderfilter.ConfigProcessor#processConfig()}
 *
 * @see ConfigProcessor
 * @see ResponseHeaderFilter
 * @author dmclau
 * @version $Id: $Id
 */
public class Mapping {
  private MappingProcessor processorClass;
  private Pattern url;
  private List<ResponseHeader> defaultResponseHeaders;
  private Map<Condition, List<ResponseHeader>> conditionalResponseHeaders;

  /**
   * Comprises of parsed values for a &lt;conditional&gt; tag <br>
   * For parsing rules, click here - {@link ConfigProcessor#getCondition(org.w3c.dom.Node)}
   *
   * @see Mapping
   * @see ResponseHeader
   * @see ConfigProcessor
   * @see ResponseHeaderFilter
   */
  public static class Condition {
    private String queryParamName;
    private Pattern queryParamValue;

    public String getQueryParamName() {
      return queryParamName;
    }

    public void setQueryParamName(String queryParamName) {
      this.queryParamName = queryParamName;
    }

    public Pattern getQueryParamValue() {
      return queryParamValue;
    }

    public void setQueryParamValue(Pattern queryParamValue) {
      this.queryParamValue = queryParamValue;
    }
  }

  /**
   * Comprises of parsed values for &lt;header&gt; nodes inside a &lt;response-headers&gt; tag<br>
   * For parsing rules, click here - {@link ConfigProcessor#getResponseHeader(org.w3c.dom.Node)}
   *
   * @see Mapping
   * @see Condition
   * @see ConfigProcessor
   * @see ResponseHeaderFilter
   */
  public static class ResponseHeader {
    private String responseHeaderKey;
    private String responseHeaderValue;

    public String getResponseHeaderKey() {
      return responseHeaderKey;
    }

    public void setResponseHeaderKey(String responseHeaderKey) {
      this.responseHeaderKey = responseHeaderKey;
    }

    public String getResponseHeaderValue() {
      return responseHeaderValue;
    }

    public void setResponseHeaderValue(String responseHeaderValue) {
      this.responseHeaderValue = responseHeaderValue;
    }
  }

  /**
   * <p>Getter for the field <code>processorClass</code>.</p>
   *
   * @return a {@link com.avlesh.web.filter.responseheaderfilter.MappingProcessor} object
   */
  public MappingProcessor getProcessorClass() {
    return processorClass;
  }

  /**
   * <p>Setter for the field <code>processorClass</code>.</p>
   *
   * @param processorClass a {@link com.avlesh.web.filter.responseheaderfilter.MappingProcessor} object
   */
  public void setProcessorClass(MappingProcessor processorClass) {
    this.processorClass = processorClass;
  }

  /**
   * <p>Getter for the field <code>url</code>.</p>
   *
   * @return a {@link java.util.regex.Pattern} object
   */
  public Pattern getUrl() {
    return url;
  }

  /**
   * <p>Setter for the field <code>url</code>.</p>
   *
   * @param url a {@link java.util.regex.Pattern} object
   */
  public void setUrl(Pattern url) {
    this.url = url;
  }

  /**
   * <p>Getter for the field <code>defaultResponseHeaders</code>.</p>
   *
   * @return a {@link java.util.List} object
   */
  public List<ResponseHeader> getDefaultResponseHeaders() {
    return defaultResponseHeaders;
  }

  /**
   * <p>Setter for the field <code>defaultResponseHeaders</code>.</p>
   *
   * @param defaultResponseHeaders a {@link java.util.List} object
   */
  public void setDefaultResponseHeaders(List<ResponseHeader> defaultResponseHeaders) {
    this.defaultResponseHeaders = defaultResponseHeaders;
  }

  /**
   * <p>Getter for the field <code>conditionalResponseHeaders</code>.</p>
   *
   * @return a {@link java.util.Map} object
   */
  public Map<Condition, List<ResponseHeader>> getConditionalResponseHeaders() {
    return conditionalResponseHeaders;
  }

  /**
   * <p>Setter for the field <code>conditionalResponseHeaders</code>.</p>
   *
   * @param conditionalResponseHeaders a {@link java.util.Map} object
   */
  public void setConditionalResponseHeaders(
      Map<Condition, List<ResponseHeader>> conditionalResponseHeaders) {
    this.conditionalResponseHeaders = conditionalResponseHeaders;
  }
}
