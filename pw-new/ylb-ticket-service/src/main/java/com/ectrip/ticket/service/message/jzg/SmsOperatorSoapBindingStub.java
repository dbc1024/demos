/**
 * SmsOperatorSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 16, 2004 (12:19:44 EST) WSDL2Java emitter.
 *//*

package com.ectrip.ticket.service.message.jzg;

public class SmsOperatorSoapBindingStub extends org.apache.axis.client.Stub implements com.ectrip.ticket.service.message.jzg.SmsOperator {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendSms");
        oper.addParameter(new javax.xml.namespace.QName("", "account"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "password"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "message"), new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "MTMessage"), com.ectrip.ticket.service.message.jzg.MTMessage.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "ArrayOfMTResponse"));
        oper.setReturnClass(com.ectrip.ticket.service.message.jzg.MTResponse[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "sendSmsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSms");
        oper.addParameter(new javax.xml.namespace.QName("", "account"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "password"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "ArrayOfMOMessage"));
        oper.setReturnClass(com.ectrip.ticket.service.message.jzg.MOMessage[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getSmsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReport");
        oper.addParameter(new javax.xml.namespace.QName("", "account"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "password"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "message"), new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "MTMessage"), com.ectrip.ticket.service.message.jzg.MTMessage.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "ArrayOfMTReport"));
        oper.setReturnClass(com.ectrip.ticket.service.message.jzg.MTReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBalance");
        oper.addParameter(new javax.xml.namespace.QName("", "account"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "password"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "Balance"));
        oper.setReturnClass(com.ectrip.ticket.service.message.jzg.Balance.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getBalanceReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

    }

    public SmsOperatorSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SmsOperatorSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SmsOperatorSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "MTMessage");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.MTMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "Balance");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.Balance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "ArrayOfMTReport");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.MTReport[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "MOMessage");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.MOMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "ArrayOfMOMessage");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.MOMessage[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "MTReport");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.MTReport.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "MTResponse");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.MTResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://3tong.cn:8080/ema_new/services/SmsOperator", "ArrayOfMTResponse");
            cachedSerQNames.add(qName);
            cls = com.ectrip.ticket.service.message.jzg.MTResponse[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call =
                    (org.apache.axis.client.Call) super.service.createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                        java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                        _call.registerTypeMapping(cls, qName, sf, df, false);
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.ectrip.ticket.service.message.jzg.MTResponse[] sendSms(java.lang.String account, java.lang.String password, com.ectrip.ticket.service.message.jzg.MTMessage message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.ema.ctc.com", "sendSms"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {account, password, message});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ectrip.ticket.service.message.jzg.MTResponse[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ectrip.ticket.service.message.jzg.MTResponse[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.ectrip.ticket.service.message.jzg.MTResponse[].class);
            }
        }
    }

    public com.ectrip.ticket.service.message.jzg.MOMessage[] getSms(java.lang.String account, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.ema.ctc.com", "getSms"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {account, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ectrip.ticket.service.message.jzg.MOMessage[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ectrip.ticket.service.message.jzg.MOMessage[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.ectrip.ticket.service.message.jzg.MOMessage[].class);
            }
        }
    }

    public com.ectrip.ticket.service.message.jzg.MTReport[] getReport(java.lang.String account, java.lang.String password, com.ectrip.ticket.service.message.jzg.MTMessage message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.ema.ctc.com", "getReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {account, password, message});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ectrip.ticket.service.message.jzg.MTReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ectrip.ticket.service.message.jzg.MTReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.ectrip.ticket.service.message.jzg.MTReport[].class);
            }
        }
    }

    public com.ectrip.ticket.service.message.jzg.Balance getBalance(java.lang.String account, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.ema.ctc.com", "getBalance"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {account, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.ectrip.ticket.service.message.jzg.Balance) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.ectrip.ticket.service.message.jzg.Balance) org.apache.axis.utils.JavaUtils.convert(_resp, com.ectrip.ticket.service.message.jzg.Balance.class);
            }
        }
    }

}
*/