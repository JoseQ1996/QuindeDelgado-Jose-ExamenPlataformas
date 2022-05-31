/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

import ec.edu.ups.entidades.Bodega;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class BodegaFacade extends AbstractFacade<Bodega> {

    @PersistenceContext(name = "ExamenPlataformasWeb")
    private EntityManager em;

    public BodegaFacade() {
        super(Bodega.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
