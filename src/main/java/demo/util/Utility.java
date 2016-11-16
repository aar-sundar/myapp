package demo.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    private static final Logger LOG = LoggerFactory.getLogger(Utility.class);

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        LOG.info("Validating email = {}", emailStr);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
