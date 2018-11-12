/**
 * EctripOTOServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 *//*

package com.ectrip.ticket.cyt.client.v1.api;

public class EctripOTOServiceServiceLocator extends org.apache.axis.client.Service implements EctripOTOServiceService {

    *//**
	 * EctripOTOServiceServiceLocator.java
	 * liujianwen
	 * 20142014-4-29
	 *//*
	private static final long serialVersionUID = 1L;

	public EctripOTOServiceServiceLocator() {
    }


    public EctripOTOServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EctripOTOServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ectripOTOService
    private java.lang.String ectripOTOService_address = "http://localhost:8091/services/ectripOTOService";

    public java.lang.String getectripOTOServiceAddress() {
        return ectripOTOService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ectripOTOServiceWSDDServiceName = "ectripOTOService";

    public java.lang.String getectripOTOServiceWSDDServiceName() {
        return ectripOTOServiceWSDDServiceName;
    }

    public void setectripOTOServiceWSDDServiceName(java.lang.String name) {
        ectripOTOServiceWSDDServiceName = name;
    }

    public EctripOTOService getectripOTOService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ectripOTOService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getectripOTOService(endpoint);
    }

    public EctripOTOService getectripOTOService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            EctripOTOServiceSoapBindingStub _stub = new EctripOTOServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getectripOTOServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setectripOTOServiceEndpointAddress(java.lang.String address) {
        ectripOTOService_address = address;
    }

    *//**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     *//*
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (EctripOTOService.class.isAssignableFrom(serviceEndpointInterface)) {
                EctripOTOServiceSoapBindingStub _stub = new EctripOTOServiceSoapBindingStub(new java.net.URL(ectripOTOService_address), this);
                _stub.setPortName(getectripOTOServiceWSDDServiceName());
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
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ectripOTOService".equals(inputPortName)) {
            return getectripOTOService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8091/services/ectripOTOService", "EctripOTOServiceService");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8091/services/ectripOTOService", "ectripOTOService"));
        }
        return ports.iterator();
    }

    *//**
    * Set the endpoint address for the specified port name.
    *//*
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ectripOTOService".equals(portName)) {
            setectripOTOServiceEndpointAddress(address);
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