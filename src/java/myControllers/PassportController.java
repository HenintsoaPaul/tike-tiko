package myControllers;

import src.summer.annotations.Param;
import src.summer.annotations.controller.*;
import src.summer.annotations.controller.verb.Get;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;
import src.summer.beans.SummerFile;

@Controller
public class PassportController {

    @Get
    @UrlMapping(url = "passeport_add")
    public ModelView add() {
        return new ModelView("fo/reservation/reservation_passeport");
    }

    @Post
    @UrlMapping(url = "passeport_save")
    public String save(
            @Param(name = "passeportFile", isFile = true) SummerFile passeportFile
    ) {
        System.out.println("FileName > " + passeportFile.getFileName());
        System.out.println("byte > " + passeportFile.getFileBytes().length);

        return "fichier";
    }
}
