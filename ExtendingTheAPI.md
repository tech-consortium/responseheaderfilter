With ResponseHeaderFilter, writing your own custom implementation for applying the rules is very easy. Many a times, you may need to apply response headers based on the request or response. The ResponseHeaderFilter takes charge of performing the match for a particular mapping (and this functionality cannot be extended). Once a particular `<mapping>` is found to be a match, your processor class will be invoked to implement the rules in the response headers.

You may define a processor at the **root level** in your config, e.g
```
<response-header-mapper processorClass="com.foo.YourClass">
  ...
</response-header-mapper>
```
the class specified above would be applicable for all the `<mapping>`'s in your file. If you need an overriding behavior for a particular mapping, you can define a `processorClass` attribute for your `<mapping>`, e.g
```
<response-header-mapper processorClass="com.foo.YourClass">
  <mapping url="(.*).js" processorClass="com.foo.YourClassForThisMapping">
    ...
  </mapping>
  ...
</response-header-mapper>
```

And, yes writing a processor class is very simple. All you need to do is to implement the `com.avlesh.web.filter.responseheaderfilter.MappingProcessor` interface. This is how a typical `MappingProcessor` implementation would look like:
```
package com.foo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SimpleMappingProcessor implements MappingProcessor {
  public void preProcess(HttpServletRequest request, HttpServletResponse response, Mapping applicableMapping) {
  }

  public void applyHeaders(HttpServletRequest request,
                           HttpServletResponse response, 
                           List<Mapping.ResponseHeader> responseHeaders,
                           Mapping applicableMapping) {
    for(Mapping.ResponseHeader responseHeader : responseHeaders){
      response.setHeader(responseHeader.getResponseHeaderKey(), responseHeader.getResponseHeaderValue());
    }
  }

  public void postProcess(HttpServletRequest request, HttpServletResponse response, Mapping applicableMapping) {
  }
}
```
The interesting pieces are the `preProcess` and `postProcess` methods. These are called just before and after the `applyHeaders` is called by the Filter, when a match happens. In all the above methods, other than the `request` and `response`, you'll also have access to the [Mapping](http://code.google.com/p/responseheaderfilter/source/browse/trunk/src/java/com/avlesh/web/filter/responseheaderfilter/Mapping.java) for this particular url as specified in the config.