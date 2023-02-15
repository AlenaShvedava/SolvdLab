package pl.solvd.concerthalls.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;
public class DbConfig {
    public class DBConfigUtil {
        private final static Logger LOG = LogManager.getLogger(DBConfigUtil.class);

        private static Properties property = new Properties();

        static {
            try {
                property.load(new FileReader("mysqlcreds.properties"));
            } catch (FileNotFoundException e) {
                LOG.error("File was not found", e);
            } catch (IOException e) {
                LOG.error("Exception while loading properties file", e);
            }
        }
        private DBConfigUtil() {

        }
        public static Properties getProps() {
            return property;
        }
    }
}
