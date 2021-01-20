package com.alexr03.dev.banking;

import com.alexr03.dev.banking.models.ELogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

@Slf4j
@Scope("singleton")
public class Utilities {
    public static final String dateRegex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    private static final Scanner scanner = new Scanner(System.in);

    public String askUser(String question) {
        log.info(question + ": ");
        return scanner.nextLine();
    }

    public String askUser(String question, String regex) {
        return askUser(question, regex, false);
    }

    public String askPassword(String question) {
        return "";
    }

    public String askUser(String question, String regex, Boolean lowerCaseResponse) {
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            String response = askUser(question);
            if (lowerCaseResponse) {
                response = response.toLowerCase();
            }
            if (pattern.matcher(response).matches()) {
                return response;
            }
            log.info("Unknown response...");
        }
    }

    public Map<String, String> mapEvent(ELogEvent logEvent){
        return Collections.singletonMap("event", logEvent.toString());
    }
}
