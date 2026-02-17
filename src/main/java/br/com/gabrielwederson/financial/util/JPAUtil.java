package br.com.gabrielwederson.financial.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;


    public class JPAUtil {

        private static EntityManagerFactory emf;

        static {
            try {
                Dotenv dotenv = Dotenv.load();

                Map<String, Object> props = new HashMap<>();
                props.put("jakarta.persistence.jdbc.url", dotenv.get("DB_URL"));
                props.put("jakarta.persistence.jdbc.user", dotenv.get("DB_USER"));
                props.put("jakarta.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));

                emf = Persistence.createEntityManagerFactory("JPAfinancial", props);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static EntityManager getEntityManager() {
            return emf.createEntityManager();
        }
    }

