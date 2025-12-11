package com.example.repository.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// use generics to make this interface reusable for different entity types

public interface DAOInterface<T> {
    // CREATE
    public Integer create(T entity) throws SQLException;

    // READ BY ID
    public Optional<T> findById(Integer id) throws SQLException;

    // READ ALL
    public List<T> findAll() throws SQLException;

    // UPDATE
    public T updateById(T entity) throws SQLException;
    
    // DELETE
    public boolean deleteById(Integer id) throws SQLException;
}
