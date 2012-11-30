package org.Nicon.Personal.LibCore.Sbin;

public class NiconUpdate{
  
  public static String CurrentNameAplication =  GlobalConfigSystem.getNameAplication();
  public static String CurrentAlternativeName = GlobalConfigSystem.getAlternativeName();
  public static String CurrentSystemVersion =   GlobalConfigSystem.getCurrentVersion();
  

  /*
   * VerifySystemVersion es el metodo encargado de verificar las versiones del sistema, NiconPersonal se compone de 
   * 2 importantes elementos el backend Sqlite donde se almacena la informacion y el frontEnd compuesto de todos los
   * entornos graficos y metodos que le dan vida a la informacion contenida. asi pues contamos con un sistema que es 
   * complejo en el manejo de sus versiones. el backend almacena la configuracion y versioning del sistema y el frontned
   * administra los nuevos cambios y metodos incorporados.
   * este metodo se encarga de almacenar la informacion actual y de desarrollo que debe ser comparada en cada inicio 
   * con la informacion de Backend. 
   */
  
        public static boolean VerifySystemVersion(){
        
            boolean update = false;
    
            try {
            //se verifica la existencia de este elemento en el backend, dado que fue incorporado en versiones psoteriores y se
            //se considera vital en la arquitectura del sistema.
                boolean VerifyTableExist = NiConection.VerifyTableExist("NiconSysInfo");
                
                    if (!VerifyTableExist){
                        UpdateToTrinity();
                    }else{
                        //se carga la informacion de el backend sobre la version actual que debe ser comparada con la informacion del
                        //frontend
                        NiconPersonalData DataAplication =NiconPersonalData.getNiconPersonalData();
//                        DataAplication=NiconPersonalData.getNiconPersonalData();
                
                        /*
                         * se procede a hacer la comparacion de los nombres clave de la version para verficar
                         * que se encuentran en el mismo canal de desarrollo en caso contrario y que la 
                         * comparacion del backend sea diferente se procede a la actualizacion de del sistema en 
                         * su backend y estructura
                         */
                            if(!DataAplication.getAlternativeName().equals(CurrentAlternativeName)){   
                                System.out.println("Iniciando actualización de los módulos de "+GlobalConfigSystem.getTitleAplication());
                                DataAplication.UpdateToVenus();                    
                                DataAplication=new NiconPersonalData(CurrentNameAplication,CurrentAlternativeName,CurrentSystemVersion);
                                DataAplication.updateDataAplication();
                                System.out.println("Actualización terminada exitosamente.");
                            }
                            if((!DataAplication.getAplicationVersion().equals(CurrentSystemVersion))){
                                System.out.println("Iniciando actualización de los módulos de "+GlobalConfigSystem.getTitleAplication());
                                DataAplication=new NiconPersonalData(CurrentNameAplication,CurrentAlternativeName,CurrentSystemVersion);
                                DataAplication.updateDataAplication();
                                System.out.println("Actualización terminada exitosamente.");
                            }      
                    }
            }catch (Exception e) {
                    System.out.println("ocurrio un error en NiconUpdate.VerifySystemVersion() detail:\n" + e);
            }
    return update;
  }
  
  /*
   * la version trinity hace cambios estructurales a nivel de el backend. como al agregacion de la tabla NiconSysInfo
   * y se inicia el proceso de versionado del sistema. ademas se hacen un cambio radical en la tabla de NiconNOtes 
   * la version anterior de NiconNOtes no es soportada ya en Trinity asi que es reemplazada por la nueva estructura.
   */
  private static void UpdateToTrinity(){
      try{         
         String UpdateDataBase = "create table if not exists NiconSysInfo (\nCurrentSystemName TEXT NOT NULL,\nCurrentAlternativeName TEXT NOT NULL,CurrentVersionSystem TEXT NOT NULL,ChanelDeveloper TEXT NOT NULL,dateUpdate TEXT NOT NULL)";
         String deleteoldNotes = "drop table notas;";
         String UpdateNotes = "create table if not exists Notas (\nCodigo INTEGER PRIMARY KEY AUTOINCREMENT,Titulo TEXT,Nota_Desc TEXT NOT NULL,Fecha_Gen TEXT NOT NULL)";
         String UpdateNiconSysInfo = "insert into NiconSysInfo values('NiconPersonal','Trinity','0.1.2','AlfaT1','" + NiconSystemAdmin.GetInstantTime() + "');";
  
        NiConection.ExecuteSentence(UpdateDataBase);
        NiConection.ExecuteSentence(UpdateNiconSysInfo);
        NiConection.ExecuteSentence(deleteoldNotes);
        NiConection.ExecuteSentence(UpdateNotes);
      }catch(Exception e){
          System.out.println("Ocurrio un Error en NiconUpdate.UdpdateToTrinity() DetailError:\n"+e);
      }
  }
  
  
}