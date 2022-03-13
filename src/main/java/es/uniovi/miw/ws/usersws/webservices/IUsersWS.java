package es.uniovi.miw.ws.usersws.webservices;

import es.uniovi.miw.ws.usersws.models.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface IUsersWS {

    @WebMethod
    User updateUser(@WebParam(name = "username") String username, @WebParam(name = "password")String password,
                    @WebParam(name = "address") String address, @WebParam(name = "isAnonymous")boolean isAnonymous);
    @WebMethod
    User deleteUser(@WebParam(name = "id") Long id);
    @WebMethod
    List<User> getUsers();
    @WebMethod
    User getUser(@WebParam(name = "username") String username);
    @WebMethod
    User login(@WebParam(name = "username") String username, @WebParam(name = "password") String password);
    @WebMethod
    User register(@WebParam(name = "username") String username, @WebParam(name = "password") String password,
                  @WebParam(name = "address") String address);
    @WebMethod
    User registerAnonymous(@WebParam(name = "address") String address);

}
