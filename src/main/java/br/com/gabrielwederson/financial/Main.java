package br.com.gabrielwederson.financial;

import br.com.gabrielwederson.financial.dao.LaunchDataDAO;
import br.com.gabrielwederson.financial.model.LaunchData;
import br.com.gabrielwederson.financial.model.Type;

public class Main {
    public static void main(String[] args){
        LaunchDataDAO dao = new LaunchDataDAO();
        LaunchData ldd = new LaunchData("Faculdade", 250.0, Type.EXPENSE);
        LaunchData ldd2 = new LaunchData("Salario", 1500.0, Type.REVENUE);

        //dao.save(ldd);
        //dao.getSum();
        //dao.findByID(29);
        //dao.update(29,"teste",10.0,Type.EXPENSE);
        //dao.remove(29);

    }
}
