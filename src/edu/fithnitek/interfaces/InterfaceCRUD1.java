/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.interfaces;

import java.util.List;

/**
 *
 * @author karra
 * @param <T>
 */
public interface InterfaceCRUD1 <T> {
    public void add(T t);
    public List<T> listeDesEntites();
    
}
