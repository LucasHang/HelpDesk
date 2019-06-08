/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.helpdesk;

import br.senai.sc.helpdesk.model.Cliente;
import br.senai.sc.helpdesk.model.Problema;
import br.senai.sc.helpdesk.model.Tecnico;
import br.senai.sc.helpdesk.model.ProblemaResolvido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senai
 */
public class BancoDeDados {
    
     public static List<Cliente> clientes = new ArrayList();
     
     public static List<Tecnico> tecnicos = new ArrayList();
     
     public static List<Problema> problemas = new ArrayList();
     
      public static List<ProblemaResolvido> problemasResolvidos = new ArrayList();
     
}
