package com.example.repository.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface {
    // CREATE
    public Integer create(Object entity);
    // READ BY ID
    public Object findById(Integer id) throws SQLException;
    // READ ALL
    public List<Object> findAll();
    // UPDATE
    public void updateById(Object entity);
    // DELETE
    public void deleteById(Integer id);
}
