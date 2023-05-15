/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author T480s
 */
public class RoleController {
   private ObservableList<Role> roles = FXCollections.observableArrayList(Role.values());

    public ObservableList<Role> getRoles() {
        return roles;
    }

    
}
