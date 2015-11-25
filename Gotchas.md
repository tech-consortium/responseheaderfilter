# Content-Type header is always text/html #
This might happen when Struts 1.x is your front controller. Struts has a `RequestProcessor` that handles an incoming request and applies rules as specified in the struts-config. Being a web application filter, the ResponseHeaderFilter comes in much before struts and applies all the response headers as specified by you in the config. Struts would **always** override the `content-type` header for an incoming request. This issue was earlier discussed on the Struts' user mailing list [here](http://www.nabble.com/RequestProcessor-and-contentType-response-header-td24126068.html).

The easiest workaround is to create your own controller class which is a subclass of the struts' `RequestProccessor` class and override the `processContent` method as follows:
```
import org.apache.struts.action.RequestProcessor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyRequestProcessor extends RequestProcessor {
  /**
   * Overriding the default behaviour of <b>always</b> setting the content-type header.
   * The super's <code>processContent</code> method is only invoked when the content-type header is
   * not already set.
   *
   * @param request (current request)
   * @param response (response to be processed)
   */
  protected void processContent(HttpServletRequest request, HttpServletResponse response) {
    if(response.getContentType() != null){
      super.processContent(request, response);
    }        
  }
}
```
and then add the above class to your struts-config.xml to tell struts that you have a custom controller:
```
<controller processorClass="com.foo.MyRequestProcessor"/>
```
**Note: Add this only after all the `<action-mappings>` in your struts-config.xml**