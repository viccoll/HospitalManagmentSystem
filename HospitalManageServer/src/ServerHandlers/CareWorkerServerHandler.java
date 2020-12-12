package ServerHandlers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CareWorkerServerHandler {
    ObjectInputStream request; //Принятие
    ObjectOutputStream respond; //Отправка


    public CareWorkerServerHandler(ObjectInputStream request, ObjectOutputStream respond) {
        this.request = request;
        this.respond = respond;
    }
}
