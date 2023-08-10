package service;

public class FilmServiceImplDAOProxy implements service.FilmServiceImplDAO {
  private String _endpoint = null;
  private service.FilmServiceImplDAO filmServiceImplDAO = null;
  
  public FilmServiceImplDAOProxy() {
    _initFilmServiceImplDAOProxy();
  }
  
  public FilmServiceImplDAOProxy(String endpoint) {
    _endpoint = endpoint;
    _initFilmServiceImplDAOProxy();
  }
  
  private void _initFilmServiceImplDAOProxy() {
    try {
      filmServiceImplDAO = (new service.FilmServiceImplDAOServiceLocator()).getFilmServiceImplDAO();
      if (filmServiceImplDAO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)filmServiceImplDAO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)filmServiceImplDAO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (filmServiceImplDAO != null)
      ((javax.xml.rpc.Stub)filmServiceImplDAO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public service.FilmServiceImplDAO getFilmServiceImplDAO() {
    if (filmServiceImplDAO == null)
      _initFilmServiceImplDAOProxy();
    return filmServiceImplDAO;
  }
  
  public java.lang.String getFilmsAll() throws java.rmi.RemoteException{
    if (filmServiceImplDAO == null)
      _initFilmServiceImplDAOProxy();
    return filmServiceImplDAO.getFilmsAll();
  }
  
  public java.lang.String getFilmById(int id) throws java.rmi.RemoteException{
    if (filmServiceImplDAO == null)
      _initFilmServiceImplDAOProxy();
    return filmServiceImplDAO.getFilmById(id);
  }
  
  public boolean addFilm(model.Film f) throws java.rmi.RemoteException{
    if (filmServiceImplDAO == null)
      _initFilmServiceImplDAOProxy();
    return filmServiceImplDAO.addFilm(f);
  }
  
  public boolean editFilm(model.Film f) throws java.rmi.RemoteException{
    if (filmServiceImplDAO == null)
      _initFilmServiceImplDAOProxy();
    return filmServiceImplDAO.editFilm(f);
  }
  
  public boolean deleteFilm(int id) throws java.rmi.RemoteException{
    if (filmServiceImplDAO == null)
      _initFilmServiceImplDAOProxy();
    return filmServiceImplDAO.deleteFilm(id);
  }
  
  
}