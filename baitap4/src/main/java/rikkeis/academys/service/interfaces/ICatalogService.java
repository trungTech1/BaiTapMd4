package rikkeis.academys.service.interfaces;

import rikkeis.academys.model.Catalog;

import java.util.List;

public interface ICatalogService {
    List<Catalog> getAllCatalogs();
    Catalog getCatalogById(int id);
    void addCatalog(Catalog catalog);
    void save(Catalog catalog);
    void deleteCatalog(int id);
    void changeCatalogStatus(int catalogId, boolean status);



}
