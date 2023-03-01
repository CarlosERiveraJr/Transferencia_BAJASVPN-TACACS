package com.telcel.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ControllerRC {
    
   @Autowired
   ServletContext context; 
   
   private static final Logger logger = Logger.getLogger(ControllerRC.class);

	/*@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}*/


        
        @RequestMapping(value = "/admin2**", method = RequestMethod.GET)
	public ModelAndView admin2Page() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Credenciales no validas");
		}

		if (logout != null) {
			model.addObject("msg", "Sesión finalizada con éxito");
		}
		model.setViewName("login");

		return model;

	}
        
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
		FileModel file = new FileModel();
                ModelAndView model = new ModelAndView("fileUpload", "command", file);
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("fileUpload");
                
                
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String name = auth.getName(); //get logged in username
                model.addObject("username", name);
                logger.warn("Usuario activo: " + name);
                
                //Map< String, String > filiales = new HashMap<String, String>();
                //model.addObject("filialesMap", filiales);

		return model;
    }  
    
    
    @RequestMapping(value = "/rmdWorkOrder**", method = RequestMethod.GET)
    public ModelAndView rmdPage() {
		FileModel file = new FileModel();
                ModelAndView model = new ModelAndView("fileUpload", "command", file);
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("fileUpload");
                
                
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String name = auth.getName(); //get logged in username
                model.addObject("username", name);
                logger.warn("UsuarioActivo: " + name);
                
                //Map< String, String > filiales = new HashMap<String, String>();
                //model.addObject("filialesMap", filiales);
                String[] cmd = new String[]{"/bin/sh", "/home/remedy/WorkOrderTACACS/rmdWorkOrder.sh"};
                try {
                    Process pr = Runtime.getRuntime().exec(cmd);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(ControllerRC.class.getName()).log(Level.SEVERE, null, ex);
                }
		return model;
    }  

   @RequestMapping(value="/admin**", method = RequestMethod.POST)
   public String fileUpload(@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
       
       
        //leer propiedades
        //String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
       // String appConfigPath = rootPath + "app.properties";
        
        //File resourcesDirectory = new File("src");
        //String appConfigPath = resourcesDirectory.getAbsolutePath() + "app.properties";
        
        File file1 = new File(
		getClass().getClassLoader().getResource("app.properties").getFile()
	);
        String appConfigPath = file1.getAbsolutePath();

        
        
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
        String filePath = appProps.getProperty("ruta");
        System.out.println(filePath);
        
        
        
      if (result.hasErrors()) {
         System.out.println("validation errors");
         return "red1";
      } else {            
         System.out.println("Fetching file");
         MultipartFile multipartFile = file.getFile();
         
         //create the upload folder if not exists
	 //   File folder = new File(filePath);
	 //   if(!folder.exists()){
	 //   	folder.mkdir();
	 //   }
  
         
         FileCopyUtils.copy(file.getFile().getBytes(), new File(filePath + File.separator + file.getFile().getOriginalFilename()));
        
         
         String fileName = multipartFile.getOriginalFilename();
         model.addAttribute("fileName", fileName);
         model.addAttribute("com", file.getDesignation());
         
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String name = auth.getName(); //get logged in username
         logger.warn("Usuario: " + name +" Cargo el archivo: "+ fileName + " En la carpeta " + file.getDesignation());
        
         return "success";
      }
   }

}