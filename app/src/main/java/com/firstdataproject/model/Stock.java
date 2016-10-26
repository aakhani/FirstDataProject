package com.firstdataproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Avdhesh AKhani on 10/26/2016.
 */

public class Stock {

        @SerializedName("c")
        @Expose
        private String c;

        @SerializedName("t")
        @Expose
        private String t;
        @SerializedName("l")
        @Expose
        private String l;

        /**
         *
         * @return
         * The t
         */
        public String getT() {
            return t;
        }

        /**
         *
         * @param t
         * The t
         */
        public void setT(String t) {
            this.t = t;
        }


        /**
         *
         * @return
         * The l
         */
        public String getL() {
            return l;
        }

        /**
         *
         * @param l
         * The l
         */
        public void setL(String l) {
            this.l = l;
        }


        /**
         *
         * @return
         * The c
         */
        public String getC() {
            return c;
        }

        /**
         *
         * @param c
         * The c
         */
        public void setC(String c) {
            this.c = c;
        }

    }

