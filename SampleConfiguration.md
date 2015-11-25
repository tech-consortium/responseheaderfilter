# /WEB-INF/response-header-filter.xml #
```
<?xml version="1.0" encoding="UTF-8" ?>
<!--
Configuration file for the ResponseHeaderFilter. 
Add a list of mappings that you want to introduce in your webapp for different url's.

For details on the contents of this file, go here - http://code.google.com/p/responseheaderfilter/wiki/ConfigXml
-->

<response-header-mapper>
  <!-- directives to set "no-cache" headers for all AJAX requests -->
  <mapping url="/ajax/*.html">
    <default>
      <response-headers>
	<header key="Content-Type" value="text/xml"/>
        <header key="Cache-Control" value="no-cache"/>
      </response-headers>
    </default>

    <!-- 
     If one of your AJAX request handlers was fetching, lets say, 
     a list of cities or countries (which thankfully don't change very often), 
     you would want the browser to cache these responses and that 
     too for a significantly longer time.
     
     The ResponseHeaderFilter gives you the power of <conditional> mappings,
     wherein you can have mappings based on a query parameter in the request 
     and its expected value (which can be a Pattern)

     Underneath is an example of how to specify directives to cache responses for 
     all "/ajax/*.html?fetch=country" or "/ajax/*.html?fetch=city" 
     requests for a day
    -->

    <conditional queryParamName="type" queryParamValue="(country|city)">
      <response-headers>
	<header key="Content-Type" value="text/xml"/>
        <header key="Cache-Control" value="private, max-age=86400"/>
      </response-headers>
    </conditional>
  </mapping>

  <!-- directives to cache all "(.*).js" requests for 30 days -->
  <mapping url="(.*).js">
    <default>
      <response-headers>
	<header key="X-Server" value="MyServer"/>
	<header key="Content-Type" value="application/x-javascript"/>
        <header key="Cache-Control" value="public, max-age=2592000"/>
      </response-headers>
    </default>
  </mapping>

  <!-- directives to keep the http connection alive for all json requests -->
  <mapping url="/json/(.*)">
    <default>
      <response-headers>
	<header key="Content-Type" value="application/json"/>
        <header key="Connection" value="Keep-Alive"/>
      </response-headers>
    </default>
  </mapping>
</response-header-mapper>
```
<br />

---

<br />
# Next steps #
  1. Get minute details on the node elements and their attributes in the configuration [here](http://code.google.com/p/responseheaderfilter/wiki/ConfigXml).
  1. Interested in implementing a custom behaviour? Learn how to [extend this API](http://code.google.com/p/responseheaderfilter/wiki/ExtendingTheAPI).
  1. Learn how to [get started](http://code.google.com/p/responseheaderfilter/#Getting_started) on this filter in your webapp.