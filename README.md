# ResponseHeaderFilter

A Java Filter to transparently set response headers for any HTTP request.

ResponseHeaderFilter is a Java Web Filter for any J2EE compliant web application server (such as Resin or Tomcat), which lets you transparently set response headers for any HTTP request. ResponseHeaderFilter abstracts the responsibility of setting these headers from your code, thereby making it easy to change header policies without changing any code.

Some of the most commonly used response headers that this filter can apply are:
- Cache-Control: Caching instructions for the client and proxies.
- Content-Type: The MIME type of the response.
- Content-Encoding: Type of encoding used in the response.
- Content-Length: Length of the response body in bytes.
- Expires: Date/time after which the response is considered stale.
- Set-Cookie: Cookies are headers too. (e.g Set-Cookie: cookieName=cookieValue; Max-Age=3600;)

## Getting started

The simplest way to make this filter work in your web app is to create a file called `response-header-filter.xml` in your project's `WEB-INF` directory. Add the response header directives for your URLs in this file. Here's a small example:

### Filter parameters (web.xml)
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<response-header-mapper>
  <!-- generic rule for all HTML requests -->
  <mapping url="(.*).html">
    <default>
      <response-headers>
        <header key="X-ServerName" value="MyServer"/>
        <header key="Content-Type" value="text/html"/>
        <!-- cache all the HTML pages for one hour -->
        <header key="Cache-Control" value="private, max-age=3600"/>
      </response-headers>
    </default>
  </mapping>
</response-header-mapper>
```

...post the creation of this file, you'll have to add this filter definition to your web.xml as underneath

### Sample configuration

```xml
<web-app>
  <filter>
    <filter-name>ResponseHeaderFilter</filter-name>
    <filter-class>com.avlesh.web.filter.responseheaderfilter.ResponseHeaderFilter</filter-class>
  </filter>

  <filter-mapping>
    <filter-name>ResponseHeaderFilter</filter-name>
    <url-pattern>*</url-pattern>
  </filter-mapping>
</web-app>
```
...and bingo your filter is already in action. Look at the Wiki for a detailed sample configuration, read further or extend this API to implement a custom behavior.

## Filter Parameters

This filter comes with a default behaviour of reloading your filter configuration file if the file has been modified. By default, the lastModifiedTimestamp of the file is checked every 10 seconds. If the lastModifiedTimestamp value has changed (meaning that the file has been modified), the configuration file is processed again and new mapping rules come into effect for the filter.

You can change this duration, called as reloadCheckInterval, in the filter definition. Moreover the file name/path, called as configFile, can also be specified as one of the filter parameters. Underneath is a sample filter mapping definition.

```xml
<filter-mapping>
  
  <filter-name>ResponseHeaderFilter</filter-name>
  
  <url-pattern>*</url-pattern>
  
  <init-param>
    <param-name>reloadCheckInterval</param-name>
    <!-- 
      Last modified time check interval (in seconds) for the config file. 
      Setting this value to less than or equal to zero disables reloading 
      capabilities of the filter.
      Default: 10 seconds
    -->
    <param-value>0</param-value>
  </init-param>
  
  <init-param>
    <param-name>configFile</param-name>
    <!-- 
      Path to your filter configuration xml file.
      Default: /WEB-INF/response-header-filter.xml
    -->
    <param-value>/WEB-INF/path-to/the-filter/config-file.xml</param-value>
  </init-param>
  
</filter-mapping>
```
