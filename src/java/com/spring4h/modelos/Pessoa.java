/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring4h.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author romulo.douro
 */
@Entity
public class Pessoa {
 @Id
 @GeneratedValue
 private int id;
 private String nome;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getNome() {
  return nome;
 }

 public void setNome(String nome) {
  this.nome = nome;
 }

 @Override
 public int hashCode() {
  int hash = 7;
  return hash;
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  final Pessoa other = (Pessoa) obj;
  if (this.id != other.id) {
   return false;
  }
  return true;
 }

 @Override
 public String toString() {
  return "Pessoa{" + "nome=" + nome + "} <a href=\"exclui/"+this.id+"\">X</a>";
 }
 
}
