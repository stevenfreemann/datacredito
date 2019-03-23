/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Steven
 */
public class Principal{
    public BufferedReader entradaDatos=new BufferedReader(new InputStreamReader (System.in));
    private HashMap<Integer,Persona> personas;
    
    public Principal() {
        personas= new HashMap();
        
    }
    
    public void menu(){
        try{
            String opcion="";
            int idCliente;
            do{
                leerArchivoTexto();

               
                System.out.println("-------- 1. Agregar Usuario.");
                System.out.println("-------- 2. Agregar Reporte.");
                System.out.println("---------3. Buscar Usuario. ");
                System.out.println("---------4. Eliminar Reporte");
                System.out.println("---------0. Salir.          ");
                System.out.print("----->");
                
                opcion = entradaDatos.readLine();
                if(opcion.equals("1")){
                    System.out.println("Datos del Cliente");
                    System.out.println("Nombre");
                    String nombre=entradaDatos.readLine();
                    System.out.println("Apellido");
                    String apellido=entradaDatos.readLine();
                    System.out.println("Identificación");
                    int cedula=Integer.parseInt(entradaDatos.readLine());
                  ////////////////////// agregar persona /////////////////////////////////
                  Persona cliente=new Persona(cedula,apellido,nombre);
                    if(personas.containsKey(cedula)){
                    System.out.println("El cliente ya está registrado en el Sistema.");
                       }else{
                         personas.put(cedula, cliente);
                         guardarArchivoTexto();
                            }
                    
                    ///////////////////////////////////////////////////////////////////
                }else if(opcion.equals("2")){
                    System.out.println("Identificacion del Cliente: ");
                    int cedula=Integer.parseInt(entradaDatos.readLine());
                    //////////////////// agregar reporte ////////////////////////////
                    agregarReporte(cedula);
                    guardarArchivoTexto();
                    }
                else if(opcion.equals("3")){
                        leerArchivoTexto();
                        System.out.println("Identificacion del Cliente");
                        int cedula=Integer.parseInt(entradaDatos.readLine());
                        verReportes(cedula);
                   }else if(opcion.equals("4")){
                        System.out.println("Identificación del Ciente: ");
                        int cedula=Integer.parseInt(entradaDatos.readLine());
                        System.out.println("codigo del reporte: ");
                        int code=Integer.parseInt(entradaDatos.readLine());
                        eliminarReporte(cedula,code);
                        guardarArchivoTexto();
                }else if(opcion.equals("0")){
                    System.exit(0);
                }
                else{
                    System.out.println("Opcion no Válida...");
                }
            }while(!(opcion.equals("0")));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
   
    public void agregarReporte(int id){
        try{
            if(personas.containsKey(id)){
                System.out.println("Datos del Reporte");
                System.out.println("Codigo");
                int codigo=Integer.parseInt(entradaDatos.readLine());
                System.out.println("Nombre");
                String nombre=entradaDatos.readLine();
                System.out.println("Estado (positivo/negativo)");
                String tipo=entradaDatos.readLine();
                System.out.println("Valor: ");
                float valor=Float.parseFloat(entradaDatos.readLine());
                Record rep=new Record(codigo,nombre,tipo,valor);
                personas.get(id).getRecord().put(codigo, rep);
            }else{
                System.out.println("El cliente no está registrado en el Sistema.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminarReporte(int cedu, int cod){
        try{
            if(personas.containsKey(cedu)){
                if(personas.get(cedu).record.isEmpty()){
                    System.out.println("El cliente no tiene reportes en el Sistema");
                }else{
                    for (Record rep : personas.get(cedu).getRecord().values()) {
                      
                        if(rep.getEstado().equals("negativo")){
                        
                            if(personas.get(cedu).getRecord().containsKey(cod) && personas.get(cedu).getRecord().get(cod).getEstado().equals("negativo")){
                                 personas.get(cedu).getRecord().remove(cod);
                                 System.out.println("El reporte ha sido eliminado con exito ");
                            
                          
                           
                        }
                        }
                        
                    }
                }
            }else{
                System.out.println("El cliente no está registrado en el Sistema.");
            }
        }catch(Exception e){
            
        }
    }
   
    
    public void verReportes(int id){
   
         
        if(personas.containsKey(id)){
            
           if(personas.get(id).record.isEmpty()){
                System.out.println("El cliente no tiene reportes en el Sistema");
                
            }else{
               
                System.out.println("Reportes de: "+personas.get(id).getNombre()+" "+personas.get(id).getApellido());
                   System.out.println("Reportes de: "+personas.get(id).getRecord());
                for (Record r : personas.get(id).record.values()){
                     
                    System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println("\tCodigo: "+r.getCodigo());
                    System.out.println("\tNombe empresa: "+r.getEmpresa());
                    System.out.println("\tEstado reporte: "+r.getEstado());
                    System.out.println("\tValor: "+r.getValor());
                    System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                  
                }
            }
        }else
            System.out.println("El cliente no está registrado en el Sistema");
    }
    
    
    public void leerArchivoTexto() throws FileNotFoundException, IOException{
     
        File f;
        f=new File("D:\\Users\\Steven\\Documents\\NetBeansProjects\\DataCredito\\datos.txt");
        try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f))) {
            personas=(HashMap)entrada.readObject();
        }catch(Exception ex){
            System.out.println("Error al leer el archivo");
            System.err.println(ex.getMessage());
        }
        
    }
    public void guardarArchivoTexto() throws FileNotFoundException, IOException{
        File f;
        f=new File("D:\\Users\\Steven\\Documents\\NetBeansProjects\\DataCredito\\datos.txt");
        ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(f));
        try{
            salida.writeObject(personas);
        }catch(Exception ex){
            System.out.println("Error al Guardar el archivo");
            ex.getMessage();
        }finally{
            salida.close();
        }
}
}
