<table cellpadding='0' cellspacing='0' border='0'>
<blockquote><tr>
<blockquote><td width='250' valign='top'><b>Quick links</b>
</blockquote><ol><li><a href='#Getting_started.md'>Getting started</a>
</li><li><a href='#Filter_Parameters.md'>Filter parameters (web.xml)</a>
</li><li><a href='http://code.google.com/p/responseheaderfilter/wiki/SampleConfiguration'>Sample confugiration</a>
</li><li><a href='http://code.google.com/p/responseheaderfilter/wiki/ConfigXml'>Understanding the config</a>
</li><li><a href='http://code.google.com/p/responseheaderfilter/wiki/ExtendingTheAPI'>Extending the FilterAPI</a>
</li><li><a href='http://code.google.com/p/responseheaderfilter/downloads/list'>Download</a>
</li><li><a href='http://code.google.com/p/responseheaderfilter/wiki/Gotchas'>Known Gotchas</a></li></ol></blockquote>

<b>Contribute</b>
<ol><li><a href='http://code.google.com/p/responseheaderfilter/wiki/HowTo'>HowTo</a>
</li><li><a href='http://code.google.com/p/responseheaderfilter/issues/list'>Report a bug</a>
</li></ol><blockquote></td>
<td valign='top'>
<h1>ResponseHeaderFilter</h1>
ResponseHeaderFilter is a Java Web Filter for any J2EE compliant web application server (such as Resin or Tomcat), which lets you transparently set response headers for any http request. ResponseHeaderFilter abstracts the responsibility of setting these header from your code, thereby making it easy to change header policies without changing any code.</blockquote>

Some of the most commonly used response headers that this filter can apply are:<br>
<ol><li>Cache-Control: Caching instructions for the client and proxies.<br>
</li><li>Content-Type: The mime type of the response.<br>
</li><li>Content-Encoding: Type of encoding used in the response.<br>
</li><li>Content-Length: Length of the response body in bytes.<br>
</li><li>Expires: Date/time after which the response is considered stale.<br>
</li><li>Set-Cookie: Cookies are headers too. (e.g Set-Cookie: cookieName=cookieValue; Max-Age=3600;)<br>
</li></ol><blockquote></td>
</blockquote><blockquote></tr>
</table>
<br />
<hr />
<br />
<h1>Getting started</h1>
The simplest way to make this filter work in you webapp, is to create a file called <code>response-header-filter.xml</code> in your project's WEB-INF directory. Add the response header directives for your url's in this file. Here's a small example:<br>
<pre><code>&lt;?xml version="1.0" encoding="UTF-8" ?&gt;<br>
&lt;response-header-mapper&gt;<br>
  &lt;!-- generic rule for all html requests --&gt;<br>
  &lt;mapping url="(.*).html"&gt;<br>
    &lt;default&gt;<br>
      &lt;response-headers&gt;<br>
	&lt;header key="X-ServerName" value="MyServer"/&gt;<br>
	&lt;header key="Content-Type" value="text/html"/&gt;<br>
        &lt;!-- cache all the html pages for one hour --&gt;<br>
        &lt;header key="Cache-Control" value="private, max-age=3600"/&gt;<br>
      &lt;/response-headers&gt;<br>
    &lt;/default&gt;<br>
&lt;/response-header-mapper&gt;<br>
</code></pre>
post the creation of this file, you'll have to add this filter definition to your web.xml as underneath<br>
<pre><code>&lt;filter&gt;<br>
  &lt;filter-name&gt;ResponseHeaderFilter&lt;/filter-name&gt;<br>
  &lt;filter-class&gt;com.avlesh.web.filter.responseheaderfilter.ResponseHeaderFilter&lt;/filter-class&gt;<br>
&lt;/filter&gt;<br>
&lt;filter-mapping&gt;<br>
  &lt;filter-name&gt;ResponseHeaderFilter&lt;/filter-name&gt;<br>
  &lt;url-pattern&gt;*&lt;/url-pattern&gt;<br>
&lt;/filter-mapping&gt;<br>
</code></pre>
... and bingo your filter is already in action. Look at a detailed <a href='http://code.google.com/p/responseheaderfilter/wiki/SampleConfiguration'>sample configuration</a>, <a href='#Filter_Parameters.md'>read further</a> or <a href='http://code.google.com/p/responseheaderfilter/wiki/ExtendingTheAPI'>extend this API</a> to implement a custom behavior.<br>
<br /><br />
<hr />
<br />
<h1>Filter Parameters</h1>
<b>This filter comes with a default behaviour of reloading your filter configuration file if the file has been modified</b>. By default, the <code>lastModifiedTimestamp</code> of the file is checked every 10 seconds. If the <code>lastModifiedTimestamp</code> value has changed (meaning that the file has been modified), the configuration file is processed again and new mapping rules come into effect for the filter.<br>
<br /><br />
You can change this duration, called as <code>reloadCheckInterval</code>, in the filter definition. Moreover the file name/path, called as <code>configFile</code>, can also be specified as one of the filter parameters.<br>
Underneath is a sample filter mapping definition.<br>
<pre><code>&lt;filter-mapping&gt;<br>
  &lt;filter-name&gt;ResponseHeaderFilter&lt;/filter-name&gt;<br>
  &lt;url-pattern&gt;*&lt;/url-pattern&gt; <br>
  &lt;init-param&gt;<br>
    &lt;param-name&gt;reloadCheckInterval&lt;/param-name&gt;<br>
    &lt;!-- <br>
      Last modified time check interval (in seconds) for the config file. <br>
      Setting this value to less than or equal to zero disables reloading <br>
      capabilities of the filter.<br>
<br>
      Default: 10 seconds<br>
    --&gt;<br>
    &lt;param-value&gt;0&lt;/param-value&gt;    <br>
  &lt;/init-param&gt;<br>
  &lt;init-param&gt;<br>
    &lt;param-name&gt;configFile&lt;/param-name&gt;<br>
    &lt;!-- <br>
      Path to your filter configuration xml file.<br>
<br>
      Default: /WEB-INF/response-header-filter.xml<br>
    --&gt;<br>
    &lt;param-value&gt;/WEB-INF/path-to/the-filter/config-file.xml&lt;/param-value&gt; <br>
  &lt;/init-param&gt;<br>
&lt;/filter-mapping&gt;<br>
</code></pre>