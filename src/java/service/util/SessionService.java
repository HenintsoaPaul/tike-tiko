package service.util;

import src.summer.beans.SummerSession;
import src.summer.exception.SummerSessionException;

public abstract class SessionService {
   public static Object getErrorFromSession (SummerSession summerSession) {
        Object err = summerSession.getAttribute("err");
        if (err != null) {
            try {
                summerSession.addAttribute("err", null);
            } catch (SummerSessionException ignored) {
            }
        }
        return err;
    }
}
