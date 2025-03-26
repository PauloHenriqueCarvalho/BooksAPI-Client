/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Senai
 */

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Book {
    
    private Integer id;
    private String name;
    private String autor;
    private String genero;

    public Book(String name, String autor, String genero) {
        this.name = name;
        this.autor = autor;
        this.genero = genero;
    }
    
    
    
}
