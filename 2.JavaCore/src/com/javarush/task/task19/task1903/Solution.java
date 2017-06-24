package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
        IncomeDataAdapter adapter = new IncomeDataAdapter(new IncomeDataImpl());
        System.out.println(adapter.getCompanyName());
        System.out.println(adapter.getName());
        System.out.println(adapter.getPhoneNumber());
        System.out.println(adapter.getCountryName());

    }

    public static class IncomeDataImpl implements IncomeData{
        @Override
        public String getCountryCode() {return "UA";}
        @Override
        public String getCompany() {return "JavaRush Ltd.";}
        @Override
        public String getContactFirstName() {return "Ivan";}
        @Override
        public String getContactLastName() {return "Ivanov";}
        @Override
        public int getCountryPhoneCode() {return 38;}
        @Override
        public int getPhoneNumber() {return 501234567;}
    }

    public static class IncomeDataAdapter implements Customer, Contact{
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getName() {
            return String.format("%s, %s", this.data.getContactLastName(), this.data.getContactFirstName());
        }

        @Override
        public String getPhoneNumber() {
            String sPhone = String.format("%10s", this.data.getPhoneNumber()).replace(" ", "0");
            String phone = sPhone.replaceAll("(.{3})(.{3})(.{2})(.{2})","($1)$2-$3-$4");
            return String.format("+%s%s", this.data.getCountryPhoneCode(), phone);
        }

        @Override
        public String getCompanyName() {
            return this.data.getCompany();
        }

        @Override
        public String getCountryName() {
            return countries.get(this.data.getCountryCode());
        }
    }

    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}