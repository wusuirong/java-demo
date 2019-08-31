package junitbook.pages;

import mockmaker.ReturnValues;
import mockmaker.VoidReturnValues;
import mockmaker.ExceptionalReturnValue;
import com.mockobjects.*;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
public class MockPageContext extends PageContext{
   private ExpectationCounter myInitializeCalls = new ExpectationCounter("javax.servlet.jsp.PageContext InitializeCalls");
   private ReturnValues myActualInitializeReturnValues = new VoidReturnValues(false);
   private ExpectationList myInitializeParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext javax.servlet.Servlet");
   private ExpectationList myInitializeParameter1Values = new ExpectationList("javax.servlet.jsp.PageContext javax.servlet.ServletRequest");
   private ExpectationList myInitializeParameter2Values = new ExpectationList("javax.servlet.jsp.PageContext javax.servlet.ServletResponse");
   private ExpectationList myInitializeParameter3Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationList myInitializeParameter4Values = new ExpectationList("javax.servlet.jsp.PageContext boolean");
   private ExpectationList myInitializeParameter5Values = new ExpectationList("javax.servlet.jsp.PageContext int");
   private ExpectationList myInitializeParameter6Values = new ExpectationList("javax.servlet.jsp.PageContext boolean");
   private ExpectationCounter myReleaseCalls = new ExpectationCounter("javax.servlet.jsp.PageContext ReleaseCalls");
   private ReturnValues myActualReleaseReturnValues = new VoidReturnValues(false);
   private ExpectationCounter mySetAttributeStringObjectCalls = new ExpectationCounter("javax.servlet.jsp.PageContext SetAttributeStringObjectCalls");
   private ReturnValues myActualSetAttributeStringObjectReturnValues = new VoidReturnValues(false);
   private ExpectationList mySetAttributeStringObjectParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationList mySetAttributeStringObjectParameter1Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.Object");
   private ExpectationCounter mySetAttributeStringObjectIntCalls = new ExpectationCounter("javax.servlet.jsp.PageContext SetAttributeStringObjectIntCalls");
   private ReturnValues myActualSetAttributeStringObjectIntReturnValues = new VoidReturnValues(false);
   private ExpectationList mySetAttributeStringObjectIntParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationList mySetAttributeStringObjectIntParameter1Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.Object");
   private ExpectationList mySetAttributeStringObjectIntParameter2Values = new ExpectationList("javax.servlet.jsp.PageContext int");
   private ExpectationCounter myGetAttributeStringCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetAttributeStringCalls");
   private ReturnValues myActualGetAttributeStringReturnValues = new ReturnValues(false);
   private ExpectationList myGetAttributeStringParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationCounter myGetAttributeStringIntCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetAttributeStringIntCalls");
   private ReturnValues myActualGetAttributeStringIntReturnValues = new ReturnValues(false);
   private ExpectationList myGetAttributeStringIntParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationList myGetAttributeStringIntParameter1Values = new ExpectationList("javax.servlet.jsp.PageContext int");
   private ExpectationCounter myFindAttributeCalls = new ExpectationCounter("javax.servlet.jsp.PageContext FindAttributeCalls");
   private ReturnValues myActualFindAttributeReturnValues = new ReturnValues(false);
   private ExpectationList myFindAttributeParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationCounter myRemoveAttributeStringCalls = new ExpectationCounter("javax.servlet.jsp.PageContext RemoveAttributeStringCalls");
   private ReturnValues myActualRemoveAttributeStringReturnValues = new VoidReturnValues(false);
   private ExpectationList myRemoveAttributeStringParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationCounter myRemoveAttributeStringIntCalls = new ExpectationCounter("javax.servlet.jsp.PageContext RemoveAttributeStringIntCalls");
   private ReturnValues myActualRemoveAttributeStringIntReturnValues = new VoidReturnValues(false);
   private ExpectationList myRemoveAttributeStringIntParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationList myRemoveAttributeStringIntParameter1Values = new ExpectationList("javax.servlet.jsp.PageContext int");
   private ExpectationCounter myGetAttributesScopeCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetAttributesScopeCalls");
   private ReturnValues myActualGetAttributesScopeReturnValues = new ReturnValues(false);
   private ExpectationList myGetAttributesScopeParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationCounter myGetAttributeNamesInScopeCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetAttributeNamesInScopeCalls");
   private ReturnValues myActualGetAttributeNamesInScopeReturnValues = new ReturnValues(false);
   private ExpectationList myGetAttributeNamesInScopeParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext int");
   private ExpectationCounter myGetOutCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetOutCalls");
   private ReturnValues myActualGetOutReturnValues = new ReturnValues(false);
   private ExpectationCounter myGetSessionCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetSessionCalls");
   private ReturnValues myActualGetSessionReturnValues = new ReturnValues(false);
   private ExpectationCounter myGetPageCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetPageCalls");
   private ReturnValues myActualGetPageReturnValues = new ReturnValues(false);
   private ExpectationCounter myGetRequestCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetRequestCalls");
   private ReturnValues myActualGetRequestReturnValues = new ReturnValues(false);
   private ExpectationCounter myGetResponseCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetResponseCalls");
   private ReturnValues myActualGetResponseReturnValues = new ReturnValues(false);
   private ExpectationCounter myGetExceptionCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetExceptionCalls");
   private ReturnValues myActualGetExceptionReturnValues = new ReturnValues(false);
   private ExpectationCounter myGetServletConfigCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetServletConfigCalls");
   private ReturnValues myActualGetServletConfigReturnValues = new ReturnValues(false);
   private ExpectationCounter myGetServletContextCalls = new ExpectationCounter("javax.servlet.jsp.PageContext GetServletContextCalls");
   private ReturnValues myActualGetServletContextReturnValues = new ReturnValues(false);
   private ExpectationCounter myForwardCalls = new ExpectationCounter("javax.servlet.jsp.PageContext ForwardCalls");
   private ReturnValues myActualForwardReturnValues = new VoidReturnValues(false);
   private ExpectationList myForwardParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationCounter myIncludeCalls = new ExpectationCounter("javax.servlet.jsp.PageContext IncludeCalls");
   private ReturnValues myActualIncludeReturnValues = new VoidReturnValues(false);
   private ExpectationList myIncludeParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.String");
   private ExpectationCounter myHandlePageExceptionExceptionCalls = new ExpectationCounter("javax.servlet.jsp.PageContext HandlePageExceptionExceptionCalls");
   private ReturnValues myActualHandlePageExceptionExceptionReturnValues = new VoidReturnValues(false);
   private ExpectationList myHandlePageExceptionExceptionParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.Exception");
   private ExpectationCounter myHandlePageExceptionThrowableCalls = new ExpectationCounter("javax.servlet.jsp.PageContext HandlePageExceptionThrowableCalls");
   private ReturnValues myActualHandlePageExceptionThrowableReturnValues = new VoidReturnValues(false);
   private ExpectationList myHandlePageExceptionThrowableParameter0Values = new ExpectationList("javax.servlet.jsp.PageContext java.lang.Throwable");
   private ExpectationCounter myPushBodyCalls = new ExpectationCounter("javax.servlet.jsp.PageContext PushBodyCalls");
   private ReturnValues myActualPushBodyReturnValues = new ReturnValues(false);
   private ExpectationCounter myPopBodyCalls = new ExpectationCounter("javax.servlet.jsp.PageContext PopBodyCalls");
   private ReturnValues myActualPopBodyReturnValues = new ReturnValues(false);
   public void setExpectedInitializeCalls(int calls){
      myInitializeCalls.setExpected(calls);
   }
   public void addExpectedInitializeValues(Servlet arg0, ServletRequest arg1, ServletResponse arg2, String arg3, boolean arg4, int arg5, boolean arg6){
      myInitializeParameter0Values.addExpected(arg0);
      myInitializeParameter1Values.addExpected(arg1);
      myInitializeParameter2Values.addExpected(arg2);
      myInitializeParameter3Values.addExpected(arg3);
      myInitializeParameter4Values.addExpected(new Boolean(arg4));
      myInitializeParameter5Values.addExpected(new Integer(arg5));
      myInitializeParameter6Values.addExpected(new Boolean(arg6));
   }
   public void initialize(Servlet arg0, ServletRequest arg1, ServletResponse arg2, String arg3, boolean arg4, int arg5, boolean arg6) throws IOException, IllegalStateException, IllegalArgumentException{
      myInitializeCalls.inc();
      myInitializeParameter0Values.addActual(arg0);
      myInitializeParameter1Values.addActual(arg1);
      myInitializeParameter2Values.addActual(arg2);
      myInitializeParameter3Values.addActual(arg3);
      myInitializeParameter4Values.addActual(new Boolean(arg4));
      myInitializeParameter5Values.addActual(new Integer(arg5));
      myInitializeParameter6Values.addActual(new Boolean(arg6));
      Object nextReturnValue = myActualInitializeReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof IOException)
          throw (IOException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof IllegalStateException)
          throw (IllegalStateException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof IllegalArgumentException)
          throw (IllegalArgumentException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionInitialize(Throwable arg){
      myActualInitializeReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedReleaseCalls(int calls){
      myReleaseCalls.setExpected(calls);
   }
   public void release(){
      myReleaseCalls.inc();
      Object nextReturnValue = myActualReleaseReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionRelease(Throwable arg){
      myActualReleaseReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedSetAttributeStringObjectCalls(int calls){
      mySetAttributeStringObjectCalls.setExpected(calls);
   }
   public void addExpectedSetAttributeStringObjectValues(String arg0, Object arg1){
      mySetAttributeStringObjectParameter0Values.addExpected(arg0);
      mySetAttributeStringObjectParameter1Values.addExpected(arg1);
   }
   public void setAttribute(String arg0, Object arg1){
      mySetAttributeStringObjectCalls.inc();
      mySetAttributeStringObjectParameter0Values.addActual(arg0);
      mySetAttributeStringObjectParameter1Values.addActual(arg1);
      Object nextReturnValue = myActualSetAttributeStringObjectReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionSetAttributeStringObject(Throwable arg){
      myActualSetAttributeStringObjectReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedSetAttributeStringObjectIntCalls(int calls){
      mySetAttributeStringObjectIntCalls.setExpected(calls);
   }
   public void addExpectedSetAttributeStringObjectIntValues(String arg0, Object arg1, int arg2){
      mySetAttributeStringObjectIntParameter0Values.addExpected(arg0);
      mySetAttributeStringObjectIntParameter1Values.addExpected(arg1);
      mySetAttributeStringObjectIntParameter2Values.addExpected(new Integer(arg2));
   }
   public void setAttribute(String arg0, Object arg1, int arg2){
      mySetAttributeStringObjectIntCalls.inc();
      mySetAttributeStringObjectIntParameter0Values.addActual(arg0);
      mySetAttributeStringObjectIntParameter1Values.addActual(arg1);
      mySetAttributeStringObjectIntParameter2Values.addActual(new Integer(arg2));
      Object nextReturnValue = myActualSetAttributeStringObjectIntReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionSetAttributeStringObjectInt(Throwable arg){
      myActualSetAttributeStringObjectIntReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedGetAttributeStringCalls(int calls){
      myGetAttributeStringCalls.setExpected(calls);
   }
   public void addExpectedGetAttributeStringValues(String arg0){
      myGetAttributeStringParameter0Values.addExpected(arg0);
   }
   public Object getAttribute(String arg0){
      myGetAttributeStringCalls.inc();
      myGetAttributeStringParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualGetAttributeStringReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (Object) nextReturnValue;
   }
   public void setupExceptionGetAttributeString(Throwable arg){
      myActualGetAttributeStringReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetAttributeString(Object arg){
      myActualGetAttributeStringReturnValues.add(arg);
   }
   public void setExpectedGetAttributeStringIntCalls(int calls){
      myGetAttributeStringIntCalls.setExpected(calls);
   }
   public void addExpectedGetAttributeStringIntValues(String arg0, int arg1){
      myGetAttributeStringIntParameter0Values.addExpected(arg0);
      myGetAttributeStringIntParameter1Values.addExpected(new Integer(arg1));
   }
   public Object getAttribute(String arg0, int arg1){
      myGetAttributeStringIntCalls.inc();
      myGetAttributeStringIntParameter0Values.addActual(arg0);
      myGetAttributeStringIntParameter1Values.addActual(new Integer(arg1));
      Object nextReturnValue = myActualGetAttributeStringIntReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (Object) nextReturnValue;
   }
   public void setupExceptionGetAttributeStringInt(Throwable arg){
      myActualGetAttributeStringIntReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetAttributeStringInt(Object arg){
      myActualGetAttributeStringIntReturnValues.add(arg);
   }
   public void setExpectedFindAttributeCalls(int calls){
      myFindAttributeCalls.setExpected(calls);
   }
   public void addExpectedFindAttributeValues(String arg0){
      myFindAttributeParameter0Values.addExpected(arg0);
   }
   public Object findAttribute(String arg0){
      myFindAttributeCalls.inc();
      myFindAttributeParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualFindAttributeReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (Object) nextReturnValue;
   }
   public void setupExceptionFindAttribute(Throwable arg){
      myActualFindAttributeReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupFindAttribute(Object arg){
      myActualFindAttributeReturnValues.add(arg);
   }
   public void setExpectedRemoveAttributeStringCalls(int calls){
      myRemoveAttributeStringCalls.setExpected(calls);
   }
   public void addExpectedRemoveAttributeStringValues(String arg0){
      myRemoveAttributeStringParameter0Values.addExpected(arg0);
   }
   public void removeAttribute(String arg0){
      myRemoveAttributeStringCalls.inc();
      myRemoveAttributeStringParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualRemoveAttributeStringReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionRemoveAttributeString(Throwable arg){
      myActualRemoveAttributeStringReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedRemoveAttributeStringIntCalls(int calls){
      myRemoveAttributeStringIntCalls.setExpected(calls);
   }
   public void addExpectedRemoveAttributeStringIntValues(String arg0, int arg1){
      myRemoveAttributeStringIntParameter0Values.addExpected(arg0);
      myRemoveAttributeStringIntParameter1Values.addExpected(new Integer(arg1));
   }
   public void removeAttribute(String arg0, int arg1){
      myRemoveAttributeStringIntCalls.inc();
      myRemoveAttributeStringIntParameter0Values.addActual(arg0);
      myRemoveAttributeStringIntParameter1Values.addActual(new Integer(arg1));
      Object nextReturnValue = myActualRemoveAttributeStringIntReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionRemoveAttributeStringInt(Throwable arg){
      myActualRemoveAttributeStringIntReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedGetAttributesScopeCalls(int calls){
      myGetAttributesScopeCalls.setExpected(calls);
   }
   public void addExpectedGetAttributesScopeValues(String arg0){
      myGetAttributesScopeParameter0Values.addExpected(arg0);
   }
   public int getAttributesScope(String arg0){
      myGetAttributesScopeCalls.inc();
      myGetAttributesScopeParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualGetAttributesScopeReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return ((Integer) nextReturnValue).intValue();
   }
   public void setupExceptionGetAttributesScope(Throwable arg){
      myActualGetAttributesScopeReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetAttributesScope(int arg){
      myActualGetAttributesScopeReturnValues.add(new Integer(arg));
   }
   public void setExpectedGetAttributeNamesInScopeCalls(int calls){
      myGetAttributeNamesInScopeCalls.setExpected(calls);
   }
   public void addExpectedGetAttributeNamesInScopeValues(int arg0){
      myGetAttributeNamesInScopeParameter0Values.addExpected(new Integer(arg0));
   }
   public Enumeration getAttributeNamesInScope(int arg0){
      myGetAttributeNamesInScopeCalls.inc();
      myGetAttributeNamesInScopeParameter0Values.addActual(new Integer(arg0));
      Object nextReturnValue = myActualGetAttributeNamesInScopeReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (Enumeration) nextReturnValue;
   }
   public void setupExceptionGetAttributeNamesInScope(Throwable arg){
      myActualGetAttributeNamesInScopeReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetAttributeNamesInScope(Enumeration arg){
      myActualGetAttributeNamesInScopeReturnValues.add(arg);
   }
   public void setExpectedGetOutCalls(int calls){
      myGetOutCalls.setExpected(calls);
   }
   public JspWriter getOut(){
      myGetOutCalls.inc();
      Object nextReturnValue = myActualGetOutReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (JspWriter) nextReturnValue;
   }
   public void setupExceptionGetOut(Throwable arg){
      myActualGetOutReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetOut(JspWriter arg){
      myActualGetOutReturnValues.add(arg);
   }
   public void setExpectedGetSessionCalls(int calls){
      myGetSessionCalls.setExpected(calls);
   }
   public HttpSession getSession(){
      myGetSessionCalls.inc();
      Object nextReturnValue = myActualGetSessionReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (HttpSession) nextReturnValue;
   }
   public void setupExceptionGetSession(Throwable arg){
      myActualGetSessionReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetSession(HttpSession arg){
      myActualGetSessionReturnValues.add(arg);
   }
   public void setExpectedGetPageCalls(int calls){
      myGetPageCalls.setExpected(calls);
   }
   public Object getPage(){
      myGetPageCalls.inc();
      Object nextReturnValue = myActualGetPageReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (Object) nextReturnValue;
   }
   public void setupExceptionGetPage(Throwable arg){
      myActualGetPageReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetPage(Object arg){
      myActualGetPageReturnValues.add(arg);
   }
   public void setExpectedGetRequestCalls(int calls){
      myGetRequestCalls.setExpected(calls);
   }
   public ServletRequest getRequest(){
      myGetRequestCalls.inc();
      Object nextReturnValue = myActualGetRequestReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (ServletRequest) nextReturnValue;
   }
   public void setupExceptionGetRequest(Throwable arg){
      myActualGetRequestReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetRequest(ServletRequest arg){
      myActualGetRequestReturnValues.add(arg);
   }
   public void setExpectedGetResponseCalls(int calls){
      myGetResponseCalls.setExpected(calls);
   }
   public ServletResponse getResponse(){
      myGetResponseCalls.inc();
      Object nextReturnValue = myActualGetResponseReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (ServletResponse) nextReturnValue;
   }
   public void setupExceptionGetResponse(Throwable arg){
      myActualGetResponseReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetResponse(ServletResponse arg){
      myActualGetResponseReturnValues.add(arg);
   }
   public void setExpectedGetExceptionCalls(int calls){
      myGetExceptionCalls.setExpected(calls);
   }
   public Exception getException(){
      myGetExceptionCalls.inc();
      Object nextReturnValue = myActualGetExceptionReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (Exception) nextReturnValue;
   }
   public void setupExceptionGetException(Throwable arg){
      myActualGetExceptionReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetException(Exception arg){
      myActualGetExceptionReturnValues.add(arg);
   }
   public void setExpectedGetServletConfigCalls(int calls){
      myGetServletConfigCalls.setExpected(calls);
   }
   public ServletConfig getServletConfig(){
      myGetServletConfigCalls.inc();
      Object nextReturnValue = myActualGetServletConfigReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (ServletConfig) nextReturnValue;
   }
   public void setupExceptionGetServletConfig(Throwable arg){
      myActualGetServletConfigReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetServletConfig(ServletConfig arg){
      myActualGetServletConfigReturnValues.add(arg);
   }
   public void setExpectedGetServletContextCalls(int calls){
      myGetServletContextCalls.setExpected(calls);
   }
   public ServletContext getServletContext(){
      myGetServletContextCalls.inc();
      Object nextReturnValue = myActualGetServletContextReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (ServletContext) nextReturnValue;
   }
   public void setupExceptionGetServletContext(Throwable arg){
      myActualGetServletContextReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupGetServletContext(ServletContext arg){
      myActualGetServletContextReturnValues.add(arg);
   }
   public void setExpectedForwardCalls(int calls){
      myForwardCalls.setExpected(calls);
   }
   public void addExpectedForwardValues(String arg0){
      myForwardParameter0Values.addExpected(arg0);
   }
   public void forward(String arg0) throws ServletException, IOException{
      myForwardCalls.inc();
      myForwardParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualForwardReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof ServletException)
          throw (ServletException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof IOException)
          throw (IOException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionForward(Throwable arg){
      myActualForwardReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedIncludeCalls(int calls){
      myIncludeCalls.setExpected(calls);
   }
   public void addExpectedIncludeValues(String arg0){
      myIncludeParameter0Values.addExpected(arg0);
   }
   public void include(String arg0) throws ServletException, IOException{
      myIncludeCalls.inc();
      myIncludeParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualIncludeReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof ServletException)
          throw (ServletException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof IOException)
          throw (IOException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionInclude(Throwable arg){
      myActualIncludeReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedHandlePageExceptionExceptionCalls(int calls){
      myHandlePageExceptionExceptionCalls.setExpected(calls);
   }
   public void addExpectedHandlePageExceptionExceptionValues(Exception arg0){
      myHandlePageExceptionExceptionParameter0Values.addExpected(arg0);
   }
   public void handlePageException(Exception arg0) throws ServletException, IOException{
      myHandlePageExceptionExceptionCalls.inc();
      myHandlePageExceptionExceptionParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualHandlePageExceptionExceptionReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof ServletException)
          throw (ServletException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof IOException)
          throw (IOException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionHandlePageExceptionException(Throwable arg){
      myActualHandlePageExceptionExceptionReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedHandlePageExceptionThrowableCalls(int calls){
      myHandlePageExceptionThrowableCalls.setExpected(calls);
   }
   public void addExpectedHandlePageExceptionThrowableValues(Throwable arg0){
      myHandlePageExceptionThrowableParameter0Values.addExpected(arg0);
   }
   public void handlePageException(Throwable arg0) throws ServletException, IOException{
      myHandlePageExceptionThrowableCalls.inc();
      myHandlePageExceptionThrowableParameter0Values.addActual(arg0);
      Object nextReturnValue = myActualHandlePageExceptionThrowableReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof ServletException)
          throw (ServletException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof IOException)
          throw (IOException)((ExceptionalReturnValue)nextReturnValue).getException();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
   }
   public void setupExceptionHandlePageExceptionThrowable(Throwable arg){
      myActualHandlePageExceptionThrowableReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setExpectedPushBodyCalls(int calls){
      myPushBodyCalls.setExpected(calls);
   }
   public BodyContent pushBody(){
      myPushBodyCalls.inc();
      Object nextReturnValue = myActualPushBodyReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (BodyContent) nextReturnValue;
   }
   public void setupExceptionPushBody(Throwable arg){
      myActualPushBodyReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupPushBody(BodyContent arg){
      myActualPushBodyReturnValues.add(arg);
   }
   public void setExpectedPopBodyCalls(int calls){
      myPopBodyCalls.setExpected(calls);
   }
   public JspWriter popBody(){
      myPopBodyCalls.inc();
      Object nextReturnValue = myActualPopBodyReturnValues.getNext();
      if (nextReturnValue instanceof ExceptionalReturnValue && ((ExceptionalReturnValue)nextReturnValue).getException() instanceof RuntimeException)
          throw (RuntimeException)((ExceptionalReturnValue)nextReturnValue).getException();
      return (JspWriter) nextReturnValue;
   }
   public void setupExceptionPopBody(Throwable arg){
      myActualPopBodyReturnValues.add(new ExceptionalReturnValue(arg));
   }
   public void setupPopBody(JspWriter arg){
      myActualPopBodyReturnValues.add(arg);
   }
   public void verify(){
      myInitializeCalls.verify();
      myInitializeParameter0Values.verify();
      myInitializeParameter1Values.verify();
      myInitializeParameter2Values.verify();
      myInitializeParameter3Values.verify();
      myInitializeParameter4Values.verify();
      myInitializeParameter5Values.verify();
      myInitializeParameter6Values.verify();
      myReleaseCalls.verify();
      mySetAttributeStringObjectCalls.verify();
      mySetAttributeStringObjectParameter0Values.verify();
      mySetAttributeStringObjectParameter1Values.verify();
      mySetAttributeStringObjectIntCalls.verify();
      mySetAttributeStringObjectIntParameter0Values.verify();
      mySetAttributeStringObjectIntParameter1Values.verify();
      mySetAttributeStringObjectIntParameter2Values.verify();
      myGetAttributeStringCalls.verify();
      myGetAttributeStringParameter0Values.verify();
      myGetAttributeStringIntCalls.verify();
      myGetAttributeStringIntParameter0Values.verify();
      myGetAttributeStringIntParameter1Values.verify();
      myFindAttributeCalls.verify();
      myFindAttributeParameter0Values.verify();
      myRemoveAttributeStringCalls.verify();
      myRemoveAttributeStringParameter0Values.verify();
      myRemoveAttributeStringIntCalls.verify();
      myRemoveAttributeStringIntParameter0Values.verify();
      myRemoveAttributeStringIntParameter1Values.verify();
      myGetAttributesScopeCalls.verify();
      myGetAttributesScopeParameter0Values.verify();
      myGetAttributeNamesInScopeCalls.verify();
      myGetAttributeNamesInScopeParameter0Values.verify();
      myGetOutCalls.verify();
      myGetSessionCalls.verify();
      myGetPageCalls.verify();
      myGetRequestCalls.verify();
      myGetResponseCalls.verify();
      myGetExceptionCalls.verify();
      myGetServletConfigCalls.verify();
      myGetServletContextCalls.verify();
      myForwardCalls.verify();
      myForwardParameter0Values.verify();
      myIncludeCalls.verify();
      myIncludeParameter0Values.verify();
      myHandlePageExceptionExceptionCalls.verify();
      myHandlePageExceptionExceptionParameter0Values.verify();
      myHandlePageExceptionThrowableCalls.verify();
      myHandlePageExceptionThrowableParameter0Values.verify();
      myPushBodyCalls.verify();
      myPopBodyCalls.verify();
   }
public MockPageContext(){
   super();
}
}
