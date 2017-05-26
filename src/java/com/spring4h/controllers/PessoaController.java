/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring4h.controllers;

import com.spring4h.modelos.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author romulo.douro
 */
@Controller
public class PessoaController {

 private EntityManagerFactory factory;

 @RequestMapping(value = {"/pessoa", "/pessoa/index"}, method = RequestMethod.GET)
 public String index() {
  return "pessoa/index";
 }

 @RequestMapping(value = {"/pessoa/cadastro"}, method = RequestMethod.GET)
 public String abreCadastro() {
  return "pessoa/cadastro";
 }

 @RequestMapping(value = {"/pessoa/cadastro"}, method = RequestMethod.POST)
 public ModelAndView efetuaCadastro(Pessoa p) {
  ModelAndView mv = new ModelAndView();
  try {
   this.factory = Persistence.createEntityManagerFactory("spring4h-PU");
   EntityManager em = this.factory.createEntityManager();
   em.persist(p);
   em.getTransaction().begin();
   em.getTransaction().commit();
   Query q = em.createQuery("select p from Pessoa p");
   List l = q.getResultList();
   mv.addObject("listaPessoas", l);
   em.close();
   this.factory.close();
  } catch (Exception ex) {
   mv.addObject("listaPessoas", "ERRO " + ex.getMessage());
  }
  mv.setViewName("pessoa/lista");
  return mv;
 }
 
 @RequestMapping(value = {"/pessoa/lista"}, method = RequestMethod.GET)
 public String abreLista(Model m) {
  try {
   this.factory = Persistence.createEntityManagerFactory("spring4h-PU");
   EntityManager em = this.factory.createEntityManager();
   Query q = em.createQuery("select p from Pessoa p");
   List l = q.getResultList();
   m.addAttribute("listaPessoas", l);
   em.close();
   this.factory.close();
  } catch (Exception ex) {

  }
  return "pessoa/lista";
 }
 
 @RequestMapping(value = {"/pessoa/exclui/{id}"}, method = RequestMethod.GET)
 public String excluiPessoa(@PathVariable(value = "id") Integer id){
  try {
   this.factory = Persistence.createEntityManagerFactory("spring4h-PU");
   EntityManager em = this.factory.createEntityManager();
   Pessoa p = em.find(Pessoa.class, id);
   em.remove(p);
   em.getTransaction().begin();
   em.getTransaction().commit();
   em.close();
   this.factory.close();
  } catch (Exception ex) {

  }
  return "redirect:/pessoa/lista";
 }
}
