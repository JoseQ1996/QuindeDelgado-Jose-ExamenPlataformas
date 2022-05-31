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
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductoFacade productoFacade;

    @EJB
    private BodegaFacade bodegaFacade;

    private List<Producto> list = new ArrayList<>();
    private int codigo;
    private String nombre;
    private int stock;
    private double precio;
    private String nombreBodega;
    private List<String> bodegas = new ArrayList<>();

    @PostConstruct
    public void init() {
        list = productoFacade.findAll();
    }

    public String add() throws Exception {
        Producto producto = productoFacade.getProductoByName(nombre);
        if (producto == null) {
            productoFacade.create(new Producto(nombre, stock, precio));
            productoFacade.agregarBodega(nombre, nombreBodega);
            list = productoFacade.findAll();
        }
        if (producto != null) {
            productoFacade.agregarBodega(nombre, nombreBodega);
            list = productoFacade.findAll();

        }
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

}
