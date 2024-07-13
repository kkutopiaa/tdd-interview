package com.kuan.tddinterview.mapper.modelmapper;

public class SpecificFieldDto {
   private String telephone;

   public void setTelephone(String telephone) {
       this.telephone = telephone;
   }

   public String getTelephone() {
       return telephone;
   }

   public SpecificFieldDto() {
   }

   public SpecificFieldDto(String telephone) {
       this.telephone = telephone;
   }

}
