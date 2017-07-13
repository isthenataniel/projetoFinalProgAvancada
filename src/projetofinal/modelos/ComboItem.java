/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.modelos;

/**
 *
 * @author root
 */
public class ComboItem
{
    private Integer key;
    private String value;

    public ComboItem(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
