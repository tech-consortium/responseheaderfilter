  * [Introduction](#Introduction.md)
  * [response-header-mapper](#response-header-mapper.md)
    * [processorClass](#processorClass.md)
  * [mapping](#mapping.md)
    * [url](#url.md)
    * [processorClass](#processorClass.md)
  * [default](#default.md)
  * [conditional](#conditional.md)
    * [queryParamName](#queryParamName.md)
    * [queryParamValue](#queryParamValue.md)
  * [response-headers](#response-headers.md)

# Introduction #
<p>
The file <code>response-header-filter.xml</code> contains instructions for the filter to process requests. It basically contains a set of <a href='#mapping.md'>mappings</a> which are internally processed as <a href='http://code.google.com/p/responseheaderfilter/source/browse/trunk/src/java/com/avlesh/web/filter/responseheaderfilter/Rule.java'>Rules</a>. If an incoming request is found to be a match with one of the mappings, the corresponding Rules are applied to the response.<br>
</p>
<p>For a sample mapping file, <a href='SampleConfiguration.md'>click here</a>
The file content is processed according to these rules:<br>
<ol>
<li>The file <b>should</b> have response-header-mapper as the root node.</li>
<li>Each node (in the xml) with the name mapping is identified as mapping rule and<br>
gets converted into a Rule.<br />url is a mandatory attribute in the mapping node; mappings without a url are rejected.<br>
</li>
<li>Subsequent mappings for the same url will <b>OVERRIDE</b> the previous Rule.<br>
<i>Last Rule wins</i>.<br>
</li>
<li>Each mapping can have only <b>one <code>default</code></b> response-header list.<br>
In cases of multiple such declarations, the <i>Last <code>&lt;default&gt;</code> declaration wins</i>.<br>
</li>
<li>Each mapping can have any number of <code>conditional</code> mappings.<br>
All these rules are treated as mutually exclusive.<br>
</li>
<li>Both, default and conditional nodes <b>should</b> have a <code>&lt;response-headers&gt;</code>
node. In case of multiple such nodes, <i>Last <code>&lt;response-headers&gt;</code> declaration wins</i>.<br>
</li>
<li>Each <code>&lt;response-headers&gt;</code> node may contain one or more <code>&lt;header&gt;</code> nodes. Each such node has to have two mandatory attributes, <code>key</code> and <code>value</code>.</li>
<li>Both, <code>queryParamName</code> and <code>queryParamValue</code>, are required attributes in a conditional tag. They <b>can't</b> be left blank or undeclared.<br>
</li>
<li>Values inside the <code>queryParamValue</code> attribute are parsed as a <code>Pattern</code>.</li>
</ol>
</p>

# response-header-mapper #
  * Description: This is the root node for the filter configuration file and is a expected node.
  * Required: Yes
  * Attributes:
> > ## processorClass ##
    * Description: A fully qualified class name for the processor class, if you want to process the response headers on your own. You would need a processor class when setting the headers needs to be done after some sort of evaluation over the request and/or response. <br />Your class should implement the `com.avlesh.web.filter.responseheaderfilter.MappingProcessor` interface.
    * Required: No
    * Default: `com.avlesh.web.filter.responseheaderfilter.DefaultMappingProcessor`

# mapping #
  * Description: A `mapping` node is set of rules for a matching `url` pattern.
  * Required: Yes
  * Attributes:
> > ## url ##
    * Description: The **base url** to which the corresponding mapping should be applied to. This is only supposed to be the `uri`. To be able to use your request `queryString`, read the [conditional](#conditional.md) node section.
    * Required: Yes
    * Example: `<mapping url="/ajax/*.html">`
> > ## processorClass ##
    * Description: A fully qualified class name for the processor class for this particular mapping, if you want to process the response headers on your own or differently from other mappings. You would need a processor class when setting the headers needs to be done after some sort of evaluation over the request and/or response. <br />Your class should implement the `com.avlesh.web.filter.responseheaderfilter.MappingProcessor` interface.
    * Required: No
    * Default: `com.avlesh.web.filter.responseheaderfilter.DefaultMappingProcessor`
    * Example: `<mapping url="/ajax/*.html" processorClass="com.foo.MyAjaxProcessor">`

# default #
  * Description: One of the possible child nodes for a `mapping` node. Rules specified under default is the default fallback in case of absence of `conditional` nodes. Each mapping can have only one default. If you specify multiple such default's the last one is respected.
  * Required: No
  * Example:
```
  <mapping url="/ajax/*.html">
    <default>
      <response-headers>
        <header key="Content-Type" value="text/xml"/>
        <header key="Cache-Control" value="no-cache"/>
      </response-headers>
    </default>
  </mapping>
```
# conditional #
  * Description: One of the possible child nodes for a `mapping` node. A `mapping` node may have many conditional nodes. The conditional nodes can be used to apply specific and different set of rules based on query parameters for the request.
  * Required: No
  * Attributes:
> > ## queryParamName ##
    * Description: The query parameter based on which you want to have a `conditional` rule. e.g. you may want to apply different response headers to `/site/log-analyzer.html?type=xxxx` based on the `type` parameter. Your `queryParamName` in this case would be type.
    * Required: Yes
> > ## queryParamValue ##
    * Description: In the above example, the value has to be specified in the `queryParamValue` attribute. This also takes a `Pattern`.
    * Required: Yes

  * Example:
```
  <mapping url="/ajax/*.html">
    <conditional queryParamName="type" queryParamValue="(country|city)">
      <response-headers>
        <header key="Content-Type" value="text/xml"/>
        <header key="Cache-Control" value="private, max-age=86400"/>
      </response-headers>
    </conditional>
  </mapping>
```
  * NOTE: **Conditional rules are evaluated from bottom to top. _Last matching rule (in the config) wins_**.

# response-headers #
Any `default` or `conditional` node is considered invalid without a list response-headers that needs to be applied for a particular `mapping`. A response-headers node comprises of a list of `header` nodes.

> ### header ###
> This the lowest unit in the configuration. Each header is a specific response header that needs to be applied to the `response`. A header comprises of
> <b>key</b>: To be specific, a response-header key.
> <br /><b>value</b>: The value for the above mentioned key.