/**
 * FilmServiceImplDAOServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package service;

public class FilmServiceImplDAOServiceLocator extends org.apache.axis.client.Service implements service.FilmServiceImplDAOService {

    public FilmServiceImplDAOServiceLocator() {
    }


    public FilmServiceImplDAOServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FilmServiceImplDAOServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FilmServiceImplDAO
    private java.lang.String FilmServiceImplDAO_address = "http://localhost:8082/Assignment-FilmSOAP/services/FilmServiceImplDAO";

    public java.lang.String getFilmServiceImplDAOAddress() {
        return FilmServiceImplDAO_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FilmServiceImplDAOWSDDServiceName = "FilmServiceImplDAO";

    public java.lang.String getFilmServiceImplDAOWSDDServiceName() {
        return FilmServiceImplDAOWSDDServiceName;
    }

    public void setFilmServiceImplDAOWSDDServiceName(java.lang.String name) {
        FilmServiceImplDAOWSDDServiceName = name;
    }

    public service.FilmServiceImplDAO getFilmServiceImplDAO() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FilmServiceImplDAO_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFilmServiceImplDAO(endpoint);
    }

    public service.FilmServiceImplDAO getFilmServiceImplDAO(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            service.FilmServiceImplDAOSoapBindingStub _stub = new service.FilmServiceImplDAOSoapBindingStub(portAddress, this);
            _stub.setPortName(getFilmServiceImplDAOWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFilmServiceImplDAOEndpointAddress(java.lang.String address) {
        FilmServiceImplDAO_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (service.FilmServiceImplDAO.class.isAssignableFrom(serviceEndpointInterface)) {
                service.FilmServiceImplDAOSoapBindingStub _stub = new service.FilmServiceImplDAOSoapBindingStub(new java.net.URL(FilmServiceImplDAO_address), this);
                _stub.setPortName(getFilmServiceImplDAOWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FilmServiceImplDAO".equals(inputPortName)) {
            return getFilmServiceImplDAO();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service", "FilmServiceImplDAOService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service", "FilmServiceImplDAO"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FilmServiceImplDAO".equals(portName)) {
            setFilmServiceImplDAOEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
