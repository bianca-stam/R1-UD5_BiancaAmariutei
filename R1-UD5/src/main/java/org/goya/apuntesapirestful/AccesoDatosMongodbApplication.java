package org.goya.apuntesapirestful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccesoDatosMongodbApplication implements CommandLineRunner {

  @Autowired
  private ClienteRepository repositorio;

  public static void main(String[] args) {
    SpringApplication.run(AccesoDatosMongodbApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    repositorio.deleteAll();

    // guardar un par de clientes
    repositorio.save(new Cliente("Alice", "Smith"));
    repositorio.save(new Cliente("Bob", "Smith"));

    // obtener todos los clientes
    System.out.println("Clientes encontrados con findAll():");
    for (Cliente cliente : repositorio.findAll()) {
      System.out.println(cliente);
    }
    System.out.println();

    // obtener un cliente por el nombre:
    System.out.println("Cliente encontrado con findByNombre('Alice'):");
    System.out.println(repositorio.findByNombre("Alice"));
    
    // obtener un cliente por el apellido:
    System.out.println("Clientes encontrados con findByApellido('Smith'):");
    for (Cliente cliente : repositorio.findByApellido("Smith")) {
      System.out.println(cliente);
    }

  }

}