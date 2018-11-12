/**
 * ApiService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ectrip.ticket.service.client;

public interface ApiService_PortType extends java.rmi.Remote {
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
    public java.lang.String getProvider(java.lang.String validcode) throws java.rmi.RemoteException;
    public java.lang.Object[] getEspdutytab() throws java.rmi.RemoteException;
    public java.lang.String test() throws java.rmi.RemoteException;
    public java.lang.String downTicketStation() throws java.rmi.RemoteException;
//    public com.ectrip.ticket.service.client.ResultBean getResultBean() throws java.rmi.RemoteException;
}
