/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.Producto;
import ec.edu.ups.facade.BodegaFacade;
import ec.edu.ups.facade.ProductoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
@Named
@SessionScoped
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductoFacade productoFacade;

    @EJB
    private BodegaFacade bodegaFacade;

    private List<Producto> list = new ArrayList<>();
    private int id;
    private String nombre;
    private int stock;
    private double precio;
    private List<String> bodegas = new ArrayList<>();

    @PostConstruct
    public void init() {
        list = productoFacade.findAll();
    }

    public String add() {
        productoFacade.create(new Producto(nombre, stock, precio));
        list = productoFacade.findAll();
        return null;
    }

    public String delete(Producto p) {
        productoFacade.remove(p);
        list = productoFacade.findAll();
        return null;
    }

    public String edit(Producto p) {
        p.setEditable(true);
        return null;
    }

    public String save(Producto p) {
        productoFacade.edit(p);
        list = productoFacade.findAll();
        p.setEditable(false);
        return null;
    }

    public List<Producto> getList() {
        return list;
    }

    public void setList(List<Producto> list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public List<String> getListaBodegas() {
        bodegas = bodegaFacade.getBodegasNames();
        return bodegas;
    }

}
