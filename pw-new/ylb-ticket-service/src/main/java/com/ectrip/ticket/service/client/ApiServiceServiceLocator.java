/**
 * ApiServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 *//*

package com.ectrip.ticket.service.client;

public class ApiServiceServiceLocator extends org.apache.axis.client.Service implements com.ectrip.ticket.service.client.ApiServiceService {

    public ApiServiceServiceLocator() {
    }


    public ApiServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ApiServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ApiService
    private java.lang.String ApiService_address = "http://127.0.0.1/services/ApiService";

    public java.lang.String getApiServiceAddress() {
        return ApiService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ApiServiceWSDDServiceName = "ApiService";

    public java.lang.String getApiServiceWSDDServiceName() {
        return ApiServiceWSDDServiceName;
    }

    public void setApiServiceWSDDServiceName(java.lang.String name) {
        ApiServiceWSDDServiceName = name;
    }

    public com.ectrip.ticket.service.client.ApiService_PortType getApiService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ApiService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getApiService(endpoint);
    }

    public com.ectrip.ticket.service.client.ApiService_PortType getApiService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ectrip.ticket.service.client.ApiServiceSoapBindingStub _stub = new com.ectrip.ticket.service.client.ApiServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getApiServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setApiServiceEndpointAddress(java.lang.String address) {
        ApiService_address = address;
    }

    *//**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     *//*
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ectrip.ticket.service.client.ApiService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ectrip.ticket.service.client.ApiServiceSoapBindingStub _stub = new com.ectrip.ticket.service.client.ApiServiceSoapBindingStub(new java.net.URL(ApiService_address), this);
                _stub.setPortName(getApiServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    *//**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     *//*
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ApiService".equals(inputPortName)) {
            return getApiService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://127.0.0.1/services/ApiService", "ApiServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://127.0.0.1/services/ApiService", "ApiService"));
        }
        return ports.iterator();
    }

    *//**
    * Set the endpoint address for the specified port name.
    *//*
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ApiService".equals(portName)) {
            setApiServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    *//**
    * Set the endpoint address for the specified port name.
    *//*
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
*/