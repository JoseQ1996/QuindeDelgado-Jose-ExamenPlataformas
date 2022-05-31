/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.Producto;
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
    private List<Producto> list = new ArrayList<>();
    private int id;
    private String nombre;
    private int stock;
    private double precio;

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

}
