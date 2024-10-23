
package observers.eventmanagement;
import observers.eventmanagement.Editor;
import observers.eventmanagement.EmailNotificationListener;
import observers.eventmanagement.EventManager;
import observers.eventmanagement.LogOpenListener;

public class Demo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));
        editor.events.subscribe("send", new SMSSupportListener("Hi, This is a java program which run support SMS and the message is sending in program.Notification is allow occur in this Observer program.Actually this is Refactoring Program which have concepts used."));
        try {
            editor.openFile("test.txt");
            editor.saveFile();
            editor.sendMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}