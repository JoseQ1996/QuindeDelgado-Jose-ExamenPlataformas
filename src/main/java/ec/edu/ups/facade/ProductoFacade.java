/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Producto;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author User
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(name = "ExamenPlataformasWeb")
    private EntityManager em;

    @EJB
    private BodegaFacade bodegaFacade;

    public ProductoFacade() {
        super(Producto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void guardarProducto(String nombre, String descripcion, double precio, int stock, String nombreBodega) throws Exception {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setStock(stock);
        Bodega bodega = bodegaFacade.getBodegaByName(nombreBodega );
        if (bodega == null) {
            throw new Exception("La bodega no existe");
        }
        p.addBodega(bodega);
        List<Producto> producto = bodega.getProductos();
        producto.add(p);
        bodega.addProducto(p);
        bodegaFacade.edit(bodega);
    }

    public Producto getProductoByName(String name) {
        String jpql = "SELECT s FROM Producto s WHERE s.nombre = '" + name + "'";
        Producto producto = (Producto) em.createQuery(jpql).getSingleResult();
        return producto;
    }
}
