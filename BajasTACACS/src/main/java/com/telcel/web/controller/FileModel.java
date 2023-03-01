

package com.telcel.web.controller;

/**
 *
 * @author
 */
import org.springframework.web.multipart.MultipartFile;

public class FileModel {
   private MultipartFile file;
   private String designation;

   public MultipartFile getFile() {
      return file;
   }

   public void setFile(MultipartFile file) {
      this.file = file;
   }
   
    public String getDesignation() {
            return designation;
    }

    public void setDesignation(String designation) {
            this.designation = designation;
    }
}