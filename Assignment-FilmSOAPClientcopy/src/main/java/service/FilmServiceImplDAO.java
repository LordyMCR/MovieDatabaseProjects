/**
 * FilmServiceImplDAO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package service;

public interface FilmServiceImplDAO extends java.rmi.Remote {
    public java.lang.String getFilmsAll() throws java.rmi.RemoteException;
    public java.lang.String getFilmById(int id) throws java.rmi.RemoteException;
    public boolean addFilm(model.Film f) throws java.rmi.RemoteException;
    public boolean editFilm(model.Film f) throws java.rmi.RemoteException;
    public boolean deleteFilm(int id) throws java.rmi.RemoteException;
}
