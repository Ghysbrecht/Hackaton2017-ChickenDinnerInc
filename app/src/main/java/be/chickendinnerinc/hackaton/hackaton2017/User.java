package be.chickendinnerinc.hackaton.hackaton2017;

import com.google.gson.annotations.Expose;

/**
 * Created by Thomas on 24/11/2017.
 */

public class User {


        @Expose
        private int id;
        @Expose
        private String name;
        @Expose
        private String address;
        @Expose
        private String citizenid;
        @Expose
        private String cellphone;
        @Expose
        private String date_of_birth;
        @Expose
        private String created_at;
        @Expose
        private String updated_at;

        public int getId(){
            return id;
        }

        public String getName(){
            return name;
        }

        public String getAddress(){
            return address;
        }

        public String getCitizenid(){ return citizenid; }

        public String getCellphone(){
            return cellphone;
        }

        public String getDate_of_birth(){
            return date_of_birth;
        }

        public String getCreated_at(){
            return created_at;
        }

        public String getUpdated_at(){
            return updated_at;
        }

}
