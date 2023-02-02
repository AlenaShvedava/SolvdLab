package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.exceptions.ServiceException;
import pl.solvd.concerthall.models.AuthorTypes;
import pl.solvd.concerthall.services.AuthorTypesService;

public class AuthorTypesServiceImpl extends MySqlDAO implements AuthorTypesService {
    @Override
    public AuthorTypes saveEntity(AuthorTypes authorTypes) throws Exception {
        return null;
    }

    @Override
    public AuthorTypes getEntityById(Long id) throws Exception {
        return null;
    }

    @Override
    public void updateEntity(AuthorTypes authorTypes) throws ServiceException {

    }

    @Override
    public String deleteEntity(Long id) throws ServiceException {
        return null;
    }
}
